package lt.sporttech.ubalancit.features.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lt.sporttech.ubalancit.core.model.Feeling
import lt.sporttech.ubalancit.util.IOS_NATIVE_BLUE

@Composable
internal fun ResultUi(
    percentage: Int,
    selectedFeeling: Feeling?,
    selectFeeling: (Feeling) -> Unit,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(IOS_NATIVE_BLUE),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        modifier = Modifier.padding(vertical = 20.dp),
        text = "Your WorkOut stats",
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
    )

    Text(
        modifier = Modifier.padding(top = 20.dp),
        text = "OVERALL COMPLETE",
        color = Color.White,
        fontSize = 16.sp,
    )

    Box(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 48.dp),
            text = "${percentage}%",
            color = IOS_NATIVE_BLUE,
            fontSize = 42.sp,
        )
    }

    Text(
        modifier = Modifier.padding(bottom = 24.dp),
        text =
            if (percentage > 80) "You are doing great!"
            else "Keep charging. Let's do it better next time!",
        color = Color.White,
        fontSize = 18.sp,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .height(1.dp)
            .background(Color.White)
    )

    Text(
        modifier = Modifier.padding(vertical = 24.dp),
        text = "Let us know how was your\nday and practice today:",
        color = Color.White,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
    )
    
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        FeelingCard(
            feeling = Feeling.EASY,
            isSelected = (selectedFeeling == Feeling.EASY),
            widthFraction = 0.25f,
            selectFeeling = selectFeeling,
        )
        FeelingCard(
            feeling = Feeling.GOOD,
            isSelected = (selectedFeeling == Feeling.GOOD),
            widthFraction = 0.3333f,
            selectFeeling = selectFeeling,
        )
        FeelingCard(
            feeling = Feeling.HARD,
            isSelected = (selectedFeeling == Feeling.HARD),
            widthFraction = 0.50f,
            selectFeeling = selectFeeling,
        )
        FeelingCard(
            feeling = Feeling.TOUGH,
            isSelected = (selectedFeeling == Feeling.TOUGH),
            widthFraction = 1.00f,
            selectFeeling = selectFeeling,
        )
    }

    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = "See you tomorrow!",
        fontSize = 24.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun FeelingCard(
    feeling: Feeling,
    isSelected: Boolean,
    widthFraction: Float,
    selectFeeling: (Feeling) -> Unit,
) = Box(
    Modifier
        .fillMaxWidth(widthFraction)
        .padding(horizontal = 4.dp)
        .clickable { selectFeeling(feeling) }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Color.White else IOS_NATIVE_BLUE)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(28.dp)
                .padding(top = 4.dp),
            painter = painterResource(feeling.iconRes),
            colorFilter = ColorFilter.tint(
                color = if (isSelected) IOS_NATIVE_BLUE else Color.White,
            ),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = feeling.name,
            color = if (isSelected) IOS_NATIVE_BLUE else Color.White,
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview
private fun ResultUiPreview() = ResultUi(65, Feeling.HARD, {  })