package service;

import bean.Page;
import bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查找所有用户数据
     * @return
     */
    List<User> findAll();

    /**
     * 添加一条用户记录
     * @param user
     */
    void add(User user);

    /**
     * 根据用户id删除该用户
     * @param id
     */
    void deleteUserById(int id);

    /**
     * 根据用户id查找该用户
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 修改用户数据
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除选中数据
     * @param uids
     */
    void delSelected(String[] uids);

    /**
     * 获取Page分页数据对象
     * @param _currentPage
     * @param _pageSize
     * @return
     */
    Page<User> getPage(String _currentPage, String _pageSize, Map<String, String[]> condition);
}
