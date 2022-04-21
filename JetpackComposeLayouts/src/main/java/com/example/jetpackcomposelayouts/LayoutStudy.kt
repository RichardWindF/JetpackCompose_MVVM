package com.example.jetpackcomposelayouts

import android.icu.text.CaseMap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayoutStudy()
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutStudy in Jetpack Compose Fy")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        //系统自带的 心形 图标
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }     //这上面都是 标题栏的内容，下面是具体页面内的内容
    ) {
        //Scaffold 默认有一个填充的（外部给），我们把填充值传进去
            innerPadding->BodyContent(Modifier.padding(innerPadding))  //自写的下面函数
    }
}

@Composable
fun BodyContent(modifier: Modifier=Modifier)
{
    //两行文字
    //这里不管传入的是多少，继续填充
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Hi,there , fy")
        Text(text = "Thanks for your kind and going through the LayoutStudy-fy ")
    }

}