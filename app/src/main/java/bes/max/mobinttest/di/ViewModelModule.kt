package bes.max.mobinttest.di

import bes.max.mobinttest.companies.presentation.CompaniesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::CompaniesViewModel)

}