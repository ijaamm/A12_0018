package com.example.finalprjectpam.ui.view.pendaftaranview

import android.os.Build
import androidx.annotation.RequiresExtension
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprjectpam.ui.customewidget.CostumeTopAppBar
import com.example.finalprjectpam.ui.navigasi.DestinasiNavigasi
import com.example.finalprjectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.UpdatePViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.UpdateUiEvent
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.UpdateUiState
import kotlinx.coroutines.launch

object DestinasiUpdateP: DestinasiNavigasi {
    override val route = "updatePendaftaran"
    override val titleRes = "Update Pendaftaran"
    const val idPdft = "id_pendaftaran"
    val routeWithArgs = "$route/{$idPdft}"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreenP(
    idPendaftaran: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(idPendaftaran) {
        viewModel.loadPendaftaran(idPendaftaran)
    }

    val updateUiState = viewModel.UpdateuiState

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateP.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBody(
            updateUiState = updateUiState,
            onValueChange = viewModel::updateState,
            onUpdateClick = {
                coroutineScope.launch {
                    viewModel.updatePdft()
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
fun UpdateBody(
    updateUiState: UpdateUiState,
    onValueChange: (UpdateUiEvent) -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(26.dp)
    ) {
        UpdateFormInput(
            updateUiEvent = updateUiState.updateUiEvent,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onUpdateClick,
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(Color(0xFF60d4cc)),
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateFormInput(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateUiEvent.idPendaftaran,
            onValueChange = { onValueChange(updateUiEvent.copy(idPendaftaran = it)) },
            label = { Text(text = "Id Pendaftaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idSiswa,
            onValueChange = { onValueChange(updateUiEvent.copy(idSiswa = it)) },
            label = { Text(text = "Id Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.idKursus,
            onValueChange = { onValueChange(updateUiEvent.copy(idKursus = it)) },
            label = { Text(text = "Id Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )
        OutlinedTextField(
            value = updateUiEvent.tanggal,
            onValueChange = { onValueChange(updateUiEvent.copy(tanggal = it)) },
            label = { Text(text = "tanggal") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}