package br.com.caelum.twittelum.repository

import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.modelo.Tweet

class TweetRepository(private val fonteDeDados:TweetDao) {

    fun lista() = fonteDeDados.lista()

    fun salva (tweet : Tweet) = fonteDeDados.salva(tweet)

    fun deleta (tweet: Tweet) = fonteDeDados.deleta(tweet)

}