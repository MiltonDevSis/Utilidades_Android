package com.mpfcoding.six_design_patterns

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DependencyInjectionPattern {

    @Singleton
    @Provides
    fun provideHamburguer(): HamburguerBuilderPattern{
        return HamburguerBuilderPattern.Builder()
            .cheese(true)
            .beef(true)
            .onions(true)
            .build()
    }
}