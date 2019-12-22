package io.github.a2kaido.moshi.inject.module

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.github.a2kaido.moshi.data.PokeRepositoryImpl
import io.github.a2kaido.moshi.data.remote.PokeService
import io.github.a2kaido.moshi.ui.repository.PokeRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModules {

    @Singleton
    @Provides
    fun providePokeRepository(service: PokeService): PokeRepository = PokeRepositoryImpl(service)

    @Singleton
    @Provides
    fun providePokeService(retrofit: Retrofit): PokeService =
        retrofit.create(PokeService::class.java)

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
}