package bes.max.mobinttest.di

import bes.max.mobinttest.companies.data.repositories.CompaniesRepositoryImpl
import bes.max.mobinttest.companies.domain.repositories.CompaniesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    singleOf(::CompaniesRepositoryImpl) bind CompaniesRepository::class

}