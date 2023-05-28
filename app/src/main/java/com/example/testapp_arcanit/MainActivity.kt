package com.example.testapp_arcanit


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val search = findViewById<Button>(R.id.button)
        search.setOnClickListener {
            if(editText.text.length>2) {
                GetStatLogin(editText.text.toString())
                GetStatRep(editText.text.toString())
            }else{
                val text = "Введите от 3 символов"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
    }
    var logins = ArrayList<String>()
    var imgs = ArrayList<String>()
    var Reps = ArrayList<String>()
    var scores = ArrayList<String>()
    var Descriptions = ArrayList<String>()
    var forks_counts = ArrayList<String>()
//    fun getMethod() {
//        // Create Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.github.com")
//            .build()
//        // Create Service
//        val service = retrofit.create(APIService::class.java)
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getUser("novohu")
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    // Convert raw JSON to pretty JSON using GSON library
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val prettyJson = gson.toJson(
//                        JsonParser.parseString(
//                            response.body()
//                                ?.string()
//                        )
//                    )
//
//                    Log.d("Pretty Printed JSON :", prettyJson)
//
//                } else {
//                    Log.e("RETROFIT_ERROR", response.code().toString())
//                }
//            }
//        }
//    }
    fun GetStatLogin(login : String) {
                val url = "https://api.github.com/search/users?q=$login"
                val queue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    url,
                    {
                            response->
                        val obj = JSONObject(response)
                        initlog(obj)
                    },
                    {
                        Log.d("MyLog","Volley error: $it")
                    }
                )
                queue.add(stringRequest)
    }

    fun GetStatRep(rep : String) {
                val url = "https://api.github.com/search/repositories?q=$rep"
                val queue = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    url,
                    {
                            response->
                        val obj = JSONObject(response)
                        initRep(obj)
                    },
                    {
                        Log.d("MyLog","Volley error: $it")
                    }
                )
                queue.add(stringRequest)
    }

    fun updatelog(){
        logins.clear()
        scores.clear()
        imgs.clear()
        HelperAdapter(logins,imgs,scores, this)!!.notifyDataSetChanged()
    }
    fun updateRep(){
        Reps.clear()
        Descriptions.clear()
        forks_counts.clear()
        HelperAdapterRep(Reps,Descriptions, forks_counts, this)!!.notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    private fun initlog(obj: JSONObject) {
        updatelog()
        val arrLogin = obj.getJSONArray("items")
        for (i in 0..arrLogin.length()-1) {
            val user_current = arrLogin.getJSONObject(i)
            val login = user_current.getString("login")
            val img = user_current.getString("avatar_url")
            val score = user_current.getString("score")
            logins.add(login)
            scores.add(score)
            imgs.add(img)
            Log.d("MyLog", (i+1).toString()+"  $login")
        }
        Log.d("MyLog", imgs.toString())
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.setLayoutManager(linearLayoutManager)
        val handler = Handler()
        handler.postDelayed({
            val helperAdapter = HelperAdapter(logins,imgs,scores, this)
            recyclerView.setAdapter(helperAdapter)
        }, 3000)
    }
    @SuppressLint("SetTextI18n")
    private fun initRep(obj: JSONObject) {
        updateRep()
        val arrRep = obj.getJSONArray("items")
        for (i in 0..arrRep.length()-1) {
            val Rep_current = arrRep.getJSONObject(i)
            val rep = Rep_current.getString("name")
            val description = Rep_current.getString("description")
            val forks_count = Rep_current.getString("forks_count")
            Reps.add(rep)
            Descriptions.add(description)
            forks_counts.add(forks_count)
            Log.d("MyLog", "Name:$rep \nDescription:$description \nForks_count:$forks_count")
        }
        Log.d("MyLog", Reps.toString() + " " + Descriptions.toString() + " " + forks_counts.toString())
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val recyclerView = findViewById<RecyclerView>(R.id.rvRep)
        recyclerView.setLayoutManager(linearLayoutManager)
        val handler = Handler()
        handler.postDelayed({
            val helperAdapter = HelperAdapterRep(Reps,Descriptions, forks_counts,this)
            recyclerView.setAdapter(helperAdapter)
        }, 3000)
    }
}
