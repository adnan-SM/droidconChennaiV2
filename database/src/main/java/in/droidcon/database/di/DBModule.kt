package `in`.droidcon.database.di

import `in`.droidcon.database.Database
import `in`.droidcon.database.EventDAO
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val dbModule = module(override=true) {
    factory<Database> { Room.databaseBuilder(androidContext(), Database::class.java, "droidcon19")
        .fallbackToDestructiveMigration().build() }
    factory<EventDAO> { EventDAO.newInstance(get()) }
}