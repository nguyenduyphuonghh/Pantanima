package com.example.pantanima.ui.activities

import android.os.Bundle
import com.example.pantanima.R
import com.example.pantanima.ui.database.repository.impl.NounRepoImpl
import com.example.pantanima.ui.database.preference.PrefConstants
import com.example.pantanima.ui.database.preference.Preferences
import com.example.pantanima.ui.database.repository.impl.GroupRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : NavActivity() {

    private val nounRepo: NounRepoImpl by inject()
    private val groupRepoImpl: GroupRepoImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        setupUI()
        setupData()
    }

    private fun setupUI() {
        supportActionBar?.hide()
    }


    private fun setupData() {
        val isFirstRunning = Preferences.getBoolean(PrefConstants.FIRST_RUNNING, true)
        if (isFirstRunning) {
            compositeJob.add(GlobalScope.launch(Dispatchers.IO) {
                nounRepo.insertInitialNouns()
                groupRepoImpl.insertInitialGroups()
                Preferences.save(PrefConstants.FIRST_RUNNING, false)
            })
        }
    }
}
