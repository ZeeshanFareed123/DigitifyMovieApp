package com.example.digitifymovieapp.remoteData

import com.example.digitifymovieapp.model.MovieDetails
//import com.example.digitifymovieapp.model.MovieResponse
import com.example.digitifymovieapp.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
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

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("api_key") apiKey: String
    ): Response<MovieDetails>




}