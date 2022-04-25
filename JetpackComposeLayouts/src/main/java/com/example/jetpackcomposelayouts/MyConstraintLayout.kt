package com.example.jetpackcomposelayouts

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstrainLayoutContent()
{
    //此时没有，需要引入依赖 compose 对 CostrainLayout 的支持,才能调用函数  ConstraintLayout()
    //file--project structure--...+ "constraintlayout"
    //不是 implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    //implementation 'androidx.constraintlayout:constraintlayout-compose:1.0.0'
    ConstraintLayout {
        //通过 creatRefs 创建使用
        val (button, text) = createRefs()         //上面按钮，下面文字（）自己输入的
        Button(
            onClick = { /*TODO*/ },
            //Modifier.constrainAs 来提供约束，引用作为它的第一个参数
            //lambda 表达式中指定约束规则
            modifier = Modifier.constrainAs(button) {
                //使用linkTo 指定约束，parent 是 ConstraintLayout 的引用
                top.linkTo(parent.top, margin = 16.dp)
            }

        ) {
            Text(text = "ButtonFy")
        }

        Text(text = "TextFy", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)     //文本的top 链接到按钮的底部
            //在 ConstraintLayout 中水平居中--注意这个语句的位置在函数体中
            //centerHorizontallyTo(button)  //这两句作用相同？
            centerHorizontallyTo(parent)
        })

    }
}

//---------------------------------------
// 形成第二个例子中图片效果的函数
@Composable
fun ConstrainLayoutContent2()
{
    ConstraintLayout {

        val (button1, button2, text) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "button1")
        }

        Text(text = "TextFy", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)     //文本的top 链接到按钮的底部
            //在 ConstraintLayout 中水平居中--注意这个语句的位置在函数体中
            centerAround(button1.end)
        })

        // 再将button1 和 text 组合起来，建立一个屏障（barrier）
        val barrier = createEndBarrier(button1, text)


        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                //左右好像都木有,只能调到 top， bottom， start，
                start.linkTo(barrier)
            }
        ) {
            Text(text = "button2")
        }


    }
}

//-------------------------------------------------------
//---------------------------------------
//  如果需求是 ，形成垂直方向中间的 guideline，右边很长文本的效果的函数
@Composable
fun LargeLayoutContent()
{
    ConstraintLayout() {
        //val (btn1,text,btn2)= this.createRefs()    //这种在kotlin 中叫多次赋值，下面这个是单次赋值
        val text = this.createRef()    //这个是单次赋值,不需要s
        val guideline = createGuidelineFromStart(0.5f)      //fraction 比例

        Text(
            text = "This is a very long,very long,very long,very longvery longvery longvery longvery longvery longvery long Text",
            Modifier.constrainAs(text)
            {
                linkTo(start = guideline,end=parent.end)
                //文字自动换行---，不加下面这句，文字并不在中间
                width= Dimension.preferredWrapContent
            }


        )
    }

}