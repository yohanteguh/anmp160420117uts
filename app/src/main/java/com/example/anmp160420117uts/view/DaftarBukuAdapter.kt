package com.example.anmp160420117uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp160420117uts.R
import com.example.anmp160420117uts.model.Buku
import com.example.anmp160420117uts.util.loadImage

class DaftarBukuAdapter(val bukuList:ArrayList<Buku>):RecyclerView.Adapter<DaftarBukuAdapter.BukuViewHolder>() {
    class BukuViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.buku_item, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val id = bukuList[position].id
        val judul = bukuList[position].judul
        val penulis = bukuList[position].penulis
        val kategori = bukuList[position].kategori
        val tahunTerbit = bukuList[position].tahunTerbit
        val deskripsi = bukuList[position].deskripsi

        holder.view.findViewById<TextView>(R.id.txtJudul).text = judul
        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            val action = DaftarBukuFragmentDirections.actionDaftarBukuFragmentToDetailBukuFragment(
                id.toString(), judul.toString(), penulis.toString(), kategori.toString(), tahunTerbit.toString(), deskripsi.toString()
            )
            Navigation.findNavController(it).navigate(action)
        }
        val imgBuku = holder.view.findViewById<ImageView>(R.id.imgBuku)
        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imgBuku.loadImage(bukuList[position].gambarBukuUrl, progressBar)
    }

    override fun getItemCount(): Int {
        return bukuList.size
    }

    fun updateBukuList(newBukuList:ArrayList<Buku>){
        bukuList.clear()
        bukuList.addAll(newBukuList)
        notifyDataSetChanged()
    }
}