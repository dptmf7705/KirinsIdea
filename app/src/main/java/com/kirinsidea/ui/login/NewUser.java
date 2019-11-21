package com.kirinsidea.ui.login;

import com.kirinsidea.ui.user.UserGender;
import com.kirinsidea.ui.user.LoginMethod;

public class NewUser {
    private String email;
    private String name;
    private UserGender gender;
    private String age;
    private LoginMethod loginMethod;
    private String firebaseUid;
    private String profileImageUrl;

    public NewUser() {
    }

    private NewUser(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
        this.loginMethod = builder.loginMethod;
        this.firebaseUid = builder.firebaseUid;
        this.profileImageUrl = builder.profileImageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public LoginMethod getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(LoginMethod loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age='" + age + '\'' +
                ", loginMethod=" + loginMethod +
                ", firebaseUid='" + firebaseUid + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                '}';
    }

    public static class Builder {
        private String email;
        private String name;
        private UserGender gender;
        private String age;
        private LoginMethod loginMethod;
        private String firebaseUid;
        private String profileImageUrl;

        public Builder() {
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGender(UserGender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(String age) {
            this.age = age;
            return this;
        }

        public Builder setLoginMethod(LoginMethod loginMethod) {
            this.loginMethod = loginMethod;
            return this;
        }

        public Builder setFirebaseUid(String firebaseUid) {
            this.firebaseUid = firebaseUid;
            return this;
        }

        public Builder setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public NewUser build() {
            return new NewUser(this);
        }
    }
}
