package com.kirinsidea.common.resources;

import com.kirinsidea.App;

public class ResourceCompat {

    public static String getString(int stringResId){
        return App.instance().getResources().getString(stringResId);
    }
}
