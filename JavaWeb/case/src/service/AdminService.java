package service;

import bean.Admin;

public interface AdminService {
    /**
     * 根据用户名密码登陆
     * @return
     */
    Admin login(Admin admin);
}
