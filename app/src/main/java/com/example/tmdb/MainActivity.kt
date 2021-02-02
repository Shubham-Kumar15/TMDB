package com.example.tmdb

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),MyAdapter.OnItemClicked,GetData.AsyncResponce {
    lateinit var list:List<Movies>;
    lateinit var adapter: MyAdapter;
    lateinit var recyclerView: RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.actMainRecyclerView);
        var getData:GetData=GetData(this,this);
        getData.execute();
    }

    override fun onClick(pos: Int) {
        var intent:Intent=Intent(this,DetailActivity::class.java);
        intent.putExtra(TMDB.ID,list[pos].id);
        startActivity(intent);
    }

    override fun onTaskComplete(list: List<Movies>) {
        this.list=list;
        adapter= MyAdapter(this,list,this);
        recyclerView.layoutManager=LinearLayoutManager(this);
        recyclerView.adapter=adapter;
    }
}

class GetData(val context: Context,val async:AsyncResponce): AsyncTask<String, String,List<Movies> >(){
    public interface AsyncResponce{
        fun onTaskComplete(list:List<Movies>);
    }
    override fun doInBackground(vararg params: String?): List<Movies> {
        val request:HttpRequest= HttpRequest();
        var res:List<Movies> = request.Methord1();
        return res;
    }

    override fun onPostExecute(result: List<Movies>?) {
        //var ar:AsyncResponce=this as AsyncResponce;
        if(result!=null)
        async.onTaskComplete(result);
//                if(result!=null)
//                {
//                    val adapter:MyAdapter= MyAdapter(context,result);
//                    recyclerView.layoutManager=LinearLayoutManager(context);
//                    recyclerView.adapter=adapter;
//                }
    }
}
