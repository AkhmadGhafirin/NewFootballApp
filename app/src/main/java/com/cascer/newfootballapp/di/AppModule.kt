package com.cascer.newfootballapp.di

import com.cascer.newfootballapp.db.FootBallDB
import com.cascer.newfootballapp.db.FootBallDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { FootBallDB.getDatabase(androidContext()) }
    single { provideMovieDao(get()) }
}

fun provideMovieDao(footBallDB: FootBallDB): FootBallDao = footBallDB.dao()