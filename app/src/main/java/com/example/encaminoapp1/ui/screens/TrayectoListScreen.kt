package com.example.encaminoapp1.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.encaminoandroid.trayecto.Trayecto
import com.example.encaminoapp1.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TrayectoItem(trayecto: Trayecto, onDelete: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .heightIn(min = 180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Delete icon in the top-right corner
            IconButton(
                onClick = {
                    Log.d("DELETE_BTN", "Clicked delete for id: ${trayecto.idTrayecto}")
                    trayecto.idTrayecto?.let { onDelete(it) }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar Trayecto",
                    tint = MaterialTheme.colorScheme.error
                )
            }

            Column(modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${trayecto.origen} → ${trayecto.destino}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp)
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${trayecto.precio} €",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text("Fecha: ${trayecto.fecha}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Hora: ${trayecto.hora}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Plazas Disponibles: ${trayecto.plazas}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun TrayectoListScreen(navController: NavController) {
    val trayectos = remember { mutableStateListOf<Trayecto>() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        RetrofitInstance.api.getAllTrayectos().enqueue(object : Callback<List<Trayecto>> {
            override fun onResponse(call: Call<List<Trayecto>>, response: Response<List<Trayecto>>) {
                isLoading = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        trayectos.clear()
                        trayectos.addAll(it)
                    }
                } else {
                    Log.e("API_ERROR", "Server error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Trayecto>>, t: Throwable) {
                isLoading = false
                Log.e("API_ERROR", "Connection failed: ${t.message}")
            }
        })
    }

    // Función para eliminar trayecto
    fun eliminarTrayecto(id: Long) {
        RetrofitInstance.api.deleteTrayecto(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    trayectos.removeAll { it.idTrayecto == id } // Elimina el trayecto de la lista localmente
                } else {
                    Log.e("API_ERROR", "Error al eliminar trayecto: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("API_ERROR", "Fallo de red al eliminar trayecto: ${t.message}")
            }
        })
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_trayecto") },
                modifier = Modifier.size(72.dp) // Bigger FAB
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir trayecto",
                    modifier = Modifier.size(36.dp) // Bigger icon
                )
            }
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)

        if (isLoading) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Cargando...", style = MaterialTheme.typography.bodyLarge)
            }
        } else if (trayectos.isEmpty()) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No hay trayectos. Añadir uno.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
                items(trayectos) { trayecto ->
                    TrayectoItem(trayecto = trayecto, onDelete = { id -> eliminarTrayecto(id) })
                }
            }
        }
    }
}

