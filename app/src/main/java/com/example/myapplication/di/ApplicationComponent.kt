package com.example.myapplication.di

import com.example.myapplication.RedditApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    ApplicationModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    AndroidInjectionModule::class
])
interface ApplicationComponent: AndroidInjector<RedditApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RedditApplication>() {
        abstract fun apiModule(module: ApiModule): Builder
        abstract fun applicationModule(application: ApplicationModule): Builder
    }

}