package lt.sporttech.ubalancit.features.chooseplan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lt.sporttech.ubalancit.util.IOS_NATIVE_BLUE

@Composable
internal fun ChoosePlanUi(
    state: ChoosePlanState,
    onGymClick: () -> Unit,
    onHomeClick: () -> Unit,
    onFullWorkoutClick: () -> Unit,
    onFifteenMinutesClick: () -> Unit,
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
            .padding(16.dp)) {
        Category(
            title = "Choose Your Plan",
            option1 = gymOption(state),
            option2 = homeOption(state),
            onClick1 = onGymClick,
            onClick2 = onHomeClick,
        )

        item { Spacer(Modifier.height(32.dp)) }

        Category(
            title = "Choose your time",
            option1 = fullWorkoutOption(state),
            option2 = fifteenMinutesOption(state),
            onClick1 = onFullWorkoutClick,
            onClick2 = onFifteenMinutesClick,
        )
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
        enabled = (
            state.isGymSelected.toInt() +
                state.isHomeSelected.toInt() +
                state.isFullWorkoutSelected.toInt() +
                state.is15minSelected.toInt() == 2
            )
    ) {
        Text(
            text = "Continue",
            color = Color.White
        )
    }
}

private fun LazyListScope.Category(
    title: String,
    option1: ChoosePlanUiOption,
    option2: ChoosePlanUiOption,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
) {
    item {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }

    item {
        Row(Modifier.fillMaxWidth()) {
            Option(0.5f, option1, onClick1)
            Option(1f, option2, onClick2)
        }
    }
}

@Composable
private fun Option(
    widthRatio: Float,
    data: ChoosePlanUiOption,
    onClick: () -> Unit
) = Column(
    Modifier
        .fillMaxWidth(widthRatio)
        .padding(12.dp)
        .clickable { onClick() }
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(125.dp)
            .background(data.backgroundColor)
            .border(
                width = 2.dp,
                color = data.strokeColor
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxWidth().padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.50f),
                painter = painterResource(data.imageRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = data.imageTint)
            )

            Text(
                text = data.title,
                color = data.imageTint,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
        }
    }

    Text(
        textAlign = TextAlign.Center,
        text = data.description,
        fontSize = 12.sp,
    )
}

private fun Boolean.toInt(): Int =
    if (this) 1 else 0

@Composable
@Preview
private fun ChoosePlanPreview() = ChoosePlanUi(
    ChoosePlanState(true, false, false, true),
    { }, { }, { }, { }, { },
)