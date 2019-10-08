package com.kirinsidea.common;

public abstract class Constant {

    public @interface Retrofit {
        /**
         * 서버의 base URL
         */
        String BASE_URL = "http://seunghuni.iptime.org:5000/";

        /**
         * 새 북마크 추가 URL
         */
        String SUB_URL_NEW_BOOKMARK = "webclipp";

        /**
         * 폴더 생성 base URL
         */
        String SUB_URL_FOLDER = "folder";
    }

    public @interface Room {
        /**
         * Room Database Name
         */
        String DATABASE_NAME = "app_database";
    }

    public @interface Query {

        /**
         * bookmark 테이블에서 id 기준으로 북마크 조회
         */
        String SELECT_BOOKMARK_BY_ID = "SELECT * FROM bookmark WHERE id = :id LIMIT 1";

        /**
         * bookmark 테이블에서 전체 북마크 조회
         */
        String SELECT_BOOKMARK_ALL = "SELECT * FROM bookmark";

        /**
         * folder 테이블에서 id 기준으로 폴더 조회
         */
        String SELECT_FOLDER_BY_NAME = "SELECT * FROM folder WHERE name = :name LIMIT 1";

        /**
         * folder 테이블에서 전체 폴더 조회
         */
        String SELECT_FOLDER_ALL = "SELECT * FROM folder";
    }

}
