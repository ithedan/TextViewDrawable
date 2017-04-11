package com.hedan.textviewdrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hedan.textdrawablelibrary.TextViewDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextViewDrawable tv4=(TextViewDrawable)findViewById(R.id.text4);
        tv4.setText("提供免费WIFI\n停车位收费标准：详情咨询商家");

    }
}
