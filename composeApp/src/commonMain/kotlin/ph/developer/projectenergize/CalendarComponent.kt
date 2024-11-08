package ph.developer.projectenergize

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Calendar() {

    val scope = rememberCoroutineScope()

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    CalendarComponent(
        state = state,
        onBackward = {
            scope.launch {
                state.animateScrollToMonth(month = it.minus(value = 1, unit = DateTimeUnit.MONTH))
                println(state.firstVisibleMonth.yearMonth.year.toString())
            }
        },
        onForward = {
            scope.launch {
                state.animateScrollToMonth(month = it.plus(value = 1, unit = DateTimeUnit.MONTH))
            }
        }
    )
}

@Composable
private fun CalendarComponent(
    modifier: Modifier = Modifier,
    state: CalendarState,
    onBackward: (yearMonth: YearMonth) -> Unit = {},
    onForward: (yearMonth: YearMonth) -> Unit = {}
) {
    Column {

        val month = state.firstVisibleMonth.yearMonth.month.name
        val year = state.firstVisibleMonth.yearMonth.year.toString()
        val monthYear = "$month $year"

        Row(modifier = Modifier) {
            IconButton(onClick = { onBackward(state.firstVisibleMonth.yearMonth) }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Arrow Back")
            }
            Text(
                modifier = Modifier.weight(1f).padding(vertical = 16.dp),
                text = monthYear,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            IconButton(onClick = { onForward(state.firstVisibleMonth.yearMonth) }) {
                Icon(Icons.AutoMirrored.Default.ArrowForward, contentDescription = "Arrow Forward")
            }
        }
        HorizontalCalendar(
            modifier = modifier,
            state = state,
            dayContent = { DayComponent(it) },
            monthHeader = { month ->
                val dayOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
                DaysOfWeekTitleComponent(daysOfWeek = dayOfWeek)
            }
        )
    }
}

@Composable
private fun DayComponent(day: CalendarDay) {
    Box(
        modifier = Modifier.aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Color.Black else Color.Gray
        )
    }
}

@Composable
private fun DaysOfWeekTitleComponent(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(narrow = false, locale = Locale.current),
            )
        }
    }
}

@Preview
@Composable
private fun DayPreview() {
    DayComponent(
        CalendarDay(
            date = LocalDate(year = 2024, month = Month.FEBRUARY, dayOfMonth = 23),
            position = DayPosition.MonthDate
        )
    )
}

@Preview
@Composable
private fun DayOfWeekPreview() {
    DaysOfWeekTitleComponent(
        daysOfWeek = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY,
        )
    )
}

@Preview
@Composable
private fun CalendarPreview() {
    CalendarComponent(state = rememberCalendarState())
}