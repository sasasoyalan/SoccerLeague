package com.sssoyalan.soccerleague.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.adapters.TeamsAdapter
import com.sssoyalan.soccerleague.ui.TeamsViewModel
import com.sssoyalan.soccerleague.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_team_list.*

class TeamListFragment : Fragment(R.layout.fragment_team_list) {

    lateinit var viewModel: TeamsViewModel
    lateinit var teamsAdapter: TeamsAdapter

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        teamsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("team", it)
            }
            findNavController().navigate(
                R.id.action_teamListFragment_to_teamDetailFragment,
                bundle
            )
        }
        viewModel.getAllTeams().observe(viewLifecycleOwner, Observer { teams ->

            teamsAdapter.differ.submitList(teams)
        })
    }

    private fun setupRecyclerView() {
        teamsAdapter = TeamsAdapter()
        rvTeamList.apply {
            adapter = teamsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}