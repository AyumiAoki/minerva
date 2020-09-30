package com.example.minerva.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity(), Runnable {
    private lateinit var thread: Thread
    private lateinit var handler: Handler
    private lateinit var auth: FirebaseAuth

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()

        handler = Handler()
        thread = Thread(this)
        thread.start()
    }

    override fun run() {
        i = 1
        try {
            while (i <= 100) {
                Thread.sleep(25)
                handler.post { i++ }
            }

            val user = auth.currentUser

            if (user != null ) {
                finish()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {
                finish()
                startActivity(Intent(applicationContext, TelaInicialActivity::class.java))
            }

        } catch (e: InterruptedException) {

        }
    }
}