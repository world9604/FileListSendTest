package com.taein.filelistsendtest.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PostingService {

    @Multipart
    @POST("v3/campus_talk/posting/upload/img")
    fun uploadPostingImage(
        @Header("X-Token") token: String?,
        @Part file: List<MultipartBody.Part?>?
    ): Call<List<String>?>

}