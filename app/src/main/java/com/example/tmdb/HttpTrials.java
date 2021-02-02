package com.example.tmdb;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HttpTrials {
    private static HttpURLConnection connection;
    public static List<Movies> Methord1(){
        //java.net.HttpUrlConnection
        BufferedReader br;
        StringBuffer sb=new StringBuffer();

        try {
            TMDB tmdb=new TMDB(1);
            URL url=new URL(tmdb.getAPI());
            connection=(HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int status=connection.getResponseCode();
            System.out.println(status);
            if(status==200){
                br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String li=br.readLine();
                while(li!=null){
                    sb.append(li);
                    li=br.readLine();
                }
                Gson gson=new Gson();
                Result result=gson.fromJson(sb.toString(),Result.class);
                for(Movies m:result.results)
                    System.out.println(m.toString());
                return result.results;
            }else{
                br=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String li=br.readLine();
                while(li!=null){
                    sb.append(li);
                    li=br.readLine();
                }
                System.out.println(sb);
            }
        }



        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            connection.disconnect();
        }
        return null;
    }

}
