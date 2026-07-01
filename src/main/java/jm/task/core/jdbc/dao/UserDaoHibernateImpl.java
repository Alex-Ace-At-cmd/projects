package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "age TINYINT)"
            ).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = HibernateUtil.getSessionFactory().openSession();) {
            Transaction tx = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession();) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);

            if(user != null) {
                session.remove(user);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            users = session.createQuery("from User", User.class).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
