package com.marzazsoft.supertool

import android.app.Application
import com.google.firebase.FirebaseApp
import com.marzazsoft.mobile.games.di.gamesLibraryModule
import com.marzazsoft.mobile.radio.di.radioLibraryModule
import com.marzazsoft.supertool.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                appModule,
                gamesLibraryModule,
                radioLibraryModule,
            )
        }
        FirebaseApp.initializeApp(this)
    }
}
