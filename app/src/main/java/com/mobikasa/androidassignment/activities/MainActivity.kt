package com.mobikasa.androidassignment.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.mobikasa.androidassignment.R
import com.mobikasa.androidassignment.Utils
import com.mobikasa.androidassignment.adapters.DataAdapter
import com.mobikasa.androidassignment.databinding.ActivityMainBinding
import com.mobikasa.androidassignment.db.BookMarkEntity
import com.mobikasa.androidassignment.models.RestaurantX
import com.mobikasa.androidassignment.viewmodels.DataViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val viewModel: DataViewModel by viewModels()
    private lateinit var mAdapter: DataAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mBookmark: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.rest)
        mAdapter = DataAdapter(::addDataToBookMark)
        binding.mRecycler.adapter = mAdapter

        lifecycleScope.launch {
            viewModel.getData("po").collectLatest {
                mAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest { loadState ->
                binding.mNoDataFound.isVisible =
                    loadState.refresh is LoadState.NotLoading && mAdapter.itemCount == 0
                binding.mProgress.isVisible = loadState.refresh is LoadState.Loading
                binding.mErrorText.isVisible = loadState.refresh is LoadState.Error
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateBookmarkIcon()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val menuItem = menu.findItem(R.id.search_bar)
        mBookmark = menu.findItem(R.id.book_mark)
        (menuItem.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    hitApiForNewKeywords(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.book_mark) {
            startActivity(Intent(this, BookmarksList::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addDataToBookMark(mData: RestaurantX) {
        viewModel.addDataIntoDatabase(
            BookMarkEntity(
                mData.id!!,
                mData.name,
                "${mData.currency} ${mData.averageCostForTwo}"
            )
        )
        updateBookmarkIcon()
        Snackbar.make(binding.root, "Added to BookMarks List", Snackbar.LENGTH_SHORT).show()
    }

    private fun hitApiForNewKeywords(keywords: String) {
        Utils.hideKeyboard(this)
        lifecycleScope.launch {
            viewModel.getData(keywords).collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun updateBookmarkIcon() {
        lifecycleScope.launch {
            delay(10)
            viewModel.fetchAllData()
            viewModel.mData1.observe(this@MainActivity, {
                if (it.isNotEmpty()) {
                    mBookmark.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_bookmarked)
                } else {
                    mBookmark.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_bookmark)
                }
            })
        }
    }
}