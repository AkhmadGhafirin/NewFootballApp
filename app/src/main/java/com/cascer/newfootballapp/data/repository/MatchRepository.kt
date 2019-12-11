package com.cascer.newfootballapp.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.network.ErrorData
import com.cascer.newfootballapp.data.response.match.Match
import com.cascer.newfootballapp.data.response.match.MatchResponse
import com.cascer.newfootballapp.data.response.match.SearchMatchResponse
import com.cascer.newfootballapp.db.FootBallDao
import com.cascer.newfootballapp.db.entity.MatchEntity
import com.cascer.newfootballapp.utils.ApiObserver
import com.cascer.newfootballapp.utils.NEXT_MATCH
import com.cascer.newfootballapp.utils.PAST_MATCH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchRepository(
    private val context: Context,
    private val dao: FootBallDao,
    private val apiService: ApiService
) {

    private val compositeDisposable = CompositeDisposable()

    val notFoundStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun requestNextMatchFromService(idLeague: String) {
        apiService.getNextEvent(idLeague)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<MatchResponse>(compositeDisposable) {
                override fun onSuccess(data: MatchResponse) {
                    data.matches?.forEach { insertMatchToDB(it, NEXT_MATCH) }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun requestPastMatchFromService(idLeague: String) {
        apiService.getPastEvent(idLeague)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<MatchResponse>(compositeDisposable) {
                override fun onSuccess(data: MatchResponse) {
                    data.matches?.forEach { insertMatchToDB(it, PAST_MATCH) }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun requestSearchMatchFromService(query: String): LiveData<Boolean> {
        apiService.searchMatch(query)
            .doOnSubscribe { GlobalScope.launch(Dispatchers.Main) { notFoundStatus.value = false } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<SearchMatchResponse>(compositeDisposable) {
                override fun onSuccess(data: SearchMatchResponse) {
                    if (data.matches.isNullOrEmpty()) notFoundStatus.value = true
                    else data.matches.forEach { insertMatchToDB(it, "-") }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
        return notFoundStatus
    }

    fun insertMatchToDB(match: Match, eventType: String) {
        GlobalScope.launch {
            dao.insertMatch(
                MatchEntity(
                    idEvent = match.idEvent,
                    idLeague = match.idLeague ?: "-",
                    schedule = match.dateEvent ?: "-",
                    strEvent = match.strEvent ?: "-",
                    strSport = match.strSport ?: "-",
                    strHomeTeam = match.strHomeTeam ?: "-",
                    strAwayTeam = match.strAwayTeam ?: "-",
                    idHomeTeam = match.idHomeTeam ?: "-",
                    idAwayTeam = match.idAwayTeam ?: "-",
                    strLeague = match.strLeague ?: "-",
                    strSeason = match.strSeason ?: "-",
                    intHomeScore = match.intHomeScore ?: "-",
                    intAwayScore = match.intAwayScore ?: "-",
                    strHomeGoalDetails = match.strHomeGoalDetails ?: "-",
                    strAwayGoalDetails = match.strAwayGoalDetails ?: "-",
                    strHomeRedCards = match.strHomeRedCards ?: "-",
                    strAwayRedCards = match.strAwayRedCards ?: "-",
                    strHomeYellowCards = match.strHomeYellowCards ?: "-",
                    strAwayYellowCards = match.strAwayYellowCards ?: "-",
                    intHomeShots = match.intHomeShots ?: "-",
                    intAwayShots = match.intAwayShots ?: "-",
                    strHomeLineupGoalkeeper = match.strHomeLineupGoalkeeper ?: "-",
                    strAwayLineupGoalkeeper = match.strAwayLineupGoalkeeper ?: "-",
                    strHomeLineupDefense = match.strHomeLineupDefense ?: "-",
                    strAwayLineupDefense = match.strAwayLineupDefense ?: "-",
                    strHomeLineupMidfield = match.strHomeLineupMidfield ?: "-",
                    strAwayLineupMidfield = match.strAwayLineupMidfield ?: "-",
                    strHomeLineupForward = match.strHomeLineupForward ?: "-",
                    strAwayLineupForward = match.strAwayLineupForward ?: "-",
                    strHomeLineupSubstitutes = match.strHomeLineupSubstitutes ?: "-",
                    strAwayLineupSubstitutes = match.strAwayLineupSubstitutes ?: "-",
                    strHomeFormation = match.strHomeFormation ?: "-",
                    strAwayFormation = match.strAwayFormation ?: "-",
                    strTime = match.strTime ?: "-",
                    dateEvent = match.dateEvent ?: "-",
                    strHomeTeamBadge = getBadgeTeam(match.idHomeTeam.toString()),
                    strAwayTeamBadge = getBadgeTeam(match.idAwayTeam.toString()),
                    isFavorite = false,
                    eventType = eventType
                )
            )
        }
    }

    private suspend fun getBadgeTeam(idTeam: String): String =
        apiService.getTeam(idTeam).teams?.get(0)?.strTeamBadge.toString()

    fun getMatchFromDB(idLeague: String, eventType: String): LiveData<List<MatchEntity>> =
        dao.getMatch(idLeague, eventType)

    fun searchMatchFromDB(query: String): LiveData<List<MatchEntity>> = dao.searchMatch("%$query%")

    fun getFavoriteMatch(): LiveData<List<MatchEntity>> = dao.getFavoriteMatch()

    fun updateFavoriteMatch(favorite: Boolean, idEvent: String) {
        GlobalScope.launch { dao.updateFavoriteMatch(favorite, idEvent) }
    }

    fun getFavoriteState(idEvent: String): LiveData<Boolean> = dao.getFavoriteState(idEvent)
}