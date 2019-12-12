package com.cascer.newfootballapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.response.league.League
import com.cascer.newfootballapp.data.response.league.LeagueResponse
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LeagueRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var leagueRepository: LeagueRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestLeaguesFromService() {
        val leagues: MutableList<League> = mutableListOf()
        val data = LeagueResponse(leagues)
        `when`(apiService.getLeagues()).thenReturn(Observable.just(data))
        val leaguesResponse = apiService.getLeagues()
        verify(apiService).getLeagues()
        assertNotNull(leaguesResponse)
        assertEquals(leagues, leaguesResponse)
    }

    @Test
    fun getLeaguesFromDB() {
        val data: LiveData<List<LeagueEntity>> = MutableLiveData()
        `when`(leagueRepository.getLeaguesFromDB()).thenReturn(data)
        verify(leagueRepository).getLeaguesFromDB()
    }
}