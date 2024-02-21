package com.example.myapplication.api.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.BookItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApi {
    val apiService: BookInterface by lazy {
        RetrofitObj.retrofit.build().create(BookInterface::class.java)
    }
    fun getBookDetail(bookid: Int): LiveData<BookItem> {
        val data = MutableLiveData<BookItem>()
        apiService.getBookItem(bookid).enqueue(object : Callback<BookItem> {
            override fun onResponse(call: Call<BookItem>, response: Response<BookItem>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<BookItem>, t: Throwable) {
                Log.d("error", "getBookDetail")
            }
        })
        return data
    }
}