package com.example.jetpackcomposelayouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.max
import kotlin.math.max

val textContents = listOf<String>(
    "Canada@USA",
    "Canada",
    "China",
    "Japan",
    "Korean",

    "Canada@USA2",
    "Canada2",
    "China2",
    "Japan2",
    "Korean2",

    "Canada@USA3",
    //共11个元素
)


@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier.padding(10.dp),    //这个让每个卡片不紧贴屏幕边框
    rows: Int = 3,
    content: @Composable () -> Unit
)
{
    // Chip(modifier,"Canada@USA")
    repeat(textContents.size)
    {
        Chip(modifier, textContents.get(it))

    }
    //此时会重叠在一起，所以 自定义布局要计算出宽度，高度

    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        //用于保存每行的宽度值
        val rowWidths = IntArray(rows) { 0 }
        //用于保存每行的高度值
        val rowHeights = IntArray(rows) { 0 }

        //测量每个元素的值？
        val placeables = measurables.mapIndexed { index, measurable ->
            //测量每个元素
            val placeable = measurable.measure(constraints)

            //计算每行的宽度与高度
            //元素下标0~10，假设总共11个元素，
            //行数假设3行， rows=3    下标0~2
            //保存行宽高 数组的下标值
            val row = index % rows   //总是0，1，2
            //一行宽度为 这一行所有元素之和
            rowWidths[row] += placeable.width
            //一行的高度，是这行高度的最大值
            rowHeights[row] = max(rowHeights[row], placeable.height)
        }

        //计算表格的宽高
        val width = rowWidths.maxOrNull() ?: constraints.minWidth  //为空时候得最小宽度
        //表格高度是所有行的高度之和
        val height = rowHeights.sumOf { it }

        //设置每一行的元素的Y 坐标
        val rowY = IntArray(rows) { 0 }
        //第一行Y 坐标为0，row[0]=0,
        for (i in 1 until rows)
        {
            //rowY[1]=rowY[0]+rowHeights[0]
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        //设置每一行的元素的x 坐标


        layout(width, height)
        {
            val rowX = IntArray(rows) { 0 }
            //设置每个元素的坐标
            placeables.forEachIndexed { index,placeable ->

                val row = index % rows
//                placeable.placeRelative(     //红线不能调用
//                    x = rowX[row],
//                    y = rowY[row]
//                )

//                rowX[row]+=placeable.width     //红线不能调用
            }
        }

    }


}

@Composable
fun Chip(
    modifier: Modifier,
    text: String
)
{
    //一个卡片，圆角，里面包含一个Row, 第一列是方框BOX, 第二列是文本
    Card(     //内部的函数Card（）
        modifier = modifier,

        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(
            modifier = Modifier.padding(8.dp, 4.dp, 8.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            ) {}    //{}可以省略掉的

            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)

        }

    }
}


@Composable
fun StaggeredGridBodyContent(modifier: Modifier=Modifier)
{
    Row(
        modifier= modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),  //水平可滚动
        content = {
            StaggeredGrid(modifier=Modifier) {
                for(topic in textContents)   // //红线不能调用
                {
                    Chip(modifier = Modifier.padding(8.dp), text =topic )
                }
            }
        }
    )
}