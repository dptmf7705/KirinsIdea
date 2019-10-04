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
 * @member folerName         북마크 폴더 이름 (PK)
 * @member folderStorageTime 폴더 생성시간
 * @member folder
 */
@Entity(tableName = "folder")
public class Folder {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folderName")
    private final String folderName;
    @NonNull
    private final Date foldeerStorageTime;
    @NonNull
    private final boolean folderPin;

    public Folder(@NonNull String folderName, @NonNull Date foldeerStorageTime, @NonNull boolean folderPin) {
        this.folderName = folderName;
        this.foldeerStorageTime = foldeerStorageTime;
        this.folderPin = folderPin;

    }
    @NonNull
    public String getFolderName(){return folderName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return folderPin == folder.folderPin &&
                Objects.equals(folderName, folder.folderName) &&
                foldeerStorageTime.equals(folder.foldeerStorageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderName, foldeerStorageTime, folderPin);
    }
}
