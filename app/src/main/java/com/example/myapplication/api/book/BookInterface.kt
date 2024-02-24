package com.example.myapplication.api.book

import com.example.myapplication.data.BookDetailResponse
import com.example.myapplication.data.BookItem
import com.example.myapplication.data.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface BookInterface {

    @GET("search/openApi/search.do")
    suspend fun getBookIsbn(
        @Query("key") key: String,
        @Query("detailSearch") detailSearch: Boolean = true,
        @Query("isbnOp") isbnOp: String = "isbn",
        @Query("isbnCode") isbnCode: String
    ): Root

    @GET("api/book/&page={page}")
    fun getBookList(
        @Header("token") accessToken: String,
        @Path("page") page: Int
    )

    @GET("api/book/{bookId}")
    fun getBookItem(
        @Path("bookId") bookId: Int
    ): Call<BookDetailResponse>
}