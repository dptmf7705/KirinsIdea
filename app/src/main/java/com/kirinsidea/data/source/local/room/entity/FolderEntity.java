package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.data.source.remote.kirin.response.NewFolderResponse;

import java.util.Objects;

/**
 * 폴더
 * @member folderId     폴더 아이디(PK)
 * @member folderName    북마크 폴더 이름 (PK)
 * @member storeTime     폴더 생성시간
 * @member isFavorite   즐겨찾기(핀) 여부
 */
@Entity(tableName = "folder")
public class FolderEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String storeTime;
    private boolean isFavorite;

    public FolderEntity() {
    }

    private FolderEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.storeTime = builder.storeTime;
        this.isFavorite = builder.isFavorite;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderEntity that = (FolderEntity) o;
        return id == that.id &&
                isFavorite == that.isFavorite &&
                name.equals(that.name) &&
                storeTime.equals(that.storeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, storeTime, isFavorite);
    }

    public static class Builder {
        private final int id = 0;
        private final String name;
        private final String storeTime;
        private boolean isFavorite = false;

        public Builder(@NonNull final NewFolderResponse response) {
            this.name = response.getName();
            this.storeTime = response.getStoreTime();
        }

        public Builder setFavorite(boolean favorite) {
            isFavorite = favorite;
            return this;
        }

        public FolderEntity build() {
            return new FolderEntity(this);
        }
    }
}
