package br.com.caelum.twittelum.Activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import br.com.caelum.twittelum.R
import br.com.caelum.twittelum.bancoDeDados.TweetDao
import br.com.caelum.twittelum.bancoDeDados.TwittelumDataBase
import br.com.caelum.twittelum.extensions.decodificaParaBase64
import br.com.caelum.twittelum.modelo.Tweet
import br.com.caelum.twittelum.viewmodel.TweetViewModel
import br.com.caelum.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_lista_tweets.*
import kotlinx.android.synthetic.main.activity_twet.*
import java.io.File
import java.net.URI

class TwetActivity : AppCompatActivity() {

    private var localFoto: String? = null

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK){
            when(requestCode){

                123 ->{
                    carregaFoto()
                }
                else ->{
                    localFoto = null
                }
            }
        }

    }

    private fun carregaFoto() {

        val bitmap = BitmapFactory.decodeFile(localFoto)

        val bm = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)

        FotoTweetForm.setImageBitmap(bm)

        val fotoBase64 = bm.decodificaParaBase64()

        FotoTweetForm.tag  = fotoBase64

        FotoTweetForm.scaleType = ImageView.ScaleType.FIT_XY




    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item?.itemId){

            R.id.salvarTweetMenu -> {
                publicaTweet()

                finish()
                true

            }

            R.id.abrirCamera ->{
                tiraFoto()
            }

            android.R.id.home ->{
                finish()

                false
            }


            else -> true

        }


    }

    private fun tiraFoto(): Boolean {

        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val caminhoFoto = defineLocalDaFoto()

        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)

        startActivityForResult(vaiPraCamera, 123)

        return true
    }

    fun defineLocalDaFoto(): Uri? {

        localFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"

        val arquivo = File(localFoto)

        return FileProvider.getUriForFile(this, "MeuProvider", arquivo)
    }

    private fun publicaTweet(){
        val texto = findViewById<EditText>(R.id.tweet).text.toString()

        val tweet = criaTweet()

        viewModel.salva(tweet)

        Toast.makeText(this, "Tweet publicado com sucesso!", Toast.LENGTH_LONG).show()
    }

    private fun criaTweet(): Tweet {

        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet)

        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()

        val foto: String? = FotoTweetForm.tag as String?

        return Tweet(mensagemDoTweet, foto)

    }
}
