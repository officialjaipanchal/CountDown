
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.DisplayScreen
import com.example.androiddevchallenge.ui.SettingScreen2
import com.example.androiddevchallenge.ui.theme.MyTheme

enum class Screen {
    Setting, Display
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(darkTheme = false) {
                MyApp()
            }
        }
    }
}

fun Long.toTime(): Pair<Int, Int> {
    return (this / 60).toInt() to (this % 60).toInt()
}

fun Pair<Int, Int>.toNum(): Int {
    return first * 60 + second
}

// Start building your app here!
@Composable
fun MyApp() {

    Surface(color = MaterialTheme.colors.background) {
        var screen by remember { mutableStateOf(Screen.Setting) }
        var timeInSec by remember { mutableStateOf(0) }

        Crossfade(targetState = screen) {
            when (screen) {
                Screen.Setting -> SettingScreen2 {
                    screen = Screen.Display
                    timeInSec = it
                }
                Screen.Display -> DisplayScreen(timeInSec) {
                    screen = Screen.Setting
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
