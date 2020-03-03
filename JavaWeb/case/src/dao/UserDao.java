package dao;

import bean.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 查找user表中所有记录
     * @return
     */
    List<User> findAll() throws SQLException;

    /**
     * 向user表中添加一条记录
     * @param user
     * @throws SQLException
     */
    void addUser(User user) throws SQLException;

    /**
     * 根据id删除记录
     * @param id
     */
    void deleteById(int id) throws SQLException;

    /**
     * 根据id查找记录
     * @param id
     */
    User findById(int id) throws SQLException;

    /**
     * 更新记录
     * @param user
     */
    void updateUser(User user) throws SQLException;

    /**
     * 查询记录总数
     * @return
     */
    int getTotalCount(Map<String, String[]> condition) throws SQLException;

    /**
     * 查询一页数据
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<User> getUserPage(int currentPage, int pageSize, Map<String, String[]> condition) throws SQLException;
}
