package com.fakhrirasyids.beritain.ui.main.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrirasyids.beritain.databinding.FragmentSearchBinding
import com.fakhrirasyids.beritain.ui.detail.DetailActivity
import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.ui.NewsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            newsAdapter = NewsAdapter()

            newsAdapter.onItemClick = { news ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, news)
                intent.putExtra(DetailActivity.EXTRA_SEARCH, true)
                startActivity(intent)
            }

            observeError()
            observeLoading()
            setUpSearchView()

            with(binding.rvNews) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }

    private fun setUpSearchView() {
        with(binding) {
            svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    searchNews(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun searchNews(query: String) {
        searchViewModel.searchNews(query).observe(viewLifecycleOwner) { news ->
            if (news != null) {
                when (news) {
                    is Resource.Loading -> {
                        searchViewModel.isLoading.value = true
                        searchViewModel.isError.value = false
                    }
                    is Resource.Success -> {
                        searchViewModel.isLoading.value = false
                        searchViewModel.isError.value = false
                    }
                    is Resource.Error -> {
                        searchViewModel.isLoading.value = false
                        searchViewModel.isError.value = true
                    }
                }
            }
        }
    }

    private fun observeLoading() {
        searchViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                dummySearchText(false)
            }
            showLoading(it)
        }
    }

    private fun observeError() {
        searchViewModel.isError.observe(viewLifecycleOwner) {
            dummySearchText(false)
            showError(it)
            if (!it) {
                searchedNewsObserver()
            }
        }
    }

    private fun searchedNewsObserver() {
        searchViewModel.searchedNews.observe(viewLifecycleOwner) { news ->
            if (news != null) {
                newsAdapter.setData(news.data)
            }
        }
    }

    private fun showError(isError: Boolean) {
        if (isError) {
            binding.tvError.visibility = View.VISIBLE
            binding.rvNews.visibility = View.GONE
        } else {
            binding.tvError.visibility = View.GONE
            binding.rvNews.visibility = View.VISIBLE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun dummySearchText(isEmpty: Boolean) {
        binding.dummySearch.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }
}