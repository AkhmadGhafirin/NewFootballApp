package com.cascer.newfootballapp.di

import com.cascer.newfootballapp.data.repository.LeagueRepository
import com.cascer.newfootballapp.data.repository.MatchRepository
import com.cascer.newfootballapp.viewmodel.LeagueViewModel
import com.cascer.newfootballapp.viewmodel.MatchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    single { LeagueRepository(androidApplication(), get(), get()) }
    single { MatchRepository(androidApplication(), get(), get()) }
    viewModel { LeagueViewModel(get()) }
    viewModel { MatchViewModel(get()) }
}