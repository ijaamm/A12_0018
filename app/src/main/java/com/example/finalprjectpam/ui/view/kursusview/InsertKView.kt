package com.example.finalprjectpam.ui.view.kursusview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprjectpam.ui.customewidget.CostumeTopAppBar
import com.example.finalprjectpam.ui.navigasi.DestinasiNavigasi
import com.example.finalprjectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.InsertKViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.InsertUiEvent
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.InsertUiState
import kotlinx.coroutines.launch

object DestinasiEntryK: DestinasiNavigasi {
    override val route = "insertKursus"
    override val titleRes = "Insert Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreenK(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryK.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
            innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onKursusValueChange = viewModel::updateInsertKrsState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKrs()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onKursusValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onKursusValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF060d4cc))
        ){
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent = InsertUiEvent(),
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true

){
    val kategoi = listOf("Saintek", "Soshum")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.idKursus,
            onValueChange = {onValueChange(insertUiEvent.copy(idKursus = it))},
            label = { Text(text = "Id Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.namaKursus,
            onValueChange = {onValueChange(insertUiEvent.copy(namaKursus = it))},
            label = { Text(text = "Nama Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiK,
            onValueChange = {onValueChange(insertUiEvent.copy(deskripsiK = it))},
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Text(text = "Kategori")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            kategoi.forEach{kt ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = insertUiEvent.kategori == kt,
                        onClick = {
                            onValueChange(insertUiEvent.copy(kategori = kt))
                        },
                    )
                    Text(
                        text = kt,
                    )
                }
            }
        }
        OutlinedTextField(
            value = insertUiEvent.harga,
            onValueChange = {onValueChange(insertUiEvent.copy(harga = it))},
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idInstruktur,
            onValueChange = {onValueChange(insertUiEvent.copy(idInstruktur = it))},
            label = { Text(text = "Id Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}