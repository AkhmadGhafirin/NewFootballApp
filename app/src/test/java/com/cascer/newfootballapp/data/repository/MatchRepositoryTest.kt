package com.cascer.newfootballapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.data.RxSchedulerRule
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.response.match.MatchResponse
import com.cascer.newfootballapp.data.response.match.SearchMatchResponse
import com.cascer.newfootballapp.getTestObserver
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchRepositoryTest {

    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var matchRepository: MatchRepository

    private val idLeague = "4328"

    private val query = "Liverpool"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun requestNextMatchFromService() {
        val data = MatchResponse(mutableListOf())
        `when`(apiService.getNextEvent(idLeague)).thenReturn(Observable.just(data))
        val matchResponse = apiService.getNextEvent(idLeague)
        verify(apiService).getNextEvent(idLeague)
        assertNotNull(matchResponse)
        apiService.getNextEvent(idLeague).test().assertValue(data).dispose()
    }

    @Test
    fun requestPastMatchFromService() {
        val data = MatchResponse(mutableListOf())
        `when`(apiService.getPastEvent(idLeague)).thenReturn(Observable.just(data))
        val matchResponse = apiService.getPastEvent(idLeague)
        verify(apiService).getPastEvent(idLeague)
        assertNotNull(matchResponse)
        apiService.getPastEvent(idLeague).test().assertValue(data).dispose()
    }

    @Test
    fun requestSearchMatchFromService() {
        val data = SearchMatchResponse(mutableListOf())
        `when`(apiService.searchMatch(query)).thenReturn(Observable.just(data))
        val matchResponse = apiService.searchMatch(query)
        verify(apiService).searchMatch(query)
        assertNotNull(matchResponse)
        apiService.searchMatch(query).test().assertValue(data).dispose()
    }

    @Test
    fun updateFavoriteMatch() {
        matchRepository.requestNextMatchFromService(idLeague)
        val observerState = MutableLiveData<Boolean>().apply { postValue(true) }
        matchRepository.updateFavoriteMatch(true, "602346")
        verify(matchRepository).updateFavoriteMatch(true, "602346")
        `when`(matchRepository.getFavoriteState("602346")).thenReturn(observerState)
        val result = matchRepository.getFavoriteState("602346")
        verify(matchRepository).getFavoriteState("602346")
        assertNotNull(result)
        assertEquals(true, result.getTestObserver().observedValues[0])
    }
}