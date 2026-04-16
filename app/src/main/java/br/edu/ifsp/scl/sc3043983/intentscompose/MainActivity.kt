package br.edu.ifsp.scl.sc3043983.intentscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            MaterialTheme {
                var currentParameter by remember { mutableStateOf("Not set yet!!!") }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Intents Compose") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "Set Parameter"
                                    )
                                }

                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Language,
                                        contentDescription = "Web"
                                    )
                                }

                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Phone,
                                        contentDescription = "Dial"
                                    )
                                }

                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Call,
                                        contentDescription = "Call"
                                    )
                                }
                            }

                        )
                    },
                    content = { innerPadding ->
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
                )
            }
        }
    }
}