package com.example.minerva.ui.activities;

import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.minerva.R
import com.example.minerva.util.SalvarNotificacao
import com.example.minerva.util.SecurityPreferences
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import kotlinx.android.synthetic.main.activity_sua_atividade.*
import java.util.*

class SuaAtividadeActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var mUsageStatsManager: UsageStatsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_atividade)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Sua atividade"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true) //Mostrar o botão
            supportActionBar!!.setHomeButtonEnabled(true)      //Ativar o botão
        }
        mUsageStatsManager =
            applicationContext.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager   // Context.USAGE_STATS_SERVICE

        val cDefault = Calendar.getInstance()

        timePickerDialog = TimePickerDialog.newInstance(
            this,
            cDefault.get(Calendar.HOUR_OF_DAY),
            cDefault.get(Calendar.MINUTE),
            true
        )

        timePickerDialog.accentColor = resources.getColor(R.color.colorPrimary)

        button_horario_lembrete.setOnClickListener{
            timePickerDialog.show(supportFragmentManager, "Love you")
        }

        val hora = SecurityPreferences(this).getString("hora_lembrete")
        if(hora.isNotEmpty()){
            val horas = hora.split(":")
            val horaText = "${horas[0]}h ${horas[1]}m >"
            button_horario_lembrete.text = horaText
        }

        diaAtual()
        grafico()
        teste()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val horaTexto = "${hourOfDay}h ${minute}m >"
        val horaLembrete = "${hourOfDay}:${minute}"
        SecurityPreferences(this).storeString("hora_lembrete", horaLembrete)
        button_horario_lembrete.text = horaTexto
        SalvarNotificacao.lembreteDiario(applicationContext, hourOfDay, minute)
    }

    fun diaAtual(){
        val semanas = arrayOf(
            "Dom",
            "Seg",
            "Ter",
            "Qua",
            "Qui",
            "Sex",
            "Sab"
        )
        val semanaAtual = ArrayList<String>()


        var diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        var cont = 0

        while(cont < 7){
            if(diaAtual >= 7 ){
                diaAtual = 0
            }
            semanaAtual.add(semanas[diaAtual])
            ++diaAtual
            ++cont
        }
        text_view_atividade_dia_1.text = semanaAtual[0]
        text_view_atividade_dia_2.text = semanaAtual[1]
        text_view_atividade_dia_3.text = semanaAtual[2]
        text_view_atividade_dia_4.text = semanaAtual[3]
        text_view_atividade_dia_5.text = semanaAtual[4]
        text_view_atividade_dia_6.text = semanaAtual[5]
        text_view_atividade_dia_7.text = semanaAtual[6]
    }

    fun grafico(){
        val corFraca = "#C9F8FF"
        val corMedia = "#0CD1E8"
        val corForte = "#00A3FF"


        val dia1 = 199
        val dia2 = 230
        val dia3 = 250
        val dia4 = 100
        val dia5 = 90
        val dia6 = 100
        val dia7 = 60

        val view1 = View(this)
        view1.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia1
        )

        val view2 = View(this)
        view2.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia2
        )

        val view3 = View(this)
        view3.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia3
        )

        val view4 = View(this)
        view4.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia4
        )

        val view5 = View(this)
        view5.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia5
        )

        val view6 = View(this)
        view6.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia6
        )

        val view7 = View(this)
        view7.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            dia7
        )

        when {
            dia1 >= 200 -> {
                view1.setBackgroundColor(parseColor(corForte))
            }
            dia1 >= 100 -> {
                view1.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view1.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia2 >= 200 -> {
                view2.setBackgroundColor(parseColor(corForte))
            }
            dia2 >= 100 -> {
                view2.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view2.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia3 >= 200 -> {
                view3.setBackgroundColor(parseColor(corForte))
            }
            dia3 >= 100 -> {
                view3.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view3.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia4 >= 200 -> {
                view4.setBackgroundColor(parseColor(corForte))
            }
            dia4 >= 100 -> {
                view4.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view4.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia5 >= 200 -> {
                view5.setBackgroundColor(parseColor(corForte))
            }
            dia5 >= 100 -> {
                view5.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view5.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia6 >= 200 -> {
                view6.setBackgroundColor(parseColor(corForte))
            }
            dia6 >= 100 -> {
                view6.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view6.setBackgroundColor(parseColor(corFraca))
            }
        }
        when {
            dia7 >= 200 -> {
                view7.setBackgroundColor(parseColor(corForte))
            }
            dia7 >= 100 -> {
                view7.setBackgroundColor(parseColor(corMedia))
            }
            else -> {
                view7.setBackgroundColor(parseColor(corFraca))
            }
        }

        desempenho_1.addView(view1)
        desempenho_2.addView(view2)
        desempenho_3.addView(view3)
        desempenho_4.addView(view4)
        desempenho_5.addView(view5)
        desempenho_6.addView(view6)
        desempenho_7.addView(view7)
    }

    fun teste(){
        val diaAtual = Calendar.getInstance()

        diaAtual.set(
            diaAtual.get(Calendar.YEAR), diaAtual.get(Calendar.MONTH), diaAtual.get(
                Calendar.DAY_OF_MONTH
            ), 0, 0, 0
        )

        diaAtual.set(Calendar.DAY_OF_YEAR, -7)
        println(diaAtual.time)


        val diaAnterior6 =Calendar.getInstance()
        diaAnterior6.timeInMillis = diaAtual.timeInMillis - 86400000  *6


        println("dia1: ${diaAtual.time}")
        println("dia7: ${diaAnterior6.time}")

        val start = diaAtual.timeInMillis
        val end =System.currentTimeMillis()
        val test= Calendar.getInstance()
        test.timeInMillis = System.currentTimeMillis()
        println("fim: ${test.time}")


        var usoSemanal: Long = 1
        val stat: List<UsageStats> = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_WEEKLY, start, end)
        println("começo: $start")
        println("fin $end")
        for (s in stat) {
            if (s.packageName.contains("minerva")) {
                println("teste")


                println(s.packageName)
                diaAtual.timeInMillis = s.firstTimeStamp
                println(diaAtual.time)
                diaAtual.timeInMillis = s.lastTimeUsed
                println(diaAtual.time)
                usoSemanal = s.totalTimeInForeground/1000/60/7
                println(s.totalTimeInForeground/1000/7)
            }
        }

        if (!checkForPermission(applicationContext)) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }

        val hora = usoSemanal / 60
        val minuto = usoSemanal % 60

        if(hora < 1){
            text_view_hora_semanal.visibility = View.INVISIBLE
            hora_id.visibility = View.INVISIBLE
        }else{
            text_view_hora_semanal.text = hora.toString()
        }
        text_view_minuto_semanal.text = minuto.toString()
        println("naooooooo")
    }

    private fun checkForPermission(context: Context): Boolean {
        val appOps = context.getSystemService(APP_OPS_SERVICE) as AppOpsManager
        val mode =
            appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), context.packageName)
        return mode == MODE_ALLOWED
    }
}