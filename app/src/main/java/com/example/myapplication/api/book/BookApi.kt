package com.example.myapplication.api.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.BookDetailResponse
import com.example.myapplication.data.BookItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookApi {
    val apiService: BookInterface by lazy {
        RetrofitObj.retrofit.build().create(BookInterface::class.java)
    }
    fun getBookDetail(bookid: Int): LiveData<BookDetailResponse> {
        val data = MutableLiveData<BookDetailResponse>()
        apiService.getBookItem(bookid).enqueue(object : Callback<BookDetailResponse> {
            override fun onResponse(call: Call<BookDetailResponse>, response: Response<BookDetailResponse>) {
                data.value = response.body()
                Log.d("bookDetail", response.body().toString())
            }

            override fun onFailure(call: Call<BookDetailResponse>, t: Throwable) {
                Log.d("getBookDetail", "error"+t.message)
            }
        })
        return data
    }
}