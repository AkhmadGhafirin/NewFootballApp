package com.cascer.newfootballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LeagueViewModelTest : KoinTest {

    @Mock
    private lateinit var leagueViewModel: LeagueViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun saveLeaguesToDB() {
        doNothing().`when`(leagueViewModel).saveLeaguesToDB()
        leagueViewModel.saveLeaguesToDB()
        verify(leagueViewModel).saveLeaguesToDB()
    }

    @Test
    fun getLeagues() {
        val data: LiveData<List<LeagueEntity>> = MutableLiveData()
        `when`(leagueViewModel.getLeagues()).thenReturn(data)
        leagueViewModel.getLeagues()
        verify(leagueViewModel).getLeagues()
    }
}