package com.example.internetbroadcastreceiver

import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internetbroadcastreceiver.databinding.ActivityMainBinding
import com.example.internetbroadcastreceiver.databinding.InternetDialogBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MyReceiver.ConnectivityReceiverListener {
    lateinit var snackBar : Snackbar
    lateinit var binding:ActivityMainBinding
    lateinit var customDialog :Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver(MyReceiver() , IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        customDialog = Dialog(this)
        var dialogBinding=InternetDialogBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCancelable(false)
    }

    override fun onNetworkConnectionChanger(isConnected: Boolean) {
       shoNetworkMessage(isConnected)

    }

    private fun shoNetworkMessage(isConnected: Boolean) {

       // customDialog.show()
        if (isConnected){
          customDialog.dismiss()


//            snackBar = Snackbar.make(this.findViewById(android.R.id.content), "You are Online ", Snackbar.LENGTH_LONG)
//            snackBar.show()
        }else{
            customDialog.show()
//            snackBar = Snackbar.make(this.findViewById(android.R.id.content), "You are Offline ", Snackbar.LENGTH_LONG);
//            snackBar.show()
        }
    }
    override fun onResume() {
        super.onResume()
        MyReceiver.connectivityReceiverListener = this
    }
}