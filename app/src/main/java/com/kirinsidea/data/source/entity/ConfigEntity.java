package com.kirinsidea.data.source.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "config")
public class ConfigEntity {
    public static final int DEFAULT_ID = 0;
    @PrimaryKey
    private int id;
    private boolean isLoggedIn = false;

    public ConfigEntity(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

}
