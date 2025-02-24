import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomePage(viewModel: CounterViewModel = viewModel()) { //Importando el viewModel
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sapee") }
            )
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = {
                        viewModel.less()
                    }
                ) {
                    Text(text = "-")
                }
                FloatingActionButton(
                    onClick = {
                        viewModel.add()
                    }
                ) {
                    Text(text = "+")
                }
            }
        }
    ) {
        paddingValues ->  LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(text = "${viewModel.count}")
            }
    }
    }
}