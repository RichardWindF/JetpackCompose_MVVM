package com.example.jetpackcomposelayouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import com.example.jetpackcomposelayouts.ui.theme.JetpackCompose_MVVMTheme


//自定义布局 仿照 Column ，3行文字
@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit     //可以有一些内容，所以设置这个
)
{
    //这里用大写Layout
    Layout(modifier = modifier, content = content)
    { measurables, constrains ->                   //这个是自己输入的，报错是因为没有调用layout-布局指定宽高
        val placeables = measurables.map { measurable ->                      //自输入一一测量
            measurable.measure(constrains)
        }

        var yPosition = 0
        //布局的大小
        layout(constrains.maxWidth, constrains.maxHeight)
        {
            placeables.forEach { placeable ->
                //设置元素的位置
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }

    }

}


@Composable
fun MyOwnColumnSample()
{
    JetpackCompose_MVVMTheme() {
        MyOwnColumn() {                      //仿照Column的效果
            Text(text = "MyOwnColumn")
            Text(text = "places items")
            Text(text = "vertically.")
            Text(text = "We have done it by hand!")
        }
    }

}