package com.example.internetbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class MyReceiver:BroadcastReceiver() {
    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanger(isConnected: Boolean)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
       connectivityReceiverListener?.onNetworkConnectionChanger(isConnectedOrConnecting(context!!))
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnectedOrConnecting(context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false


            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
//        } else {
//            @Suppress("DEPRECATION")
//            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
//            @Suppress("DEPRECATION")
//            return networkInfo.isConnected
//        }
    }

    companion object{
        var connectivityReceiverListener:ConnectivityReceiverListener? = null
    }
}