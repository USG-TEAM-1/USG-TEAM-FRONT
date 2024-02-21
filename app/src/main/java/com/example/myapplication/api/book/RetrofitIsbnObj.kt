package com.example.myapplication.api.book

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitIsbnObj {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.nl.go.kr/NL/")
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        .build()

    val bookApi = retrofit.create(BookInterface::class.java)
}