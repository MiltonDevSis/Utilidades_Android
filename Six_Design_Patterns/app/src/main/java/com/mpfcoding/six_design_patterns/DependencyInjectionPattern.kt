package com.mpfcoding.six_design_patterns

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object DependencyInjectionPattern {

    @Provides
    @Named("TextMain")
    fun provideHamburguer() = "Injetar hamburguer"

    @Provides
    @Named("TextMainTwo")
    fun provideChesse() = "Injetar chesse"
}