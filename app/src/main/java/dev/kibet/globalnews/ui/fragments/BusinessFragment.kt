package dev.kibet.globalnews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.kibet.globalnews.adapter.BusinessAdapter
import dev.kibet.globalnews.databinding.FragmentBusinessBinding
import dev.kibet.globalnews.ui.viewmodel.MainViewModel
import dev.kibet.globalnews.utils.Status

@AndroidEntryPoint
class BusinessFragment : Fragment() {
    private val viewModel:MainViewModel by activityViewModels()
    private lateinit var binding: FragmentBusinessBinding
    private val newsAdapter = BusinessAdapter()
    private lateinit var businessRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBusinessBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        viewModel.getHeadlines()

        businessRecycler = binding.businessRecyclerView
        businessRecycler.layoutManager = LinearLayoutManager(context)



    }


    private fun observeViewModel() {
        viewModel.getBusinessNewsStatus.observe(viewLifecycleOwner, {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        it.data?.let { article ->
                            Toast.makeText(context, "${it.data}", Toast.LENGTH_LONG).show()
                            newsAdapter.differ.submitList(article.articles)
                            businessRecycler.adapter = newsAdapter
                        }
                    }
                    Status.LOADING -> {
                        //progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        //progressBar.visibility = View.GONE
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}