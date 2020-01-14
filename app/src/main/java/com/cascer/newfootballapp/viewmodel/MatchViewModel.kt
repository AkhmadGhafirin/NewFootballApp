package com.cascer.newfootballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cascer.newfootballapp.data.repository.MatchRepository
import com.cascer.newfootballapp.db.entity.MatchEntity

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    private var searchResult: LiveData<List<MatchEntity>> = MutableLiveData()
    private val mQuery: MutableLiveData<String> = MutableLiveData()

    init {
        searchResult = Transformations.switchMap(mQuery) { repository.searchMatchFromDB(it) }
    }

    fun updateFavoriteMatch(favorite: Boolean, idEvent: String) {
        repository.updateFavoriteMatch(favorite, idEvent)
    }

    fun requestNextMatchFromService(idLeague: String) {
        repository.requestNextMatchFromService(idLeague)
    }

    fun requestPastMatchFromService(idLeague: String) {
        repository.requestPastMatchFromService(idLeague)
    }

    fun requestSearchMatchFromService(query: String): LiveData<Boolean> =
        repository.requestSearchMatchFromService(query)


    fun getMatch(leagueID: String, eventType: String): LiveData<List<MatchEntity>> =
        repository.getMatchFromDB(leagueID, eventType)

    fun getFavoriteMatch(): LiveData<List<MatchEntity>> = repository.getFavoriteMatch()

    fun getFavoriteState(idEvent: String): LiveData<Boolean> = repository.getFavoriteState(idEvent)

    fun setQuerySearchMatch(query: String) {
        mQuery.value = query
    }

    fun getResultSearchMatchFromDB(): LiveData<List<MatchEntity>> = searchResult
}