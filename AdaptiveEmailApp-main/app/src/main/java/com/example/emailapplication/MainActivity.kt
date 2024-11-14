package com.example.emailapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue),
            ) {
                Email(this)
            }
        }
    }
}

@Composable
fun Email(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Emails",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    blurRadius = 4f
                )
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.home_screen),
            contentDescription = "Home Screen Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, SendMailActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF35fa03)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            elevation = ButtonDefaults.elevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = "Send Email",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, ViewMailActivity::class.java))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFfa0303)),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            elevation = ButtonDefaults.elevation(defaultElevation = 6.dp)
        ) {
            Text(
                text = "Sent Emails",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
