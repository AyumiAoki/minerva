package com.example.minerva.util

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.minerva.ui.activities.MainActivity


class MyNotificationSystem : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        //Cria a notificação

        val bundle = intent!!.extras
        var titulo = bundle?.getString("titulo") ?: "Olá"
        var id = bundle?.getInt("id") ?: 0

        println("Niceeeeeeeeeeeeeeeeeee")
        println(id)
        val mBuilder = NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.sym_def_app_icon)
               // .setContentTitle(context.getString(R.string.notification_title))
               // .setContentText(context.getString(R.string.notification_text))
                .setContentTitle("Minerva")
                .setContentText(titulo)
                .setAutoCancel(true)

        //Checa a versão e define se deve ser colocado um ícone grande na notificação
        //(Isso pode ser feito para qualquer versão, mas eu acho que não fica muito bom em versões
        //mais antigas do Android).
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          //  val bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.icone)
           // mBuilder.setLargeIcon(bm)
        }*/

        //Cria uma intent indicanado que activity será chamada quando a notificação for clicada
        val resultIntent = Intent(context, MainActivity::class.java)

        //Se a activity aberta pela notificação não for exclusiva da notificação é necessário criar
        //definir uma pilha
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent: PendingIntent = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT)
        mBuilder.setContentIntent(resultPendingIntent)

        //Envia a noitificação para o usuário
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(5, mBuilder.build())
    }
}