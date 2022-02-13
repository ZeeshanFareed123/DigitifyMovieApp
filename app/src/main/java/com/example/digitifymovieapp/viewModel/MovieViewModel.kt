package com.example.digitifymovieapp.viewModel


import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.digitifymovieapp.remoteData.ApiEndPoint
import com.example.digitifymovieapp.model.MovieDetails
import com.example.digitifymovieapp.repo.MovieDetailsRepository
import com.example.digitifymovieapp.ui.paging.MoviePaging
import com.example.digitifymovieapp.utils.Events
import com.example.digitifymovieapp.utils.Result
import com.example.digitifymovieapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieInterface: ApiEndPoint,
    private val repository: MovieDetailsRepository
) : ViewModel() {


    private val query = MutableLiveData<String>()


    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MoviePaging(query, movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<Result<MovieDetails>>>()
    val movieDetails: LiveData<Events<Result<MovieDetails>>> = _movieDetails


    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(Result(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))

    }


}