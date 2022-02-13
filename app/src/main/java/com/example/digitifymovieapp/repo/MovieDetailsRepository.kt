package com.example.digitifymovieapp.repo

import com.example.digitifymovieapp.model.DetailMovieResponse
import com.example.digitifymovieapp.remoteData.ApiEndPoint
import com.example.digitifymovieapp.utils.Constants
import com.example.digitifymovieapp.utils.Status
import com.example.digitifymovieapp.utils.Result

class MovieDetailsRepository(private val movieInterface: ApiEndPoint) {

    suspend fun getMovieDetails(imdbId: String): Result<DetailMovieResponse> {

        return try {
            val response = movieInterface.getMovieDetails(imdbId, Constants.TOKEN)
            if (response.isSuccessful) {
                Result(Status.SUCCESS, response.body(), null)
            } else {
                Result(Status.ERROR, null, null)
            }

        } catch (e: Exception) {
            Result(Status.ERROR, null, null)
        }

    }

}