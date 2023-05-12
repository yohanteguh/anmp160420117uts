package com.example.anmp160420117uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.anmp160420117uts.R

class DetailBukuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_buku, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtJudulDetail = view.findViewById<TextView>(R.id.txtJudulDetail)
        val txtPenulis = view.findViewById<TextView>(R.id.txtPenulis)
        val txtKategori = view.findViewById<TextView>(R.id.txtKategori)
        val txtTahunTerbit = view.findViewById<TextView>(R.id.txtTahunTerbit)
        val txtDeskripsi = view.findViewById<TextView>(R.id.txtDeskripsi)
        val btnBackLogin = view.findViewById<Button>(R.id.btnBackLogin)

        if (arguments != null){
            val id = DetailBukuFragmentArgs.fromBundle(requireArguments()).id
            val judulDetail = DetailBukuFragmentArgs.fromBundle(requireArguments()).judul
            val penulis = DetailBukuFragmentArgs.fromBundle(requireArguments()).penulis
            val kategori = DetailBukuFragmentArgs.fromBundle(requireArguments()).kategori
            val tahunTerbit = DetailBukuFragmentArgs.fromBundle(requireArguments()).tahunTerbit
            val deskripsi = DetailBukuFragmentArgs.fromBundle(requireArguments()).deskripsi

            txtJudulDetail.text = judulDetail
            txtPenulis.text = penulis
            txtKategori.text = kategori
            txtTahunTerbit.text = tahunTerbit
            txtDeskripsi.text = deskripsi
        }

        btnBackLogin.setOnClickListener {
            val action = DetailBukuFragmentDirections.actionDetailBukuFragmentToItemDaftarBuku()
            Navigation.findNavController(it).navigate(action)
        }
    }
}