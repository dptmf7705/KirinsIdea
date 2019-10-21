package com.kirinsidea.common;

public abstract class Constants {

    public @interface Message {
        /* init view model 에러 메시지 */
        String ERROR_INIT_VIEW_MODEL = "Unknown ViewModel class";

        /* init repository 에러 메시지 */
        String ERROR_INIT_REPOSITORY = "Unknown Repository class";
    }
}
