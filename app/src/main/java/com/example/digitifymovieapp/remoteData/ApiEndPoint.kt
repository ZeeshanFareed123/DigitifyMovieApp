package com.example.digitifymovieapp.remoteData

import com.example.digitifymovieapp.model.DetailMovieResponse
import com.example.digitifymovieapp.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("search/movie")
    suspend fun getMoviesSearch(
        @Query("query") query: String,
        @Query("language") lang: String,
        @Query("include_adult") adult: Boolean,
        @Query("api_key") apiKey: String,
       // @Query("page") page: Int,
    ): Response<SearchResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id : String,
        @Query("api_key") apiKey: String
    ): Response<DetailMovieResponse>




}