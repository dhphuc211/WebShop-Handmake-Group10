package com.example.backend.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * PasswordUtil - mã hóa và kiểm tra mật khẩu
 *
 */
public class PasswordUtil {

    /**
     * Mã hóa mật khẩu bằng MD5 (Đơn giản, dễ hiểu)
     * @param password Mật khẩu gốc
     * @return Mật khẩu đã mã hóa
     */
    public static String encrypt(String password) {
        try {
            // Sử dụng thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Chuyển đổi password thành mảng byte và băm
            md.update(password.getBytes());
            byte[] digest = md.digest();
            
            // Chuyển đổi mảng byte thành chuỗi Hex (hệ 16)
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                // %02x nghĩa là in ra 2 ký tự hex, nếu thiếu thì thêm số 0 đằng trước
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tạo mật khẩu ngẫu nhiên (dùng cho chức năng quên mật khẩu)
     *
     * @param length Độ dài mật khẩu mong muốn
     * @return Mật khẩu ngẫu nhiên
     */
    public static String generateRandomPassword(int length) {
        // Các ký tự có thể sử dụng trong mật khẩu
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // SecureRandom an toàn hơn Random thông thường
        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }

    /**
     * Kiểm tra độ mạnh của mật khẩu
     *
     * @param password Mật khẩu cần kiểm tra
     * @return true nếu mật khẩu đủ mạnh
     *
     * Yêu cầu:
     * - Ít nhất 6 ký tự
     * - (Có thể thêm: chữ hoa, chữ thường, số, ký tự đặc biệt)
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        // Kiểm tra độ dài tối thiểu
        if (password.length() < 6) {
            return false;
        }

        return true;
    }
}
