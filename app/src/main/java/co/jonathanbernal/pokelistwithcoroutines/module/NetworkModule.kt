package co.jonathanbernal.pokelistwithcoroutines.module

import android.util.Log
import co.jonathanbernal.pokelistwithcoroutines.data.datasource.remote.service.PokeListService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(interceptor: Interceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(interceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Log.e("Server Response", message)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    @Provides
    @Singleton
    fun provideMercaService(retrofit: Retrofit): PokeListService = retrofit.create(PokeListService::class.java)



}