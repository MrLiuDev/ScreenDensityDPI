package me.mrliu.screendensitydpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv1;
    private EditText etScreenSize;
    private Button btnGetScreenDpi;
    private TextView tv2;
    private EditText etWidth, etHeight, etSize;
    private Button btnGetDpi;
    private TextView tv3;

    private int width, height;
    private float density;
    private int densityDpi;
    private String screenSize;
    private String strWidth, strHeight, strSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
        getScreenInfo();
        tv1.append(densityDpi+"");

    }



    private void getScreenInfo() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;     // 屏幕宽度（像素）
        height = metrics.heightPixels;   // 屏幕高度（像素）
        density = metrics.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        densityDpi = metrics.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
    }

    private void setListeners() {
        btnGetScreenDpi.setOnClickListener(this);
        btnGetDpi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_screen_dpi:
                screenSize = etScreenSize.getText().toString();
                if (screenSize.equals("")) {
                    Toast.makeText(this, "输入屏幕尺寸", Toast.LENGTH_SHORT).show();
                    return;
                }
                calculateDpi(Float.parseFloat(screenSize));
                break;

            case R.id.btn_get_dpi:
                strWidth = etWidth.getText().toString();
                strHeight = etHeight.getText().toString();
                strSize = etSize.getText().toString();
                if (strWidth.equals("") | strHeight.equals("") | strSize.equals("")) {
                    Toast.makeText(this, "检查 宽度/高度/尺寸 是否输入正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                calculateDpi(Float.parseFloat(strWidth), Float.parseFloat(strHeight), Float.parseFloat(strSize));
                break;

        }
    }

    private void calculateDpi(float width, float height, float size) {
        float dpi = (float) (Math.sqrt(Math.pow(width, 2)+ Math.pow(height, 2)) / size);
        tv3.setText("DPI："+dpi);
    }

    private void calculateDpi(float size) {
        float dpi = (float) (Math.sqrt(Math.pow(width, 2)+ Math.pow(height, 2)) / size);
        tv2.setText("DPI："+dpi);
    }

    private void initViews() {
        tv1 = (TextView) findViewById(R.id.tv1);
        etScreenSize = (EditText) findViewById(R.id.et_screen_size);
        btnGetScreenDpi = (Button) findViewById(R.id.btn_get_screen_dpi);
        tv2 = (TextView) findViewById(R.id.tv2);
        etWidth = (EditText) findViewById(R.id.et_width);
        etHeight = (EditText) findViewById(R.id.et_height);
        etSize = (EditText) findViewById(R.id.et_size);
        btnGetDpi = (Button) findViewById(R.id.btn_get_dpi);
        tv3 = (TextView) findViewById(R.id.tv3);
    }
}
