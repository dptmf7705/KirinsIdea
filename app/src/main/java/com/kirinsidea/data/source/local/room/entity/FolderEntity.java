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
    @PrimaryKey
    @NonNull
    private final String name;
    @NonNull
    private final String saveTime;
    private final boolean isFavorite;

    public FolderEntity(@NonNull final String name,
                        @NonNull final String saveTime,
                        final boolean isFavorite) {
        this.name = name;
        this.saveTime = saveTime;
        this.isFavorite = isFavorite;

    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getSaveTime() {
        return saveTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderEntity folderEntity = (FolderEntity) o;
        return isFavorite == folderEntity.isFavorite &&
                Objects.equals(name, folderEntity.name) &&
                saveTime.equals(folderEntity.saveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, saveTime, isFavorite);
    }

    public static class Builder {
        private String name;
        private String saveTime;
        private boolean isFavorite;

        public Builder fromRequest(@NonNull final NewFolderRequest request) {
            this.name = request.getFolderName();
            this.isFavorite = false;
            this.saveTime = request.getStorageTime();
            return this;
        }

        public FolderEntity build() {
            return new FolderEntity(name,
                    saveTime,
                    isFavorite);
        }
    }
}
