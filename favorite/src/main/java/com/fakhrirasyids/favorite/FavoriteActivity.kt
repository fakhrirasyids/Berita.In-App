package com.fakhrirasyids.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrirasyids.beritain.ui.detail.DetailActivity
import com.fakhrirasyids.core.ui.NewsAdapter
import com.fakhrirasyids.favorite.databinding.ActivityFavoriteBinding
import com.fakhrirasyids.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val newsAdapter = NewsAdapter()

        newsAdapter.onItemClick = { news ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, news)
            startActivity(intent)
        }

        favoriteViewModel.favoriteTourism.observe(this) { favoritedNewsList ->
            newsAdapter.setData(favoritedNewsList)
            binding.layoutEmpty.root.visibility =
                if (favoritedNewsList.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavoritedNews) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }
}