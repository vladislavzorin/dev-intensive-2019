package ru.skillbranch.devintensive.extensions


import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) :Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()):String{
    val timeDiff: Long = (date.time - this.time)
    var timeString: String = ""

    when(abs(timeDiff)){
        in(0 .. 1 * SECOND) -> timeString = "только что"

        in(1 * SECOND .. 45 * SECOND) -> timeString = if(timeDiff > 0)"несколько секунд назад" else "через несколько секунд"

        in(45 * SECOND .. 75 * SECOND) -> timeString = if(timeDiff > 0) "минуту назад" else "через минуту"

        in(75 * SECOND .. 45 * MINUTE) -> timeString = if(timeDiff > 0)"${TimeUnits.MINUTE.plural((abs(timeDiff)/ MINUTE).toInt())} назад" else "через ${TimeUnits.MINUTE.plural((abs(timeDiff)/ MINUTE).toInt())}"

        in(45 * MINUTE .. 75 * MINUTE) -> timeString = if(timeDiff > 0) "час назад" else "через час"

        in(75 * MINUTE .. 22 * HOUR) -> timeString = if(timeDiff > 0)"${TimeUnits.HOUR.plural((abs(timeDiff)/ HOUR).toInt())} назад" else "через ${TimeUnits.HOUR.plural((abs(timeDiff)/ HOUR).toInt())}"

        in(22 * HOUR .. 26 * HOUR) -> timeString = if(timeDiff > 0) "день назад" else "через день"

        in(26 * HOUR .. 360 * DAY) -> timeString = if(timeDiff > 0)"${TimeUnits.DAY.plural ((abs(timeDiff)/ DAY).toInt())} назад" else "через ${TimeUnits.DAY.plural ((abs(timeDiff)/ DAY).toInt())}"

        else -> timeString = if(timeDiff > 0) "более года назад" else "через более года"
    }
    return timeString
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val absValue = abs(value)
        val is11to14 = absValue % 100 in 11..14
        val valMod10 = absValue % 10
        return when (this) {
            SECOND ->
                when {
                    is11to14 -> "$absValue секунд"
                    valMod10 == 1 -> "$absValue секунду"
                    valMod10 in 2..4 -> "$absValue секунды"
                    else -> "$absValue секунд"
                }
            MINUTE ->
                when {
                    is11to14 -> "$absValue минут"
                    valMod10 == 1 -> "$absValue минуту"
                    valMod10 in 2..4 -> "$absValue минуты"
                    else -> "$absValue минут"
                }
            HOUR ->
                when {
                    is11to14 -> "$absValue часов"
                    valMod10 == 1 -> "$absValue час"
                    valMod10 in 2..4 -> "$absValue часа"
                    else -> "$absValue часов"
                }
            DAY ->
                when {
                    is11to14 -> "$absValue дней"
                    valMod10 == 1 -> "$absValue день"
                    valMod10 in 2..4 -> "$absValue дня"
                    else -> "$absValue дней"
                }


        }
    }
}