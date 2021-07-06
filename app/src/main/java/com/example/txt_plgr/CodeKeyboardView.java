package com.example.txt_plgr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
//import com.android.internal.R;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.txt_plgr.CodePref.mShadowColor;
import static com.example.txt_plgr.CodePref.mShadowRadius;

public class CodeKeyboardView extends KeyboardView {
    private static final boolean DEBUG = false;
    private Drawable mKeyBackground;
    private float mLabelTextSize = 80;
    private int mKeyTextDefaultColor = R.color.white;
//    хз на что влияет
    private float mKeyTextSize = 30;
    private Rect mPadding = new Rect(0, 0, 0, 0);

    public CodeKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CodeKeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
//    @SuppressLint("CustomViewStyleable")
//    public CodeKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
//        super(context, attrs, defStyleAttr, defStyleRes);
//
////         TypedArray a = context.obtainStyledAttributes(
////                attrs, android.R.styleable.KeyboardView, defStyleAttr, defStyleRes);
//
//
////         int n = a.getIndexCount();
//
////         for (int i = 0; i < n; i++) {
////             int attr = a.getIndex(i);
////             if(attr == com.android.internal.R.styleable.KeyboardView_keyBackground){
////                 mKeyBackground = a.getDrawable(attr);
////             }
////         }
////        mKeyBackground = super.
//
////         mKeyBackground.getPadding(mPadding);
//
////         a.recycle();
//
//    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: THERE IS LOL");
        super.onDraw(canvas);
        
        Paint paint = new Paint();
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(mKeyTextSize);
//        paint.setColor(Color.LTGRAY);

        List<Keyboard.Key> keys = getKeyboard().getKeys();
//        for(Keyboard.Key key: keys) {
//            if(key.label != null) {
//                if (key.label.equals("q")) {
//                    canvas.drawText("1", key.x + (key.width - 25), key.y + 40, paint);
//                }
//                CodePref.drawCode(canvas, key);
//                paint.setColor( Color.CYAN );
//                canvas.drawText("2", key.x + (key.width)/2 , key.y + (key.height)/2 + 30, paint);
//            }
//        }

//        doBuffer(canvas, keys);
    }

    @SuppressLint("ResourceAsColor")
    public void doBuffer(Canvas mCanvas, List<Keyboard.Key> keyss){


        mCanvas.save();
        final Canvas canvas = mCanvas;
//        canvas.clipRect(mDirtyRect);

        final Paint paint = new Paint();
        final Drawable keyBackground = mKeyBackground;
//        final Rect clipRegion = mClipRegion;
        int padleft = super.getPaddingLeft();
        int padright = super.getPaddingRight();
        int padtop = super.getPaddingTop();
        int padbottom = super.getPaddingBottom();
//        final Rect padding = mPadding;
//        final int kbdPaddingLeft = mPaddingLeft;
//        final int kbdPaddingTop = mPaddingTop;
//        final Keyboard.Key[] keys = mKeys;
        final Keyboard.Key[] keys = keyss.toArray(new Keyboard.Key[keyss.size()]);
//        final Keyboard.Key invalidKey = mInvalidatedKey;

        paint.setColor(mKeyTextDefaultColor);
        boolean drawSingleKey = false;
//        if (invalidKey != null && canvas.getClipBounds(clipRegion)) {
//            // Is clipRegion completely contained within the invalidated key?
//            if (invalidKey.x + kbdPaddingLeft - 1 <= clipRegion.left &&
//                    invalidKey.y + kbdPaddingTop - 1 <= clipRegion.top &&
//                    invalidKey.x + invalidKey.width + kbdPaddingLeft + 1 >= clipRegion.right &&
//                    invalidKey.y + invalidKey.height + kbdPaddingTop + 1 >= clipRegion.bottom) {
//                drawSingleKey = true;
//            }
//        }
//        canvas.drawColor(0x00000000, PorterDuff.Mode.CLEAR);
        final int keyCount = keys.length;
        for (int i = 0; i < keyCount; i++) {
            final Keyboard.Key key = keys[i];
//            if (drawSingleKey && invalidKey != key) {
//                continue;
//            }
            int[] drawableState = key.getCurrentDrawableState();
//            keyBackground.setState(drawableState);
//
            // Switch the character to uppercase if shift is pressed
            String label = key.label == null? null : (key.label).toString();

//            final Rect bounds = keyBackground.getBounds();
//            if (key.width != bounds.right ||
//                    key.height != bounds.bottom) {
//                keyBackground.setBounds(0, 0, key.width, key.height);
//            }

//            canvas.translate(key.x + super.getLeftPaddingOffset(), key.y + super.getTopPaddingOffset());
            canvas.translate(key.x + 0, key.y + 0);
//            keyBackground.draw(canvas);

            if (label != null) {
                // For characters, use large font. For labels like "Done", use small font.
                if (label.length() > 1 && key.codes.length < 2) {
                    paint.setTextSize(mLabelTextSize);
                    paint.setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    paint.setTextSize(mKeyTextSize);
                    paint.setTypeface(Typeface.DEFAULT);
                }
                // Draw a drop shadow for the text
                paint.setShadowLayer(mShadowRadius, 0, 0, mShadowColor);
                // Draw the text
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setColor(Color.CYAN);
                canvas.drawText(label,
                        (key.width - padleft - padright) / 2
                                + padleft,
                        (key.height - padtop - padbottom) / 2
                                + (paint.getTextSize() - paint.descent()) / 2 + padtop,
                        paint);
                // Turn off drop shadow
                paint.setShadowLayer(0, 0, 0, 0);
            } else if (key.icon != null) {
                final int drawableX = (key.width - padleft - padright
                        - key.icon.getIntrinsicWidth()) / 2 + padleft;
                final int drawableY = (key.height - padtop - padbottom
                        - key.icon.getIntrinsicHeight()) / 2 + padtop;
                canvas.translate(drawableX, drawableY);
                key.icon.setBounds(0, 0,
                        key.icon.getIntrinsicWidth(), key.icon.getIntrinsicHeight());
                key.icon.draw(canvas);
                canvas.translate(-drawableX, -drawableY);
            }
//            canvas.translate(-key.x - kbdPaddingLeft, -key.y - kbdPaddingTop);
            canvas.translate(-key.x - 0, -key.y - 0);
        }
//        mInvalidatedKey = null;
        // Overlay a dark rectangle to dim the keyboard
//        if (mMiniKeyboardOnScreen) {
//            paint.setColor((int) (mBackgroundDimAmount * 0xFF) << 24);
//            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
//        }

//        if (DEBUG && mShowTouchPoints) {
//            paint.setAlpha(128);
//            paint.setColor(0xFFFF0000);
//            canvas.drawCircle(mStartX, mStartY, 3, paint);
//            canvas.drawLine(mStartX, mStartY, mLastX, mLastY, paint);
//            paint.setColor(0xFF0000FF);
//            canvas.drawCircle(mLastX, mLastY, 3, paint);
//            paint.setColor(0xFF00FF00);
//            canvas.drawCircle((mStartX + mLastX) / 2, (mStartY + mLastY) / 2, 2, paint);
//        }
        mCanvas.restore();
//        mDrawPending = false;
//        mDirtyRect.setEmpty();

    }



}



