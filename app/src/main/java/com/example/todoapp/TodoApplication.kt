package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.database.AppContainer
import com.example.todoapp.data.database.AppDataContainer

class TodoApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}