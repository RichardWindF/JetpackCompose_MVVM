package com.example.jetpackcomposelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposelayouts.ui.theme.JetpackCompose_MVVMTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            //注意这个 moudleNameTheme 名字好像不能换，多模块怎办呢？
            JetpackCompose_MVVMTheme{
               // PhotographterCard()

             //   LayoutStudy()

              //  SimpleList()       // 文件Lists.kt 中的函数
              //  LazyList()

              //  ScrollListFy()        //网上加载图片

              //  TextWithPddingToBaseline()     //FirstBaseLineToTop.kt 中的函数

                MyOwnColumnSample()
            }

        }
    }
}

