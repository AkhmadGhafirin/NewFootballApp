package com.cascer.newfootballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.utils.PAST_MATCH
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchViewModelTest {

    @Mock
    private lateinit var matchViewModel: MatchViewModel

    private val idLeague = "4328"

    private val query = "Liverpool"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun updateFavoriteMatch() {
        val isFavorite = true
        val idEvent = "602278"
        doNothing().`when`(matchViewModel).updateFavoriteMatch(isFavorite, idEvent)
        matchViewModel.updateFavoriteMatch(isFavorite, idEvent)
        verify(matchViewModel).updateFavoriteMatch(isFavorite, idEvent)
    }

    @Test
    fun requestNextMatchFromService() {
        doNothing().`when`(matchViewModel).requestNextMatchFromService(idLeague)
        matchViewModel.requestNextMatchFromService(idLeague)
        verify(matchViewModel).requestNextMatchFromService(idLeague)
    }

    @Test
    fun requestPastMatchFromService() {
        doNothing().`when`(matchViewModel).requestPastMatchFromService(idLeague)
        matchViewModel.requestPastMatchFromService(idLeague)
        verify(matchViewModel).requestPastMatchFromService(idLeague)
    }

    @Test
    fun requestSearchMatchFromService() {
        val isFound: MutableLiveData<Boolean> = MutableLiveData()
        `when`(matchViewModel.requestSearchMatchFromService(query)).thenReturn(isFound)
        matchViewModel.requestSearchMatchFromService(query)
        verify(matchViewModel).requestSearchMatchFromService(query)
    }

    @Test
    fun getResultSearchMatchFromDB() {
        doNothing().`when`(matchViewModel).setQuerySearchMatch(query)
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchViewModel.getResultSearchMatchFromDB()).thenReturn(data)
        matchViewModel.getResultSearchMatchFromDB()
        verify(matchViewModel).getResultSearchMatchFromDB()
    }

    @Test
    fun getMatch() {
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchViewModel.getMatch(idLeague, PAST_MATCH)).thenReturn(data)
        matchViewModel.getMatch(idLeague, PAST_MATCH)
        verify(matchViewModel).getMatch(idLeague, PAST_MATCH)
    }

    @Test
    fun getFavoriteMatch() {
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchViewModel.getFavoriteMatch()).thenReturn(data)
        matchViewModel.getFavoriteMatch()
        verify(matchViewModel).getFavoriteMatch()
    }
}