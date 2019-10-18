package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

/**
 * 폴더
 *
 * @member folerName    북마크 폴더 이름 (PK)
 * @member saveTime     폴더 생성시간
 * @member isFavorite   즐겨찾기(핀) 여부
 */
@Entity(tableName = "folder")
public class Folder {
    @PrimaryKey
    @NonNull
    private final String name;
    @NonNull
    private final String saveTime;
    private final boolean isFavorite;

    public Folder(@NonNull final String name,
                  @NonNull final String saveTime,
                  final boolean isFavorite) {
        this.name = name;
        this.saveTime = saveTime;
        this.isFavorite = isFavorite;

    }
    @NonNull
    public String getName(){return name;}

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
        Folder folder = (Folder) o;
        return isFavorite == folder.isFavorite &&
                Objects.equals(name, folder.name) &&
                saveTime.equals(folder.saveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, saveTime, isFavorite);
    }
}
