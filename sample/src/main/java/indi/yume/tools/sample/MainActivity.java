package indi.yume.tools.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import indi.yume.tools.avocado.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.m("");
    }
}
