package service.impl;

import bean.Page;
import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用UserDao中的findAll
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        //调用UserDao中的addUser方法
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(int id) {
        //调用UserDao中的deleteById方法
        try {
            userDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(int id) {
        //调用UserDao中的findById方法
        try {
            return userDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        //调用UserDao中的updateUser方法
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delSelected(String[] uids) {
        for (String uid : uids) {
            deleteUserById(Integer.parseInt(uid));
        }
    }

    @Override
    public Page<User> getPage(String _currentPage, String _pageSize, Map<String, String[]> condition) {
        //1.获取参数
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2.设置参数
        Page<User> userPage = new Page<>();
        userPage.setCurrentPage(currentPage);
        userPage.setPageSize(pageSize);

        int totalCount = 0;
        List<User> list = null;
        try {
            totalCount = userDao.getTotalCount(condition);
            list = userDao.getUserPage(currentPage, pageSize, condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userPage.setCount(totalCount);
        userPage.setList(list);

        int pages = (totalCount%pageSize==0) ? (totalCount/pageSize) : (totalCount/pageSize+1);
        userPage.setPages(pages);

        return userPage;
    }
}
