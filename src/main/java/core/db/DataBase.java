package core.db;

import next.dao.UserDao;
import next.model.User;

import java.util.Collection;

public class DataBase {
    private final static UserDao userDao = new UserDao();

    public static void addUser(User user) {
        userDao.insert(user);
    }

    public static void updateUser(User user) {
        userDao.update(user);
    }

    public static User findUserById(String userId) {
        return userDao.findByUserId(userId);
    }

    public static Collection<User> findAll() {
        return userDao.findAll();
    }
}
