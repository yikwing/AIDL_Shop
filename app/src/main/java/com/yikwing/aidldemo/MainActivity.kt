package com.yikwing.aidldemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.yikwing.aidldemo.aidl.Book
import com.yikwing.aidldemo.aidl.IBookManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mIBookManager: IBookManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBinder.setOnClickListener {
            val intent = Intent()
            intent.action = "com.yikwing.aidldemo.aidl.BookManagerService"
            intent.run {
                setPackage(packageName)
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
        }

        onAdd.setOnClickListener {
            if (mIBookManager != null) {
                try {
                    mIBookManager?.addBook(Book(18, "漫画书"));
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }

    }


    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mIBookManager = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mIBookManager = IBookManager.Stub.asInterface(service)
            Toast.makeText(this@MainActivity, "绑定成功", Toast.LENGTH_SHORT).show()
        }

    }


}
