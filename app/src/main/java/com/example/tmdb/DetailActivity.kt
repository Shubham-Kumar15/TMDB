package com.example.tmdb

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.jvm.javaClass
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class DetailActivity : AppCompatActivity(),GetMovieData.AsyncResponce {
    lateinit var imageView: ImageView;
    lateinit var textViewTitle: TextView;
    lateinit var  textViewTag:TextView;
    lateinit var textViewGenres:TextView;
    lateinit var textViewwLang:TextView;
    lateinit var textViewDet:TextView;
    lateinit var textViewRelease:TextView;
    lateinit var textViewBudget:TextView;
    lateinit var textViewRevenue:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var intent:Intent=getIntent();
        var id:Int=intent.getIntExtra(TMDB.ID, 0);
        imageView=findViewById(R.id.imageView2);
        textViewTitle=findViewById(R.id.textView);
        textViewTag=findViewById(R.id.textView3);
        textViewGenres=findViewById(R.id.textView5);
        textViewwLang=findViewById(R.id.textView6);
        textViewRelease=findViewById(R.id.textView8);
        textViewBudget=findViewById(R.id.textView9);
        textViewRevenue=findViewById(R.id.textView10);
        textViewDet=findViewById(R.id.textView7);
        Log.e("The id received",id.toString())
        var gd:GetMovieData= GetMovieData(id,this,this);
        gd.execute();
    }

    override fun onTaskComplete(result: MoviesDetail) {
          textViewDet.text=result.overview;
        textViewTag.text=result.tagline
        textViewRevenue.setText("REVENUE  :  "+result.revenue);
        textViewBudget.setText("BUDGET  :  "+result.budget);
        textViewRelease.setText("RELEASE DATE :  "+result.release_date);
        textViewwLang.setText("LANGUAGE  :  "+result.original_language);
        val sb:StringBuffer= StringBuffer();
        for(i in result.genres)
            sb.append(i.name+"\n");
        textViewGenres.text=sb.toString();
        //textViewTag.text=result.tagline;
        textViewTitle.text=result.original_title;
        Glide.with(this).load(TMDB.baseImage+result.poster_path).into(imageView)

    }
}


class GetMovieData(var id: Int,var context: Context,var ar:AsyncResponce) : AsyncTask<Void, Void, MoviesDetail?>(){
    public interface AsyncResponce{
        fun onTaskComplete(result:MoviesDetail)
    }

    override fun doInBackground(vararg params: Void?): MoviesDetail? {
        val tmdb:TMDB=TMDB(1);
//        try{
//
//        }catch (e:Exception){
//            Log.e("ERROR FROM SECOND aCTIVITY",e.toString());
//        }
        val api:String=tmdb.getMovieAPI(id);
        Log.e("The api call goes to",api);
        var url:URL= URL(api);
        var connection:HttpURLConnection=url.openConnection() as HttpURLConnection;
        connection.connectTimeout = 5000
        connection.requestMethod = "GET"
        connection.readTimeout = 5000
        val status:Int=connection.responseCode;
        if(status==200){
            var sb:StringBuffer= StringBuffer();
            val br:BufferedReader= BufferedReader(InputStreamReader(connection.inputStream));
            var line:String?=br.readLine();
            while(line!=null){
                sb.append(line);
                line=br.readLine();
            }
            Log.e("The result of API call",sb.toString())
            var gson:Gson= Gson();
            var md:MoviesDetail = gson.fromJson(sb.toString(),MoviesDetail::class.java);
            return md;
        }
        return null;
    }

    override fun onPostExecute(result: MoviesDetail?) {
        if(result==null){
            return;
        }
        ar.onTaskComplete(result);
//        textViewDet.text=result.overview;
//        textViewRevenue.setText("REVENUE  :  "+result.revenue);
//        textViewBudget.setText("BUDGET  :  "+result.budget);
//        textViewRelease.setText("RELEASE DATE :  "+result.release_date);
//        textViewwLang.setText("LANGUAGE  :  "+result.original_language);
//        val sb:StringBuffer= StringBuffer();
//        for(i in result.genres)
//            sb.append(i.name+"\n");
//        textViewGenres.text=sb.toString();
//        textViewTag.text=result.tagline;
//        textViewTitle.text=result.original_title;
//        Glide.with(context).load(TMDB.baseImage+result.poster_path).into(imageView)
    }
}
