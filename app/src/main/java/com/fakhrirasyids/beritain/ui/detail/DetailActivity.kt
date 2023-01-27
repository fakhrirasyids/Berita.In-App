package com.fakhrirasyids.beritain.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fakhrirasyids.beritain.databinding.ActivityDetailBinding
import com.fakhrirasyids.beritain.ui.main.home.loadmore.LatestNewsActivity
import com.fakhrirasyids.core.domain.model.News
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    private var isLatest: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)
        val isSearch = intent.getBooleanExtra(EXTRA_SEARCH, false)
        isLatest = intent.getBooleanExtra(EXTRA_LATEST, false)

        if (isSearch) {
            binding.fab.visibility = View.GONE
        }

        showDetailNews(detailNews)
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            supportActionBar?.title = detailNews.title
            val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputDate = SimpleDateFormat("h:mm a, d MMM")
            val d: Date? = inputDate.parse(detailNews.publishedAt.toString())
            val formatted: String = outputDate.format(d!!)
            binding.content.tvTime.text = formatted
            binding.content.tvDetailNews.text = detailNews.description
            Glide.with(this)
                .load(detailNews.urlToImage)
                .placeholder(com.fakhrirasyids.core.R.drawable.dummy_image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteTourism(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)

                if (isLatest) {
                    val intent = Intent()
                    intent.putExtra(LatestNewsActivity.EXTRA_TITLE, detailNews.title)
                    setResult(RESULT_OK, intent)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    com.fakhrirasyids.beritain.R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    com.fakhrirasyids.beritain.R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_SEARCH = "extra_search"
        const val EXTRA_LATEST = "extra_latest"
    }
}