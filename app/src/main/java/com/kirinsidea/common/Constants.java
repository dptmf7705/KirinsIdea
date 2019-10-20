package com.kirinsidea.common;

public abstract class Constants {

    public @interface Retrofit {
        /* 서버의 base URL */
        String BASE_URL = "http://133.186.146.239:5000/";

        /* 새 북마크 추가 URL */
        String SUB_URL_NEW_BOOKMARK = "webclipp";

        /* 폴더 생성 URL */
        String SUB_URL_NEW_FOLDER = "folder";
    }

    public @interface Room {
        /* Room Database Name */
        String DATABASE_NAME = "app_database";
    }

    public @interface Query {
        /* bookmark 테이블에서 id 기준으로 북마크 조회 */
        String SELECT_BOOKMARK_BY_ID = "SELECT * FROM bookmark WHERE id = :id LIMIT 1";

        /* bookmark 테이블에서 전체 북마크 조회 */
        String SELECT_ALL_BOOKMARK = "SELECT * FROM bookmark";

        /* bookmark 테이블에서 originalWebUrl 기준으로 북마크 조회*/
        String SELECT_BOOKMARK_BY_URL = "SELECT COUNT(*) FROM bookmark WHERE originalWebUrl = :originalWebUrl ";

        /* folder 테이블에서 name 기준으로 폴더 조회 */
        String SELECT_FOLDER_BY_NAME = "SELECT * FROM folder WHERE name = :name LIMIT 1";

        /* folder 테이블에서 전체 폴더 조회 */
        String SELECT_ALL_FOLDER = "SELECT * FROM folder";
    }

    public @interface Message {
        /* init view model 에러 메시지 */
        String ERROR_INIT_VIEW_MODEL = "Unknown ViewModel class";

        /* init repository 에러 메시지 */
        String ERROR_INIT_REPOSITORY = "Unknown Repository class";
    }
}
