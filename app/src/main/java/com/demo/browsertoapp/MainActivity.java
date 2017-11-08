package com.demo.browsertoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button btnToSecond;
    private Button btnToThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
            ThirdActivity.actionStart(MainActivity.this, intent.getData(), false);
        }

        btnToSecond = (Button) findViewById(R.id.btn_to_second);
        btnToThird = (Button) findViewById(R.id.btn_to_third);
        btnToSecond.setOnClickListener(this);
        btnToThird.setOnClickListener(this);
    }

    public void clickMeToSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void clickMeToThird(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == btnToSecond) {
            SecondActivity.actionStart(MainActivity.this);
        } else if (v == btnToThird) {
            ThirdActivity.actionStart(MainActivity.this, null, false);
        }
    }

}
