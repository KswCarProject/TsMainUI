package com.ts.can;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.ts.MainUI.R;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GifView extends ImageView {
    private static final int DEFAULT_DURATION = 1000;
    private int counts;
    private boolean endLastFrame;
    private volatile boolean hasPlay;
    private volatile boolean hasStart;
    /* access modifiers changed from: private */
    public List<Movie> mGifMovies;
    /* access modifiers changed from: private */
    public int[] mGifResIds;
    private long mMoviePauseTime;
    private long mMovieStart;
    private OnPlayListener mOnPlayListener;
    /* access modifiers changed from: private */
    public int mOrderIndex;
    private OnPlayListener mOrderPlayListener;
    private volatile boolean mPaused;
    private float mScale;
    private float mScaleH;
    private float mScaleW;
    private boolean mVisible;
    private Movie movie;
    private int movieDuration;
    private long offsetTime;
    float percent;
    private volatile boolean reverse;

    public interface OnPlayListener {
        void onPlayEnd();

        void onPlayPause(boolean z);

        void onPlayRestart();

        void onPlayStart();

        void onPlaying(float f);
    }

    public GifView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GifView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mScaleW = 1.0f;
        this.mScaleH = 1.0f;
        this.mScale = 1.0f;
        this.counts = -1;
        this.reverse = false;
        this.mVisible = true;
        this.endLastFrame = false;
        this.mOrderPlayListener = new OnPlayListener() {
            public void onPlaying(float percent) {
            }

            public void onPlayStart() {
            }

            public void onPlayRestart() {
            }

            public void onPlayPause(boolean pauseSuccess) {
            }

            public void onPlayEnd() {
                Movie m;
                GifView gifView = GifView.this;
                gifView.mOrderIndex = gifView.mOrderIndex + 1;
                GifView.this.mOrderIndex = GifView.this.mOrderIndex >= GifView.this.mGifResIds.length ? 0 : GifView.this.mOrderIndex;
                if (GifView.this.mGifMovies.size() != GifView.this.mGifResIds.length) {
                    m = Movie.decodeStream(GifView.this.getResources().openRawResource(GifView.this.mGifResIds[GifView.this.mOrderIndex]));
                    GifView.this.mGifMovies.add(m);
                } else {
                    m = (Movie) GifView.this.mGifMovies.get(GifView.this.mOrderIndex);
                }
                GifView.this.playGifMovie(m);
            }
        };
        initAttributes(context, attrs, defStyle);
    }

    private void initAttributes(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GifView, defStyle, 0);
        int srcID = a.getResourceId(0, 0);
        boolean authPlay = a.getBoolean(1, true);
        this.counts = a.getInt(2, -1);
        this.endLastFrame = a.getBoolean(3, false);
        if (srcID > 0) {
            setGifResource(srcID, (OnPlayListener) null);
            if (authPlay) {
                play(this.counts);
            }
        }
        a.recycle();
        setLayerType(1, (Paint) null);
    }

    public void setGifResource(int movieResourceId, OnPlayListener onPlayListener) {
        Bitmap bitmap;
        if (onPlayListener != null) {
            this.mOnPlayListener = onPlayListener;
        }
        reset();
        this.movie = Movie.decodeStream(getResources().openRawResource(movieResourceId));
        if (this.movie != null || (bitmap = BitmapFactory.decodeResource(getResources(), movieResourceId)) == null) {
            this.movieDuration = this.movie.duration() == 0 ? 1000 : this.movie.duration();
            requestLayout();
            return;
        }
        setImageBitmap(bitmap);
    }

    public void setGifResource(int movieResourceId) {
        setGifResource(movieResourceId, (OnPlayListener) null);
    }

    public void setGifResource(String path, OnPlayListener onPlayListener) {
        Bitmap bitmap;
        this.movie = Movie.decodeFile(path);
        this.mOnPlayListener = onPlayListener;
        reset();
        if (this.movie != null || (bitmap = BitmapFactory.decodeFile(path)) == null) {
            this.movieDuration = this.movie.duration() == 0 ? 1000 : this.movie.duration();
            requestLayout();
            if (this.mOnPlayListener != null) {
                this.mOnPlayListener.onPlayStart();
                return;
            }
            return;
        }
        setImageBitmap(bitmap);
    }

    /* access modifiers changed from: private */
    public void playGifMovie(Movie m) {
        this.movie = m;
        this.movieDuration = this.movie.duration() == 0 ? 1000 : this.movie.duration();
        requestLayout();
        play(1);
    }

    public void playOrder(int[] resIds) {
        if (resIds != null) {
            this.mGifResIds = resIds;
            this.mGifMovies = new ArrayList();
            this.mOnPlayListener = this.mOrderPlayListener;
            this.mOrderIndex = 0;
            Movie m = Movie.decodeStream(getResources().openRawResource(resIds[this.mOrderIndex]));
            this.mGifMovies.add(m);
            playGifMovie(m);
        }
    }

    public void playOver() {
        if (this.movie != null) {
            play(-1);
        }
    }

    public void playReverse() {
        if (this.movie != null) {
            reset();
            this.reverse = true;
            if (this.mOnPlayListener != null) {
                this.mOnPlayListener.onPlayStart();
            }
            invalidate();
        }
    }

    public void play(int counts2) {
        this.counts = counts2;
        reset();
        if (this.mOnPlayListener != null) {
            this.mOnPlayListener.onPlayStart();
        }
        invalidate();
    }

    private void reset() {
        this.reverse = false;
        this.mMovieStart = SystemClock.uptimeMillis();
        this.mPaused = false;
        this.hasStart = true;
        this.mMoviePauseTime = 0;
        this.offsetTime = 0;
    }

    public void play() {
        if (this.movie != null) {
            this.hasPlay = true;
            if (!this.hasStart) {
                play(-1);
            } else if (this.mPaused && this.mMoviePauseTime > 0) {
                this.mPaused = false;
                this.offsetTime = (this.offsetTime + SystemClock.uptimeMillis()) - this.mMoviePauseTime;
                invalidate();
                if (this.mOnPlayListener != null) {
                    this.mOnPlayListener.onPlayRestart();
                }
            }
        }
    }

    public void pause() {
        this.hasPlay = false;
        if (this.movie != null && !this.mPaused && this.hasStart) {
            this.mPaused = true;
            invalidate();
            this.mMoviePauseTime = SystemClock.uptimeMillis();
            if (this.mOnPlayListener != null) {
                this.mOnPlayListener.onPlayPause(true);
            }
        } else if (this.mOnPlayListener != null) {
            this.mOnPlayListener.onPlayPause(false);
        }
    }

    private int getCurrentFrameTime() {
        if (this.movieDuration == 0) {
            return 0;
        }
        long now = SystemClock.uptimeMillis() - this.offsetTime;
        int nowCount = (int) ((now - this.mMovieStart) / ((long) this.movieDuration));
        if (this.counts == -1 || nowCount < this.counts) {
            float currentTime = (float) ((now - this.mMovieStart) % ((long) this.movieDuration));
            this.percent = currentTime / ((float) this.movieDuration);
            if (this.mOnPlayListener != null && this.hasStart) {
                double f1 = new BigDecimal((double) this.percent).setScale(2, 4).doubleValue();
                if (f1 == 0.99d) {
                    f1 = 1.0d;
                }
                this.mOnPlayListener.onPlaying((float) f1);
            }
            return (int) currentTime;
        }
        this.hasStart = false;
        if (this.mOnPlayListener != null) {
            this.mOnPlayListener.onPlayEnd();
        }
        if (this.endLastFrame) {
            return this.movieDuration;
        }
        return 0;
    }

    public void setPercent(float percent2) {
        if (this.movie != null && this.movieDuration > 0) {
            this.percent = percent2;
            this.movie.setTime((int) (((float) this.movieDuration) * percent2));
            invalidateView();
            if (this.mOnPlayListener != null) {
                this.mOnPlayListener.onPlaying(percent2);
            }
        }
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public boolean isPlaying() {
        return !this.mPaused && this.hasStart;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.movie != null && this.hasPlay) {
            if (this.mPaused || !this.hasStart) {
                drawMovieFrame(canvas);
                return;
            }
            if (this.reverse) {
                this.movie.setTime(this.movieDuration - getCurrentFrameTime());
            } else {
                this.movie.setTime(getCurrentFrameTime());
            }
            drawMovieFrame(canvas);
            invalidateView();
        }
    }

    private void drawMovieFrame(Canvas canvas) {
        canvas.save();
        canvas.scale(1.0f / this.mScale, 1.0f / this.mScale);
        this.movie.draw(canvas, 0.0f, 0.0f);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.movie != null) {
            int movieWidth = this.movie.width();
            int movieHeight = this.movie.height();
            if (widthMode == 1073741824) {
                this.mScaleW = ((float) movieWidth) / ((float) sizeWidth);
            }
            if (heightMode == 1073741824) {
                this.mScaleH = ((float) movieHeight) / ((float) sizeHeight);
            }
            this.mScale = Math.max(this.mScaleW, this.mScaleH);
            if (widthMode != 1073741824) {
                sizeWidth = movieWidth;
            }
            if (heightMode != 1073741824) {
                sizeHeight = movieHeight;
            }
            setMeasuredDimension(sizeWidth, sizeHeight);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint({"NewApi"})
    private void invalidateView() {
        if (this.mVisible) {
            postInvalidateOnAnimation();
        }
    }

    public int getDuration() {
        if (this.movie != null) {
            return this.movie.duration();
        }
        return 0;
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int screenState) {
        boolean z = true;
        super.onScreenStateChanged(screenState);
        if (screenState != 1) {
            z = false;
        }
        this.mVisible = z;
        invalidateView();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        this.mVisible = visibility == 0;
        invalidateView();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.mVisible = visibility == 0;
        invalidateView();
    }
}
