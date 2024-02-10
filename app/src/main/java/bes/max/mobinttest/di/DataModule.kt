package bes.max.mobinttest.di

import bes.max.mobinttest.core.data.network.CompaniesApiService
import bes.max.mobinttest.core.data.network.NetworkClient
import bes.max.mobinttest.core.data.network.RetrofitNetworkClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    singleOf(::RetrofitNetworkClient) bind NetworkClient::class

    single<CompaniesApiService> {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("TOKEN", "123")
                            .build()
                    )
                }
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        Retrofit.Builder()
            .baseUrl(COMPANIES_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CompaniesApiService::class.java)
    }


}

private const val COMPANIES_BASE_URL = "http://devapp.bonusmoney.pro/mobileapp/"
private const val MOBINT_TEST_KEY = "MobintTestKey"
