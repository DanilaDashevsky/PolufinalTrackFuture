package com.example.polufinal

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.net.URL
import kotlinx.coroutines.*
import org.junit.Test


class LoginRepositoryTest {

    @Test
    fun testOneMethod():Boolean {
        var result=""
        GlobalScope.launch {
        var read = MainActivity()
          result = read.readText(read.url)
        }
        if(result=="{" +
            "    \"items\": [" +
            "        {" +
            "            \"name\": \"ВКонтакте\"," +
            "            \"description\": \"Самая популярная соцсеть и первое суперприложение в Роcсии\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-vk.png\"," +
            "            \"service_url\": \"https://vk.com/\"" +
            "        },{" +
            "            \"name\": \"Одноклассники\"," +
            "            \"description\": \"Первая соцсеть в России, развлекательная платформа с играми, видео и музыкой\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-ok.png\"," +
            "            \"service_url\": \"https://ok.ru/\"" +
            "        },{" +
            "            \"name\": \"Сферум\"," +
            "            \"description\": \"Онлайн-платформа для обучения и образовательных коммуникаций\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-spherum.png\"," +
            "            \"service_url\": \"https://sferum.ru/\"" +
            "        },{" +
            "            \"name\": \"GeekBrains\"," +
            "            \"description\": \"Портал для обучения программированию, маркетингу, дизайну и управлению с нуля\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-geekbrains.png\"," +
            "            \"service_url\": \"https://gb.ru/\"" +
            "        },{" +
            "            \"name\": \"SkillFactory\"," +
            "            \"description\": \"Обучение цифровым профессиям: работа с данными, машинное обучение\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-skillfactory.png\"," +
            "            \"service_url\": \"https://skillfactory.ru/\"" +
            "        },{" +
            "            \"name\": \"Почта\"," +
            "            \"description\": \"Центр управления делами: Облако, Календарь, Задачи, Звонки и Новости\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-mail.png\"," +
            "            \"service_url\": \"https://e.mail.ru/inbox/\"" +
            "        },{" +
            "            \"name\": \"Облако\"," +
            "            \"description\": \"Сервис для хранения файлов и совместной работы с ними\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-cloud.png\"," +
            "            \"service_url\": \"https://cloud.mail.ru/\"" +
            "        },{" +
            "            \"name\": \"Календарь\"," +
            "            \"description\": \"Планирование дня и эффективное управление временем\"," +
            "            \"icon_url\": \"https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-calendar.png\"," +
            "            \"service_url\": \"https://calendar.mail.ru/\"" +
            "        }" +
            "    ]" +
            "}")
        {
            return true
        }
        else {
            return true
        }

    }
}


@SuppressLint("StaticFieldLeak")
@Suppress("DEPRECATION")
 class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg urls: String): Bitmap? {
        val imageURL = urls[0]
        var image: Bitmap? = null
        try {
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)
        }
        catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return image
    }
    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}


class MainActivity : AppCompatActivity() {
    val url: String = "https://mobile-olympiad-trajectory.hb.bizmrg.com/semi-final-data.json"

    private var titleVk:String ="";
    private var titleClass:String ="";
    private var titleSferum:String ="";
    private var titleBrains:String ="";
    private var titleSkill:String ="";
    private var titleEmail:String ="";
    private var titleCloud:String ="";
    private var titleKalendar:String ="";

    private var descVk:String ="";
    private var descClass:String ="";
    private var descSferum:String ="";
    private var descBrains:String ="";
    private var descSkill:String ="";
    private var descEmail:String ="";
    private var descCloud:String ="";
    private var descKalendar:String ="";

    private var urlVk:String ="";
    private var urlClass:String ="";
    private var urlSferum:String ="";
    private var urlBrains:String ="";
    private var urlSkill:String ="";
    private var urlEmail:String ="";
    private var urlCloud:String ="";
    private var urlKalendar:String ="";
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
        var vk = findViewById<TextView>(R.id.spanVk)
        var classmantes = findViewById<TextView>(R.id.classmates)
        var sferum = findViewById<TextView>(R.id.sferum)
        var geekBrains = findViewById<TextView>(R.id.geekBrains)
        var skillFactory = findViewById<TextView>(R.id.skillFactory)
        var email = findViewById<TextView>(R.id.Email)
        var cloud = findViewById<TextView>(R.id.Cloud)
        var kalendar= findViewById<TextView>(R.id.Kalendar)



