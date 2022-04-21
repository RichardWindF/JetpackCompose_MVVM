package com.example.jetpackcomposelayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PhotographterCard(modifier: Modifier = Modifier)
{
    Row(      //注意这里用的小写设置的，下面那个用大写设置的
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)    //白色
            .clickable(onClick = {})//点击后水波纹效果， 注意后面两句顺序效果不同
            .padding(16.dp)
    )
    {
        //Image(painter =, contentDescription =)

        Surface(                       //要加图片，要先有个Surface?
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)   //黑色
        ) {
            Image(
                painter = painterResource(id = R.drawable.girl),
                contentDescription = "childrenPic"
            )

        }

        //Column()          //对文字的修改在这个圆括号中
        Column(              //对文字的修改在这个圆括号中 ,类似 布局xml 的头部,但都用 modifier
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        {
            //fontWeight字体加粗
            //fontSize
            //letterSpacing
            //style  字体
            //typography 输入方法？
            Text(text = "姓名：Alfred Sisley", fontWeight = FontWeight.Bold)

            //LocalContentAlpha.provides(ContentAlpha.medium)//下面是值是这个的简写，调透明度的
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium)
            {
            Text(text = "来访时间：3 minutes ago", style = MaterialTheme.typography.body2)   //style字体
            }
        }

    }

}