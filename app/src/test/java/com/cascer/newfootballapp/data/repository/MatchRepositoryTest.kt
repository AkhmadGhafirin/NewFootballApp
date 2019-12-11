package com.cascer.newfootballapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.response.match.Match
import com.cascer.newfootballapp.data.response.match.MatchResponse
import com.cascer.newfootballapp.data.response.match.SearchMatchResponse
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.utils.PAST_MATCH
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchRepositoryTest {

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
        val match: MutableList<Match> = mutableListOf()
        val data = MatchResponse(match)
        `when`(apiService.getNextEvent(idLeague)).thenReturn(Observable.just(data))
        apiService.getNextEvent(idLeague)
        verify(apiService).getNextEvent(idLeague)
    }

    @Test
    fun requestPastMatchFromService() {
        val match: MutableList<Match> = mutableListOf()
        val data = MatchResponse(match)
        `when`(apiService.getPastEvent(idLeague)).thenReturn(Observable.just(data))
        apiService.getPastEvent(idLeague)
        verify(apiService).getPastEvent(idLeague)
    }

    @Test
    fun requestSearchMatchFromService() {
        val match: MutableList<Match> = mutableListOf()
        val data = SearchMatchResponse(match)
        `when`(apiService.searchMatch(query)).thenReturn(Observable.just(data))
        apiService.searchMatch(query)
        verify(apiService).searchMatch(query)
    }

    @Test
    fun searchMatchFromDB() {
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchRepository.searchMatchFromDB(query)).thenReturn(data)
        matchRepository.searchMatchFromDB(query)
        verify(matchRepository).searchMatchFromDB(query)
    }

    @Test
    fun getMatchFromDB() {
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchRepository.getMatchFromDB(idLeague, PAST_MATCH)).thenReturn(data)
        matchRepository.getMatchFromDB(idLeague, PAST_MATCH)
        verify(matchRepository).getMatchFromDB(idLeague, PAST_MATCH)
    }

    @Test
    fun getFavoriteMatch() {
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchRepository.getFavoriteMatch()).thenReturn(data)
        matchRepository.getFavoriteMatch()
        verify(matchRepository).getFavoriteMatch()
    }
}