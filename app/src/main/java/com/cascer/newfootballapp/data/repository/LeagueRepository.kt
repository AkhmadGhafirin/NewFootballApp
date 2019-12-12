package com.cascer.newfootballapp.data.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.cascer.newfootballapp.data.network.ApiService
import com.cascer.newfootballapp.data.network.ErrorData
import com.cascer.newfootballapp.data.response.league.LeagueResponse
import com.cascer.newfootballapp.db.FootBallDao
import com.cascer.newfootballapp.db.entity.LeagueEntity
import com.cascer.newfootballapp.utils.ApiObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LeagueRepository(
    private val context: Context, private val dao: FootBallDao, private val apiService: ApiService
) {

    private val compositeDisposable = CompositeDisposable()

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

    fun getLeaguesFromDB(): LiveData<List<LeagueEntity>> = dao.getLeagues()
}