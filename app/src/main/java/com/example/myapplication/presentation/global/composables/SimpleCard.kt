import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCard(title: String) {
    Column(
        modifier = Modifier.padding(16.dp).clip(
            RoundedCornerShape(16.dp)
        ).background(
            MaterialTheme.colorScheme.onPrimary
        ).padding(
            horizontal = 16.dp,
            vertical = 16.dp
        )
    ) {
        Text(text = title,
            color = MaterialTheme.colorScheme.primary
        )
        //Divider()
    }
}