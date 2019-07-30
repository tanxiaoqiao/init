package com.init.constant;


public enum ErrorEnum {
    LACKPARAMETER(50001, "参数缺失"),
    DATASTATUSERROR(50002, "数据已失效，刷新重试"),
    PARAS_ERROR(50003,"参数格式错误"),
    UPLOAD_ERROR(50004,"上传失败"),
    SERVER_ERROR(500, "服务器异常"),
    LOGIN_FAIL(40000, "帐号或者密码错误"),
    NO_LOGIN(40001, "用户未登录"),
    ACCOUNT_EXIST(40002, "账户已存在"),
    PARAMS_ERROR(40003, "参数错误"),
    PASSWORD_ERROR(40004, "用户名或密码错误"),
    NOT_FOUND(40005, "没有该记录"),
    NO_PERMISSION(40006,"无权限"),
;


    private int code;

    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
