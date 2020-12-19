package com.example.minerva.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.*

object SalvarNotificacao {
    private var alarmIntent: PendingIntent? = null

    fun scheduleNotification(context: Context,ano:Int, mes:Int, dia:Int, hora: Int, minuto:Int, id:Int, titulo:String) {

        //Em seguida criamos uma instância da classe Alarm Mangager e uma Intent indicando quem
        //deve ser chamado quando o momento chegar, nesse casso a classe MyNotificationSystem
        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyNotificationSystem::class.java)
        val bundle = Bundle()
        bundle.putString("titulo", titulo)
        bundle.putInt("id", id)
        intent.putExtras(bundle)
        println(id)
        //Criamos uma Pending Intenten
        alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //Definimos o agendamento de acordo com o que recebemos
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.YEAR, ano)
        calendar.set(Calendar.MONTH, mes)
        calendar.set(Calendar.DAY_OF_MONTH, dia)
        calendar.set(Calendar.HOUR_OF_DAY, hora)
        calendar.set(Calendar.MINUTE, minuto)
        calendar.set(Calendar.SECOND, 0)

        //Verificamos se o evento foi agendado para uma data no futuro e, se não,
        //colocamos mais um dia no agendamento
        //Nunca se deve agendar um evento num tempo passado! (coisas estranhas acontecem)
        if (!calendar.after(Calendar.getInstance())) calendar.roll(Calendar.DATE, true)

        //Criamos nosso agendamento
        alarmMgr.setRepeating(
            AlarmManager.RTC, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, alarmIntent)
        println("Chegou aqui")
        println(calendar.time)
    }

    fun excluirNotificação(context: Context, id:Int, titulo:String) {

        //Em seguida criamos uma instância da classe Alarm Mangager e uma Intent indicando quem
        //deve ser chamado quando o momento chegar, nesse casso a classe MyNotificationSystem
        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyNotificationSystem::class.java)
        val bundle = Bundle()
        bundle.putString("titulo", titulo)
        bundle.putInt("id", id)
        intent.putExtras(bundle)
        println(id)
        //Criamos uma Pending Intenten
        alarmIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        println("Chegou aqui")
        alarmMgr.cancel(alarmIntent)
    }

    fun lembreteDiario(context: Context, hora: Int, minuto: Int){
        println("Blzz")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hora)
        calendar.set(Calendar.MINUTE, minuto)
        calendar.set(Calendar.SECOND, 0)


        val alarmMgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyNotificationSystemDaily::class.java)
        alarmIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        if (!calendar.after(Calendar.getInstance())) calendar.roll(Calendar.DATE, true)

        println(calendar.time)
        alarmMgr.setInexactRepeating(
            AlarmManager.RTC, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, alarmIntent)
    }
}