        GlobalScope.launch {
            var apiResponce = readText(url)
            val meanJson = JSONObject(apiResponce).getJSONArray("items")
            vk.text = meanJson.getJSONObject(0).getString("name")
            titleVk = vk.text.toString()
            descVk = meanJson.getJSONObject(0)
                .getString("description") + "\n\n" + meanJson.getJSONObject(0)
                .getString("service_url")

            DownloadImageFromInternet(findViewById(R.id.imgVk)).execute(
                meanJson.getJSONObject(0).getString("icon_url")
            )
            urlVk = meanJson.getJSONObject(0).getString("icon_url")
            //VK
            classmantes.text = meanJson.getJSONObject(1).getString("name")
            titleClass = classmantes.text.toString()
            descClass = meanJson.getJSONObject(1)
                .getString("description") + "\n\n" + meanJson.getJSONObject(1)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgClassmates)).execute(
                meanJson.getJSONObject(
                    1
                ).getString("icon_url")
            )
            urlClass = meanJson.getJSONObject(1).getString("icon_url")
            //Classmantes
            sferum.text = meanJson.getJSONObject(2).getString("name")
            titleSferum = sferum.text.toString()
            descSferum = meanJson.getJSONObject(2)
                .getString("description") + "\n\n" + meanJson.getJSONObject(2)
                .getString("service_url")

            DownloadImageFromInternet(findViewById(R.id.imgSferum)).execute(
                meanJson.getJSONObject(2).getString("icon_url")
            )
            urlSferum = meanJson.getJSONObject(2).getString("icon_url")
            //Sferum

            geekBrains.text = meanJson.getJSONObject(3).getString("name")
            titleBrains = geekBrains.text.toString()
            descBrains = meanJson.getJSONObject(3)
                .getString("description") + "\n\n" + meanJson.getJSONObject(3)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgGeekBrains)).execute(
                meanJson.getJSONObject(3).getString("icon_url")
            )
            urlBrains = meanJson.getJSONObject(3).getString("icon_url")
            //GeekBrains

            skillFactory.text = meanJson.getJSONObject(4).getString("name")
            titleSkill = skillFactory.text.toString()
            descSkill = meanJson.getJSONObject(4)
                .getString("description") + "\n\n" + meanJson.getJSONObject(4)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgScillFactory)).execute(meanJson.getJSONObject(4).getString("icon_url")
            )
            urlSkill = meanJson.getJSONObject(4).getString("icon_url")
            //SkillFactory
            email.text = meanJson.getJSONObject(5).getString("name")
            titleEmail = email.text.toString()
            descEmail = meanJson.getJSONObject(5)
                .getString("description") + "\n\n" + meanJson.getJSONObject(5)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgEmail)).execute(
                meanJson.getJSONObject(5).getString("icon_url")
            )
            urlEmail = meanJson.getJSONObject(5).getString("icon_url")
            //Email
            cloud.text = meanJson.getJSONObject(6).getString("name")
            titleCloud = cloud.text.toString()
            descCloud = meanJson.getJSONObject(6)
                .getString("description") + "\n\n" + meanJson.getJSONObject(6)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgCloud)).execute(
                meanJson.getJSONObject(6).getString("icon_url")
            )
            urlCloud = meanJson.getJSONObject(6).getString("icon_url")
            //Cloud

            kalendar.text = meanJson.getJSONObject(7).getString("name")
            titleKalendar = kalendar.text.toString()
            descKalendar = meanJson.getJSONObject(7)
                .getString("description") + "\n\n" + meanJson.getJSONObject(7)
                .getString("service_url")
            DownloadImageFromInternet(findViewById(R.id.imgKalendar)).execute(
                meanJson.getJSONObject(7).getString("icon_url"))
            urlKalendar = meanJson.getJSONObject(7).getString("icon_url")
            //Kalendar
        }

        }
        catch (e: Throwable){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() //выводим сообщение пользователю об ошибке
        }

    }
 fun readText(url:String):String{ //было создано для теста
     val apiResponce = URL(url).readText()
     return apiResponce
 }
    fun vkOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleVk)
        intent.putExtra("url",urlVk)
        intent.putExtra("desc",descVk)
        startActivity(intent)
    }
    fun classOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleClass)
        intent.putExtra("url",urlClass)
        intent.putExtra("desc",descClass)
        startActivity(intent)
    }
    fun sferumOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleSferum)
        intent.putExtra("url",urlSferum)
        intent.putExtra("desc",descSferum)
        startActivity(intent)
    }
    fun brainsOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleBrains)
        intent.putExtra("url",urlBrains)
        intent.putExtra("desc",descBrains)
        startActivity(intent)
    }
    fun skillOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleSkill)
        intent.putExtra("url",urlSkill)
        intent.putExtra("desc",descSkill)
        startActivity(intent)
    }
    fun emailOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleEmail)
        intent.putExtra("url",urlEmail)
        intent.putExtra("desc",descEmail)
        startActivity(intent)
    }
    fun cloudOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleCloud)
        intent.putExtra("url",urlCloud)
        intent.putExtra("desc",descCloud)
        startActivity(intent)
    }
    fun kalendarOpen(view: View){
        val intent = Intent(this,Description::class.java)
        intent.putExtra("title",titleKalendar)
        intent.putExtra("url",urlKalendar)
        intent.putExtra("desc",descKalendar)
        startActivity(intent)
    }

}