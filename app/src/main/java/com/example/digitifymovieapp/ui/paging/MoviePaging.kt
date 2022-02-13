package com.example.digitifymovieapp.ui.paging


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.digitifymovieapp.model.Result
import com.example.digitifymovieapp.remoteData.ApiEndPoint
import com.example.digitifymovieapp.utils.Constants

class MoviePaging(private val s: String, private val movieInterface: ApiEndPoint) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1

        return try {

            val data = movieInterface.getMoviesSearch(s, "en-US", false, Constants.TOKEN)
            Log.d("TAG", "load: ${data.body()}")
            LoadResult.Page(
                data = data.body()?.results!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if ( data.body()?.results?.isEmpty()!!) null else page + 1
            )


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }


    }
}