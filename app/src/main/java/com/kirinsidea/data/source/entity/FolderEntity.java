package com.kirinsidea.data.source.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kirinsidea.ui.folderlist.Folder;

/**
 * 폴더
 *
 * @member folderId     폴더 아이디(PK)
 * @member folderName   북마크 폴더 이름
 * @member storeTime    폴더 생성시간
 * @member isFavorite   즐겨찾기(핀) 여부
 */
@Entity(tableName = "FolderEntity")
public class FolderEntity {
    @PrimaryKey
    @SerializedName("folderId")
    @Expose
    @NonNull
    private String id;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @SerializedName("storeTiime")
    @Expose
    @Nullable
    private String storeTime;

    private boolean isFavorite;

    public FolderEntity() {
    }

    @Ignore
    public FolderEntity(String name) {
        this.name = name;
    }

    @Ignore
    public FolderEntity(String id, String name, String storeTime, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.storeTime = storeTime;
        this.isFavorite = isFavorite;
    }

    private FolderEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.isFavorite = builder.isFavorite;
    }

    @Ignore
    public FolderEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public static class Builder {
        private final String id;
        private final String name;
        private boolean isFavorite;

        public Builder(@NonNull final Folder model) {
            this.id = model.getId();
            this.name = model.getName();
            this.isFavorite = model.isFavorite();
        }

        public static Builder with(@NonNull final Folder model) {
            return new Builder(model);
        }

        public FolderEntity build() {
            return new FolderEntity(this);
        }
    }
}
