package com.example.finalprjectpam.ui.view.instrukturview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprjectpam.ui.customewidget.CostumeTopAppBar
import com.example.finalprjectpam.ui.navigasi.DestinasiNavigasi
import com.example.finalprjectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.InsertIViewModel
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.InsertUiEvent
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.InsertUiState
import kotlinx.coroutines.launch

object DestinasiEntryI: DestinasiNavigasi {
    override val route = "insertInstruktur"
    override val titleRes = "Insert Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreenI(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertIViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryI.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
            innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onInstrukturValueChange = viewModel::updateInsertInsState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertIns()
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
    onInstrukturValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onInstrukturValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF60d4cc))
        ){
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true

){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.idInstruktur,
            onValueChange = {onValueChange(insertUiEvent.copy(idInstruktur = it))},
            label = { Text(text = "Id Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.namaInstruktur,
            onValueChange = {onValueChange(insertUiEvent.copy(namaInstruktur = it))},
            label = { Text(text = "Nama Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.emailI,
            onValueChange = {onValueChange(insertUiEvent.copy(emailI = it))},
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.noTeleponI,
            onValueChange = {onValueChange(insertUiEvent.copy(noTeleponI = it))},
            label = { Text(text = "Nomor telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsiI,
            onValueChange = {onValueChange(insertUiEvent.copy(deskripsiI = it))},
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}