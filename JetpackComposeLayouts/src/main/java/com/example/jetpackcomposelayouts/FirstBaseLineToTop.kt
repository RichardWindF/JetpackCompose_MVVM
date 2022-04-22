package com.example.jetpackcomposelayouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposelayouts.ui.theme.JetpackCompose_MVVMTheme

// 自定义布局 ， layout ， modifier 修饰符， 定义文字底部到父容器的顶部距离25dp

fun Modifier.firstBaselineToTop(                     //自定义的扩展函数？
    firstBaselineToTop: Dp
) =this.then(
    layout{measurable, constraints ->
        //测量元素
        val placeable=measurable.measure(constraints)
        //测量后获得元素的基线值
        val firstBaseline=placeable[FirstBaseline]
        //元素新的Y 坐标=新基线值-旧基线值
        val placeableY=firstBaselineToTop.roundToPx()-firstBaseline
        val height=placeable.height+placeableY

     layout(placeable.width,height)
     {
         //设置元素的位置
         placeable.placeRelative(0,placeableY)
     }


    }
)


@Composable
fun TextWithPddingToBaseline()
{
    JetpackCompose_MVVMTheme {                      //此处是因为Theme。kt 中自动形成的这个函数名
        Text(
            text = "Hi,there!",
            Modifier
               // .padding(25.dp)  //这里期望使用上面的自定义
                .firstBaselineToTop(50.dp)
                //使用这个下面会变红，因为链式编程，需要返回本身的对象，所以上面修改成 this.then()-返回当前的modifier

                .background(Color.Red)
        )
    }
}