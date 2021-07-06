package com.example.txt_plgr;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.view.inputmethod.InputConnection;
import android.text.TextUtils;
import android.inputmethodservice.InputMethodService;

import java.util.HashMap;


public interface CodePref {

    float mShadowRadius = 10;
    int mShadowColor = Color.BLACK;


    public static int colorOfCode(int code){
        switch(code){
            case 10001:
                return Integer.parseInt("@colors/driftwood");
            default:
                return Color.WHITE;
        }
    }

    @SuppressLint("ResourceAsColor")
    static void drawCode(Canvas canvas, Keyboard.Key key){
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(28);
        Rect padding = new Rect(0, 0, 0, 0);
//        if(10001 <= key.codes[0] && key.codes[0]  <= 10013 ){
//        if(true){
//            paint.setColor( Color.WHITE );
//            canvas.drawText("1", key.x + (key.width - 25), key.y + 40, paint);
//        }else{
//            // Draw a drop shadow for the text
//            paint.setShadowLayer(mShadowRadius, 0, 0, mShadowColor);
//            // Draw the text
//            String label = (String) key.label;
//            canvas.drawText(label,
//                    (key.width - padding.left - padding.right) / 2
//                            + padding.left,
//                    (key.height - padding.top - padding.bottom) / 2
//                            + (paint.getTextSize() - paint.descent()) / 2 + padding.top,
//                    paint);
//            // Turn off drop shadow
//            paint.setShadowLayer(0, 0, 0, 0);
//        }
    }


    static String codeType(int code){
        if( 10001 <=  code && code <= 10013)
            return "main";
        return "default";
    }

    static String onCheckTxt(int code){
        switch (code){
            case 10010:
                return " = 0;\n";
            case 10006:
                return " (n);\n";
            case 10007:
                return" (n, vector<ll>);\n";
            default:
                return " ;";
        }
    }


    static String onCheckNextKbd(int code){
//        inputConnection = getCurrentInputConnection();
        switch (code){
            case 10010:
//                inputConnection.commitText( " = 0;\n", 0);
                return "start";
            case 10006:
//                inputConnection.commitText( " (n);\n", 0);
                return "start";
            case 10007:
//                inputConnection.commitText(" (n, vector<ll>);\n", 0);
                return "start";
            default:
                return "start";
        }
    }

    public static String meanTxt(int code){
        switch (code){
            case 10001:
                return "forn( ,  )";
            case 10002:
                return "while(  )";
            case 10003:
                return "if(  ){\n   \n}";
            case 10004:
                return "if(  ){\n   \n}else{\n   \n}";
            case 10005:
                return "else{\n   \n}";
            case 10006:
                return "vector<ll> ";
            case 10007:
                return "vector<vector<ll>> ";
            case 10008:
                return "(n, vector<ll>(0))";
            case 10009:
                return " = 0;";
            case 10010:
                return "ll ";
            case 10011:
                return "ll a, ";
            case 10012:
                return "cin >> ";
            case 10013:
                return "cout << ";
            case 10014:
                return "#include <iostream>\n" +
                        "#include <vector>\n" +
                        "#include <map>\n" +
                        "#include <set>\n" +
                        "#include <queue>\n" +
                        "#include <utility>\n" +
                        "#include <algorithm>\n" +
                        "using namespace std;\n" +
                        "#define forn(i, n) for(ll i = 0; i < n; i++)\n" +
                        "#define all(x) x.begin(), x.end()\n" +
                        "#define f first\n" +
                        "#define s second\n" +
                        "typedef long long ll;\n" +
                        "typedef long double ld;\n\n\n" +
                        "int main(){\n" +
                        "   ios_base::sync_with_stdio(false);\n" +
                        "   cin.tie(0);\n\n\n}";
            case 10015:
                return "set <ll> ";
            case 10016:
                return "pair <ll, ll> ";
            case 10017:
                return "map <ll, ll> ";
            case 10018:
                return "queue <ll>";
            case 10019:
                return "all( ";
            case 10020:
                return "sort( ";
            case 10021:
                return "reverse( ";
            case 10022:
                return "upper_bound( ";
            case 10023:
                return "lower_bound( ";
            case 10024:
                return ".find( ";
            case 10025:
                return ".begin()";
            case 10026:
                return ".end()";
            case 10027:
                return ".upper_bound( ";
            case 10028:
                return ".lower_bound( ";
            case 10029:
                return ".f ";
            case 10030:
                return ".s ";
            case 10031:
                return "   ";
            case 10032:
                return ">> ";
            case 10033:
                return "<< ";
            case 10034:
                return "++;";
            case 10035:
                return "--;";
            case 10036:
                return "+= ";
            case 10037:
                return "-=";
            case 10038:
                return "*=";
            case 10039:
                return "/=";
            case 10040:
                return "%=";
            case 10041:
                return " \" \" ";
            case 10042:
                return " \"\n\" ";
            default:
                return "";
        }
    }

    public static int nextPos(int code){
        switch (code){
            case 10001:
                return 0;
            case 10002:
                return 6;
            case 10003:
                return 3;
            case 10004:
                return 3;
            case 10005:
                return 4;
            default:
                return 0;
        }
    }


}
