package com.mpfcoding.viewmodelbadimplementation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.douglasmotta.viewmodelbadpractices.databinding.FragmentMainBinding
import com.mpfcoding.viewmodelbadimplementation.data.NewsRepositoryImpl
import com.mpfcoding.viewmodelbadimplementation.db.NewsDatabase
import com.mpfcoding.viewmodelbadimplementation.network.ApiService
import kotlinx.coroutines.Dispatchers

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentMainBinding

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
            NewsRepositoryImpl(
                Dispatchers.IO,
                NewsDatabase.getDatabase(requireContext().applicationContext).newsDao(),
                ApiService()
            )
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
    }
}