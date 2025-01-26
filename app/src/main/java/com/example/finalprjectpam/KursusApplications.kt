package com.example.finalprjectpam

import android.app.Application
import com.example.finalprjectpam.di.InsContainer
import com.example.finalprjectpam.di.InstrukturContainer
import com.example.finalprjectpam.di.KrsContainer
import com.example.finalprjectpam.di.KursusContainer
import com.example.finalprjectpam.di.PdftContainer
import com.example.finalprjectpam.di.PendaftaranContainer
import com.example.finalprjectpam.di.SiswaContainer
import com.example.finalprjectpam.di.SswContainer

class KursusApplications : Application() {
    lateinit var containerS: SiswaContainer
    lateinit var containerI: InstrukturContainer
    lateinit var containerK: KursusContainer
    lateinit var containerP: PendaftaranContainer

    override fun onCreate() {
        super.onCreate()
        containerS = SswContainer()
        containerI = InsContainer()
        containerK = KrsContainer()
        containerP = PdftContainer()
    }
}