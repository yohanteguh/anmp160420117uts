package com.example.anmp160420117uts.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp160420117uts.R
import com.example.anmp160420117uts.viewmodel.ListViewModel

class DaftarBukuFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val bukuListAdapter = DaftarBukuAdapter(arrayListOf())
    val recView = view?.findViewById<RecyclerView>(R.id.recView)
    val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
    val txtError = view?.findViewById<TextView>(R.id.txtError)
    val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_buku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = bukuListAdapter

        observeViewModel()

        refreshLayout?.setOnRefreshListener {
            recView?.visibility = View.GONE
            txtError?.visibility = View.GONE
            progressLoad?.visibility = View.VISIBLE
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
                txtError?.visibility = View.VISIBLE
            }
            else{
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            }
            else{
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }


}