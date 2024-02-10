package bes.max.mobinttest.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import bes.max.mobinttest.companies.data.database.CompanyDao
import bes.max.mobinttest.companies.data.database.CompanyEntity

@Database(
    entities = [CompanyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract val companyDao: CompanyDao
}