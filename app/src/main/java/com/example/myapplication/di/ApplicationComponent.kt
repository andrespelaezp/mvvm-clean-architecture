package com.example.myapplication.di

import com.example.myapplication.RedditApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    OkHttpModule::class,
    ApplicationModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class
])
interface ApplicationComponent: AndroidInjector<RedditApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RedditApplication>() {
        abstract fun apiModule(module: ApiModule): Builder
        abstract fun applicationModule(application: ApplicationModule): Builder
    }

}