package dev.kibet.globalnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.kibet.globalnews.R
import dev.kibet.globalnews.adapter.NewsAdapter
import dev.kibet.globalnews.databinding.FragmentHeadLinesBinding
import dev.kibet.globalnews.ui.viewmodel.MainViewModel
import dev.kibet.globalnews.utils.Status

@AndroidEntryPoint
class HeadLinesFragment : Fragment() {
    private lateinit var binding: FragmentHeadLinesBinding
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private val newsAdapter = NewsAdapter()
    private lateinit var newsRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeadLinesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        progressBar = binding.callProgress
        viewModel.getHeadlines()

        newsRecycler = binding.recyclerView
        newsRecycler.layoutManager = LinearLayoutManager(context)

    }

    private fun observeViewModel() {
        viewModel.getHeadlinesStatus.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let { article ->
                            newsAdapter.differ.submitList(article.articles)
                            newsRecycler.adapter = newsAdapter
                        }
                        Toast.makeText(context, "${it.data}", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}