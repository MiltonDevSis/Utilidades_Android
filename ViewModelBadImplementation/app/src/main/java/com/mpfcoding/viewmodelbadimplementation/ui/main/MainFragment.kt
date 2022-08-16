package com.mpfcoding.viewmodelbadimplementation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.douglasmotta.viewmodelbadpractices.databinding.FragmentMainBinding
import com.mpfcoding.viewmodelbadimplementation.data.NewsRepository
import com.mpfcoding.viewmodelbadimplementation.network.ApiService

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = MainViewModel.MainViewModelFactory(
            requireContext(),
            NewsRepository(),
            ApiService()
        )

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.newsEvent.observe(viewLifecycleOwner) { newsList ->
            val titles = newsList.map {
                "${it.title}\n"
            }

            binding.news.text = ""
            titles.forEach {
                binding.news.text = "${binding.news.text} $it"
            }
        }

        viewModel.getNews()
    }
}