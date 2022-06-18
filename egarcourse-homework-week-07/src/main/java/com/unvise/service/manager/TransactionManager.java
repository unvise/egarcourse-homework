package com.unvise.service.manager;

public interface TransactionManager {

    void perform();

    boolean wasSuccessful();

}
