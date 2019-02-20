package com.yikwing.aidldemo.aidl

import android.os.Parcel
import android.os.Parcelable

/**
 * @author   yikwing
 * @date   2019/2/20 3:13 PM
 * 温馨提示
 * 代码千万行
 * 注释第一行
 * 命名不规范
 * 同事两行泪
 */
data class Book(var bookId: Int, var bookName: String) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(bookId)
        writeString(bookName)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Book> = object : Parcelable.Creator<Book> {
            override fun createFromParcel(source: Parcel): Book =
                Book(source)
            override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
        }
    }
}