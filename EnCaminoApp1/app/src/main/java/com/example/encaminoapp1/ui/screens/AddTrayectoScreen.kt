package com.example.encaminoapp1.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.encaminoandroid.trayecto.Trayecto
import com.example.encaminoapp1.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrayectoScreen(navController: NavController) {
    var origen by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }

    var plazasText by remember { mutableStateOf("0") }
    var precioText by remember { mutableStateOf("0.0") }

    var isSubmitting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Top App Bar with Back Button
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = origen,
            onValueChange = { origen = it },
            label = { Text("Origen") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            label = { Text("Destino") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            placeholder = { Text("yyyy-mm-dd") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hora,
            onValueChange = { hora = it },
            label = { Text("Hora") },
            placeholder = { Text("hh:mm") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = plazasText,
            onValueChange = { plazasText = it },
            label = { Text("Plazas Disponibles") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused && plazasText == "0") {
                        plazasText = ""
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precioText,
            onValueChange = { precioText = it },
            label = { Text("Precio") },
            placeholder = { Text("0.0") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused && precioText == "0.0") {
                        precioText = ""
                    }
                }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                isSubmitting = true

                val plazas = plazasText.toIntOrNull() ?: 0
                val precio = precioText.toDoubleOrNull() ?: 0.0

                val nuevo = Trayecto(
                    origen = origen,
                    destino = destino,
                    fecha = fecha,
                    hora = hora,
                    plazas = plazas,
                    precio = precio
                )

                RetrofitInstance.api.createTrayecto(nuevo).enqueue(object : Callback<Trayecto> {
                    override fun onResponse(call: Call<Trayecto>, response: Response<Trayecto>) {
                        isSubmitting = false
                        if (response.isSuccessful) {
                            navController.navigate("trayecto_list") {
                                popUpTo("trayecto_list") { inclusive = true }
                            }
                        } else {
                            Log.e("API_ERROR", "Error al crear trayecto: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<Trayecto>, t: Throwable) {
                        isSubmitting = false
                        Log.e("API_ERROR", "Fallo de red: ${t.message}")
                    }
                })
            },
            enabled = !isSubmitting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSubmitting) "Guardando..." else "Añadir Trayecto")
        }
    }
}

