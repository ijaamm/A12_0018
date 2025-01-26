package com.example.finalprjectpam.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.finalprjectpam.R
import com.example.finalprjectpam.ui.navigasi.DestinasiNavigasi


object HomePage : DestinasiNavigasi {
    override val route = "home page"
    override val titleRes = "Home page"
}
@Composable
fun Splashview(
    onSiswaButton: () -> Unit,
    onInstrukturButton: () -> Unit,
    onKursusButton: () -> Unit,
    onPendaftaranButton: () -> Unit,

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(
                    id = R.color.white
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.kursus
            ),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF69b3cc)
            ),
            onClick = {
                onSiswaButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Siswa",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF75afc4)
            ),
            onClick = {
                onInstrukturButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Instruktur",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7fabbb)
            ),
            onClick = {
                onKursusButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Kursus",
            )
        }
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF87a7b3)
            ),
            onClick = {
                onPendaftaranButton()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Text(
                text = "Pendaftaran",
            )
        }
    }
}