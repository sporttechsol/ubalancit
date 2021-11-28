package lt.sporttech.ubalancit.features.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lt.sporttech.ubalancit.core.model.Complex
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.Exercise
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay
import lt.sporttech.ubalancit.core.model.Set
import lt.sporttech.ubalancit.util.*
import lt.sporttech.ubalancit.util.durationString
import lt.sporttech.ubalancit.R

private enum class ExercisesRelation {
    NOT_EXIST,
    DISABLED,
    ENABLED
}

@Composable
internal fun ExercisesListUi(
    state: ExercisesListState,
    onStartWorkoutClick: () -> Unit
) = when (state) {
    is ExercisesListState.Loading -> LoadingUi()
    is ExercisesListState.RelaxDay -> RelaxUi()
    is ExercisesListState.WorkoutDay -> WorkoutUi(state.data, onStartWorkoutClick)
}

@Composable
private fun LoadingUi() = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    contentAlignment = Alignment.Center
) {
    CircularProgressIndicator()
}

@Composable
private fun RelaxUi() = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    contentAlignment = Alignment.Center,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.fillMaxWidth(0.7f).aspectRatio(1.0f),
            painter = painterResource(R.drawable.relax),
            contentDescription = null,
        )

        Text(
            text = "Relax!",
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Today is your rest day.",
            color = Color.Black,
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "See you tomorrow!",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun WorkoutUi(
    data: ScheduledWorkoutDay,
    onStartWorkoutClick: () -> Unit
) = Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        for (complexIndex in data.complexes.indices) {
            val complex = data.complexes[complexIndex]
            item { Complex(complex.title) }
            item { Separator() }

            for (exerciseIndex in complex.exercises.indices) {
                item {
                    Exercise(
                        index = complexIndex,
                        data = complex.exercises[exerciseIndex],
                        relationWithPrevious = when (exerciseIndex) {
                            0 -> ExercisesRelation.NOT_EXIST
                            else -> ExercisesRelation.DISABLED
                        },
                        relationWithNext = when (exerciseIndex) {
                            complex.exercises.lastIndex -> ExercisesRelation.NOT_EXIST
                            else -> ExercisesRelation.DISABLED
                        },
                    )
                }

                item { Separator() }
            }
        }

        item {
            Spacer(Modifier.height(86.dp))
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        onClick = onStartWorkoutClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = IOS_NATIVE_BLUE,
        )
    ) {
        Text(
            text = "Start Workout",
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun Complex(
    title: String
) = Text(
    modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 16.dp),
    text = title,
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Black,
)

@Composable
private fun Exercise(
    index: Int,
    data: Exercise,
    relationWithPrevious: ExercisesRelation,
    relationWithNext: ExercisesRelation,
) {
    val isEnabled =
        (relationWithPrevious == ExercisesRelation.NOT_EXIST ||
            relationWithPrevious == ExercisesRelation.ENABLED) &&
            (index == 0)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(if (isEnabled) Color.White else Color(0xff_f3_f3_f8)),
        contentAlignment = Alignment.CenterStart
    ) {

        Column(Modifier.padding(start = 16.dp)) {
            ExercisesRelation(relationWithPrevious)

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(setsRelationColor(isEnabled))
            )


            ExercisesRelation(relationWithNext)
        }

        Text(
            modifier = Modifier.padding(start = 46.dp),
            color = setsRelationColor(isEnabled),
            text = data.title,
            fontSize = 18.sp,
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            val set = data.sets[0]
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = durationString(set.repeats, set.time),
                color = if (isEnabled) Color.Black else Color.Gray,
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
private fun ExercisesRelation(relation: ExercisesRelation) {
    if (relation != ExercisesRelation.NOT_EXIST) {
        Box(
            modifier = Modifier
                .width(9.dp)
                .height(19.dp)
                .padding(start = 7.dp)
                .background(setsRelationColor(relation == ExercisesRelation.ENABLED))
        )
    } else {
        Spacer(Modifier.height(16.dp))
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
@Preview
private fun ExercisesListUiPreview() = ExercisesListUi(
    ExercisesListState.WorkoutDay(
        ScheduledWorkoutDay(
            dayOfWeek = DayOfWeek.SUNDAY,
            complexes = listOf(
                Complex(
                    id = 0,
                    title = "Warm Up",
                    exercises = listOf(
                        Exercise(
                            id = 0,
                            title = "Joint Mobility",
                            imageRes = 0,
                            sets = listOf(
                                Set(0, null, 600_000L)
                            )
                        )
                    )
                )
            )
        ),
    ),

    {  }
)

@Composable
@Preview
private fun RelaxPreview() = ExercisesListUi(
    ExercisesListState.RelaxDay,
    {  }
)