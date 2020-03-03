package dao;

import bean.User;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void test() {
        UserDAO userDAO = new UserDAO();
        User user = new User(0, "aa", "123");
        System.out.println(userDAO.login(user));
    }
}
