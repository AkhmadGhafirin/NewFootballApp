package com.cascer.newfootballapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.getTestObserver
import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LeagueViewModelTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var leagueViewModel: LeagueViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getLeagues() {
        doNothing().`when`(leagueViewModel).requestLeagues()
        val data: LiveData<List<LeagueEntity>> = MutableLiveData()
        `when`(leagueViewModel.getLeagues()).thenReturn(data)
        val result = leagueViewModel.getLeagues()
        verify(leagueViewModel).getLeagues()
        Assert.assertEquals(
            data.getTestObserver().observedValues,
            result.getTestObserver().observedValues
        )
    }
}