package br.com.caelum.twittelum.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class TwetActivity : AppCompatActivity() {

    private val viewModel : TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twet)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId){

            R.id.salvarTweetMenu -> {
                publicaTweet()

                finish()
                true

            }

            android.R.id.home ->{
                finish()

                false
            }

            else -> true

        }


    }

    private fun publicaTweet(){
        val texto = findViewById<EditText>(R.id.tweet).text.toString()
        val tweet = Tweet(texto)

        viewModel.salva(tweet)

        Toast.makeText(this, "$tweet ", Toast.LENGTH_LONG).show()
    }
}
