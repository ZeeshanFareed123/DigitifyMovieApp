package com.example.digitifymovieapp.di

import com.example.digitifymovieapp.remoteData.ApiEndPoint
import com.example.digitifymovieapp.repo.MovieDetailsRepository
import com.example.digitifymovieapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {


    @Singleton
    @Provides
    fun provideRetrofitInterface(): ApiEndPoint {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiEndPoint::class.java)
    }

    @Provides
    fun provideRepository(movieInterface: ApiEndPoint): MovieDetailsRepository {
        return MovieDetailsRepository(movieInterface)
    }



}