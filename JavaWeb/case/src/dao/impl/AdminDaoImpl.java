package dao.impl;

import bean.Admin;
import dao.AdminDao;
import dao.BaseDAO;

import java.sql.SQLException;

public class AdminDaoImpl extends BaseDAO<Admin> implements AdminDao {
    @Override
    public Admin getAdminByNameAndPassword(Admin admin) throws SQLException {
        String sql = "select * from admin where name = ? and password = ?";
        return searchSingleRecord(sql, admin.getName(), admin.getPassword());
    }
}
