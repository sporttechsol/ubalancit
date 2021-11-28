package lt.sporttech.ubalancit.features.workout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lt.sporttech.ubalancit.R
import lt.sporttech.ubalancit.core.model.*
import lt.sporttech.ubalancit.core.model.Set
import lt.sporttech.ubalancit.util.COLOR_DONE
import lt.sporttech.ubalancit.util.COLOR_SKIP
import lt.sporttech.ubalancit.util.COLOR_SO_SO

private enum class SetsRelation {
    NOT_EXIST,
    DISABLED,
    ENABLED
}

@Composable
internal fun WorkoutUi(
    state: WorkoutState,
    submitResult: (SetResult) -> Unit,
) = when (state) {
    is WorkoutState.Progressing -> Exercise(
        state.day.complexes[state.complexIndex].exercises[state.exerciseIndex],
        state.setIndex,
        state::setResultInCurrentExercise,
        submitResult,
    )
    is WorkoutState.Loading -> LoadingUi()
}

@Composable
private fun LoadingUi() {
    // TODO...
}

@Composable
private fun Exercise(
    exercise: Exercise,
    currentSetIndex: Int,
    setResultInCurrentExercise: (setIndex: Int) -> SetResult?,
    submitResult: (SetResult) -> Unit,
) = Column(
    Modifier
        .fillMaxSize()
        .background(Color.White)) {

    Image(exercise.imageRes)

    LazyColumn(Modifier.fillMaxSize()) {
        item { Title(exercise.title) }
        item { Separator() }

        for (setIndex in exercise.sets.indices) {
            item {
                Set(
                    index = setIndex,
                    data = exercise.sets[setIndex],
                    relationWithPrevious = when {
                        setIndex == 0 -> SetsRelation.NOT_EXIST
                        setIndex <= currentSetIndex -> SetsRelation.ENABLED
                        else -> SetsRelation.DISABLED
                    },
                    relationWithNext = when {
                        setIndex == exercise.sets.lastIndex -> SetsRelation.NOT_EXIST
                        setIndex < currentSetIndex -> SetsRelation.ENABLED
                        else -> SetsRelation.DISABLED
                    },
                    result = setResultInCurrentExercise(setIndex),
                    submitResult = submitResult,
                )
            }
            item { Separator() }
        }
    }
}

@Composable
private fun Set(
    index: Int,
    data: Set,
    relationWithPrevious: SetsRelation,
    relationWithNext: SetsRelation,
    result: SetResult?,
    submitResult: (SetResult) -> Unit,
) {
    val isSetEnabled =
        relationWithPrevious == SetsRelation.NOT_EXIST ||
            relationWithPrevious == SetsRelation.ENABLED

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(if (isSetEnabled) Color.White else Color(0xff_f3_f3_f8)),
        contentAlignment = Alignment.CenterStart
    ) {

        Column(Modifier.padding(start = 16.dp)) {
            SetsRelation(relationWithPrevious)

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(setsRelationColor(isSetEnabled))
            )

            SetsRelation(relationWithNext)
        }

        Text(
            modifier = Modifier.padding(start = 46.dp),
            color = setsRelationColor(isSetEnabled),
            text = "Set ${index + 1}",
            fontSize = 18.sp,
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            when {
                result == SetResult.DONE -> Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 16.dp),
                    painter = painterResource(R.drawable.ic_done),
                    colorFilter = ColorFilter.tint(COLOR_DONE),
                    contentDescription = null,
                )

                result == SetResult.SO_SO -> Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 16.dp),
                    painter = painterResource(R.drawable.ic_done),
                    colorFilter = ColorFilter.tint(COLOR_SO_SO),
                    contentDescription = null,
                )

                result == SetResult.SKIP -> Image(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 16.dp),
                    painter = painterResource(R.drawable.ic_skip),
                    colorFilter = ColorFilter.tint(COLOR_SKIP),
                    contentDescription = null,
                )

                result == null && isSetEnabled -> Row {
                    SetResultButton(
                        color = COLOR_SKIP,
                        iconRes = R.drawable.ic_skip,
                        text = "SKIP",
                        onClick = { submitResult(SetResult.SKIP) },
                    )
                    SetResultButton(
                        color = COLOR_SO_SO,
                        iconRes = R.drawable.ic_done,
                        text = "SO-SO",
                        onClick = { submitResult(SetResult.SO_SO) },
                    )
                    SetResultButton(
                        color = COLOR_DONE,
                        iconRes = R.drawable.ic_done,
                        text = "DONE",
                        onClick = { submitResult(SetResult.DONE) },
                    )
                }

                else -> Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = durationString(data.repeats, data.time),
                    color = if (isSetEnabled) Color.Black else Color.Gray,
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
private fun SetsRelation(relation: SetsRelation, ) {
    if (relation != SetsRelation.NOT_EXIST) {
        Box(
            modifier = Modifier
                .width(9.dp)
                .height(19.dp)
                .padding(start = 7.dp)
                .background(setsRelationColor(relation == SetsRelation.ENABLED))
        )
    } else {
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SetResultButton(
    color: Color,
    @DrawableRes iconRes: Int,
    text: String,
    onClick: () -> Unit,
) = Row(
    Modifier
        .fillMaxHeight()
        .background(color)
        .clickable { onClick() },
    verticalAlignment = Alignment.CenterVertically,
) {
    Image(
        modifier = Modifier
            .size(24.dp)
            .padding(8.dp),
        painter = painterResource(iconRes),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null
    )

    Text(
        modifier = Modifier.padding(end = 16.dp),
        text = text,
        color = Color.White,
        fontSize = 16.sp,
    )
}

@Composable
private fun Image(
    @DrawableRes imageRes: Int
) = Image(
    modifier = Modifier.fillMaxWidth(),
    painter = painterResource(imageRes),
    contentDescription = null,
    contentScale = ContentScale.Crop
)

@Composable
private fun Title(title: String) = Text(
    modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp),
    text = title,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
)

@Composable
private fun Separator() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.Gray)
)

@Preview
@Composable
private fun WorkoutUiPreview() = WorkoutUi(
    WorkoutState.Progressing(
        ScheduledWorkoutDay(
            DayOfWeek.TUESDAY,
            complexes = listOf(
                Complex(
                    "Main Complex",
                    exercises = listOf(
                        Exercise(
                            "Wide push ups",
                            R.drawable.incline_push_ups,
                            listOf(
                                Set(10, 15_000L),
                                Set(10, 15_000L),
                                Set(null, 15_000L),
                                Set(null, 150_000L),
                                Set(null, 15_000L),
                            )
                        )
                    )
                )
            )
        ),

        complexIndex = 0,
        exerciseIndex = 0,
        setIndex = 3,
        setResults = listOf(
            SetResult.DONE,
            SetResult.SKIP,
            SetResult.SO_SO,
        )
    ),
    {  },
)