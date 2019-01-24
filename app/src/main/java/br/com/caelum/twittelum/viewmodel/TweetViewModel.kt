package br.com.caelum.twittelum.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.repository.TweetRepository

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun lista() = repository.lista()

    fun salva(tweet: Tweet) = repository.salva(tweet)

}