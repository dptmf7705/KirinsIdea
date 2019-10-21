package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

import java.util.Objects;

/**
 * 폴더
 *
 * @member folerName    북마크 폴더 이름 (PK)
 * @member saveTime     폴더 생성시간
 * @member isFavorite   즐겨찾기(핀) 여부
 */
@Entity(tableName = "folder")
public class FolderEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String saveTime;
    private boolean isFavorite;

    public FolderEntity() {
    }

    private FolderEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.saveTime = builder.saveTime;
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

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
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
                saveTime.equals(that.saveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, saveTime, isFavorite);
    }

    public static class Builder {
        private final int id = 0;
        private final String name;
        private final String saveTime;
        private boolean isFavorite = false;

        public Builder(@NonNull final NewFolderRequest request) {
            this.name = request.getFolderName();
            this.saveTime = request.getStorageTime();
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
