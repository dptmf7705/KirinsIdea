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

    public @interface Message {
        /* init view model 에러 메시지 */
        String ERROR_INIT_VIEW_MODEL = "Unknown ViewModel class";

        /* init repository 에러 메시지 */
        String ERROR_INIT_REPOSITORY = "Unknown Repository class";
    }
}
