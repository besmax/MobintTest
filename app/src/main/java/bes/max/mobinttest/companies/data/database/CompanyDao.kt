package bes.max.mobinttest.companies.data.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CompanyDao {

    @Insert
    suspend fun insertsCompanies(companies: CompanyEntity)

}