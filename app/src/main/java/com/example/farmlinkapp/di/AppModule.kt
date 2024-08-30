package com.example.farmlinkapp.di

import com.example.farmlinkapp.model.Item
import com.example.farmlinkapp.model.Category
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRealmDatabase() : Realm {
        return Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Category::class,
                    Item::class
                )
            )
        )
    }
}