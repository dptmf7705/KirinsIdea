package com.kirinsidea.ui.folderlist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Ignore;

import com.kirinsidea.data.source.entity.FolderEntity;

import java.util.Objects;

public class Folder {
    public static final Folder ALL_BOOKMARK = new Folder("-1", "전체");

    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private boolean isFavorite;
    @NonNull
    private boolean isSelected;

    @Ignore
    public Folder(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
        this.isFavorite = false;
        this.isSelected = false;
    }

    public Folder(@NonNull String id, @NonNull String name, boolean isFavorite, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isFavorite = isFavorite;
        this.isSelected = isSelected;
    }

    private Folder(@NonNull final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.isFavorite = builder.isFavorite;
        this.isSelected = builder.isSelected;
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
        private boolean isFavorite;
        private boolean isSelected = false;

        public Builder(@NonNull final FolderEntity entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.isFavorite = entity.isFavorite();
        }

        @NonNull
        public Builder setSelected(@NonNull final boolean isSelected) {
            this.isSelected = isSelected;
            return this;
        }

        @NonNull
        public Folder build() {
            return new Folder(this);
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
