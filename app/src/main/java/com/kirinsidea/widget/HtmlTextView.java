package com.kirinsidea.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kirinsidea.App;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class HtmlTextView extends AppCompatTextView {
    @NonNull
    private final PublishSubject<Boolean> imageLoadingStream = PublishSubject.create();

    private final Html.ImageGetter imageGetter = source -> {
        DrawablePlaceHolder drawable = new DrawablePlaceHolder(imageLoadingStream);

        Glide.with(App.instance().getContext())
                .load(source)
                .listener(drawable)
                .submit();

        return drawable;
    };

    private Disposable disposable = imageLoadingStream
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(__ -> notifyTextChanged());

    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * html 텍스트, 이미지 랜더링
     */
    public void setTextFromHtml(@NonNull final CharSequence html) {
        setText(Html.fromHtml(html.toString(), imageGetter, null));
    }

    private void notifyTextChanged() {
        setText(getText());
    }

    @Override
    protected void onDetachedFromWindow() {
        imageLoadingStream.onComplete();
        disposable.dispose();
        super.onDetachedFromWindow();
    }


    private static class DrawablePlaceHolder extends Drawable implements RequestListener<Drawable> {
        @NonNull
        private final PublishSubject<Boolean> imageLoadingStream;

        private Drawable drawable;

        private DrawablePlaceHolder(@NonNull final PublishSubject<Boolean> imageLoadingStream) {
            this.imageLoadingStream = imageLoadingStream;
        }

        @Override
        public void draw(@NonNull final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(@NonNull final Drawable drawable) {
            this.drawable = drawable;
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();

            drawable.setBounds(0, 0, width, height);
            setBounds(0, 0, width, height);

            this.imageLoadingStream.onNext(true);
        }

        @Override
        public boolean onResourceReady(Drawable resource,
                                       Object model,
                                       Target<Drawable> target,
                                       DataSource dataSource,
                                       boolean isFirstResource) {
            setDrawable(resource);
            return false;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e,
                                    Object model,
                                    Target target,
                                    boolean isFirstResource) {
            // TODO. 에러 이미지 로딩 후 imageLoadingStream.onNext(false) 호출
//            setDrawable();
            return false;
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }
    }
}
