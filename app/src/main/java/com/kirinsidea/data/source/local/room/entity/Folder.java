package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * 북마크
 *
 * @member id               북마크 ID (PK)
 * @member folerName        북마크 폴더 이름
 */
@Entity(tableName = "folder")
public class Folder {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final int id;
    @NonNull
    private final String folderName;

    public Folder(int id, @NonNull String folderName) {
        this.id = id;
        this.folderName = folderName;
    }
    public int getId() {
        return id;
    }
    @NonNull
    public String getFolderName(){return folderName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return id == folder.id &&
                folderName.equals(folder.folderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, folderName);
    }
}
