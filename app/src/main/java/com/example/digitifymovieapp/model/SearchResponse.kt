package com.example.digitifymovieapp.model

data class SearchResponse(
    var page: Int? = null,
    var results: List<Result>,
    var totalPages: Int? = null,
    var totalResults: Int? = null
)

data class Result (
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: ArrayList<Int> = arrayListOf(),
    var id: Int? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Int? = null,
    var voteCount: Int? = null
)


