import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.presentation.pages.login.controller.LoginViewModel
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginPage(viewModel: LoginViewModel = viewModel()) {

    val state by viewModel.state.collectAsState()
    val corutine = rememberCoroutineScope()
    Scaffold(
        topBar = {TopAppBar(title = {Text(text = "Caja Maynas")})}
    ) {
        paddingValues ->  LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                TextField(
                    enabled = state.enabled,
                    value = state.email,
                    onValueChange = viewModel::onEmailChange,
                    label = { Text(text = "Email")},
                    isError = state.emailError != null
                )
                if(state.emailError != null) {
                    Text(text = state.emailError!!, color = MaterialTheme.colorScheme.error)
                }
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                TextField(
                    enabled = state.enabled,
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    label = { Text(text = "Password")},
                    isError = state.passwordError != null
                )
                if(state.passwordError != null) {
                    Text(text = state.passwordError!!, color = MaterialTheme.colorScheme.error)
                }

                Button (
                    onClick = {
                        corutine.launch {
                            viewModel.onEnter()
                        }
                    },
                    enabled = state.enabled,
                ) {
                    if(state.enabled) {
                        Text(text = "Iniciar Sesión")
                    } else {
                        CircularProgressIndicator()
                    }
                }
            }
    }
    }
}