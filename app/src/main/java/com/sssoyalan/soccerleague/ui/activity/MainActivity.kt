package com.sssoyalan.soccerleague.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sssoyalan.soccerleague.R
import com.sssoyalan.soccerleague.db.TeamDatabase
import com.sssoyalan.soccerleague.repository.TeamsRepository
import com.sssoyalan.soccerleague.ui.TeamsViewModel
import com.sssoyalan.soccerleague.ui.TeamsViewModelProviderFactory
import com.sssoyalan.soccerleague.util.Resource
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TeamsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamsRepository = TeamsRepository(TeamDatabase(this))
        val viewModelProviderFactory =
            TeamsViewModelProviderFactory(
                teamsRepository
            )
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(TeamsViewModel::class.java)
        bottomNavigationView.setupWithNavController(tableNavHostFragment.findNavController())

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val appSettingsPref = getSharedPreferences("appSettingPref",0)
        val isNightModeOn : Boolean = appSettingsPref.getBoolean("NightMode",false)
        val key = sharedPref.getInt("isItFirst", 0)

        if (isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        iBtnsettings.setOnClickListener {
            val nightModeOn : Boolean = appSettingsPref.getBoolean("NightMode",false)
            settingdialog(appSettingsPref, nightModeOn)
        }

        if (key != 1) {
            showProgressBar()
            viewModel.teams.observe(this, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { teamResponse ->
                            for (team in teamResponse[0].result) {
                                viewModel.saveResult(team)
                                with (sharedPref.edit()) {
                                    putInt("isItFirst", 1)
                                    apply()
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let {message ->
//                            Log.e(TAG,"An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun settingdialog(appSettingsPref: SharedPreferences, nightModeOn: Boolean) {
        val bottomSheetDialog =
            BottomSheetDialog(this@MainActivity, R.style.BottomSheetDialogTheme)
        val bottomSheetView: View =
            if (nightModeOn){
            LayoutInflater.from(applicationContext)
                .inflate(
                    R.layout.dialog_settings_dark,
                    findViewById<View>(R.id.bottomSheetContainer) as? LinearLayout
                )
            }else{
            LayoutInflater.from(applicationContext)
                .inflate(
                    R.layout.dialog_settings,
                    findViewById<View>(R.id.bottomSheetContainer) as? LinearLayout
                )
            }

        bottomSheetView.findViewById<View>(R.id.btn_dark_mode)
            .setOnClickListener {
                if (!nightModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    appSettingsPref.edit().putBoolean("NightMode",true).apply()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    appSettingsPref.edit().putBoolean("NightMode",false).apply()
                }
                bottomSheetDialog.dismiss()
            }
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    private fun hideProgressBar() {
        paginationProgressBarMain.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBarMain.visibility = View.VISIBLE
    }
}
