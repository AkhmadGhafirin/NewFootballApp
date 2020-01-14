package com.cascer.newfootballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cascer.newfootballapp.data.repository.LeagueRepository
import com.cascer.newfootballapp.data.response.standings.Standings
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.db.entity.TeamEntity

class LeagueViewModel(private val repository: LeagueRepository) : ViewModel() {

    private var searchResult: LiveData<List<TeamEntity>> = MutableLiveData()
    private val mQuery: MutableLiveData<String> = MutableLiveData()

    init {
        searchResult = Transformations.switchMap(mQuery) { repository.searchTeamFromDB(it) }
    }

    fun requestLeagues() {
        repository.requestLeaguesFromService()
    }

    fun requestStandings(idLeague: String): LiveData<List<Standings>> {
        return repository.requestStandingsFromService(idLeague)
    }

    fun requestTeamsFromService(idLeague: String) {
        repository.requestTeamsFromService(idLeague)
    }

    fun getLeagues(): LiveData<List<LeagueEntity>> = repository.getLeaguesFromDB()

    fun getTeams(leagueID: String): LiveData<List<TeamEntity>> = repository.getTeamsFromDB(leagueID)

    fun requestSearchTeamFromService(query: String): LiveData<Boolean> =
        repository.requestSearchTeamFromService(query)

    fun setQuerySearchTeam(query: String) {
        mQuery.value = query
    }

    fun getResultSearchTeamFromDB(): LiveData<List<TeamEntity>> = searchResult
}