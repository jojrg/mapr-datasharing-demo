package com.mapr.datasharingdemo.service;

/**
 * Created by jojrg on 02.12.17.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException() {
        super();
        this.printStackTrace();
    }

    public DataAccessException(Throwable ex) {
        super(ex);
        this.printStackTrace();
    }

    public DataAccessException(String msg) {
        super(msg);
        this.printStackTrace();
    }


}
