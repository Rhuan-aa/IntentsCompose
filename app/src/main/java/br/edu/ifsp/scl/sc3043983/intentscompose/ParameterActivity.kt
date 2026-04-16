package br.edu.ifsp.scl.sc3043983.intentscompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp

class ParameterActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialParameter = intent.getStringExtra("EXTRA_PARAMETER") ?: ""

        setContent {
            MaterialTheme {
                var parameterText by remember { mutableStateOf(initialParameter) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Parameter") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = parameterText,
                                onValueChange = { parameterText = it },
                                label = { Text("Parameter") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Button(
                                onClick = {
                                    val returnIntent = Intent().apply {
                                        putExtra("EXTRA_PARAMETER", parameterText)
                                    }
                                    setResult(RESULT_OK, returnIntent)
                                    finish()
                                },
                                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                            ) {
                                Text("Save and quit")
                            }
                        }
                    }
                )
            }
        }
    }
}