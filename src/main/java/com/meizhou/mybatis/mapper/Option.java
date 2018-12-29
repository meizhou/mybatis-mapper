package com.meizhou.mybatis.mapper;

/**
 * Created by hejun on 16/10/25.
 */
public class Option {
    private boolean readSlave = true;

    public boolean isReadSlave() {
        return readSlave;
    }

    public void setReadSlave(boolean readSlave) {
        this.readSlave = readSlave;
    }
}
