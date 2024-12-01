package ph.developer.projectenergize

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen() {
    DashboardContent()
}

@Composable
private fun DashboardContent(modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = { 3 }) // Number of pages

    val isArrowLeftVisible by derivedStateOf { pagerState.canScrollBackward }

    val isArrowRightVisible by derivedStateOf { pagerState.canScrollForward }

    val currentIndex = pagerState.currentPage

    var showAddMemberScreen by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(modifier = Modifier, backgroundColor = MaterialTheme.colors.background) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp).weight(1f),
                    text = "TRX Class",
                    style = MaterialTheme.typography.h6
                )
                IconButton(modifier = Modifier, onClick = {
                    showAddMemberScreen = showAddMemberScreen.not()
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Arrow Forward")
                }
            }
        },
    ) {
        Calendar(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(it),
            content = {

                if (showAddMemberScreen) {
                    Dialog(
                        properties = DialogProperties(usePlatformDefaultWidth = false),
                        onDismissRequest = { showAddMemberScreen = false }
                    ) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Text("Dialog!", modifier = Modifier.clickable { showAddMemberScreen = false })
                        }
                    }
                }

                Column {
                    Divider(modifier = Modifier.fillMaxWidth())
                    Box {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                        ) { page ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Page $page", style = MaterialTheme.typography.body1)
                            }
                        }

                        androidx.compose.animation.AnimatedVisibility(
                            visible = isArrowLeftVisible,
                            modifier = Modifier.align(alignment = Alignment.CenterStart),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(onClick = {
                                scope.launch { pagerState.animateScrollToPage(page = currentIndex - 1) }
                            }) {
                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Arrow left")
                            }
                        }

                        androidx.compose.animation.AnimatedVisibility(
                            visible = isArrowRightVisible,
                            modifier = Modifier.align(alignment = Alignment.CenterEnd),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(onClick = {
                                scope.launch { pagerState.animateScrollToPage(page = currentIndex + 1) }
                            }) {
                                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Arrow right")
                            }
                        }
                    }
                }
            }
        )
    }

}

@Composable
@Preview
private fun Preview() {
    DashboardScreen()
}