package com.yikwing.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import com.yikwing.aidlserver.IMyAidlInterface;

/**
 * @author yikwing
 * @date 2019/2/20 4:29 PM
 * 温馨提示
 * 代码千万行
 * 注释第一行
 * 命名不规范
 * 同事两行泪
 */
public class MainActivity extends AppCompatActivity {


    private IMyAidlInterface mIMyAidlInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnCLick);


        Intent intent = new Intent();
        // 服务端AndroidManifest.xml文件该Service所配置的action
        intent.setAction("com.yikwing.aidlserver");
        // Service所在的包名
        intent.setPackage("com.yikwing.aidlserver");
        bindService(intent, ConnectCallBack, Context.BIND_AUTO_CREATE);


        btn.setOnClickListener(v -> {


            if (mIMyAidlInterface != null) {
                try {
                    mIMyAidlInterface.login("yikwing", "123456");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });

    }


    private ServiceConnection ConnectCallBack = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };


//    class ConnectCallBack implements ServiceConnection {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mIMyAidlInterface = null;
//        }
//    }
}
