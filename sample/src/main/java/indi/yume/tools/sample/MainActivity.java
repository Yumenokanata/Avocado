package indi.yume.tools.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import indi.yume.tools.avocado.reflect.ReflectUtil;
import indi.yume.tools.avocado.util.LogUtil;
import indi.yume.tools.avocado.util.Timer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text_view)
    TextView textView;
    @Bind(R.id.java_button)
    Button javaButton;
    @Bind(R.id.kotlin_button)
    Button kotlinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LogUtil.m("");

        javaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RangeMapCheck rangeMapCheck = new RangeMapCheck();
                rangeMapCheck.setUp();
//                for (int i = 0; i < 10; i++) {
//                    rangeMapCheck.testSum();
//                    System.out.println("============================");
//                }
                rangeMapCheck.testSum();
            }
        });
        kotlinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RangeMapKotlinTest rangeMapKotlinTest = new RangeMapKotlinTest();
//                rangeMapKotlinTest.doOnBefore();
//                for (int i = 0; i < 1; i++) {
//                    rangeMapKotlinTest.testAll();
//                    System.out.println("============================");
//                }
                testModelCopy();
            }
        });
    }

    public void testModelCopy() {
        TestModel1 source = TestModel1.of(true, "Name1", "Name2", new TestSubModel("ModelName"), 1, 0.62f);
        TestModel2 target = new TestModel2();

        Timer timer = new Timer();
        timer.start();
        ReflectUtil.copyModel(source, target);
        timer.stopAndPrint();
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class TestModel1 {
        private Boolean isAutoLogin;
        private String name;
        private String name2;
        private TestSubModel name5;

        private int name6;
        private Float name7;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class TestModel2 {
        private boolean isAutoLogin;
        private String name;
        private String name2;
        private TestSubModel name5;

        private int name3;
        private Float name4;
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    public static class TestSubModel {
        private final String name;
    }
}
