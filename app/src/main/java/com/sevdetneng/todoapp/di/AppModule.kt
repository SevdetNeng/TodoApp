package com.sevdetneng.todoapp.di

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.sevdetneng.todoapp.data.TodoDatabase
import com.sevdetneng.todoapp.data.todoDao
import com.sevdetneng.todoapp.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context : Context) : TodoDatabase = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todo_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase) : todoDao = todoDatabase.todoDao()

    @Singleton
    @Provides
    fun provideRepository(todoDao : todoDao) : TodoRepository = TodoRepository(todoDao)

}