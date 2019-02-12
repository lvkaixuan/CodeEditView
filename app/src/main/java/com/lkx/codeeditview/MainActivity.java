package com.lkx.codeeditview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.lkx.library.CodeEditView;

/**
 * 作者: LKX
 * 时间: 2018/9/3
 * 描述: 验证码输入框  https://github.com/lvkaixuan
 */
public class MainActivity extends AppCompatActivity {

    private CodeEditView mCodeEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        init();
    }

    private void init() {
        mCodeEditView = (CodeEditView) findViewById(R.id.codeEditView);
        final TextView textView = (TextView) findViewById(R.id.textview);
        mCodeEditView.setOnInputEndCallBack(new CodeEditView.inputEndListener() {
            @Override
            public void input(String text) { //输入完毕后的回调
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(String text) { //输入内容改变后的回调
                textView.setText(text);
            }
        });
    }

    //清除文字
    public void clear(View view) {
        mCodeEditView.clearText();
    }
}
