package ph.developer.projectenergize

import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.datetime.DayOfWeek
import platform.Foundation.NSCalendar
import platform.Foundation.NSLocale
import androidx.compose.ui.text.intl.Locale

actual fun DayOfWeek.getDisplayName(narrow: Boolean, locale: Locale): String {
    return NSCalendar.currentCalendar.let {
        it.setLocale(NSLocale(locale.toLanguageTag()))
        val values = if (narrow) {
            it.veryShortWeekdaySymbols
        } else {
            it.shortWeekdaySymbols
        }
        values[sundayBasedWeek.indexOf(this)] as String
    }
}

private val sundayBasedWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY)
