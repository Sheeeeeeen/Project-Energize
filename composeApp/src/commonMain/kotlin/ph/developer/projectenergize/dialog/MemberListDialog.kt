package ph.developer.projectenergize.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ph.developer.projectenergize.model.AddMemberDetails

@Composable
fun MemberListDialog(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    MemberListContent(
        modifier = modifier,
        onBack = onBack,
    )
}

@Composable
private fun MemberListContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar {
                Text(
                    "Member List", style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f).padding(start = 16.dp)
                )
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.Close, "close")
                }
            }
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(88) {
                MemberItemComponent(
                    modifier = Modifier
                    .clickable { }
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                )
                Divider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun MemberItemComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(40.dp).background(
                color = MaterialTheme.colors.secondary,
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "J",
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Text(text = "Jennelyn", modifier = Modifier, style = MaterialTheme.typography.h6)
            Text("jennelyn.recto@globe.com.ph")
        }
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = { }
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "Arrow Forward")
        }
    }
}

@Composable
@Preview
private fun PreviewMemmberItem() {

    MemberItemComponent(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
}

@Composable
@Preview
private fun Preview() {
    MemberListContent(onBack = {})
}

