package com.example.finalprjectpam.ui.view.pendaftaranview

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
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.InsertPViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.InsertUiEvent
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.InsertUiState
import kotlinx.coroutines.launch

object DestinasiEntryP: DestinasiNavigasi {
    override val route = "insertPendaftaran"
    override val titleRes = "Insert Pendaftaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreenP(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryP.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
            innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onPendaftaranValueChange = viewModel::updateInsertPdftState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPdft()
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
    onPendaftaranValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onPendaftaranValueChange,
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
            value = insertUiEvent.idPendaftaran,
            onValueChange = {onValueChange(insertUiEvent.copy(idPendaftaran = it))},
            label = { Text(text = "Id Pendaftaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idSiswa,
            onValueChange = {onValueChange(insertUiEvent.copy(idSiswa = it))},
            label = { Text(text = "Id Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idKursus,
            onValueChange = {onValueChange(insertUiEvent.copy(idKursus = it))},
            label = { Text(text = "Id Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggal,
            onValueChange = {onValueChange(insertUiEvent.copy(tanggal = it))},
            label = { Text(text = "Tanggal") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}