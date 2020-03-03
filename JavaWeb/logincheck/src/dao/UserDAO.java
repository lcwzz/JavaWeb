package dao;

import bean.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO extends BaseDAO<User> {
    public User login(User user) {
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from user_login where name = ? and password = ?";
            User tempUser = searchSingleRecord(connection, sql, user.getName(), user.getPassword());
            JDBCUtils.closeConnection(connection);
            return tempUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
