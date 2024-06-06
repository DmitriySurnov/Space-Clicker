package com.example.task_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Background()
            Content()
        }
    }
}


@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.fon),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun Content() {
    var counter = remember { mutableIntStateOf(value = 0) }
    var coins = remember { mutableIntStateOf(value = 0) }
    var count = remember { mutableIntStateOf(value = 0) }
    var maxcount = remember { mutableIntStateOf(value = 10) }
    var level = remember { mutableIntStateOf(value = 1) }
    var progress = remember { mutableStateOf(value = 0.0f) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        FirstElement(counter)
        NumberCoins(coins)
        ShowText(
            text = "Уровень ${level.value}",
            color = Color.Red, fontSize = 6f
        )

        LinearProgressIndicator(
            progress = progress.value,
            modifier = Modifier
                .height(25.dp)
                .padding(top = 15.dp, start = 5.dp,end = 5.dp)
                .fillMaxWidth(),
            trackColor = Color.LightGray,
            color = Color.Green
        )
        ShowText(
            text = "Опыт ${count.value} из ${maxcount.value}",
            color = Color.White, fontSize = 6f
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red, shape = CircleShape)
                .clickable {
                    counter.value++
                    coins.value++
                    if (count.value == maxcount.value) {
                        count.value = 0
                        level.value++
                        maxcount.value += 10
                        progress.value = 0f
                    }
                    count.value++
                    val result: Float = (1.0 / maxcount.value).toFloat()
                    progress.value += result
                },
            contentAlignment = Alignment.Center
        ) { ShowText("Нажми", Color.White, 4f) }
    }
}

@Composable
fun FirstElement(counter: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ShowText("Вы кликнули ", Color.Green, 8f)
        ShowText(counter.value.toString(), Color.Green, 8f)
        ShowText(" раз", Color.Green, 8f)
    }
}

@Composable
fun NumberCoins(coins: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ShowText("Монеты ", Color.Yellow, 8f)
        ShowText(coins.value.toString(), Color.Yellow, 8f)
        ShowText(" шт.", Color.Yellow, 8f)
    }
}

@Composable
fun ShowText(text: String, color: Color, fontSize: Float) {
    Text(
        text = text,
        color = color,
        fontSize = TextUnit(fontSize, TextUnitType.Em)
    )
}