package com.sushildlh.mytasks.Network

import com.sushildlh.mytasks.Modal.TaskResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkApi {

    @GET("task")
    fun getData(): Observable<TaskResponse>

}