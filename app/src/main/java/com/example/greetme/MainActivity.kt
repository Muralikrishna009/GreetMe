package com.example.greetme

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val focusManager = LocalFocusManager.current
            val scaffoldState = rememberScaffoldState()
            var textFieldState by remember {
                mutableStateOf("")
            }

            Scaffold(
                scaffoldState = scaffoldState
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = textFieldState,
                        onValueChange = {
                            textFieldState = it
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        ),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            GlobalScope.launch {
                                focusManager.clearFocus()
                                scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                            }
                        },

                        ) {
                        Text(text = "Click me")
                    }
                }
            }

        }
    }
}


