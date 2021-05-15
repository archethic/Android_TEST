package com.mcna.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mcna.testapp.*
import com.mcna.testapp.`class`.CustomFileClassLoader
import org.bson.*


val classLoader = CustomFileClassLoader()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClick() {

    }
}