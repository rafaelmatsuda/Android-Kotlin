package br.com.caelum.twittelum.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase_Impl
import br.com.caelum.twittelum.modelo.Tweet
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)


    }

    override fun onResume() {
        super.onResume()

        val tweetDao = TwittelumDataBase.getInstance().tweetDao()
        val tweets : List<Tweet> = tweetDao.lista()
        val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)

        listaTweets.adapter = adapter

        fabNovoTweet.setOnClickListener {
            val intencao = Intent(this, TwetActivity::class.java)
            startActivity(intencao)
        }

    }
}