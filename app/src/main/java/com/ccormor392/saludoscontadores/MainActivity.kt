package com.ccormor392.saludoscontadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ccormor392.saludoscontadores.ui.theme.SaludosContadoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaludosContadoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainBoton()
                }
            }
        }
    }
}



@Preview
@Composable
fun MainBoton() {
    val texto by rememberSaveable{ mutableStateOf("Prueba")}

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Saludar")
        }
        Text(text = texto, Modifier.padding(top = 20.dp))
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogExample(
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, bottom = 20.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), horizontalArrangement = Arrangement.End) {
                    Text(text = "Configuracion", fontSize = 20.sp, modifier = Modifier.padding(end = 20.dp))
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .weight(2f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    TextField(value = "", onValueChange ={})
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .weight(1f), horizontalArrangement = Arrangement.Center) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                        Button(onClick = {/*TODO*/}) {
                            Text(text = "Acep")
                        }
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                        Button(onClick = { /*TODO*/}) {
                            Text(text = "Canc")
                        }
                    }
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                        Button(onClick = { /*TODO*/}) {
                            Text(text = "Limp")
                        }
                    }
                }
            }
        }
    }
}
