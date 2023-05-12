package com.example.anmp160420117uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmp160420117uts.R
import com.example.anmp160420117uts.viewmodel.AkunViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: AkunViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtUsername = view.findViewById<TextView>(R.id.txtUsername)
        val txtPassword = view.findViewById<TextView>(R.id.txtPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnBackLogin = view.findViewById<Button>(R.id.btnBackLogin)

        viewModel = ViewModelProvider(this).get(AkunViewModel::class.java)

        observeViewModel()

        btnLogin.setOnClickListener {
            viewModel.inputUser(txtUsername.text.toString(), txtPassword.text.toString())
            viewModel.refresh()
            Log.d("akun ld", viewModel.akunLD.value.toString())
            if (viewModel.akunLD.value.isNullOrEmpty() == false){
                val action = LoginFragmentDirections.actionLoginFragmentToItemAkun(viewModel.akunLD.value.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }

        btnBackLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToItemAkun()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.akunLD.observe(viewLifecycleOwner, Observer {
            Log.d("akun", it.toString())
        })
    }
}