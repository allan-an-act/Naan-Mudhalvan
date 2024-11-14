package com.example.emailapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SendMailActivity : ComponentActivity() {
    private lateinit var databaseHelper: EmailDatabaseHelper

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = EmailDatabaseHelper(this)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = Color(0xFFadbef4),
                        modifier = Modifier.height(80.dp),
                        title = {
                            Text(
                                text = "Send Mail",
                                fontSize = 32.sp,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    )
                }
            ) {
                openEmailer(this, databaseHelper)
            }
        }
    }
}

@Composable
fun openEmailer(context: Context, databaseHelper: EmailDatabaseHelper) {
    var recevierMail by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1e3c72), Color(0xFF2a5298))
                )
            )
            .padding(top = 55.dp, bottom = 25.dp, start = 25.dp, end = 25.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Receiver Email-Id",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        TextField(
            value = recevierMail,
            onValueChange = { recevierMail = it },
            label = { Text(text = "Email address", color = Color.White) },
            placeholder = { Text(text = "abc@gmail.com", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF8e44ad),
                unfocusedIndicatorColor = Color.White,
                cursorColor = Color.White,
                textColor = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Mail Subject",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        TextField(
            value = subject,
            onValueChange = { subject = it },
            placeholder = { Text(text = "Subject", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF8e44ad),
                unfocusedIndicatorColor = Color.White,
                cursorColor = Color.White,
                textColor = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Mail Body",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        TextField(
            value = body,
            onValueChange = { body = it },
            placeholder = { Text(text = "Body", color = Color.Gray) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF8e44ad),
                unfocusedIndicatorColor = Color.White,
                cursorColor = Color.White,
                textColor = Color.White
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (recevierMail.isNotEmpty() && subject.isNotEmpty() && body.isNotEmpty()) {
                    val email = Email(
                        id = null,
                        recevierMail = recevierMail,
                        subject = subject,
                        body = body
                    )
                    databaseHelper.insertEmail(email)
                    error = "Mail Saved"
                } else {
                    error = "Please fill all fields"
                }

                val i = Intent(Intent.ACTION_SEND)
                val emailAddress = arrayOf(recevierMail)
                i.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                i.putExtra(Intent.EXTRA_SUBJECT, subject)
                i.putExtra(Intent.EXTRA_TEXT, body)
                i.type = "message/rfc822"
                ctx.startActivity(Intent.createChooser(i, "Choose an Email client : "))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8e44ad)),
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Send Email",
                color = Color.White,
                fontSize = 15.sp
            )
        }

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color(0xFFe74c3c),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
