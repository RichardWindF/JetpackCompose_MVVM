package com.example.jetpackcompose_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose_mvvm.ui.theme.JetpackCompose_MVVMTheme

class MainActivity03 : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("老冯", "I love Kotlin & Java"))
        }
    }

    @Composable
    fun MessageCard(msg: Message)
    {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.man),                           //这里是对无障碍支持，比如盲人的提示
                contentDescription =null
            )
            Column()
            {

                Text(text = msg.author)
                Text(text = msg.body)
            }
        }

    }
}


