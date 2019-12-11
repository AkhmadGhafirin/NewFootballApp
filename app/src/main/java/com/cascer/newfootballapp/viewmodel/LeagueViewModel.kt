package com.cascer.newfootballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cascer.newfootballapp.data.repository.LeagueRepository
import com.cascer.newfootballapp.db.entity.LeagueEntity

class LeagueViewModel(private val repository: LeagueRepository) : ViewModel() {

    fun saveLeaguesToDB() {
        repository.requestLeaguesFromService()
    }

    fun getLeagues(): LiveData<List<LeagueEntity>> = repository.getLeaguesFromDB()
}