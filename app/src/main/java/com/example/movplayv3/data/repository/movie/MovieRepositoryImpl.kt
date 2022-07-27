package com.example.movplayv3.data.repository.movie

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movplayv3.data.local.db.AppDatabase
import com.example.movplayv3.data.model.*
import com.example.movplayv3.data.model.movie.Movie
import com.example.movplayv3.data.model.movie.MovieDetails
import com.example.movplayv3.data.model.movie.MovieEntity
import com.example.movplayv3.data.model.movie.MovieEntityType
import com.example.movplayv3.data.paging.movie.DiscoverMoviesPagingDataSource
import com.example.movplayv3.data.paging.movie.MoviesRemotePagingMediator
import com.example.movplayv3.data.remote.api.movie.TmdbMoviesApiHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiMovieHelper: TmdbMoviesApiHelper,
    private val appDatabase: AppDatabase
) : MovieRepository {
    override fun discoverMovies(
        deviceLanguage: DeviceLanguage,
        sortType: SortType,
        sortOrder: SortOrder,
        genresParam: GenresParam,
        watchProvidersParam: WatchProvidersParam,
        voteRange: ClosedFloatingPointRange<Float>,
        onlyWithPosters: Boolean,
        onlyWithScore: Boolean,
        onlyWithOverview: Boolean,
        releaseDateRange: DateRange
    ): Flow<PagingData<Movie>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        DiscoverMoviesPagingDataSource(
            apiMovieHelper = apiMovieHelper,
            deviceLanguage = deviceLanguage,
            sortType = sortType,
            sortOrder = sortOrder,
            genresParam = genresParam,
            watchProvidersParam = watchProvidersParam,
            voteRange = voteRange,
            onlyWithPosters = onlyWithPosters,
            onlyWithScore = onlyWithScore,
            onlyWithOverview = onlyWithOverview,
            releaseDateRange = releaseDateRange
        )
    }.flow.flowOn(defaultDispatcher)

    override fun popularMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiMovieHelper = apiMovieHelper,
                appDatabase = appDatabase,
                type = MovieEntityType.Popular
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Popular,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun upcomingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                deviceLanguage = deviceLanguage,
                apiMovieHelper = apiMovieHelper,
                appDatabase = appDatabase,
                type = MovieEntityType.Upcoming
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Upcoming,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun trendingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                apiMovieHelper = apiMovieHelper,
                deviceLanguage = deviceLanguage,
                appDatabase = appDatabase,
                type = MovieEntityType.Trending
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.Trending,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)

    override fun topRatedMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> =
        Pager(
            PagingConfig(pageSize = 20),
            remoteMediator = MoviesRemotePagingMediator(
                apiMovieHelper = apiMovieHelper,
                deviceLanguage = deviceLanguage,
                appDatabase = appDatabase,
                type = MovieEntityType.TopRated
            ),
            pagingSourceFactory = {
                appDatabase.moviesDao().getAllMovies(
                    type = MovieEntityType.TopRated,
                    language = deviceLanguage.languageCode
                )
            }
        ).flow.flowOn(defaultDispatcher)


    override fun nowPlayingMovies(deviceLanguage: DeviceLanguage): Flow<PagingData<MovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun similarMovies(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        TODO("Not yet implemented")
    }

    override fun moviesRecommendation(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        TODO("Not yet implemented")
    }

    override fun movieDetails(movieId: Int, isoCode: String): Call<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun movieCredits(movieId: Int, isoCode: String): Call<Credits> {
        TODO("Not yet implemented")
    }

    override fun movieImages(movieId: Int): Call<ImagesResponse> {
        TODO("Not yet implemented")
    }

    override fun movieReviews(movieId: Int): Flow<PagingData<Review>> {
        TODO("Not yet implemented")
    }

    override fun movieReview(movieId: Int): Call<ReviewsResponse> {
        TODO("Not yet implemented")
    }

    override fun collection(collectionId: Int, isoCode: String): Call<CollectionResponse> {
        TODO("Not yet implemented")
    }

    override fun watchProviders(movieId: Int): Call<WatchProvidersResponse> {
        TODO("Not yet implemented")
    }

    override fun getExternalIds(movieId: Int): Call<ExternalIds> {
        TODO("Not yet implemented")
    }

    override fun getMovieVideos(movieId: Int, isoCode: String): Call<VideosResponse> {
        TODO("Not yet implemented")
    }

    override fun moviesOfDirector(
        directorId: Int,
        deviceLanguage: DeviceLanguage
    ): Flow<PagingData<Movie>> {
        TODO("Not yet implemented")
    }
}