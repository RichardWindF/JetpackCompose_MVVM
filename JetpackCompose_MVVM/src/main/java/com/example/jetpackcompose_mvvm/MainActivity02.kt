package com.example.jetpackcompose_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose_mvvm.ui.theme.JetpackCompose_MVVMTheme

class MainActivity02 : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("老冯","I love Kotlin & Java"))
        }
    }

    @Composable
    fun MessageCard(msg:Message)
    {
//        Text(text = "I love Kotlin & Java")
//        Text(text = "作者：   $name")

         Text(text = msg.author)
        Text(text = msg.body)
    }
}

data class Message(val author:String, val body:String)

