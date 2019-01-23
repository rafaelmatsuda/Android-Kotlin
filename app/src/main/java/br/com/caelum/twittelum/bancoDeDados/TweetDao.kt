package br.com.caelum.twittelum.bancoDeDados

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import br.com.caelum.twittelum.modelo.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salva(tweet: Tweet)
}