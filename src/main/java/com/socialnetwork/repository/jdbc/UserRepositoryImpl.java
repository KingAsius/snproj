package com.socialnetwork.repository.jdbc;

import com.socialnetwork.entity.User;
import com.socialnetwork.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Vladislav on 9/17/2016.
 */
@Repository("jdbcUserRepository")
public class UserRepositoryImpl implements UserRepository {
    private static final Logger log = Logger.getLogger(UserRepositoryImpl.class);
    private static String url;
    private static String userName;
    private static String password;

    private static String REGISTRATE_USER = "INSERT INTO public.users(name, surname, login, password, photoname, birthday, birthmonth, birthyear, city, info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static String SELECT_ALL = "SELECT * FROM public.users;";
    private static String SELECT_BY_LOGIN = "SELECT * FROM public.users WHERE login=?;";
    private static String SELECT_BY_ID = "SELECT * FROM public.users WHERE id=?;";
    private static String UPDATE_USER = "UPDATE public.users SET name=?, surname=?, login=?, password=?, photoname=?, birthday=?, birthmonth=?, birthyear=?, city=?, info=? WHERE id=?;";
    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try(Connection dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String password = resultSet.getString(5);
            user = new User(login, password);
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setPhotopath(resultSet.getString(6));
            user.setId(resultSet.getInt(1));
            user.setBirthDay(resultSet.getByte(7));
            user.setBirthMonth(resultSet.getString(8));
            user.setBirthYear(resultSet.getShort(9));
            user.setCity(resultSet.getString(10));
            user.setInfo(resultSet.getString(11));
        }
        catch (SQLException e) { log.info(e.getMessage()); }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User getUserById(int id) {

        User user = null;
        try(Connection dbConnection = getDBConnection()) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String password = resultSet.getString(5);
            String login = resultSet.getString(4);
            user = new User(login, password);
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setPhotopath(resultSet.getString(6));
            user.setBirthDay(resultSet.getByte(7));
            user.setBirthMonth(resultSet.getString(8));
            user.setBirthYear(resultSet.getShort(9));
            user.setCity(resultSet.getString(10));
            user.setInfo(resultSet.getString(11));
            user.setId(id);
        }
        catch (SQLException e) { log.info(e.getMessage()); }
        return user;
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();

        try(Connection dbConnection = getDBConnection()) {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                User user = new User(login, password);
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setPhotopath(resultSet.getString(6));
                user.setBirthDay(resultSet.getByte(7));
                user.setBirthMonth(resultSet.getString(8));
                user.setBirthYear(resultSet.getShort(9));
                user.setCity(resultSet.getString(10));
                user.setInfo(resultSet.getString(11));
                users.add(user);
            }
        }
        catch (SQLException e) { log.info(e.getMessage()); }
        return users;
    }

    @Override
    public void registrateUser(User user) {
        try(Connection dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(REGISTRATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhotopath());
            preparedStatement.setInt(6, user.getBirthDay());
            preparedStatement.setString(7, user.getBirthMonth());
            preparedStatement.setInt(8, user.getBirthYear());
            preparedStatement.setString(9, user.getCity());
            preparedStatement.setString(10, user.getInfo());

            preparedStatement.execute();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    public UserRepositoryImpl() {
        Properties property = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try(InputStream input = classLoader.getResourceAsStream("db/postgresql.properties")) {
            property.load(input);
            url = property.getProperty("postgre.url");
            userName = property.getProperty("postgre.username");
            password = property.getProperty("postgre.password");
        }
        catch (IOException e) {
            log.info(e.getMessage());
        }

    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(org.postgresql.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url, userName, password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhotopath());
            preparedStatement.setInt(6, user.getBirthDay());
            preparedStatement.setString(7, user.getBirthMonth());
            preparedStatement.setInt(8, user.getBirthYear());
            preparedStatement.setString(9, user.getCity());
            preparedStatement.setString(10, user.getInfo());
            preparedStatement.setInt(11, user.getId());
            preparedStatement.execute();
        }
        catch (SQLException e) { log.info(e.getMessage());
            System.out.println(e.getMessage());}
    }
}
