package com.example.lpiem.smsvote

import android.app.Application
import com.example.lpiem.smsvote.data.entity.ParseVote
import com.parse.Parse
import com.parse.ParseInstallation
import com.parse.ParseObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.BufferedInputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.*

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE)

        ParseObject.registerSubclass(ParseVote::class.java)

        Parse.enableLocalDatastore(applicationContext)
        Parse.initialize(Parse.Configuration.Builder(applicationContext)
            .applicationId("1915484469352")
            .clientKey("195951")
            .clientBuilder(getOkHttpClientBuilder())
            .server("https://bigoud.games/smsvote/")
            .build()
        )

        ParseInstallation.getCurrentInstallation().saveInBackground()
    }

    fun getOkHttpClientBuilder() : OkHttpClient.Builder
    {
        // Load CAs from an InputStream
        val certificateFactory = CertificateFactory.getInstance("X.509")

        // Load self-signed certificate (*.crt file)
        val inputStream =  applicationContext.resources.openRawResource(R.raw.bigoudinc)
        val certificate = certificateFactory.generateCertificate(inputStream)
        inputStream.close()

        // Create a KeyStore containing our trusted CAs
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", certificate)

        // Create a TrustManager that trusts the CAs in our KeyStore.
        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
        trustManagerFactory.init(keyStore)

        val trustManagers = trustManagerFactory.trustManagers
        val x509TrustManager = trustManagers[0] as X509TrustManager

        // Create an SSLSocketFactory that uses our TrustManager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf(x509TrustManager), null)
        var sslSocketFactory = sslContext.socketFactory

        // Create an instance of OkHttpClient
        var m_client = OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, x509TrustManager)
            .hostnameVerifier(myHostNameVerifier())

        return m_client
    }

    private fun myHostNameVerifier(): HostnameVerifier {
        return HostnameVerifier { hostname, _ ->
            if (hostname == "bigoud.games") {
                return@HostnameVerifier true
            }

            false
        }
    }

}