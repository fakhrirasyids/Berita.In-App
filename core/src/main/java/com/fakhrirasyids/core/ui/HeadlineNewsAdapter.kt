package com.fakhrirasyids.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakhrirasyids.core.R
import com.fakhrirasyids.core.databinding.ItemHeadlineLatestNewsBinding
import com.fakhrirasyids.core.databinding.LoadMoreLayoutBinding
import com.fakhrirasyids.core.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

class HeadlineNewsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listNews = ArrayList<News>()

    private lateinit var headlineViewHolder: HeadlineViewHolder
    private lateinit var buttonViewHolder: ButtonViewHolder

    var onItemClick: ((News) -> Unit)? = null
    var onLoadMoreClick: ((ArrayList<News>) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<News>?) {
        if (newListData == null) return
        listNews.clear()
        listNews.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        headlineViewHolder = HeadlineViewHolder(
            ItemHeadlineLatestNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        buttonViewHolder = ButtonViewHolder(
            LoadMoreLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        return if (viewType == NEWS) {
            headlineViewHolder
        } else {
            buttonViewHolder
        }
    }

    override fun getItemCount() = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listNews.isNotEmpty()) {
            if (getItemViewType(position) == NEWS) {
                headlineViewHolder.bind(listNews[position])
            } else {
                buttonViewHolder.showButton()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 3) {
            LOAD_MORE
        } else {
            NEWS
        }
    }

    inner class HeadlineViewHolder(var binding: ItemHeadlineLatestNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(news: News) {
            binding.tvNewsTitle.text = news.title
            Glide.with(binding.root)
                .load(news.urlToImage)
                .placeholder(R.drawable.dummy_image)
                .into(binding.ivImgNews)
            val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputDate = SimpleDateFormat("h:mm a, d MMM")
            val d: Date? = inputDate.parse(news.publishedAt!!)
            val formatted: String = outputDate.format(d!!)
            binding.tvTime.text = formatted
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listNews[adapterPosition])
            }
        }
    }

    inner class ButtonViewHolder(
        private var binding: LoadMoreLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun showButton() {
            binding.btnLoadMoreHeadline.visibility = View.VISIBLE
        }

        init {
            binding.btnLoadMoreHeadline.setOnClickListener {
                onLoadMoreClick?.invoke(listNews)
            }
        }
    }

    companion object {
        const val NEWS = 0
        const val LOAD_MORE = 1
    }
}