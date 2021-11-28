package lt.sporttech.ubalancit.features.choosedays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.util.IOS_NATIVE_BLUE

@Composable
internal fun ChooseDaysUi(
    state: ChooseDaysState,
    toggleSelection: (DayOfWeek) -> Unit,
    onContinueClick: () -> Unit,
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    contentAlignment = Alignment.BottomCenter
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Title() }

        item { Separator() }
        item { Day(state, DayOfWeek.MONDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.TUESDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.WEDNESDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.THURSDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.FRIDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.SATURDAY, toggleSelection) }
        item { Separator() }
        item { Day(state, DayOfWeek.SUNDAY, toggleSelection) }
        item { Separator() }

        item { Tip() }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = IOS_NATIVE_BLUE,
            disabledBackgroundColor = Color.Gray,
        ),
        onClick = onContinueClick,
        enabled = state.selectedDays.size == 3,
    ) {
        Text(
            text = "Get Started",
            color = Color.White
        )
    }
}

@Composable
private fun Title() = Text(
    modifier = Modifier.padding(bottom = 16.dp),
    text = "Choose Your Workout Days",
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    textAlign = TextAlign.Center,
)

@Composable
private fun Day(
    state: ChooseDaysState,
    day: DayOfWeek,
    toggleSelection: (DayOfWeek) -> Unit,
) = Box(
    Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)) {

    val isSelected = state.selectedDays.contains(day)

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
        Text(
            text = day.title,
            fontSize = 16.sp,
            color = if (isSelected) IOS_NATIVE_BLUE else Color.Gray,
        )
    }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
        Switch(
            checked = isSelected,
            onCheckedChange = { toggleSelection(day) }
        )
    }
}

@Composable
private fun Separator() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.Gray)
)

@Composable
private fun Tip() = Row(Modifier.fillMaxWidth().padding(8.dp)) {
    Text(
        text = "TIP:",
        color = Color.Red,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )

    Text(
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
        text = "The optimum training sessions per week is 3 days with the rest day in between.",
        fontSize = 14.sp
    )
}

@Preview
@Composable
private fun ChooseDaysUiPreview() = ChooseDaysUi(
    ChooseDaysState(
        selectedDays = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
    ),
    { }, { },
)