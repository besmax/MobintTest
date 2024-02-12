package bes.max.mobinttest.companies.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CompanyDao {

    @Upsert
    suspend fun insertsCompanies(companies: List<CompanyEntity>)

    @Query("SELECT * FROM companies_table")
    fun getAllCompanies(): PagingSource<Int, CompanyEntity>

    @Query("DELETE FROM companies_table")
    suspend fun clearAll()


}