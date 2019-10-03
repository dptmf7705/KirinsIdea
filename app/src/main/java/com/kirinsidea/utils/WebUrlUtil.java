package com.kirinsidea.utils;

import android.util.Patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class WebUrlUtil {
    public static String findUrlInText(String text) {
        List<String> urls = new ArrayList<>();
        Matcher m = Patterns.WEB_URL.matcher(text);
        while (m.find()) {
            String url = m.group();
            urls.add(url);
        }
        if (urls.size() > 0) {
            return urls.get(0);
        }
        return null;
    }
}
