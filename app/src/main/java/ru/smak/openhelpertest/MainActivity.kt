package ru.smak.openhelpertest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.smak.openhelpertest.ui.theme.OpenHelperTestTheme

class MainActivity : ComponentActivity() {
    lateinit var dbHelper: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DbHelper(applicationContext)
        setContent {
            OpenHelperTestTheme {
                Button(onClick = {
                    dbHelper.addStudent("Иванов Иван", 19)
                    Log.i("LST", dbHelper.getSudents().joinToString())
                }) {
                    Text("Добавить студента")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenHelperTestTheme {
        Greeting("Android")
    }
}