package com.mobikasa.androidassignment.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mobikasa.androidassignment.R
import com.mobikasa.androidassignment.adapters.BookMarksAdapter
import com.mobikasa.androidassignment.databinding.ActivityBookmarksListBinding
import com.mobikasa.androidassignment.viewmodels.DataViewModel

class BookmarksList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding = ActivityBookmarksListBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.bookmarks)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val viewModel by viewModels<DataViewModel>()
        viewModel.fetchAllData()
        viewModel.mData1.observe(this, {
            if (it.isNullOrEmpty()) {
                mBinding.noDataFound.visibility = View.VISIBLE
                mBinding.mRecyclerBookMark.visibility = View.GONE
            } else {
                mBinding.noDataFound.visibility = View.GONE
                mBinding.mRecyclerBookMark.visibility = View.VISIBLE
                mBinding.mRecyclerBookMark.adapter = BookMarksAdapter(it)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return false
        }
        return super.onOptionsItemSelected(item)
    }
}