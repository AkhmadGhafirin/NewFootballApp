package com.cascer.newfootballapp.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.network.ErrorData
import com.cascer.newfootballapp.data.response.league.LeagueResponse
import com.cascer.newfootballapp.data.response.standings.Standings
import com.cascer.newfootballapp.data.response.standings.StandingsResponse
import com.cascer.newfootballapp.data.response.team.Team
import com.cascer.newfootballapp.data.response.team.TeamResponse
import com.cascer.newfootballapp.db.FootBallDao
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.db.entity.TeamEntity
import com.cascer.newfootballapp.utils.ApiObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueRepository(
    private val context: Context, private val dao: FootBallDao, private val apiService: ApiService
) {

    private val compositeDisposable = CompositeDisposable()

    val notFoundStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun requestLeaguesFromService() {
        apiService.getLeagues()
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<LeagueResponse>(compositeDisposable) {
                override fun onSuccess(data: LeagueResponse) {
                    data.leagues?.forEach {
                        if (it.strSport == "Soccer") {
                            requestDataLeaguesFromService(it.idLeague ?: "")
                        }
                    }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun requestDataLeaguesFromService(idLeague: String) {
        apiService.getDataLeague(idLeague)
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<LeagueResponse>(compositeDisposable) {
                override fun onSuccess(data: LeagueResponse) {
                    val league = data.leagues?.get(0)
                    dao.insertLeague(
                        LeagueEntity(
                            league?.idLeague ?: "", league?.strLeague,
                            league?.strDescriptionEN, league?.strBadge
                        )
                    )
                }

                override fun onError(e: ErrorData) {
//                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun requestTeamsFromService(idLeague: String) {
        apiService.getTeams(idLeague)
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<TeamResponse>(compositeDisposable) {
                override fun onSuccess(data: TeamResponse) {
                    data.teams?.forEach { insertTeamToDB(it) }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getTeamsFromDB(idLeague: String): LiveData<List<TeamEntity>> {
        return dao.getTeams(idLeague)
    }

    fun searchTeamFromDB(query: String): LiveData<List<TeamEntity>> = dao.searchTeam("%$query%")

    fun requestSearchTeamFromService(query: String): LiveData<Boolean> {
        apiService.searchTeam(query)
            .doOnSubscribe { GlobalScope.launch(Dispatchers.Main) { notFoundStatus.value = false } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<TeamResponse>(compositeDisposable) {
                override fun onSuccess(data: TeamResponse) {
                    if (data.teams.isNullOrEmpty()) notFoundStatus.value = true
                    else data.teams.forEach {
                        if (it.strSport == "Soccer") insertTeamToDB(it)
                        else notFoundStatus.value = true
                    }
                }

                override fun onError(e: ErrorData) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            })
        return notFoundStatus
    }

    private fun insertTeamToDB(team: Team) {
        GlobalScope.launch {
            dao.insertTeam(
                TeamEntity(
                    idTeam = team.idTeam,
                    idLeague = team.idLeague,
                    strTeam = team.strTeam,
                    strAlternate = team.strAlternate,
                    strDescription = team.strDescriptionEN,
                    strLeague = team.strLeague,
                    strStadium = team.strStadium,
                    strTeamBadge = team.strTeamBadge
                )
            )
        }
    }

    fun requestStandingsFromService(idLeague: String): LiveData<List<Standings>> {
        val result: MutableLiveData<List<Standings>> = MutableLiveData()
        apiService.getStandings(idLeague)
            .subscribeOn(Schedulers.io())
            .subscribe(object : ApiObserver<StandingsResponse>(compositeDisposable) {
                override fun onSuccess(data: StandingsResponse) {
                    result.postValue(data.table)
                }

                override fun onError(e: ErrorData) {
                    result.postValue(null)
                }
            })
        return result
    }

    fun getLeaguesFromDB(): LiveData<List<LeagueEntity>> = dao.getLeagues()
}