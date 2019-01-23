package br.com.caelum.twittelum.application

import android.app.Application

class TwittellumApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this;
    }

    companion object {

        private lateinit var instance: TwittellumApp

        fun getInstace() : TwittellumApp =
                instance
    }
}