package br.com.caelum.twittelum.viewmodel

import br.com.caelum.twittelum.application.TwittellumApp
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase

object Injetor {

    private val dataBase = TwittelumDataBase.getInstance()

    fun tweetDao() = dataBase.tweetDao()

}