package com.unvise.user;

import lombok.Getter;

@Getter
public abstract class AbstractAccount<ID extends Number> {

    protected ID id;

    protected AbstractAccount(ID id) {
        this.id = id;
    }

}
