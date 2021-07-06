package com.example.txt_plgr;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;

import java.util.List;

public class CodeKeyboard extends Keyboard {
    public CodeKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId);
    }


}
