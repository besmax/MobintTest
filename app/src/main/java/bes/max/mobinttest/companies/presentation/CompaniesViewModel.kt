package bes.max.mobinttest.companies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import bes.max.mobinttest.companies.data.database.CompanyEntity
import bes.max.mobinttest.companies.data.database.map
import kotlinx.coroutines.flow.map

class CompaniesViewModel(
    private val pager: Pager<Int, CompanyEntity>
) : ViewModel() {

    val companiesPagerFlow = pager
        .flow
        .map { paggingData ->
            paggingData.map { it.map() }
        }
        .cachedIn(viewModelScope)
}