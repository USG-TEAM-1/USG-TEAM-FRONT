package com.example.myapplication.api

import com.example.myapplication.data.BookItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookInterface {
    @GET("api/book/&page={page}")
    fun getBookList(
        @Path("page") page: Int
    )

    @GET("api/book/{bookId}")
    fun getBookItem(
        @Path("bookId") bookId: Int
    ): Call<BookItem>
}