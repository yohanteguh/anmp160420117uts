package com.example.anmp160420117uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp160420117uts.model.Akun
import com.example.anmp160420117uts.model.Buku
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AkunViewModel(application: Application): AndroidViewModel(application) {
    val akunLD = MutableLiveData<String>()
    var username = String()
    var password = String()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://demurer-grips.000webhostapp.com/akun.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Akun>>() { }.type
                val result = Gson().fromJson<ArrayList<Akun>>(it, sType)
                Log.d("nilai result", result.toString())

                var resultLogin = String()
                for (i in result){
                    if (i.username.toString() == username && i.password.toString() == password){
                        resultLogin = i.nama.toString()
                    }
                }
                akunLD.value = resultLogin

                Log.d("nilai username", username)
                Log.d("nilai password", password)
                Log.d("volleyakunberhasil", akunLD.value.toString())

            },
            {
                Log.d("volleyerror", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun inputUser(user:String, pass:String){
        username = user
        password = pass
    }
}