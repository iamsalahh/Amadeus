package com.salah.amadeus.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.salah.amadeus.R
import com.salah.amadeus.databinding.ActivityMainBinding
import com.salah.amadeus.ui.adapter.CitiesPagingAdapter
import com.salah.amadeus.utils.DataHandler.ERROR
import com.salah.amadeus.utils.DataHandler.LOADING
import com.salah.amadeus.utils.DataHandler.SUCCESS
import com.salah.amadeus.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var citiesPagingAdapter: CitiesPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCitiesListFromRemoteSource()
        setUpRecyclerview()
        observeCitiesFromLocalDb()
        searchViewListener()
        observeState();
    }

    private fun setUpRecyclerview() {
        citiesPagingAdapter = CitiesPagingAdapter()
        binding.rvCitiesList.layoutManager = LinearLayoutManager(this)
        binding.rvCitiesList.adapter = citiesPagingAdapter
        binding.rvCitiesList.itemAnimator = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.mode_switch)
        if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES)
            item?.title = getString(R.string.light_mode)
        else
            item?.title = getString(R.string.dark_mode)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mode_switch -> {
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        item.title = getString(R.string.dark_mode)
                    }
                    Configuration.UI_MODE_NIGHT_NO -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        item.title = getString(R.string.light_mode)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeState() {
        viewModel.state.observe(this) { dataHandler ->
            when (dataHandler) {
                is SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                }
                is ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
                is LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
            }

        }
    }

    private fun searchViewListener() {
        binding.searchCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchCityInDb(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchCityInDb(it) }
                return false
            }

        })
    }

    private fun observeCitiesFromLocalDb() {
        viewModel.offlineCitiesList.observe(this) {
            lifecycleScope.launch {
                citiesPagingAdapter.submitData(it)
            }
        }
    }
}