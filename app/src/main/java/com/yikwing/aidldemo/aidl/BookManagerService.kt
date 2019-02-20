package com.yikwing.aidldemo.aidl

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast


/**
 * @author   yikwing
 * @date   2019/2/20 3:26 PM
 * 温馨提示
 * 代码千万行
 * 注释第一行
 * 命名不规范
 * 同事两行泪
 */
class BookManagerService : Service() {


    private val TAG = "BookManagerService"

    private val mHandler = Handler()

    private val mBookList = ArrayList<Book>()


    override fun onBind(intent: Intent?): IBinder? {
        return mIBinder
    }

    private val mIBinder = object : IBookManager.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun addBook(book: Book) {
            mBookList.add(book)
            val bookCount = mBookList.size
            mHandler.post {
                Toast.makeText(
                    this@BookManagerService,
                    String.format("添加了一本新书, 现在有%d本", bookCount), Toast.LENGTH_SHORT
                ).show()
            }
            Log.d(TAG, String.format("添加了一本新书, 现在有%d本", bookCount))
            Log.d(TAG, "currentThread = " + Thread.currentThread().name)
        }

        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

    }

}