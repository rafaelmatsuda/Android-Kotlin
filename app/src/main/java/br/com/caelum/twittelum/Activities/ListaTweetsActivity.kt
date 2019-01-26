package br.com.caelum.twittelum.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase_Impl
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista_tweets.*
import kotlinx.android.synthetic.main.activity_twet.*

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
            //Log.e("Foto","${tweets!![0].foto!!.trim()}")
        })

        fabNovoTweet.setOnClickListener {
            val intencao = Intent(this, TwetActivity::class.java)
            startActivity(intencao)

        }

        val listener = AdapterView.OnItemClickListener{ adapter, listener, posicao, id ->

            val tweet = listaTweets.getItemAtPosition(posicao) as Tweet

            deletaSeNecessario(tweet)

        }

        listaTweets.onItemClickListener = listener
    }

    private fun deletaSeNecessario(tweet: Tweet) {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Atenção!")
        builder.setIcon(R.drawable.ic_warning)
        builder.setMessage("Excluir o Tweet ?")

        builder.setPositiveButton("Sim"){_:DialogInterface, _:Int -> viewModel.deleta(tweet)}
        builder.setNegativeButton("Não", null)

        builder.show();
    }

}
