package com.tugas.www.finder.di

import androidx.room.Room
import com.tugas.www.finder.database.AppDatabase
import com.tugas.www.finder.database.repository.NoteRepository
import com.tugas.www.finder.database.repository.PlanRepository
import com.tugas.www.finder.database.repository.UserRepository
import com.tugas.www.finder.expenselimit.ExpenseLimitViewModel
import com.tugas.www.finder.history.HistoryViewModel
import com.tugas.www.finder.home.HomeViewModel
import com.tugas.www.finder.inputmonetary.InputNoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "finder_database")
            .build()
    }

    single {
        get<AppDatabase>().noteDao()
    }

    single {
        get<AppDatabase>().planDao()
    }

    single {
        get<AppDatabase>().userDao()
    }

    single {
        NoteRepository(get())
    }

    single {
        PlanRepository(get())
    }

    single {
        UserRepository(get())
    }

    viewModel {
        InputNoteViewModel(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        ExpenseLimitViewModel(get())
    }

    viewModel {
        HistoryViewModel(get())
    }
}