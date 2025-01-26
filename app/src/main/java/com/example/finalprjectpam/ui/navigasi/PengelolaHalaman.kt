package com.example.finalprjectpam.ui.navigasi

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalprjectpam.ui.view.HomePage
import com.example.finalprjectpam.ui.view.Splashview
import com.example.finalprjectpam.ui.view.instrukturview.DestinasiDetailI
import com.example.finalprjectpam.ui.view.instrukturview.DestinasiEntryI
import com.example.finalprjectpam.ui.view.instrukturview.DestinasiHomeI
import com.example.finalprjectpam.ui.view.instrukturview.DestinasiUpdateI
import com.example.finalprjectpam.ui.view.instrukturview.DetailViewI
import com.example.finalprjectpam.ui.view.instrukturview.HomeScreenI
import com.example.finalprjectpam.ui.view.instrukturview.InsertScreenI
import com.example.finalprjectpam.ui.view.instrukturview.UpdateScreenI
import com.example.finalprjectpam.ui.view.kursusview.DestinasiDetailK
import com.example.finalprjectpam.ui.view.kursusview.DestinasiEntryK
import com.example.finalprjectpam.ui.view.kursusview.DestinasiHomeK
import com.example.finalprjectpam.ui.view.kursusview.DestinasiUpdateK
import com.example.finalprjectpam.ui.view.kursusview.DetailViewK
import com.example.finalprjectpam.ui.view.kursusview.HomeScreenK
import com.example.finalprjectpam.ui.view.kursusview.InsertScreenK
import com.example.finalprjectpam.ui.view.kursusview.UpdateScreenK
import com.example.finalprjectpam.ui.view.pendaftaranview.DestinasiDetailP
import com.example.finalprjectpam.ui.view.pendaftaranview.DestinasiEntryP
import com.example.finalprjectpam.ui.view.pendaftaranview.DestinasiHomeP
import com.example.finalprjectpam.ui.view.pendaftaranview.DestinasiUpdateP
import com.example.finalprjectpam.ui.view.pendaftaranview.DetailView
import com.example.finalprjectpam.ui.view.pendaftaranview.HomeScreenP
import com.example.finalprjectpam.ui.view.pendaftaranview.InsertScreenP
import com.example.finalprjectpam.ui.view.pendaftaranview.UpdateScreenP
import com.example.finalprjectpam.ui.view.siswaview.DestinasiDetailS
import com.example.finalprjectpam.ui.view.siswaview.DestinasiEntryS
import com.example.finalprjectpam.ui.view.siswaview.DestinasiHomeS
import com.example.finalprjectpam.ui.view.siswaview.DestinasiUpdateS
import com.example.finalprjectpam.ui.view.siswaview.DetailViewS
import com.example.finalprjectpam.ui.view.siswaview.HomeScreenS
import com.example.finalprjectpam.ui.view.siswaview.InsertScreenS
import com.example.finalprjectpam.ui.view.siswaview.UpdateScreenS


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = HomePage.route,
        modifier = Modifier,
    ) {
        composable(HomePage.route) {
            Splashview(
                    onSiswaButton = {
                        navController.navigate(DestinasiHomeS.route)
                    },
                    onInstrukturButton = {
                        navController.navigate(DestinasiHomeI.route)
                    },
                    onKursusButton = {
                        navController.navigate(DestinasiHomeK.route)
                    },
                    onPendaftaranButton = {
                        navController.navigate(DestinasiHomeP.route)
                    }
            )
        }

        //Siswa
        composable(DestinasiHomeS.route){
            HomeScreenS(
                navigateToitemEntry = {navController.navigate(DestinasiEntryS.route)},
                navigateBack = {
                    navController.navigate(HomePage.route){
                        popUpTo(HomePage.route){
                            inclusive = true
                        }
                    }
                },
                onDetailClick = {idSiswa ->
                    navController.navigate("${DestinasiDetailS.route}/$idSiswa")
                    println(
                        "PengelolaHalaman: IdSiswa = $idSiswa"
                    )
                },
                onUpdateClick = { idSiswa ->
                    navController.navigate("${DestinasiUpdateS.route}/$idSiswa")
                    println(
                        "PengelolaHalaman: IdSiswa = $idSiswa"
                    )
                }
            )
        }
        composable(DestinasiEntryS.route){
            InsertScreenS(navigateBack = {
                navController.navigate(DestinasiHomeS.route){
                    popUpTo(DestinasiHomeS.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailS.routeWithArgs,arguments = listOf(
            navArgument(DestinasiDetailS.idSsw){
                type = NavType.StringType
            }
        )
        ){
            val idSiswa = it.arguments?.getString(DestinasiDetailS.idSsw)
            idSiswa?.let { idSiswa ->
                DetailViewS(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateS.route}/$it")
                    },
                    idSiswa = idSiswa,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateS.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateS.idSsw) {
                    type = NavType.StringType
                }
            )
        ) {
            val idSiswa = it.arguments?.getString(DestinasiUpdateS.idSsw)
            idSiswa?.let { idSiswa ->
                UpdateScreenS(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    idSiswa = idSiswa
                )
            }
        }

        //Instruktur
        composable(DestinasiHomeI.route){
            HomeScreenI(
                navigateToitemEntry = {navController.navigate(DestinasiEntryI.route)},
                navigateBack = {
                    navController.navigate(HomePage.route){
                        popUpTo(HomePage.route){
                            inclusive = true
                        }
                    }
                },
                onDetailClick = {idInstruktur ->
                    navController.navigate("${DestinasiDetailI.route}/$idInstruktur")
                    println(
                        "PengelolaHalaman: IdInstruktur = $idInstruktur"
                    )
                },
                onUpdateClick = { idInstruktur ->
                    navController.navigate("${DestinasiUpdateI.route}/$idInstruktur")
                    println(
                        "PengelolaHalaman: IdInstruktur = $idInstruktur"
                    )
                }
            )
        }
        composable(DestinasiEntryI.route){
            InsertScreenI(navigateBack = {
                navController.navigate(DestinasiHomeI.route){
                    popUpTo(DestinasiHomeI.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailI.routeWithArgs,arguments = listOf(
            navArgument(DestinasiDetailI.idIns){
                type = NavType.StringType
            }
        )
        ){
            val idInstruktur = it.arguments?.getString(DestinasiDetailI.idIns)
            idInstruktur?.let { idInstruktur ->
                DetailViewI(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateI.route}/$it")
                    },
                    idInstruktur = idInstruktur,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateI.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateI.idIns) {
                    type = NavType.StringType
                }
            )
        ) {
            val idInstruktur = it.arguments?.getString(DestinasiUpdateI.idIns)
            idInstruktur?.let { nim ->
                UpdateScreenI(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    idInstruktur = idInstruktur
                )
            }
        }

        //kursus
        composable(DestinasiHomeK.route){
            HomeScreenK(
                navigateToitemEntry = {navController.navigate(DestinasiEntryK.route)},
                onDetailClick = {idKursus ->
                    navController.navigate("${DestinasiDetailK.route}/$idKursus")
                    println(
                        "PengelolaHalaman: krs = $idKursus"
                    )
                },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiEntryK.route){
            InsertScreenK(navigateBack = {
                navController.navigate(DestinasiHomeK.route){
                    popUpTo(DestinasiHomeK.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailK.routeWithArgs,arguments = listOf(
            navArgument(DestinasiDetailK.idKrs){
                type = NavType.StringType
            }
        )
        ){
            val idKursus = it.arguments?.getString(DestinasiDetailK.idKrs)
            idKursus?.let { idKursus ->
                DetailViewK(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateK.route}/$it")
                    },
                    idKursus = idKursus,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateK.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateK.idKrs) {
                    type = NavType.StringType
                }
            )
        ) {
            val idKursus = it.arguments?.getString(DestinasiUpdateK.idKrs)
            idKursus?.let { idKursus ->
                UpdateScreenK(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    idKursus = idKursus
                )
            }
        }

        //pendaftaran
        composable(DestinasiHomeP.route){
            HomeScreenP(
                navigateToitemEntry = {navController.navigate(DestinasiEntryP.route)},
                onDetailClick = {idPendaftaran ->
                    navController.navigate("${DestinasiDetailP.route}/$idPendaftaran")
                    println(
                        "PengelolaHalaman: idPendaftaran = $idPendaftaran"
                    )
                },
                navigateBack = {
                    navController.navigate(HomePage.route) {
                        popUpTo(HomePage.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiEntryP.route){
            InsertScreenP(navigateBack = {
                navController.navigate(DestinasiHomeP.route){
                    popUpTo(DestinasiHomeP.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetailP.routeWithArgs,arguments = listOf(
            navArgument(DestinasiDetailP.idPdft){
                type = NavType.StringType
            }
        )
        ){
            val idPendaftaran = it.arguments?.getString(DestinasiDetailP.idPdft)
            idPendaftaran?.let { idPendaftaran ->
                DetailView(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateP.route}/$it")
                    },
                    idPendaftaran = idPendaftaran,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateP.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateP.idPdft) {
                    type = NavType.StringType
                }
            )
        ) {
            val idPendaftaran = it.arguments?.getString(DestinasiUpdateP.idPdft)
            idPendaftaran?.let { idPendaftaran ->
                UpdateScreenP(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    idPendaftaran = idPendaftaran
                )
            }
        }
    }
}