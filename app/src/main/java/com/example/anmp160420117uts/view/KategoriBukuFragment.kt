package com.example.anmp160420117uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp160420117uts.R
import com.example.anmp160420117uts.viewmodel.KategoriViewModel

class KategoriBukuFragment : Fragment() {

    private lateinit var viewModel: KategoriViewModel
    private val kategoriListAdapter = KategoriBukuAdapter(arrayListOf())
    val recViewKategori = view?.findViewById<RecyclerView>(R.id.recViewKategori)
    val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori_buku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(KategoriViewModel::class.java)
        viewModel.refresh()

        recViewKategori?.layoutManager = LinearLayoutManager(context)
        recViewKategori?.adapter = kategoriListAdapter

        observeViewModel()

        refreshLayout?.setOnRefreshListener {
            recViewKategori?.visibility = View.GONE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.kategoriLD.observe(viewLifecycleOwner, Observer {
            kategoriListAdapter.updateBukuList(it)
        })
    }
}