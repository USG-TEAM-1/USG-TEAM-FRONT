package com.example.myapplication.api.book

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.BookDetailResponse
import com.example.myapplication.data.BookItem
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

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

    fun registerBook(bookName: String, bookComment: String, author: String, bookPostName: String, bookPrice: Int, isbn: String, bookRealPrice: Int, publisher: String, imageUris: List<Uri> ) {
        val bookNamePart = RequestBody.create("text/plain".toMediaTypeOrNull(), bookName)
        val bookCommentPart = RequestBody.create("text/plain".toMediaTypeOrNull(), bookComment)
        val authorPart = RequestBody.create("text/plain".toMediaTypeOrNull(), author)
        val bookPostNamePart = RequestBody.create("text/plain".toMediaTypeOrNull(), bookPostName)
        val bookPricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), bookPrice.toString())
        val isbnPart = RequestBody.create("text/plain".toMediaTypeOrNull(), isbn)
        val bookRealPricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), bookRealPrice.toString())
        val publisherPart = RequestBody.create("text/plain".toMediaTypeOrNull(), publisher)

        val imageParts: MutableList<MultipartBody.Part> = mutableListOf()
        for(uri in imageUris) {
            val file = File(uri.path) // Uri에서 File을 얻습니다.
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file.readBytes())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)
            imageParts.add(imagePart)
        }

        apiService.uploadBook(bookNamePart, bookCommentPart, authorPart, bookPostNamePart, bookPricePart, isbnPart, bookRealPricePart, publisherPart, imageParts)
            .enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.d("registerBook", "성공")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // 실패 처리
                    Log.d("registerBook", "실패")
                }
            })
    }


}