package com.honeywell.finger.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具
 *
 */
public class PasswordUtils {

    /**
     * sha256的随机盐值加密
     *
     * @param pwd
     * @return
     */
    public static String sha256Encode(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(pwd);
        return encoderPassword;
    }

    /**
     * sha256密码校验
     *
     * @param rawPwd
     * @param encoderPwd
     * @return
     */
    public static boolean sha256PasswordVerify(String rawPwd, String encoderPwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean result = encoder.matches(rawPwd, encoderPwd);
        return result;
    }
}
