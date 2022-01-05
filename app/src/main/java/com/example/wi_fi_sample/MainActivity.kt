package com.example.wi_fi_sample

import android.content.Context
import android.content.res.Resources.getSystem
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


class MainActivity : AppCompatActivity() {
    private val textView : TextView? = null
    private var wifiManager : WifiManager? = null
    private var wifiInfo : WifiInfo? =null
    private val scrollView :ScrollView?=null
    private val context : Context? = null
    private val WifiConfiguration: List<*>? = null
    private var scanResult: ScanResult? = null
    private var WifiList: List<ScanResult>? = null
    private var stringBuffer = StringBuffer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var scrollView = findViewById<ScrollView>(R.id.mScrollView)
        var open_bt = findViewById<Button>(R.id.open_bt)
        var close_bt = findViewById<Button>(R.id.close_bt)
        var check_bt = findViewById<Button>(R.id.check_bt)
        var search_bt = findViewById<Button>(R.id.search_bt)
        var  textView = findViewById<TextView>(R.id.text)
        open_bt.setOnClickListener(open_btListener())
        close_bt.setOnClickListener(close_btListener())
        check_bt.setOnClickListener(check_btListener())
        search_bt.setOnClickListener(search_btListener())
    }
    private inner class search_btListener : View.OnClickListener {
        override fun onClick(v: View?) {
            //Wi-Fi スキャン
            wifiManager?.startScan()
            //スキャン結果を取得
            WifiList = wifiManager?.getScanResults()
            wifiInfo = wifiManager?.getConnectionInfo()
            if (stringBuffer != null) {
                stringBuffer = StringBuffer()
            }
            var stringBuffer = stringBuffer.append("Wifi名前").append(" ").append("Wifi住所").append(" ")
                .append("Wifi周波数").append("").append("Wifi信号")
                .append("\n")
            if (WifiList != null) {
                for (i in 0 until WifiList!!.size) {
                    scanResult = WifiList!!.get(i)
                    val stringBuffer = stringBuffer.append(scanResult?.SSID).append(" ")
                        .append(scanResult?.BSSID).append(" ")
                        .append(scanResult?.frequency).append(" ")
                        .append(scanResult?.level).append("\n")
                    textView?.setText(stringBuffer.toString())
                }
                stringBuffer = stringBuffer.append("-----------------------------------------------")
                        .append("\n")
                textView?.setText(stringBuffer.toString())
                stringBuffer =
                    stringBuffer.append("Wifi—BSSID").append(":").append(wifiInfo?.getBSSID())
                        .append("\n")
                        .append("wifi—HiddenSSID").append(": ").append(wifiInfo?.getHiddenSSID())
                        .append("\n")
                        .append("Wifi—IpAddress").append(": ").append(wifiInfo?.getIpAddress())
                        .append("\n")
                        .append("Wifi—LinkSpeed").append(": ").append(wifiInfo?.getLinkSpeed())
                        .append("\n")
                        //.append("Wifi—MacAddress").append(": ").append(wifiInfo.getMacAddress())
                        //.append("\n")
                        .append("Wifi—Network ID").append(": ").append(wifiInfo?.getNetworkId())
                        .append("\n")
                        .append("Wifi—RSSI").append(": ").append(wifiInfo?.getRssi()).append("\n")
                        .append("Wifi—SSID").append(": ").append(wifiInfo?.getSSID()).append("\n")
                        .append("-----------------------------------------------").append("\n")
                        .append("本体Wifi情報").append(": ").append(wifiInfo.toString())
                textView?.setText(stringBuffer.toString())
            }
            //stringBuffer=stringBuffer.append("-----------------------------------------------").append("\n");
            //textView.setText()
        }
    }

    private inner class check_btListener : View.OnClickListener {
        override fun onClick(v: View?) {
            // TODO Auto-generated method stub
            wifiManager = this@MainActivity
                .getSystemService(Context.WIFI_SERVICE) as WifiManager
            System.out.println(wifiManager!!.getWifiState())
            Toast.makeText(
                this@MainActivity,
                "状態は:" + change(), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private inner class close_btListener : View.OnClickListener {
        override fun onClick(v: View?) {
            // TODO Auto-generated method stub
            wifiManager = this@MainActivity
                .getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager!!.setWifiEnabled(false)
            System.out.println(wifiManager!!.getWifiState())
            Toast.makeText(
                this@MainActivity,
                "状態は" + change(), Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private inner class open_btListener : View.OnClickListener {
        override fun onClick(v: View?) {
            // TODO Auto-generated method stub
            wifiManager =this@MainActivity.getSystemService(Context.WIFI_SERVICE) as WifiManager
            wifiManager!!.setWifiEnabled(true)
            System.out.println(wifiManager!!.getWifiState())
            Toast.makeText(
                this@MainActivity,
                "状態は" + change(), Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    fun change(): String? {
        var temp: String? = null
        when (wifiManager?.getWifiState()) {
            0 -> temp = "wifi閉じています"
            1 -> temp = "wifi閉じました"
            2 -> temp = "wifi開いています"
            3 -> temp = "wifi開きました"
            else -> {
            }
        }
        return temp
    }
}