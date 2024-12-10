package ph.developer.projectenergize

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ph.developer.projectenergize.dashboard.DashboardScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        DashboardScreen()
    }
}