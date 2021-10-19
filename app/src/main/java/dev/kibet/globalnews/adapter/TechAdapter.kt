package dev.kibet.globalnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.kibet.globalnews.data.model.ArticleX
import dev.kibet.globalnews.databinding.ArticleItemBinding

class TechAdapter: RecyclerView.Adapter<TechAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ArticleItemBinding): RecyclerView.ViewHolder(binding.root)
    private val diffCallBack = object : DiffUtil.ItemCallback<ArticleX>(){
        override fun areItemsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
        return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticleX, newItem: ArticleX): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int): Unit = with(holder.binding) {
        val article = differ.currentList[position]
        val context = holder.itemView.context

        articleTitle.text = article.title
        articleDesc.text = article.description
        Glide.with(context).load(article.urlToImage).into(articleImage)

    }

    override fun getItemCount(): Int = differ.currentList.size
}