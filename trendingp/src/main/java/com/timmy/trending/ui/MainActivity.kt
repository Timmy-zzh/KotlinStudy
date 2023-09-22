package com.timmy.trending.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.timmy.trending.data.entity.RepoList
import com.timmy.trending.databinding.ActivityMainBinding
import com.timmy.trending.ui.adapter.RepoAdapter
import com.timmy.trending.ui.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RepoAdapter
    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadRepos()
        initObserve()

    }

    private fun initObserve() {
        viewModel.repos.observe(this) {
            display(it)
        }
    }

    private fun display(it: RepoList) {
        adapter = RepoAdapter(it)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}