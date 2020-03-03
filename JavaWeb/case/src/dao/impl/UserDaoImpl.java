package dao.impl;

import bean.User;
import dao.BaseDAO;
import dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
    @Override
    public List<User> findAll() throws SQLException {
        String sql = "select * from user";
        return searchMutipleRecord(sql);
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into user(name, gender, age, address, qq, email) " +
                "values(?, ?, ?, ?, ?, ?)";
        updateUniversal(sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "delete from user where id = ?";
        updateUniversal(sql, id);
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "select * from user where id = ?";
        return searchSingleRecord(sql, id);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "update user set name=?, gender=?, age=?, address=?, qq=?, email=? " +
                "where id=?";
        updateUniversal(sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    private String getSql(StringBuilder sql, Map<String, String[]> condition) {
        Set<Map.Entry<String, String[]>> entrySet = condition.entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            //排除分页参数
            if ("curPage".equals(key) || "pageSize".equals(key)) {
                continue;
            }

            if (!"".equals(value)) {
                sql.append(" and ").append(key).append(" like '%").append(value).append("%'");
            }
        }
        return sql.toString();
    }

    @Override
    public int getTotalCount(Map<String, String[]> condition) throws SQLException {
        StringBuilder sql = new StringBuilder("select count(*) from user where 1 = 1");
        return Integer.parseInt(getValue(getSql(sql, condition)).toString());
    }

    @Override
    public List<User> getUserPage(int currentPage, int pageSize, Map<String, String[]> condition) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from user where 1 = 1");
        return searchMutipleRecord(getSql(sql, condition)+" limit ?, ?" , (currentPage-1)*pageSize, pageSize);
    }
}
