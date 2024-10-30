package ph.developer.projectenergize

import androidx.compose.ui.text.intl.Locale
import kotlinx.datetime.DayOfWeek

expect fun DayOfWeek.getDisplayName(narrow: Boolean = false, locale: Locale): String