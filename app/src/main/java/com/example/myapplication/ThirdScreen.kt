package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityThirdScreenBinding
import com.example.myapplication.response.UserItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdScreen : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var binding: ActivityThirdScreenBinding

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            finish()
        }

        viewModel = ViewModelProvider(this, ViewModelFactoryUser.getInstance(this))[ThirdScreenViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        adapter = UserAdapter()

        binding.rvUser.adapter = adapter

        getData()

        binding.swipeRefresh.setOnRefreshListener{
            getData()
            binding.swipeRefresh.isRefreshing = true
            adapter.refresh()
        }
    }

    private fun showListUser(state: Boolean){
        if (state){
            binding.swipeRefresh.visibility = View.VISIBLE
            binding.noDataText.visibility = View.GONE
        }else{
            binding.swipeRefresh.visibility = View.GONE
            binding.noDataText.visibility = View.VISIBLE
        }
    }

    private fun getData(){
        viewModel.getPaging.observe(this){pagingData ->
            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest {
                    showListUser(it.refresh !is LoadState.Error)
                }
            }

            binding.swipeRefresh.isRefreshing = false

            if (pagingData != null){
                setUser(pagingData)
            }
        }
    }

    private fun setUser(user: PagingData<UserItem>){
        adapter.submitData(lifecycle, user)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserItem) {
                setResult(Activity.RESULT_OK, intent.putExtra(THIRD_INTENT_KEY, "${data.firstName} ${data.lastName}"))
                finish()
            }
        })
    }

    companion object {
        const val THIRD_INTENT_KEY = "THIRD SCREEN KEY "
    }
}