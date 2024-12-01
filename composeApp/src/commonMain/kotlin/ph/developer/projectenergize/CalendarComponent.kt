package ph.developer.projectenergize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun rememberCalendarStateWithYearMonth(): CalendarState {

    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val daysOfWeek = remember { daysOfWeek() }

    return rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    content: @Composable (CalendarDay) -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    calendarState: CalendarState = rememberCalendarStateWithYearMonth()
) {
    CalendarComponent(
        modifier = modifier,
        state = calendarState,
        onBackward = {
            scope.launch {
                calendarState.animateScrollToMonth(month = it.minus(value = 1, unit = DateTimeUnit.MONTH))
                Logger.setTag("Calendar Component")
                Logger.d(message = { calendarState.firstVisibleMonth.yearMonth.year.toString() })
            }
        },
        onForward = {
            scope.launch {
                calendarState.animateScrollToMonth(month = it.plus(value = 1, unit = DateTimeUnit.MONTH))
            }
        },
        content = content
    )
}

@Composable
private fun CalendarComponent(
    modifier: Modifier = Modifier,
    state: CalendarState,
    content: @Composable (CalendarDay) -> Unit,
    onBackward: (yearMonth: YearMonth) -> Unit = {},
    onForward: (yearMonth: YearMonth) -> Unit = {},
    currentDay: CalendarDay = CalendarDay(LocalDate.now(), DayPosition.MonthDate)
) {

    var selectedDay by remember { mutableStateOf(currentDay) }

    val month = state.firstVisibleMonth.yearMonth.month.name
    val year = state.firstVisibleMonth.yearMonth.year.toString()
    val monthYear = "$month $year"

    Column(modifier = modifier) {
        Row {
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
            dayContent = {
                DayComponent(
                    day = it,
                    isCurrentDate = { selectedDay == it }
                ) { day ->
                    selectedDay = day
                }
            },
            monthHeader = { calendarMonth ->
                val dayOfWeek = calendarMonth.weekDays.first().map { it.date.dayOfWeek }
                DaysOfWeekTitleComponent(daysOfWeek = dayOfWeek)
            }
        )
        content(selectedDay)
    }
}

@Composable
private fun DayComponent(day: CalendarDay, isCurrentDate: () -> Boolean, onClick: (CalendarDay) -> Unit = {}) {
    val modifier =
        if (isCurrentDate()) Modifier
            .padding(8.dp)
            .background(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small)
            .aspectRatio(1f)
        else Modifier.aspectRatio(1f).padding(8.dp)
    TextButton(modifier = modifier, onClick = { onClick(day) }) {
        val color = when {
            isCurrentDate() -> Color.White
            day.position == DayPosition.MonthDate -> Color.Black
            else -> {
                Color.Gray
            }
        }
        Text(
            modifier = Modifier,
            text = day.date.dayOfMonth.toString(),
            color = color
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
        day = CalendarDay(
            date = LocalDate(year = 2024, month = Month.FEBRUARY, dayOfMonth = 23),
            position = DayPosition.MonthDate
        ),
        isCurrentDate = { true }) {

    }
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
    CalendarComponent(state = rememberCalendarState(), content = {})
}
