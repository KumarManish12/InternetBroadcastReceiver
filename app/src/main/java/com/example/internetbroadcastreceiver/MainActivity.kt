package com.example.internetbroadcastreceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MyReceiver.ConnectivityReceiverListener {
    lateinit var snackBar : Snackbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(MyReceiver() , IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onNetworkConnectionChanger(isConnected: Boolean) {
        shoNetworkMessage(isConnected)
    }

    private fun shoNetworkMessage(isConnected: Boolean) {
        if (isConnected){
            snackBar = Snackbar.make(this.findViewById(android.R.id.content), "You are Online ", Snackbar.LENGTH_LONG)
            snackBar.show()
        }else{
            snackBar = Snackbar.make(this.findViewById(android.R.id.content), "You are Offline ", Snackbar.LENGTH_LONG);
            snackBar.show()
        }
    }
    override fun onResume() {
        super.onResume()
        MyReceiver.connectivityReceiverListener = this
    }
}