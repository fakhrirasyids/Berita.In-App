package com.fakhrirasyids.beritain.di

import com.fakhrirasyids.beritain.ui.detail.DetailViewModel
import com.fakhrirasyids.beritain.ui.main.home.HomeViewModel
import com.fakhrirasyids.beritain.ui.main.search.SearchViewModel
import com.fakhrirasyids.core.domain.usecase.NewsInteractor
import com.fakhrirasyids.core.domain.usecase.NewsUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}