package ph.developer.projectenergize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen() {
    DashboardContent()
}

@Composable
private fun DashboardContent() {
    Calendar(modifier = Modifier.wrapContentHeight().fillMaxWidth(), content = {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    color = MaterialTheme.colors.primarySurface.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("${it.date}")
        }
    })
}

@Composable
@Preview
private fun Preview() {
    DashboardScreen()
}