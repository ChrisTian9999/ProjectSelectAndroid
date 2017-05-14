package com.chris.pss.data.interceptor;

import java.io.IOException;

public class NetworkConnectionException extends IOException {
    public String msg; //用户提示信息
    public int status;

    public NetworkConnectionException(final String message) {
        super(message);
    }

    public NetworkConnectionException(final String msg, final int status) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
