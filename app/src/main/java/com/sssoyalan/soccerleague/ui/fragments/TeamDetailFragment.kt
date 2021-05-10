package com.sssoyalan.soccerleague.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.ui.activity.MainActivity
import com.sssoyalan.soccerleague.ui.TeamsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class TeamDetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var viewModel: TeamsViewModel
    val args : TeamDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val team = args.team
        tvTitle.text=team.Team
        tvFoundedAt.text=team.date
    }
}