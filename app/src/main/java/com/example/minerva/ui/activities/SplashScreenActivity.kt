package com.example.minerva.ui.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        handler = Handler(Looper.getMainLooper())
        thread = Thread(this)
        thread.start()

    }

    override fun run() {
        i = 1
        createNotificationChannel()
        val intentMain = Intent(applicationContext, MainActivity::class.java)
        val intentInicial = Intent(applicationContext, TelaInicialActivity::class.java)
        try {
            while (i <= 80) {
                Thread.sleep(10)
                handler.post { i++ }
            }

            val user = auth.currentUser

            if (user != null ) {
                finish()
                startActivity(intentMain)
            } else {
                finish()
                startActivity(intentInicial)
            }

        } catch (e: InterruptedException) {

        }
    }

    private fun createNotificationChannel() {
        println("Oiiiii")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "teste"
            val descriptionText = "teste"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.channel_id), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}