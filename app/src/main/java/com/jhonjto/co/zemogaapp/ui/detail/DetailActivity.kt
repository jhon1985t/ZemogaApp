package com.jhonjto.co.zemogaapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jhonjto.co.zemogaapp.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbar))


    }
}