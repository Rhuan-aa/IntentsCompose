package br.edu.ifsp.scl.sc3043983.intentscompose

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var currentParameter by remember { mutableStateOf("Not set yet!!!") }
                val context = LocalContext.current
                val callPermissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        val callIntent = Intent(Intent.ACTION_CALL).apply {
                            data = "tel:$currentParameter".toUri()
                        }
                        context.startActivity(callIntent)
                    } else {
                        Toast.makeText(
                            context,
                            "Permission required!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Intents Compose") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Set Parameter"
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        val uri = currentParameter.toUri()
                                        val navigatorIntent = Intent(Intent.ACTION_VIEW, uri)
                                        context.startActivity(navigatorIntent)
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Language,
                                        contentDescription = "Web"
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                            data = "tel:$currentParameter".toUri()
                                        }
                                        context.startActivity(dialIntent)
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Phone,
                                        contentDescription = "Dial"
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        val permission = Manifest.permission.CALL_PHONE
                                        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                                            val callIntent = Intent(Intent.ACTION_CALL).apply {
                                                data = "tel:$currentParameter".toUri()
                                            }
                                            context.startActivity(callIntent)
                                        } else {
                                            callPermissionLauncher.launch(permission)
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Call,
                                        contentDescription = "Call"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = currentParameter,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}