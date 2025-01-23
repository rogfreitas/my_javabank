package io.codeforall.bootcamp.javabank.persistence.daos.jdbc;

import io.codeforall.bootcamp.javabank.model.Customer;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.persistence.daos.CustomerDao;
import io.codeforall.bootcamp.javabank.persistence.jdbc.JDBCSessionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCCustomerDao implements CustomerDao {
    JDBCSessionManager jdbcSessionManager;
    JBDCAccountDao jbdcAccountDao;

    public void setJdbcSessionManager(JDBCSessionManager jdbcSessionManager) {
        this.jdbcSessionManager = jdbcSessionManager;

    }

    public  void setJdbcAccountDao(JBDCAccountDao jbdcAccountDao){
        this.jbdcAccountDao=jbdcAccountDao;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Customer findById(Integer customer_id) {
        Customer customer = null;
        try {
            String query = "SELECT customer.id AS cid, first_name, last_name, phone, email, customer.version AS cVersion, account.id AS aid " +
                    "FROM customer " +
                    "LEFT JOIN account " +
                    "ON customer.id = account.customer_id " +
                    "WHERE customer.id = ?";

            PreparedStatement statement = jdbcSessionManager.getCurrentSession().prepareStatement(query);
            statement.setInt(1,customer_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                if (customer == null) {
                    customer = buildCustomer(resultSet);
                }

                int accountId = resultSet.getInt("aid");

                Account account = jbdcAccountDao.findById(accountId);

                if (account == null) {
                    break;
                }

                customer.addAccount(account);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;






    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return null;
    }

    @Override
    public void delete(Integer customer_id) {

    }
    private Customer buildCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("cid"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setVersion(resultSet.getInt("cVersion"));

        return customer;
    }
}
