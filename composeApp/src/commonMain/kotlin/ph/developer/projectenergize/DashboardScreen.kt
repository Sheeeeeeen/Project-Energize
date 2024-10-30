package ph.developer.projectenergize

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen(){
    DashboardContent()
}

@Composable
private fun DashboardContent(){
    Calendar()
}

@Composable
@Preview
private fun Preview(){
    DashboardScreen()
}