package com.example.anmp160420117uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp160420117uts.R

class KategoriBukuAdapter(val kategoriList: ArrayList<String>) :
    RecyclerView.Adapter<KategoriBukuAdapter.BukuViewHolder>() {

    class BukuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kategori_item, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val kategori = kategoriList[position]

        kategoriList.add(kategori.toString())

        holder.view.findViewById<TextView>(R.id.btnKategori).text = kategori
        holder.view.findViewById<Button>(R.id.btnKategori).setOnClickListener {
            val action =
                KategoriBukuFragmentDirections.actionItemKategoriBukuToDaftarBukuKategoriFragment(
                    kategori.toString()
                )
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return kategoriList.size
    }

    fun updateBukuList(newBukuList: ArrayList<String>) {
        kategoriList.clear()
        kategoriList.addAll(newBukuList)
        notifyDataSetChanged()
    }
}