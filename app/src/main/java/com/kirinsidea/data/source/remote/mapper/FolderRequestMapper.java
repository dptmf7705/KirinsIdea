package com.kirinsidea.data.source.remote.mapper;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

public class FolderRequestMapper {

    @NonNull
    public static Folder toFolder(@NonNull final NewFolderRequest request){

        return new Folder(request.getFolderName(), request.getStorageTime(),false);
    }
}
