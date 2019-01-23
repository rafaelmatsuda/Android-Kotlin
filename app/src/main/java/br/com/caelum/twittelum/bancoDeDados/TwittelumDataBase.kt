package br.com.caelum.twittelum.bancoDeDados

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.caelum.twittelum.application.TwittellumApp
import br.com.caelum.twittelum.modelo.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TwittelumDataBase : RoomDatabase() {

    abstract fun tweetDao():TweetDao

    companion object {
        private var database: TwittelumDataBase? = null

        private val DATABASE = "TwittellumDB"

        fun getInstance() : TwittelumDataBase{
            return database ?: criaBanco()
        }

        private fun criaBanco(): TwittelumDataBase {

            val instance = TwittellumApp.getInstace()

            return Room.databaseBuilder(instance, TwittelumDataBase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}