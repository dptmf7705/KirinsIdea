package com.kirinsidea.common;

public class Constant {

    public interface Query {
        String selectBookmarkById = "SELECT * FROM bookmark WHERE id = :id LIMIT 1";
        String selectBookmarkAll = "SELECT * FROM bookmark";
        String selectFolderByName = "SELECT * FROM folder WHERE folderName = :folderName LIMIT 1";
        String selectFolderAll = "SELECT * FROM folder";
    }

    public interface InjectionType {
        String Login = "Login";
        String Bookmark = "Bookmark";
        String BookmarkList = "BookmarkList";
        String AddNewBookmark = "AddNewBookmark";
        String Folder = "Folder";
    }
}
