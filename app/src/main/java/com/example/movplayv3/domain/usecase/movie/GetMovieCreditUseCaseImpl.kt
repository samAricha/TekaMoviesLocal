package com.example.movplayv3.domain.usecase.movie

import com.example.movplayv3.data.model.Credits
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.remote.api.ApiResponse
import com.example.movplayv3.data.remote.api.awaitApiResponse
import com.example.movplayv3.data.repository.movie.MovieRepository
import com.example.movplayv3.domain.usecase.interfaces.movie.GetMovieCreditUseCase
import javax.inject.Inject

class GetMovieCreditUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetMovieCreditUseCase {
    override suspend fun invoke(
        movieId: Int,
        deviceLanguage: DeviceLanguage
    ): ApiResponse<Credits> {
        return movieRepository.movieCredits(
            movieId = movieId,
            isoCode = deviceLanguage.languageCode
        ).awaitApiResponse()
    }

}