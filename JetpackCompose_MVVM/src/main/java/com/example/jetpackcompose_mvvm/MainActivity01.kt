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

class MainActivity01 : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            
            MessageCard(name = "老冯")
        }
    }
    
    
    @Composable
    fun MessageCard(name:String)
    {
        Text(text = "I love Kotlin & Java---$name")
    }
    
    @Preview                   //预览函数是不能带参数的,预览时候显示，最终是上面的运行结果
    @Composable
    fun PreviewMessageCard()
    {
        MessageCard(name = "fy预览")
    }
    
}

