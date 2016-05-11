package indi.yume.tools.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import indi.yume.tools.avocado.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.m("");

        TextView textView = (TextView) findViewById(R.id.text_view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RangeMapCheck rangeMapCheck = new RangeMapCheck();
                rangeMapCheck.setUp();
                for(int i = 0; i < 10; i++) {
                    rangeMapCheck.testSum();
                    System.out.println("============================");
                }
            }
        });
    }
}
