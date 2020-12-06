package com.example.minerva.util

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.minerva.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class CurrentDayDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
   // private val drawable: Drawable?
    var myDay = currentDay
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
        //view.setSelectionDrawable(drawable!!)

     //   view.addSpan(DotSpan(10f, Color.parseColor("#0CD1E8")))
        view.addSpan(DotSpan(5f, Color.parseColor("#000000")))
    }


    init {
         // You can set background for Decorator via drawable here
        //drawable = ContextCompat.getDrawable(context!!, R.drawable.ic_anotacao)
    }
}