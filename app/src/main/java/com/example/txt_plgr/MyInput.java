package com.example.txt_plgr;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MyInput extends InputMethodService implements KeyboardView.OnKeyboardActionListener {


    private CodeKeyboardView myKeyboardView;
    private CodeKeyboard myKeyboard;
    boolean needProcess = true;
    ArrayList<Integer> cntLastAdded = new ArrayList<Integer>();
    ArrayList<String> kbsOrder = new ArrayList<String>();
    ArrayList<Integer> keysOrder = new ArrayList<Integer>();
    ArrayList<String> states = new ArrayList<String>();

    @Override
    public View onCreateCandidatesView(){
        View objView = (View) getLayoutInflater().inflate(R.layout.candidates_pallete, null);
        return objView;
    }

    @Override
    public View onCreateInputView(){
        myKeyboardView = (CodeKeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        myKeyboard = new CodeKeyboard(this, R.xml.start_pad);
        myKeyboardView.setKeyboard(myKeyboard);
        myKeyboardView.setOnKeyboardActionListener(this);
        states.add(getString(R.string.st_def));
        kbsOrder.add("start");

        return myKeyboardView;
    }

    private CodeKeyboard getKeyboard(String kbtype){
        switch (kbtype){
            case "start":
                return new CodeKeyboard(this, R.xml.start_pad);
            case "ifes":
                return new CodeKeyboard(this, R.xml.buttons_pad);
            case "letters_en":
                return new CodeKeyboard(this, R.xml.letters_pad);
            case "letters_ru":
                return new CodeKeyboard(this, R.xml.letters_pad_ru);
            case "nums":
                return new CodeKeyboard(this, R.xml.number_pad);
            case "syb":
                return new CodeKeyboard(this, R.xml.symbols_pad);
            default:
                return new CodeKeyboard(this, R.xml.start_pad);
        }
    }




    private void changeKeyboard(String keyboardTypeTo){
        myKeyboard = (CodeKeyboard) getKeyboard(keyboardTypeTo);
        myKeyboardView.setKeyboard(myKeyboard);
        if(!keyboardTypeTo.equals("letters_en") && !keyboardTypeTo.equals("letters_ru"))
           kbsOrder.add(keyboardTypeTo);
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {
        needProcess = true;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        Log.d(TAG, "onKey: KEK " + primaryCode );
        if(inputConnection == null){
            return;
        }
        boolean flag = true;
        if(primaryCode == 50000){
            int codeOf = keysOrder.get(keysOrder.size()-1);
            String nxtKbd = CodePref.onCheckNextKbd(codeOf);
            changeKeyboard(nxtKbd);
            String out = CodePref.onCheckTxt(codeOf);
            inputConnection.commitText( out, out.length()+1);

        }else if(primaryCode > 20000){
            if(primaryCode == 20010)
                changeKeyboard("syb");
            if(primaryCode == 20011)
                changeKeyboard("start");
            if(primaryCode == 20012)
                changeKeyboard("letters_en");
            if(primaryCode == 20013)
                changeKeyboard("letters_ru");
            if(primaryCode == 20014)
                changeKeyboard("letters_en");
            if(primaryCode == 20015)
                changeKeyboard("start");
            if(primaryCode == 20016)
                changeKeyboard("nums");
            if(primaryCode == 20030)
                changeKeyboard("ifes");
        }else if(primaryCode > 10000){
            String txt = CodePref.meanTxt(primaryCode);
            inputConnection.commitText( txt, txt.length()+1 );
            cntLastAdded.add( CodePref.meanTxt(primaryCode).length() );
        }else{
            flag = false;
            switch (primaryCode){
                case -5:
                    CharSequence selectedText = inputConnection.getSelectedText(0);
                    if (TextUtils.isEmpty(selectedText)) {
//                        if(cntLastAdded.isEmpty())
                            inputConnection.deleteSurroundingText(1, 0);
//                        else{
//                            inputConnection.deleteSurroundingText( cntLastAdded.get( cntLastAdded.size()-1 ) , 0);
//                            cntLastAdded.remove( cntLastAdded.size()-1 );
//                        }

                    }else{
                        inputConnection.commitText("", 1);
                    }
                    break;
//                case 10001:
//                    inputConnection.commitText("forn", 4);
//                    break;
                default:
                    char code = (char) primaryCode;
                    inputConnection.commitText(String.valueOf(code), 1);
                    cntLastAdded.add(1);
            }
        }
        if(primaryCode == 10006 || primaryCode == 10007 || primaryCode == 10010){
            changeKeyboard("letters_en");
        }
        if(flag)
            keysOrder.add(primaryCode);

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {
        InputConnection inputConnection = getCurrentInputConnection();
        inputConnection.commitText( "", -1 );
    }

    @Override
    public void swipeRight() {
        InputConnection inputConnection = getCurrentInputConnection();
        inputConnection.commitText( "", 2 );
    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
