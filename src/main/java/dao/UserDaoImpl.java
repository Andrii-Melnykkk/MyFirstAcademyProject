package dao;

import models.ROLE;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import static utils.DBUtils.getConnection;


public class UserDaoImpl implements Dao<User> {
    private static final String INSERT_QUERY = "insert into users(login, name, email, password) " +
            "values (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "select * from users";
    private static final String SELECT_WITHOUT_ADMINS_QUERY = "select * from users where role != 'ADMIN' order by id ";
    private static final String GET_QUERY = "select * from users where id=?";
    private static final String GET_BY_LOGIN_AND_PASSWORD_QUERY = "select * from users where login=? and password=?";
    private static final String GET_BY_LOGIN_QUERY = "select * from users where login=?";
    private static final String DELETE_QUERY = "delete from users where id=?";
    private static final String UPDATE_QUERY = "update users set name=?, password=?, email=?, role =?"
            + "where login=?";
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private Connection connection;

    public UserDaoImpl() {
        connection = getConnection();
    }


    @Override
    public void add(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY)) {

            int i = 0;
            preparedStatement.setString(++i, user.getLogin());
            preparedStatement.setString(++i, user.getName());
            preparedStatement.setString(++i, user.getEmail());
            preparedStatement.setString(++i, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to create user", e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_QUERY)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(ROLE.valueOf(rs.getString("role")));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get all users", e);
        }
        return users;
    }

    // delete by login
    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            int i = 0;
            preparedStatement.setInt(++i, (int) id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to delete user", e);
        }
    }


    @Override
    public User get(long id) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {

            int i = 0;
            preparedStatement.setInt(++i, (int) id);
            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(ROLE.valueOf(rs.getString("role")));
                    user.setPassword(rs.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get user", e);
        }

        return user;
    }

    // update Users fields -name - password - email -role by Users login
    @Override
    public void update(User user) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);) {

            int i = 0;
            preparedStatement.setString(++i, user.getName());
            preparedStatement.setString(++i, user.getPassword());
            preparedStatement.setString(++i, user.getEmail());
            preparedStatement.setString(++i, user.getRole().name());
            preparedStatement.setString(++i, user.getLogin());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to update user", e);
        }
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN_AND_PASSWORD_QUERY);
             ) {

            int i = 0;
            preparedStatement.setString(++i, login);
            preparedStatement.setString(++i, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(ROLE.valueOf(rs.getString("role")));
                    user.setPassword(rs.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get user", e);
        }

        return user;
    }

    public User getByLogin(String login) {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOGIN_QUERY);) {
            int i = 0;
            preparedStatement.setString(++i, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(ROLE.valueOf(rs.getString("role")));
                    user.setPassword(rs.getString("password"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get user", e);
        }


        return user;
    }

    public List<User> getAllUsersWithoutAdmins() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_WITHOUT_ADMINS_QUERY)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(ROLE.valueOf(rs.getString("role")));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to get all users without admins", e);
        }
        return users;
    }


}
