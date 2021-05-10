package com.sssoyalan.soccerleague.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.adapters.TableAdapter
import com.sssoyalan.soccerleague.ui.TeamsViewModel
import com.sssoyalan.soccerleague.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_table.*


class TableFragment : Fragment(R.layout.fragment_table) {

    lateinit var viewModel: TeamsViewModel
    lateinit var tableAdapter: TableAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        tableAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("team", it)
            }
            findNavController().navigate(
                R.id.action_tableFragment_to_teamDetailFragment,
                bundle
            )
        }
        viewModel.getAllTeams().observe(viewLifecycleOwner, Observer { teams ->

            tableAdapter.differ.submitList(teams)
        })
        hideProgressBar()
    }

    private fun setupRecyclerView() {
        tableAdapter = TableAdapter()
        rvTable.apply {
            adapter = tableAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBarTable.visibility = View.INVISIBLE
    }
}