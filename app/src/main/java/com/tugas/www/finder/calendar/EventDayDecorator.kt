package com.tugas.www.finder.calendar

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDayDecorator(days: Collection<CalendarDay>) : DayViewDecorator {

    var myDay: HashSet<CalendarDay> = HashSet(days)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return myDay.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(10.0f, Color.BLUE))
    }

}