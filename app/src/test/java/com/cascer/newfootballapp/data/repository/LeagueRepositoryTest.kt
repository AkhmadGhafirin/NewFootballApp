package com.cascer.newfootballapp.data.repository

import com.cascer.newfootballapp.data.RxSchedulerRule
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.response.league.LeagueResponse
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LeagueRepositoryTest {

    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestLeaguesFromService() {
        val data = LeagueResponse(mutableListOf())
        `when`(apiService.getLeagues()).thenReturn(Observable.just(data))
        val leaguesResponse = apiService.getLeagues()
        verify(apiService).getLeagues()
        assertNotNull(leaguesResponse)
        apiService.getLeagues().test().assertValue(data).dispose()
    }
}