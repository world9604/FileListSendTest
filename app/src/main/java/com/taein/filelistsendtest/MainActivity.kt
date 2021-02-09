package com.taein.filelistsendtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.taein.filelistsendtest.remote.CampusRetrofitServiceFactory
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendFile()
    }

    fun sendFile() {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Im5hdmVyXzU5NDIzMjc2IiwiaWR4IjoyNzg5MSwibmlja25hbWUiOiLslbzsiJjsmKQiLCJlbWFpbCI6Indpbm5lcl93b3JsZEBuYXZlci5jb20iLCJ1cGRhdGVkX2F0IjoxNjAwNzQ5ODQ3MDAwLCJ0aW1lIjoxNjA5MjI0MzQwNzQ1LCJpYXQiOjE2MDkyMjQzNDAsImV4cCI6MTY5NTYyNDM0MH0.3sDgGt0Q5-nJJ6KBzDG57LL_xJIbkOjXNnOPFu8lgNk"

        val fileList: MutableList<File> = mutableListOf()
        val file1 = createTempFile("test1", ".jpg")
        val file2 = createTempFile("test2", ".jpg")
        val file3 = createTempFile("test3", ".jpg")
        fileList.add(file1)
        fileList.add(file2)
        fileList.add(file3)

        val images = ArrayList<MultipartBody.Part>()
        for (file in fileList) {
            val surveyBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            images.add(MultipartBody.Part.createFormData("file", file.name, surveyBody))
        }

        CampusRetrofitServiceFactory.create().uploadPostingImage(token, images)
            .enqueue(object : retrofit2.Callback<List<String>?> {
            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                Log.d("TEST", "onFailure()")
                Log.d("TEST", "onFailure2()")
            }

            override fun onResponse(call: Call<List<String>?>, response: Response<List<String>?>) {
                if (response.isSuccessful()) {
                    response.body()
                    Log.d("TEST", "onResponse()")
                }
                Log.d("TEST", "onResponse2()")
            }
        })
    }
}