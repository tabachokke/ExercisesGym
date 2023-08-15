package com.example.exercisesgym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = "https://www.google.com/"

/*        //Смартфон Google
        fun isGoogleBrand(): Boolean {
            val brand = android.os.Build.BRAND
            return brand.equals("google", ignoreCase = true)
        }
        //Эмулятор
        fun isEmulator(): Boolean {
            return (android.os.Build.BRAND.equals("generic", ignoreCase = true) &&
                    android.os.Build.MODEL.contains("sdk", ignoreCase = true))
        }
        fun showFallback() {
        }
        // Проверяем условия
        if (url.isNullOrEmpty() ||
            isGoogleBrand() ||
            isEmulator()
        ) {
            // Заглушка
            showFallback()
        } else {
            // Открываем ссылку в WebView
            //showWebView(url) }*/

//Время обновления
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 60
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.change_url)

            //Для изменения ссылки
            fun getValueFromFireBase() {
                remoteConfig.fetchAndActivate()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val updated = task.result
                            Log.d("MainActivity", "Config params updated: $updated")
                            remoteConfig.getString("change_url")
                        } else {
                            Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

            setContentView(R.layout.activity_main)

            //Обозначаем WebView
/*            val webView: WebView = findViewById(R.id.webView)
            webView.webViewClient = WebViewClient()*/

            //вкл. javaScript
/*            val webSettings: WebSettings = webView.settings
            webSettings.javaScriptEnabled = true

            fun onSaveInstanceState(outState: Bundle) {
                super.onSaveInstanceState(outState)
                webView.saveState(outState)
            }

            if (savedInstanceState != null) {
                webView.restoreState(savedInstanceState)
            } else {
                webView.loadUrl("https://www.google.com/")
            }

            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)

            //Настройки Веб вью
            val settings = webView.settings
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.setSupportZoom(false)
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true*/

            val itemsList: RecyclerView = findViewById(R.id.itemsList)
            val items = arrayListOf<Cell>()

            items.add(
                Cell(
                    1,
                    "benchpress",
                    "Bench press",
                    "Lie down on the bench, try to bring the shoulder blades together and bend slightly in the lower back, while the buttocks, upper back and head should be tightly pressed against the bench.Barbell should be located approximately at eye level."
                )
            )
            items.add(
                Cell(
                    2,
                    "dumbbells",
                    "Dumbbells press",
                    "Stand at the barbell, lower yourself to chest level so that it lies on the muscles of the upper back (thoracic region). Place your feet shoulder-width apart and turn them slightly outward."
                )
            )
            items.add(
                Cell(
                    3,
                    "squat",
                    "Squat",
                    "Stand up straight, feet shoulder-width apart or slightly wider. Place the barbell on the muscles of the upper back. Slowly lower yourself, bending your knees and hips, as if sitting on a chair."
                )
            )
            items.add(
                Cell(
                    4,
                    "shrugs",
                    "Shrugs",
                    " Take dumbbells from the floor or from the racks.Straighten your back, direct your gaze forward.Lift the dumbbells up while exhaling.lower the dumbbells down, inhaling and feeling the muscles stretch."
                )
            )

            itemsList.layoutManager = LinearLayoutManager(this)
            itemsList.adapter = NewsAdapter(items, this)

        }
    }
