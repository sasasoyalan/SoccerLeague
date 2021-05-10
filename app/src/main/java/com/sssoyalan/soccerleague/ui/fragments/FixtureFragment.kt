package com.sssoyalan.soccerleague.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.adapters.FixtureAdapter
import com.sssoyalan.soccerleague.adapters.ViewPagerAdapter
import com.sssoyalan.soccerleague.models.FixtureModel
import com.sssoyalan.soccerleague.models.MatchModel
import com.sssoyalan.soccerleague.ui.TeamsViewModel
import com.sssoyalan.soccerleague.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_fixture.*


class FixtureFragment : Fragment(R.layout.fragment_fixture) {

    private lateinit var viewModel: TeamsViewModel
    private var teams: MutableList<String>? = null
    private var weeks : ArrayList<FixtureModel> = ArrayList()
    lateinit var fixtureAdapter: FixtureAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        fixtureAdapter = FixtureAdapter()

        paginationProgressBarFixture.visibility=View.VISIBLE
        val team_list = arrayListOf<String>()

        val viewPager2: ViewPager2 = view.findViewById(R.id.viewPager2_)
        val list: MutableList<String> = ArrayList()

        viewModel.getAllTeams().observe(viewLifecycleOwner, Observer {teams ->
            for (team in teams) {
                team_list.add(team.Team)
            }

            for(week in 1 until (team_list.size-1)*2+1 step 1)
            {
                list.add("Week $week")
            }

            goFixtureFirstHalf(team_list)
            goFixtureSecondHalf(team_list)
            val marginPageTransformer = MarginPageTransformer(50)
            viewPager2.setPageTransformer(CompositePageTransformer().also {
                it.addTransformer(marginPageTransformer)
                it.addTransformer(ViewPager2PageTransformation())
            })
            viewPager2.adapter = ViewPagerAdapter(context!!, list, viewPager2,weeks,fixtureAdapter)
            paginationProgressBarFixture.visibility=View.INVISIBLE
        })

    }

    class ViewPager2PageTransformation : ViewPager2.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            val absPos = Math.abs(position)
            page.apply {
                translationY = absPos * 500f
                translationX = absPos * 500f
                scaleX = 1f
                scaleY = 1f
            }
            when {
                position < -1 ->
                    page.alpha = 0.1f
                position <= 1 -> {
                    page.alpha = Math.max(0.2f, 1 - Math.abs(position))
                }
                else -> page.alpha = 0.1f
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun goFixtureFirstHalf(team_list: ArrayList<String>) {
        val numTeams = team_list.size
        if (numTeams % 2 != 0) {
            team_list.add("Week Off")
        }
        val numDays = numTeams - 1
        val halfSize = numTeams / 2

        teams = team_list.toMutableList()
        teams?.removeAt(0)

        val teamsSize: Int = teams!!.size


        for (day in 0 until numDays step 1){
            val matches : ArrayList<MatchModel> = ArrayList()
            val week : FixtureModel = FixtureModel()
            val teamIdx = day % teamsSize
            val match : MatchModel = MatchModel()
            match.matchWeek=day+1
            match.homeTeam=teams?.get(teamIdx)
            match.awayTeam=team_list[0]
            matches.add(match)

            for (idx in 1 until halfSize step 1){
                val firstTeam : Int = (day + idx) % teamsSize
                val secondTeam : Int = (day  + teamsSize - idx) % teamsSize
                val match_ : MatchModel = MatchModel()
                match_.matchWeek=day+1
                match_.homeTeam=teams?.get(firstTeam)
                match_.awayTeam=teams?.get(secondTeam)
                matches.add(match_)
            }
            week.match_model=matches
            week.week= day+1
            weeks.add(week)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun goFixtureSecondHalf(team_list: ArrayList<String>) {
        val numTeams = team_list.size
        if (numTeams % 2 != 0) {
            team_list.add("Week Off")
        }
        val numDays = numTeams - 1
        val halfSize = numTeams / 2

        teams = team_list.toMutableList()
        teams?.removeAt(0)

        val teamsSize: Int = teams!!.size


        for (day in 0 until numDays step 1){
            val matches : ArrayList<MatchModel> = ArrayList()
            val week : FixtureModel = FixtureModel()
            val teamIdx = day % teamsSize
            val match : MatchModel = MatchModel()
            match.matchWeek=day+1
            match.awayTeam=teams?.get(teamIdx)
            match.homeTeam=team_list[0]
            matches.add(match)

            for (idx in 1 until halfSize step 1){
                val firstTeam : Int = (day + idx) % teamsSize
                val secondTeam : Int = (day  + teamsSize - idx) % teamsSize
                val match_ : MatchModel = MatchModel()
                match_.matchWeek=day+1
                match_.awayTeam=teams?.get(firstTeam)
                match_.homeTeam=teams?.get(secondTeam)
                matches.add(match_)
            }
            week.match_model=matches
            week.week= day+1
            weeks.add(week)
        }
    }
}

