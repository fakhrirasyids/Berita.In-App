package com.fakhrirasyids.beritain.ui.main.home.loadmore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhrirasyids.beritain.databinding.ActivityLatestNewsBinding
import com.fakhrirasyids.beritain.ui.detail.DetailActivity
import com.fakhrirasyids.core.domain.model.News
import com.fakhrirasyids.core.ui.NewsAdapter
import kotlin.collections.ArrayList

class LatestNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLatestNewsBinding

    private lateinit var newsAdapter: NewsAdapter
    private var newsList: ArrayList<News>? = ArrayList()

    private val startActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    val newsTitle: String? = result.data!!.getStringExtra(EXTRA_TITLE)
                    for (news in newsList!!) {
                        if (news.title == newsTitle) {
                            news.isFavorite = !news.isFavorite
                        }
                    }

                    setList()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        newsList = intent.getParcelableArrayListExtra(EXTRA_NEWS_LIST)

        setList()
    }

    private fun setList() {
        newsAdapter = NewsAdapter()
        newsAdapter.setData(newsList)
        newsAdapter.onItemClick = { news ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, news)
            intent.putExtra(DetailActivity.EXTRA_LATEST, true)
            startActivityResult.launch(intent)
        }

        with(binding.rvLatestNews) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    companion object {
        const val EXTRA_NEWS_LIST = "extra_news_list"
        const val EXTRA_TITLE = "extra_title"
    }
}