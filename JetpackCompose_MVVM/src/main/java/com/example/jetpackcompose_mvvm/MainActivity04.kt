package com.example.jetpackcompose_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose_mvvm.ui.theme.JetpackCompose_MVVMTheme

class MainActivity04 : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {

            //此处用Material Design 的深色背景

            JetpackCompose_MVVMTheme {
                MessageCard(Message("老冯", "I love Kotlin & Java"))
            }
        }
    }

    @Composable
    fun MessageCard(msg: Message)
    {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                //.background(Color.Red)) {
                .background(MaterialTheme.colors.background)    //背景根据手机设置的背景自动切换深色背景，或者浅色背景
        )
        {
            Image(
                painter = painterResource(id = R.drawable.man),                           //这里是对无障碍支持，比如盲人的提示
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Column()
            {

                Text(text = msg.author)
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = msg.body)
            }
        }

    }
}


