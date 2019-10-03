package com.kirinsidea.common;

public class Constant {

    public interface Query {
        String selectBookmarkById = "SELECT * FROM bookmark WHERE id = :id LIMIT 1";
        String selectBookmarkAll = "SELECT * FROM bookmark";
        String selectFolderById = "SELECT * FROM folder WHERE id = :id LIMIT 1";
    }
}
