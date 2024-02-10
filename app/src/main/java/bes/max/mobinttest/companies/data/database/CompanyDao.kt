package bes.max.mobinttest.companies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompanyDao {

    @Insert
    suspend fun insertsCompanies(companies: CompanyEntity)

    @Query("SELECT * FROM companies_table")
    suspend fun getAllCompanies(): List<CompanyEntity>

}