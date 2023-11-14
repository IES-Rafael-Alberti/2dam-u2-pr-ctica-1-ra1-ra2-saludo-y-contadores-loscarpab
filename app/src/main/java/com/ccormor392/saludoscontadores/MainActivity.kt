package com.ccormor392.saludoscontadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.setValue
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
    var texto by rememberSaveable { mutableStateOf("") }
    var nombre by rememberSaveable { mutableStateOf("") }
    var dialogo by rememberSaveable { mutableStateOf(false) }
    var saludo by rememberSaveable { mutableStateOf(false) }
    var contadorCancelar by rememberSaveable { mutableStateOf(0) }
    var contadorAceptar by rememberSaveable { mutableStateOf(0) }
    var textoAceptar by rememberSaveable { mutableStateOf("Acep") }
    var textoCancelar by rememberSaveable { mutableStateOf("Canc") }

    if (contadorAceptar > 0 || contadorCancelar > 0){
        textoAceptar = "A ($contadorAceptar)"
        textoCancelar = "C ($contadorCancelar)"
    }

    if (dialogo)
        DialogExample(
            nombre = nombre,
            acepButton = {
                dialogo = false
                saludo = true
                contadorAceptar++
            },
            cancButton = {
                dialogo = false
                saludo = false
                contadorCancelar++
            },
            limpButton = {
                nombre = ""
            },
            textAcep = textoAceptar,
            textCanc = textoCancelar,
            onValueChange = { nombre = it })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { dialogo = true }) {
            Text(text = "Saludar")
        }
        texto = if (saludo) "Hola, $nombre"
        else ""
        Text(text = texto, Modifier.padding(top = 20.dp))
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogExample(
    nombre: String,
    acepButton: () -> Unit,
    cancButton: () -> Unit,
    limpButton: () -> Unit,
    textCanc:String,
    textAcep:String,
    onValueChange: (String) -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, bottom = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Configuracion",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(value = nombre, onValueChange = {onValueChange(it)}, label = {Text(text = "Introduce tu nombre")})
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .weight(1f), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { acepButton() }) {
                        Text(text = textAcep)
                    }
                    Button(onClick = { cancButton() }) {
                        Text(text = textCanc)
                    }
                    Button(onClick = { limpButton()}) {
                        Text(text = "Limp")
                    }
                }
            }
        }
    }
}

