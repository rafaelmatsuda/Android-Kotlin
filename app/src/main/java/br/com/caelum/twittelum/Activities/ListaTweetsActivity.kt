package br.com.caelum.twittelum.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase_Impl
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {

    private val viewModel : TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        val liveData = viewModel.lista()

        liveData.observe(this, Observer { tweets ->
            listaTweets.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tweets!!)
        })

        fabNovoTweet.setOnClickListener {
            val intencao = Intent(this, TwetActivity::class.java)
            startActivity(intencao)
        }
    }

}
