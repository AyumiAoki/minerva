package com.example.minerva.util

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import com.example.minerva.R

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.minerva.ui.activities.MainActivity


class MyNotificationSystem : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val bundle = intent!!.extras
        val titulo = bundle?.getString("titulo") ?: "Olá"
        val id = bundle?.getInt("id") ?: 0

        println("Niceeeeeeeeeeeeeeeeeee")
        println(id)
        val mBuilder = NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle("Lembrete")
                .setContentText(titulo)
                .setAutoCancel(true)

        //Checa a versão e define se deve ser colocado um ícone grande na notificação
        //(Isso pode ser feito para qualquer versão, mas eu acho que não fica muito bom em versões
        //mais antigas do Android).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_notification)
            mBuilder.setLargeIcon(bm)
        }

        //Cria uma intent indicanado que activity será chamada quando a notificação for clicada
        val resultIntent = Intent(context, MainActivity::class.java)

        //Se a activity aberta pela notificação não for exclusiva da notificação é necessário criar
        //definir uma pilha
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent: PendingIntent = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)

        with(NotificationManagerCompat.from(context)) {
            notify(id, mBuilder.build())
        }
    }
}