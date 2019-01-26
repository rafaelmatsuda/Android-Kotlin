package br.com.caelum.twittelum.bancoDeDados

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object Migra1Para2 : Migration(1 , 2) {
    override fun migrate(database: SupportSQLiteDatabase) {

             database.execSQL("alter table Tweet add column foto text")
    }


}
