package com.fakhrirasyids.beritain.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fakhrirasyids.beritain.R
import com.fakhrirasyids.beritain.databinding.FragmentHomeBinding
import com.fakhrirasyids.beritain.ui.detail.DetailActivity
import com.fakhrirasyids.beritain.ui.main.home.fragment.*
import com.fakhrirasyids.beritain.ui.main.home.loadmore.LatestNewsActivity
import com.fakhrirasyids.core.data.Resource
import com.fakhrirasyids.core.ui.HeadlineNewsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var headlineNewsAdapter: HeadlineNewsAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnCheckedChangeListener()

        if (activity != null) {
            headlineNewsAdapter = HeadlineNewsAdapter()

            headlineNewsAdapter.onItemClick = { news ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, news)
                startActivity(intent)
            }

            headlineNewsAdapter.onLoadMoreClick = { listNews ->
                val intent = Intent(activity, LatestNewsActivity::class.java)
                intent.putParcelableArrayListExtra(
                    LatestNewsActivity.EXTRA_NEWS_LIST,
                    listNews
                )
                startActivity(intent)
            }

            latestNewsObserver()

            val horizontalLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            with(binding.rvHeadlineLatestNews) {
                layoutManager = horizontalLayoutManager
                setHasFixedSize(true)
                adapter = headlineNewsAdapter
            }
        }
    }

    private fun latestNewsObserver() {
        homeViewModel.latestNews.observe(viewLifecycleOwner) { news ->
            if (news != null) {

                when (news) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.containerHome.visibility = View.GONE
                        binding.tvError.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.containerHome.visibility = View.VISIBLE
                        binding.tvError.visibility = View.GONE
                        headlineNewsAdapter.setData(news.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.containerHome.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setOnCheckedChangeListener() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class SectionPagerAdapter(activity: HomeFragment) : FragmentStateAdapter(activity) {
        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = TechnologyFragment()
                1 -> fragment = SportsFragment()
                2 -> fragment = ScienceFragment()
                3 -> fragment = HealthFragment()
                4 -> fragment = BusinessFragment()
                5 -> fragment = EntertainmentFragment()
            }
            return fragment as Fragment
        }

        override fun getItemCount(): Int {
            return 6
        }
    }

    companion object {
        val TAB_TITLES = intArrayOf(
            R.string.technology,
            R.string.sports,
            R.string.science,
            R.string.health,
            R.string.business,
            R.string.entertainment
        )
    }
}