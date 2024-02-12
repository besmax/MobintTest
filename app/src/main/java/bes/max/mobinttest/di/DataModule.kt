package bes.max.mobinttest.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import bes.max.mobinttest.companies.data.database.CompanyDao
import bes.max.mobinttest.companies.data.database.CompanyEntity
import bes.max.mobinttest.companies.data.repositories.CompaniesRepositoryImpl
import bes.max.mobinttest.core.data.database.AppRoomDatabase
import bes.max.mobinttest.core.data.network.CompaniesApiService
import bes.max.mobinttest.core.data.network.NetworkClient
import bes.max.mobinttest.core.data.network.RetrofitNetworkClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalPagingApi::class)
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

    single<AppRoomDatabase> {
        Room.databaseBuilder(androidContext(), AppRoomDatabase::class.java, "database").build()
    }

    single<CompanyDao> {
        val database = get<AppRoomDatabase>()
        database.companyDao
    }

    single<Pager<Int, CompanyEntity>> {
        Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = CompaniesRepositoryImpl(get(), get()),
            pagingSourceFactory = {
                val dao = get<CompanyDao>()
                dao.getAllCompanies()
            }
        )
    }

}

private const val COMPANIES_BASE_URL = "http://devapp.bonusmoney.pro/mobileapp/"
