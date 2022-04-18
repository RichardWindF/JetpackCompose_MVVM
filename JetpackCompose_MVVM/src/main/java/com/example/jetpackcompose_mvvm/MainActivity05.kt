package com.example.jetpackcompose_mvvm

import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose_mvvm.ui.theme.JetpackCompose_MVVMTheme

class MainActivity05 : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {

            //此处用Material Design 的深色背景

            JetpackCompose_MVVMTheme {
                //MessageCard(Message("老冯", "I love Kotlin & Java"))
                Coversation(SampleData.conversationSample)                    //Listview 的数据
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

            var isExpanded by remember { mutableStateOf(false) }   //remember 存储对应函数的值？？？

            val surfaceColor: Color by animateColorAsState(          //点击条目，条目变色
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            Column(
                modifier = Modifier.clickable { isExpanded = !isExpanded }    //点一下变true，点一下false
                //结合上面及下面的从而实现点一下展开，点一下折叠成一行
            )
            {

                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant
                )
                Spacer(modifier = Modifier.height(10.dp))

                androidx.compose.material.Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                )
                {

                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2,

                        maxLines = if (isExpanded) Int.MAX_VALUE else 1

                    )
                }
            }
        }

    }


    //ListView 的显示在这里精简成了这个样子，有数据源， 没有 adapter, 没有条目布局（用条目函数代替？）
    @Composable
    fun Coversation(messages: List<Message>)
    {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }

        //LazyRow(content = )
    }
}


