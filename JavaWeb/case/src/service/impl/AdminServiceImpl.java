package service.impl;

import bean.Admin;
import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(Admin admin) {
        try {
            return adminDao.getAdminByNameAndPassword(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
