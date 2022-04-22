package com.example.jetpackcomposelayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

//这个就类似 ListView 了
@Composable
fun SimpleList()
{
    //Column , Row 默认是不滚动的，如果要滚动
    val rememberScrollState = rememberScrollState()

    Column(
        Modifier.verticalScroll(rememberScrollState)
    )
    {
        repeat(100)
        {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.woman),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterVertically)
                )
                {
                    Text(text = "Item #$it")        //显示出系列号
                    Text(text = "名字： 张三", style = MaterialTheme.typography.subtitle1)
                    //字体
                    Text(text = "age:  25")
                }
            }
        }
    }
}

//---------------------------
//如果不知道行数，怎么办呢？ 使用 LazyColumn 或 LazyRow   //这个就类似 RecyclerView 了
@Composable
fun LazyList()
{
    //Column , Row 默认是不滚动的，如果要滚动
    //val rememberScrollState = rememberScrollState()
    val rememberLazyListState = rememberLazyListState()

    LazyColumn(state = rememberLazyListState)
    {
        items(6)    //数量这里输入？
        {
            getLazyLists(it)
        }
    }

}

@Composable
private fun getLazyLists(it: Int)
{
    Row() {
        Image(
            painter = painterResource(id = R.drawable.woman),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Column(
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterVertically)
        )
        {
            Text(text = "Item #$it-lazyLists")        //显示出系列号
            Text(text = "名字： 张三", style = MaterialTheme.typography.subtitle1)
            //字体
            Text(text = "age:  25")
        }
    }
}

//-----------------------------------------------
//--例子，如果两个按钮  Scroll to the top,  Scroll to the end,程序中改变state， 来完成滑动
@Composable
fun ScrollListFy()
{
    val listSize=100   //数量这里输入？
    val rememberLazyListState = rememberLazyListState()

    //携程有一个作用域，当作用域消失后，所有里面的携程都取消了？
    val coroutineScope= rememberCoroutineScope()     //通过这个可以拿到一个携程的作用域

    Column() {
        Row() {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    //这个挂起函数只能在挂起函数中调用，怎么办？--引入了携程--？ 类似子线程吗？

                    //通过这个方法可以拿到一个携程的作用域，启动一个携程？
                    coroutineScope.launch {
                    rememberLazyListState.animateScrollToItem(0)
                        //滚动到顶部
                    }
                }
            ) {
                Text(text = "Scroll to Top")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    //通过这个方法可以拿到一个携程的作用域，启动一个携程？
                    coroutineScope.launch {
                        rememberLazyListState.animateScrollToItem(listSize-1)
                        //滚动到底部
                    }
                }
            ) {
                Text(text = "Scroll to End")
            }
        }




        LazyColumn(state = rememberLazyListState)
        {
            items(listSize)    //数量这里输入？
            {
                // getLazyLists(it) //从本地的图片
                ImageListItem(it) //网上图片
            }
        }
    }
}

//除了上面可以用 modifier 设置居中， 也可以用Row或者 Column本身的 verticalAlignment 什么的来设置
//这里用一个函数演示一下
@Composable
fun ImageListItem(index: Int)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,


        ) {

        Image(
            //这里使用Coil 去加载各种图片 ， 在kotlin 中最为方便
            //painter = painterResource(id = R.drawable.ic_launcher_foreground),
            //painter = painterResource("https://i.hnh.cc/shuoshuo/qq12737.jpg"),
            //painter = rememberImagePainter("https://i.hnh.cc/shuoshuo/qq12737.jpg"),      //弃用，提示改用下面这个
            painter = rememberAsyncImagePainter("https://i.hnh.cc/shuoshuo/qq12737.jpg"),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)      //这句和最下面那句均能让 item的行距增加
        )

        Spacer(modifier = Modifier.size(10.dp))     //图片与文本之间隔一个距离
        Column() {

            Text(text = "Item #$index- 网路加载图片")        //显示出系列号
            Text(text = "名字： 张三", style = MaterialTheme.typography.subtitle1)
            //字体
            Text(text = "age:  25")
        }
    }

    //Spacer(modifier = Modifier.size(20.dp))
}