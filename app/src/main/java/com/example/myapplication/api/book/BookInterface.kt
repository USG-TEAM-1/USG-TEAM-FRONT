package com.example.myapplication.api.book

import com.example.myapplication.data.BookDetailResponse
import com.example.myapplication.data.BookItem
import com.example.myapplication.data.Root
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
        @Header("Authorization") accessToken: String,
        @Path("page") page: Int
    )

    @GET("api/book/{bookId}")
    fun getBookItem(
        @Path("bookId") bookId: Int
    ): Call<BookDetailResponse>

    @Multipart
    @POST("upload")
    fun uploadBook(
        @Part("bookName") bookName: RequestBody,
        @Part("bookComment") bookComment: RequestBody,
        @Part("bookPostName") bookPostName: RequestBody,
        @Part("bookPrice") bookPrice: RequestBody,
        @Part("isbn") isbn: RequestBody,
        @Part("bookRealPrice") bookRealPrice: RequestBody,
        @Part("author") author: RequestBody,
        @Part("publisher") publisher: RequestBody,
        @Part images: List<MultipartBody.Part>
    ): Call<ResponseBody>
}