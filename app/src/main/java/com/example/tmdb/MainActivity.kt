package com.example.tmdb

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView=findViewById(R.id.actMainRecyclerView);
        class GetData(val context: Context): AsyncTask<String, String,List<Movies> >(){
            override fun doInBackground(vararg params: String?): List<Movies> {
                val request:HttpRequest= HttpRequest();
                var res:List<Movies> = request.Methord1();
                return res;
            }

            override fun onPostExecute(result: List<Movies>?) {
                if(result!=null)
                {
                    val adapter:MyAdapter= MyAdapter(context,result);
                    recyclerView.layoutManager=LinearLayoutManager(context);
                    recyclerView.adapter=adapter;
                }
            }
        }
        var getData:GetData=GetData(this);
        getData.execute();
    }

}