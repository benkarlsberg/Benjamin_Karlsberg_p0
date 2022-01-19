package repositories;

import exceptions.ResourceNotFoundException;
import models.User;
import util.JDBCConnection;
import util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBRepo implements UserRepo {

    Connection conn = JDBCConnection.getConnection();

    @Override
    public User addUser(User u) {

        String sql = "INSERT INTO users VALUES (default, ?, ?, ?, ?) RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getUserName());
            ps.setString(4, u.getPassword());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(int id) {

        String sql = "SELECT * FROM users WHERE u_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<User> getAllUsers() {

        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            LinkedList<User> users = new LinkedList<>();
            while(rs.next()) {
                users.insert(buildUser(rs));
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User change) {

        String sql = "UPDATE users set first_name=?, last_name=?, username=?, pw=? WHERE u_id = ? RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, change.getFirstName());
            ps.setString(2, change.getLastName());
            ps.setString(3, change.getUserName());
            ps.setString(4, change.getPassword());
            ps.setInt(5, change.getUserId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildUser(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User deleteUser(int id) throws ResourceNotFoundException {

        String sql = "DELETE FROM users WHERE u_id = ? RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildUser(rs);
            } else {
                throw new ResourceNotFoundException("Resource with id: " + id + " was not found in database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Helper Method
    private User buildUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("u_id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setUserName(rs.getString("username"));
        u.setPassword(rs.getString("pw"));
        return u;
    }
}
