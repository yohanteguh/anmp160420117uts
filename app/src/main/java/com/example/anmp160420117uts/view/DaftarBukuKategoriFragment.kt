package com.example.anmp160420117uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp160420117uts.R
import com.example.anmp160420117uts.viewmodel.ListViewModel

class DaftarBukuKategoriFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val bukuListAdapter = DaftarBukuAdapter(arrayListOf())
    val recViewBukuKategori = view?.findViewById<RecyclerView>(R.id.recViewBukuKategori)
    val progressLoadBukuKategori = view?.findViewById<ProgressBar>(R.id.progressLoadBukuKategori)
    val txtErrorBukuKategori = view?.findViewById<TextView>(R.id.txtErrorBukuKategori)
    val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_buku_kategori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val kategori = DaftarBukuKategoriFragmentArgs.fromBundle(requireArguments()).kategori
        }
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        Log.d("bukuLoadError", viewModel.bukuLoadErrorLD.value.toString())
        Log.d("loading", viewModel.loadingLD.value.toString())
        Log.d("bukuLD", viewModel.bukuLD.value.toString())

        recViewBukuKategori?.layoutManager = LinearLayoutManager(context)
        recViewBukuKategori?.adapter = bukuListAdapter

        observeViewModel()

        refreshLayout?.setOnRefreshListener {
            recViewBukuKategori?.visibility = View.GONE
            txtErrorBukuKategori?.visibility = View.GONE
            progressLoadBukuKategori?.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.bukuLD.observe(viewLifecycleOwner, Observer {
            bukuListAdapter.updateBukuList(it)
            Log.d("buku", it.toString())
        })

        viewModel.bukuLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                txtErrorBukuKategori?.visibility = View.VISIBLE
            }
            else{
                txtErrorBukuKategori?.visibility = View.GONE
            }
            Log.d("bukuLoadError", it.toString())
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                recViewBukuKategori?.visibility = View.GONE
                progressLoadBukuKategori?.visibility = View.VISIBLE
            }
            else{
                recViewBukuKategori?.visibility = View.VISIBLE
                progressLoadBukuKategori?.visibility = View.GONE
            }
            Log.d("recView Visible", recViewBukuKategori?.visibility.toString())
        })
    }
}