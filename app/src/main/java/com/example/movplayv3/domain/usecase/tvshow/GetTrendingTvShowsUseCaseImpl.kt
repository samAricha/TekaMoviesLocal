package com.example.movplayv3.domain.usecase.tvshow

import androidx.paging.PagingData
import com.example.movplayv3.data.model.DeviceLanguage
import com.example.movplayv3.data.model.Presentable
import com.example.movplayv3.domain.usecase.interfaces.tvshow.GetTrendingTvShowsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTrendingTvShowsUseCaseImpl @Inject constructor() : GetTrendingTvShowsUseCase {
    override fun invoke(deviceLanguage: DeviceLanguage): Flow<PagingData<Presentable>> {
        TODO("Not yet implemented")
    }
}