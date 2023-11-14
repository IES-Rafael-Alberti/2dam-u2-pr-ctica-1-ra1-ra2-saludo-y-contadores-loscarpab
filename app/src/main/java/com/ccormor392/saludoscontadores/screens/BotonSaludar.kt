package com.ccormor392.saludoscontadores.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog



@Preview
@Composable
fun BotonSaludar() {
    var texto by rememberSaveable { mutableStateOf("") } //Texto mostrado debajo del boton (saludo)
    var nombre by rememberSaveable { mutableStateOf("") }// nombre al que saluda la variable texto. El nombre solo lo actualiza el dialogo
    var dialogo by rememberSaveable { mutableStateOf(false) }//Booleano que dice si se abre o se cierra el dialogo
    var saludo by rememberSaveable { mutableStateOf(false) }//Booleando que dice si has pulsado aceptar, y por tanto, el texto debe mostrar el saludo(true) o si pulsaste cancelar, que no saluda(false)
    var contadorCancelar by rememberSaveable { mutableStateOf(0) }//Int que dice cuantas veces has pulsado cancelar
    var contadorAceptar by rememberSaveable { mutableStateOf(0) }//Int que dice cuantas veces has pulsado aceptar
    var textoAceptar by rememberSaveable { mutableStateOf("Acep") }//String que se muestra en el boton Aceptar del dialogo
    var textoCancelar by rememberSaveable { mutableStateOf("Canc") }//String que se muestra en el boton Cancelar del dialogo

    //si alguno de los contadores es mayor a uno significa que se ha pulsado por primera vez un boton y tiene que mostrar los contadores
    if (contadorAceptar > 0 || contadorCancelar > 0){
        textoAceptar = "A ($contadorAceptar)"
        textoCancelar = "C ($contadorCancelar)"
    }

    if (dialogo)
        DialogExample(
            nombre = nombre,
            acepButton = {
                dialogo = false//cierras dialogo
                saludo = true//tiene que saludar
                contadorAceptar++//aumenta contador
            },
            cancButton = {
                dialogo = false//cierras dialogo
                saludo = false//no tiene que saludar
                contadorCancelar++//aumenta contador
            },
            limpButton = {
                nombre = ""//limpia el textView
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

