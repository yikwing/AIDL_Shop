package com.yikwing.aidlserver;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author yikwing
 * @date 2019/2/20 4:04 PM
 * 温馨提示
 * 代码千万行
 * 注释第一行
 * 命名不规范
 * 同事两行泪
 */
public class MainActivity extends AppCompatActivity implements AIDLService.OnLoginListener {


    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, mAIDLConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection mAIDLConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AIDLService.MyBinder binder = (AIDLService.MyBinder) service;
            AIDLService aidlService = binder.getService();
            aidlService.setOnLoginListener(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Handler mHandler = new Handler();

    @Override
    public void login(final String username, final String password) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTv.setText(username + ", " + password);
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, YiPay.class));
            }
        });
    }
}
