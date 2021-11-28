package lt.sporttech.ubalancit.features.chooseplan

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import lt.sporttech.ubalancit.util.IOS_NATIVE_BLUE
import lt.sporttech.ubalancit.R

data class ChoosePlanUiOption(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String,
    val strokeColor: Color,
    val imageTint: Color,
    val backgroundColor: Color,
)

internal fun gymOption(state: ChoosePlanState) = ChoosePlanUiOption(
    imageRes = R.drawable.place_gym,
    title = "Gym",
    description = "Training plan for those who has access to gym equipment.",
    strokeColor = strokeColor(state.isGymSelected),
    imageTint = imageTint(state.isGymSelected),
    backgroundColor = backgroundColor(state.isGymSelected),
)

internal fun homeOption(state: ChoosePlanState) = ChoosePlanUiOption(
    imageRes = R.drawable.place_home,
    title = "Home",
    description = "Plan that you can do anywhere and almost without any equipment.",
    strokeColor = strokeColor(state.isHomeSelected),
    imageTint = imageTint(state.isHomeSelected),
    backgroundColor = backgroundColor(state.isHomeSelected),
)

internal fun fullWorkoutOption(state: ChoosePlanState) = ChoosePlanUiOption(
    imageRes = R.drawable.workout_full,
    title = "Full workout",
    description = "Training plan for the best results.",
    strokeColor = strokeColor(state.isFullWorkoutSelected),
    imageTint = imageTint(state.isFullWorkoutSelected),
    backgroundColor = backgroundColor(state.isFullWorkoutSelected),
)

internal fun fifteenMinutesOption(state: ChoosePlanState) = ChoosePlanUiOption(
    imageRes = R.drawable.workout_15min,
    title = "15 minutes",
    description = "Short plan for those who likes it quick.",
    strokeColor = strokeColor(state.is15minSelected),
    imageTint = imageTint(state.is15minSelected),
    backgroundColor = backgroundColor(state.is15minSelected),
)

private fun strokeColor(isSelected: Boolean): Color =
    if (isSelected) IOS_NATIVE_BLUE else Color.Gray

private fun imageTint(isSelected: Boolean): Color =
    if (isSelected) Color.White else IOS_NATIVE_BLUE

private fun backgroundColor(isSelected: Boolean): Color =
    imageTint(!isSelected)