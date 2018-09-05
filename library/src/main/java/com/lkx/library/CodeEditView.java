package com.lkx.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 作者: LKX
 * 时间: 2018/9/3
 * 描述: 验证码输入框
 */

public class CodeEditView extends LinearLayout implements TextWatcher, View.OnClickListener {
    private static final int EditViewNum = 6; //默认输入框数量
    private ArrayList<TextView> mTextViewsList = new ArrayList<>(); //存储EditText对象
    private Context mContext;
    private EditText mEditText;
    private int borderSize = 35; //方格边框大小
    private int borderMargin = 10; //方格间距
    private int textSize = 8; //字体大小
    private int textColor = 0xff; //字体颜色
    private int inputTyte = InputType.TYPE_CLASS_NUMBER;
    private inputEndListener callBack;

    public CodeEditView(Context context) {
        super(context);
        init(context);
    }

    public CodeEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context,attrs);
        init(context);
    }

    public CodeEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context,attrs);
        init(context);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CodeEditView);
        borderSize = array.getInteger(R.styleable.CodeEditView_borderSize, 35);
        borderMargin = array.getInteger(R.styleable.CodeEditView_borderMargin, 10);
        textSize = array.getInteger(R.styleable.CodeEditView_textSize, 8);
        textColor = array.getColor(R.styleable.CodeEditView_textColor, Color.BLACK);
    }

    /**
     * 获取输入框内容
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    public void setOnInputEndCallBack(inputEndListener onInputEndCallBack) {
        callBack = onInputEndCallBack;
    }

    public interface inputEndListener{
        void input(String text);
        void afterTextChanged(String text);
    }

    private void init(Context context) {
        mContext = context;
        initEditText(context);
        //设置方格间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                DensityUtil.dip2px(mContext,borderSize), DensityUtil.dip2px(mContext,borderSize));
        params.setMargins(DensityUtil.dip2px(mContext,borderMargin),0,0,0);
        //设置方格文字
        for (int i = 0; i < EditViewNum; i++) {
            TextView textView = new TextView(mContext);
            textView.setBackgroundResource(R.drawable.shape_border_normal);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(DensityUtil.sp2px(mContext,textSize));
            textView.getPaint().setFakeBoldText(true);
            textView.setLayoutParams(params);
            textView.setInputType(inputTyte);
            textView.setTextColor(textColor);
            textView.setOnClickListener(this);
            mTextViewsList.add(textView);
            addView(textView);
        }
        //显示隐藏软键盘
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                mEditText.setHintTextColor(Color.parseColor("#ff0000"));
            }
        }, 500);
        //监听删除键
        mEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_DEL) {
                    if (mEditText.getText().length()>=mTextViewsList.size()) return false;
                    mTextViewsList.get(mEditText.getText().length()).setText("");
                }
                return false;
            }
        });
    }

    private void initEditText(Context context) {
        mEditText = new EditText(context);
        mEditText.setBackgroundColor(Color.parseColor("#00000000"));
        mEditText.setMaxLines(1);
        mEditText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(EditViewNum)});
        mEditText.addTextChangedListener(this);
        mEditText.setTextSize(0);
        addView(mEditText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void afterTextChanged(Editable s) {
        if (callBack!=null) {
            callBack.afterTextChanged(s.toString());
        }
        if (s.length() <= 1) {
            mTextViewsList.get(0).setText(s);
        } else {
            mTextViewsList.get(mEditText.getText().length() - 1).setText(s.subSequence(s.length() - 1, s.length()));
        }
        if (s.length()==EditViewNum) {
            if (callBack!=null) {
                callBack.input(mEditText.getText().toString());
            }
        }
    }

    @Override
    public void onClick(View v) { //TextView点击时获取焦点弹出输入法
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
