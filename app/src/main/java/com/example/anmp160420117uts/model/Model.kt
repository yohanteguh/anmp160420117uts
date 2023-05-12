package com.example.anmp160420117uts.model

data class Buku(
    val id:String?,
    val judul:String?,
    val penulis:String?,
    val kategori:String?,
    val tahunTerbit:String?,
    val deskripsi:String?,
    val gambarBukuUrl:String?
)

data class Akun(
    val id:String?,
    val username:String?,
    val nama:String?,
    val password:String?
)
