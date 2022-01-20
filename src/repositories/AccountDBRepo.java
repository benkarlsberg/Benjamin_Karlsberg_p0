package repositories;

import exceptions.ResourceNotFoundException;
import models.Account;
import util.JDBCConnection;
import util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDBRepo implements AccountRepo{

    Connection conn = JDBCConnection.getConnection();

    /**
     * Adds account into SQL database
     * @param a account to be added
     * @return Account added
     */
    @Override
    public Account addAccount(Account a) {
        String sql = "INSERT INTO accounts VALUES (default, ?, ?, ?) RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, a.getUserId());
            ps.setString(2, a.getAccountType());
            ps.setDouble(3, a.getBalance());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
               return buildAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns account from SQL database based on acc_id
     * @param id acc_id
     * @return Account with acc_id given
     */
    @Override
    public Account getAccount(int id) {

        String sql = "SELECT * FROM accounts WHERE acc_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all accounts
     * @return LinkedList<Account> of accounts
     */
    @Override
    public LinkedList<Account> getAllAccounts() {

        String sql = "SELECT * FROM accounts";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            LinkedList<Account> accounts = new LinkedList<>();
            while(rs.next()) {
                accounts.insert(buildAccount(rs));
            }
            return accounts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates SQL database with account balance change
     * @param change account with changes
     * @return account with changes
     */
    @Override
    public Account updateAccount(Account change) {

        String sql = "UPDATE accounts set Balance=? WHERE acc_id = ? RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, change.getBalance());
            ps.setInt(2, change.getAccId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildAccount(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes account with given acc_id
     * @param id acc_id
     * @return account deleted
     * @throws ResourceNotFoundException if not found in SQL database
     */
    @Override
    public Account deleteAccount(int id) throws ResourceNotFoundException {

        String sql = "DELETE FROM accounts WHERE acc_id = ? RETURNING *";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return buildAccount(rs);
            } else {
                throw new ResourceNotFoundException("Resource with id: " + id + " was not found in database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all accounts of a user
     * @param id u_id
     * @return LinkedList<Account> of all accounts
     */
    @Override
    public LinkedList<Account> getUserAccounts(int id) {

        String sql = "SELECT * FROM accounts WHERE u_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            LinkedList<Account> accounts = new LinkedList<>();
            while(rs.next()) {
                accounts.insert(buildAccount(rs));
            }
            return accounts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Helper Method
    private Account buildAccount(ResultSet rs) throws SQLException {
        Account a = new Account();
        a.setAccId(rs.getInt("acc_id"));
        a.setUserId(rs.getInt("u_id"));
        a.setAccountType(rs.getString("account_type"));
        a.setBalance(rs.getDouble("balance"));
        return a;
    }
}
