package ai.rever.boss.components.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun FirstSessionBanner(
    onOpenToolbox: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showAgentAddress by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface.copy(alpha = 0.9f))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Icon + Text
            Icon(
                imageVector = Icons.Default.Celebration,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Welcome to BOSS — here's where to start",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Center: Three action buttons
            TextButton(onClick = onOpenToolbox) {
                Icon(Icons.Default.FolderOpen, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Open a Workspace")
            }

            TextButton(onClick = { showAgentAddress = !showAgentAddress }) {
                Icon(Icons.Default.Link, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Connect an Agent")
            }

            TextButton(onClick = onOpenToolbox) {
                Icon(Icons.Default.Extension, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text("Open Toolbox")
            }

            Spacer(modifier = Modifier.weight(1f))

            // Right: Close button
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss welcome banner",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

        if (showAgentAddress) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Point your agent at ",
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = "127.0.0.1:7677",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                IconButton(
                    onClick = { clipboardManager.setText(AnnotatedString("127.0.0.1:7677")) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy address",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}
