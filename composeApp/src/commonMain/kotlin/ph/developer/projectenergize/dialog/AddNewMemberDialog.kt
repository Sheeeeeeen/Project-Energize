package ph.developer.projectenergize.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ph.developer.projectenergize.model.AddMemberDetails
import ph.developer.projectenergize.model.EmployeeId
import ph.developer.projectenergize.model.WorkMail

@Composable
fun AddNewMemberDialog(modifier: Modifier = Modifier, onBack: () -> Unit, onSave: (AddMemberDetails) -> Unit) {

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var workMail by rememberSaveable { mutableStateOf("") }
    var employeeId by rememberSaveable { mutableStateOf("") }

    AddNewMemberContent(
        modifier = modifier,
        firstName = firstName,
        onFirstNameValueChange = { firstName = it },
        lastName = lastName,
        onLastNameValueChange = { lastName = it },
        workMail = workMail,
        onWorkMailValueChange = { workMail = it },
        employeeId = employeeId,
        onEmployeeIdValueChange = { employeeId = it },
        onBack = onBack,
        onSave = {
            onSave(
                AddMemberDetails(
                    firstName = firstName,
                    lastName = lastName,
                    workMail = WorkMail(workMail),
                    employeeId = EmployeeId(employeeId)
                )
            )
        }
    )
}

@Composable
private fun AddNewMemberContent(
    modifier: Modifier = Modifier,
    firstName: String,
    onFirstNameValueChange: (String) -> Unit,
    lastName: String,
    onLastNameValueChange: (String) -> Unit,
    workMail: String,
    onWorkMailValueChange: (String) -> Unit,
    employeeId: String,
    onEmployeeIdValueChange: (String) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "close")
                }
                Text(
                    "Add New Member", style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = onSave) {
                    Text("Save", color = MaterialTheme.colors.surface)
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                value = firstName,
                onValueChange = onFirstNameValueChange,
                label = {
                    Text("First Name")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                value = lastName,
                onValueChange = onLastNameValueChange,
                label = {
                    Text("Last Name")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                value = workMail,
                onValueChange = onWorkMailValueChange,
                label = {
                    Text("Work Mail")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                value = employeeId,
                onValueChange = onEmployeeIdValueChange,
                label = {
                    Text("Employee ID")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AddNewMemberContent(
        firstName = "",
        onFirstNameValueChange = {},
        lastName = "",
        onLastNameValueChange = {},
        workMail = "",
        onWorkMailValueChange = {},
        employeeId = "",
        onEmployeeIdValueChange = {},
        onBack = {},
        onSave = {}
    )
}

