import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCard(title: String) {
    Column(
        modifier = Modifier.clip(
            RoundedCornerShape(16.dp)
        ).background(
            MaterialTheme.colorScheme.primaryContainer
        ).padding(
            horizontal = 16.dp,
            vertical = 16.dp
        ).fillMaxWidth()
    ) {
        Text(text = title,
            color = MaterialTheme.colorScheme.primary
        )
        //Divider()
    }
}