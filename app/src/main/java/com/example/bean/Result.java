package com.example.bean;

/**
 * @author master
 * @date 2018/6/6
 */

public class Result {
    private int installRate;
    private int code;
    private String msg;

    public int getInstallRate() {
        return installRate;
    }

    public void setInstallRate(int installRate) {
        this.installRate = installRate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "installRate=" + installRate +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
