package com.example.backend.dao;

import com.example.backend.model.User;

public class UserDAO {
    /**
     * Đăng ký tài khoản mới
     *
     * @param user Đối tượng User chứa thông tin đăng ký
     * @return true nếu đăng ký thành công, false nếu thất bại
     */
    public boolean register(User user) {
        return false;
    }

    /**
     * Kiểm tra email đã tồn tại chưa
     */
    public boolean isEmailExists(String email) {
        return findByEmail(email) != null;
    }

    private boolean findByEmail(String email) {
        return false;
    }

    /**
     * Kiểm tra phone đã tồn tại chưa
     */
    public boolean isPhoneExists(String phone) {
        return findByPhone(phone) != null;
    }

    private boolean findByPhone(String phone) {
        return false;
    }
}
