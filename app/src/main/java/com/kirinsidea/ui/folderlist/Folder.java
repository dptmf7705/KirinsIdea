package com.kirinsidea.ui.folderlist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Ignore;

import com.kirinsidea.data.source.entity.FolderEntity;

import java.util.Objects;

public class Folder {
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private boolean isFavorite;
    @NonNull
    private boolean isSelected;

    @Ignore
    public Folder(@NonNull String name){
        this.name = name;
    }

    public Folder(@NonNull String id, @NonNull String name, boolean isFavorite, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isFavorite = isFavorite;
        this.isSelected = isSelected;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return isFavorite == folder.isFavorite &&
                isSelected == folder.isSelected &&
                id.equals(folder.id) &&
                name.equals(folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isFavorite, isSelected);
    }

    public static class Builder {
        private String id;
        private String name;
        private boolean isFavorite ;
        private boolean isSelected =false;

        @NonNull
        public Builder fromEntity(@NonNull final FolderEntity entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.isFavorite = entity.isFavorite();
            return this;
        }

        @NonNull
        public Builder setSelected(@NonNull final boolean isSelected){
            this.isSelected = isSelected;
            return this;
        }

        @NonNull
        public Folder build(){
            return new Folder(id, name, isFavorite, isSelected);
        }

    }

    public static final DiffUtil.ItemCallback<Folder> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Folder>() {
                @Override
                public boolean areItemsTheSame(@NonNull Folder oldItem, @NonNull Folder newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Folder oldItem, @NonNull Folder newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
