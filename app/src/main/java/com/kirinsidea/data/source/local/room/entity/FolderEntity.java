package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.kirinsidea.data.source.remote.kirin.response.NewFolderResponse;

import java.util.Objects;

/**
 * 폴더
 *
 * @member folderId     폴더 아이디(PK)
 * @member folderName   북마크 폴더 이름
 * @member storeTime    폴더 생성시간
 * @member isFavorite   즐겨찾기(핀) 여부
 * @member isSelected   아이템 클릭여부
 */
@Entity(tableName = "folder")
public class FolderEntity {
    @PrimaryKey
    private int id;
    private String name;
    private String storeTime;
    private boolean isFavorite;

    private boolean isSelected;

    public FolderEntity() {
    }

    @Ignore
    public FolderEntity(String name) {
        this.name = name;
    }

    @Ignore
    public FolderEntity(int id, String name, String storeTime, boolean isFavorite, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.storeTime = storeTime;
        this.isFavorite = isFavorite;
        this.isSelected = isSelected;
    }

    private FolderEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.storeTime = builder.storeTime;
        this.isFavorite = builder.isFavorite;
        this.isSelected = builder.isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderEntity that = (FolderEntity) o;
        return id == that.id &&
                isFavorite == that.isFavorite &&
                isSelected == that.isSelected &&
                name.equals(that.name) &&
                storeTime.equals(that.storeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, storeTime, isFavorite, isSelected);
    }

    public static class Builder {
        private final int id;
        private final String name;
        private final String storeTime;
        private boolean isFavorite = false;
        private boolean isSelected = false;

        public Builder(@NonNull final NewFolderResponse response) {
            this.id = response.getId();
            this.name = response.getName();
            this.storeTime = response.getStoreTime();
        }

        public Builder setFavorite(boolean favorite) {
            isFavorite = favorite;
            return this;
        }

        public Builder setSelected(boolean selected) {
            isSelected = selected;
            return this;
        }

        public FolderEntity build() {
            return new FolderEntity(this);
        }
    }
}
