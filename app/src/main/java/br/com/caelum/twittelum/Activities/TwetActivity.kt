package br.com.caelum.twittelum.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.modelo.Tweet

class TwetActivity : AppCompatActivity() {

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

        val tweetDao = TwittelumDataBase.getInstance().tweetDao().salva(tweet)

        Toast.makeText(this, "$tweet ", Toast.LENGTH_LONG).show()
    }
}
