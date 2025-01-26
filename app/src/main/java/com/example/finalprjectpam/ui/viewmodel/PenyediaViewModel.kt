package com.example.finalprjectpam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalprjectpam.KursusApplications
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.DetailIViewModel
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.HomeIViewModel
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.InsertIViewModel
import com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel.UpdateIViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.DetailKViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.HomeKViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.InsertKViewModel
import com.example.finalprjectpam.ui.viewmodel.kursusviewmodel.UpdateKViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.DetailPViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.HomePViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.InsertPViewModel
import com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel.UpdatePViewModel
import com.example.finalprjectpam.ui.viewmodel.siswaviewmodel.DetailSViewModel
import com.example.finalprjectpam.ui.viewmodel.siswaviewmodel.HomeSViewModel
import com.example.finalprjectpam.ui.viewmodel.siswaviewmodel.InsertSViewModel
import com.example.finalprjectpam.ui.viewmodel.siswaviewmodel.UpdateSViewModel

fun CreationExtras.aplikasiKursus(): KursusApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KursusApplications)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // siswa
        initializer { HomeSViewModel(aplikasiKursus().containerS.siswaRepository) }
        initializer { InsertSViewModel(aplikasiKursus().containerS.siswaRepository) }
        initializer { UpdateSViewModel(aplikasiKursus().containerS.siswaRepository) }
        initializer { DetailSViewModel(aplikasiKursus().containerS.siswaRepository) }

        // instruktur
        initializer { HomeIViewModel(aplikasiKursus().containerI.instrukturRepository) }
        initializer { InsertIViewModel(aplikasiKursus().containerI.instrukturRepository) }
        initializer { UpdateIViewModel(aplikasiKursus().containerI.instrukturRepository) }
        initializer { DetailIViewModel(aplikasiKursus().containerI.instrukturRepository) }

        // kursus
        initializer { HomeKViewModel(aplikasiKursus().containerK.kursusRepository) }
        initializer { InsertKViewModel(aplikasiKursus().containerK.kursusRepository) }
        initializer { UpdateKViewModel(aplikasiKursus().containerK.kursusRepository) }
        initializer { DetailKViewModel(aplikasiKursus().containerK.kursusRepository) }

        //pendaftaran
        initializer { HomePViewModel(aplikasiKursus().containerP.pendaftaranRepository) }
        initializer { InsertPViewModel(aplikasiKursus().containerP.pendaftaranRepository) }
        initializer { UpdatePViewModel(aplikasiKursus().containerP.pendaftaranRepository) }
        initializer { DetailPViewModel(aplikasiKursus().containerP.pendaftaranRepository) }
    }
}
