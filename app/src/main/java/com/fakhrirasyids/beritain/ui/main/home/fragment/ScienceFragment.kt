package com.fakhrirasyids.beritain.ui.main.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrirasyids.beritain.databinding.FragmentScienceBinding
import com.fakhrirasyids.beritain.ui.detail.DetailActivity
import com.fakhrirasyids.beritain.ui.main.home.HomeViewModel
import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.ui.NewsAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class ScienceFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentScienceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScienceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            newsAdapter = NewsAdapter()

            newsAdapter.onItemClick = { news ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, news)
                startActivity(intent)
            }

            scienceNewsObserver()

            with(binding.rvCategoryNews) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }

    private fun scienceNewsObserver() {
        homeViewModel.scienceNews.observe(viewLifecycleOwner) { news ->
            if (news != null) {
                when (news) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        newsAdapter.setData(news.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }
}