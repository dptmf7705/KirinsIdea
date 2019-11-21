package com.kirinsidea.ui.login;

import com.kirinsidea.R;
import com.kirinsidea.common.resources.ResourceCompat;

public enum LinkText {

    TERMS_OF_USE(R.string.login_terms_of_use_string, R.string.login_terms_of_use_url),
    PRIVACY_POLICY(R.string.login_privacy_policy_string, R.string.login_privacy_policy_url);

    private final int stringResId;
    private final int urlResId;

    LinkText(int stringResId, int urlResId) {
        this.stringResId = stringResId;
        this.urlResId = urlResId;
    }

    public int getStringResId() {
        return stringResId;
    }

    public int getUrlResId() {
        return urlResId;
    }

    public String getString() {
        return ResourceCompat.getString(stringResId);
    }

    public String getUrl() {
        return ResourceCompat.getString(urlResId);
    }
}
