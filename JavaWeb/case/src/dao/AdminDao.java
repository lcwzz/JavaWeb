package dao;

import bean.Admin;

import java.sql.SQLException;

public interface AdminDao {
    Admin getAdminByNameAndPassword(Admin admin) throws SQLException;
}
