package ph.developer.projectenergize

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
actual fun DayOfWeek.getDisplayName(narrow: Boolean, locale: Locale): String {
    val style = if (narrow) TextStyle.NARROW else TextStyle.SHORT
    return getDisplayName(style, java.util.Locale(locale.toLanguageTag()))
}