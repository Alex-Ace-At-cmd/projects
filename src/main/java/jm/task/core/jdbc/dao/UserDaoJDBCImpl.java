package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR (255)," +
                "lastName VARCHAR (255)," +
                "age TINYINT" + ")";
        try(Connection connection = Util.getConnection();
            PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";

        try(Connection conn = Util.getConnection();
            PreparedStatement stat = conn.prepareStatement(drop)) {
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement insertStat = connection.prepareStatement(insertSql)) {

            insertStat.setString(1, name);
            insertStat.setString(2, lastName);
            insertStat.setByte(3, age);
            insertStat.executeUpdate();

            System.out.println("User - " + name + "has been added to data base");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM users WHERE id = ?";

        try(Connection conn = Util.getConnection();
            PreparedStatement removeStat = conn.prepareStatement(remove)) {
            removeStat.setLong(1, id);
            removeStat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        String select = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getConnection();
            PreparedStatement selectStat = conn.prepareStatement(select);
             ResultSet rs = selectStat.executeQuery()) {

            while(rs.next()) {
                User user = new User();

                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE users";

        try(Connection conn = Util.getConnection();
            PreparedStatement stat = conn.prepareStatement(clean)) {
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
