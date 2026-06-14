package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userDao = new UserServiceImpl();

        userDao.createUsersTable();
        userDao.saveUser("Alex", "Long", (byte) 29);
        userDao.saveUser("Albert", "Double", (byte) 32);
        userDao.saveUser("Ash", "Float", (byte)17);
        userDao.saveUser("Almond", "Byte", (byte)20);

        userDao.getAllUsers();
       userDao.cleanUsersTable();
       userDao.dropUsersTable();



    }
}
