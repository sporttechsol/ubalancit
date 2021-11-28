package lt.sporttech.ubalancit.logo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import lt.sporttech.ubalancit.R

@Composable
internal fun LogoUi() = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    contentAlignment = Alignment.Center
) {
    Image(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .clip(CircleShape)
            .aspectRatio(1.0f),
        painter = painterResource(R.drawable.logo),
        contentDescription = null
    )
}