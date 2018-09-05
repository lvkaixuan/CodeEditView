package com.lkx.codeeditview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lkx.library.CodeEditView;

public class MainActivity extends AppCompatActivity {

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
        CodeEditView codeEditView = (CodeEditView) findViewById(R.id.codeEditView);
        final TextView textView = (TextView) findViewById(R.id.textview);
        codeEditView.setOnInputEndCallBack(new CodeEditView.inputEndListener() {
            @Override
            public void input(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(String text) {
                textView.setText(text);
            }
        });
    }
}
