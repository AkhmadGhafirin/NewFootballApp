package com.cascer.newfootballapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.getTestObserver
import com.cascer.newfootballapp.utils.PAST_MATCH
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var matchViewModel: MatchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getResultSearchMatchFromDB() {
        val query = "Liverpool"
        doNothing().`when`(matchViewModel).setQuerySearchMatch(query)
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchViewModel.getResultSearchMatchFromDB()).thenReturn(data)
        val result = matchViewModel.getResultSearchMatchFromDB()
        verify(matchViewModel).getResultSearchMatchFromDB()
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }

    @Test
    fun getMatch() {
        val idLeague = "4328"
        val data: LiveData<List<MatchEntity>> = MutableLiveData()
        `when`(matchViewModel.getMatch(idLeague, PAST_MATCH)).thenReturn(data)
        val result = matchViewModel.getMatch(idLeague, PAST_MATCH)
        verify(matchViewModel).getMatch(idLeague, PAST_MATCH)
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }
}