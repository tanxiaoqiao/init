package com.honeywell.finger.constant;


public enum ErrorEnum {
    LACKPARAMETER(50001, "参数缺失"),
    DATASTATUSERROR(50002, "数据已失效，刷新重试"),
    PARAS_ERROR(50003,"参数格式错误"),
    UPLOAD_ERROR(50004,"上传失败"),
    SERVER_ERROR(500, "服务器异常"),

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
