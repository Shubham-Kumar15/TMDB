package com.example.tmdb;

public class TMDB {
    static final String base="https://api.themoviedb.org/3/movie/popular?";
    static final String moviesBase="https://api.themoviedb.org/3/movie/";
    static final String ApiKey="f34c7550ac4ff9b6367d6cb0656c3d4c";
    static final String baseImage="https://image.tmdb.org/t/p/original/";
    static final String lan="en-US";
    int page;
    final static String ID="ID";

    public TMDB(int page) {
        this.page = page;
    }
    public String getAPI(){
        String res=base+"api_key="+ApiKey+"&";
        res=res+"language="+lan+"&";
        res=res+"page="+page;
        page++;
        return  res;
    }
    public String getMovieAPI(int id){
        String res=moviesBase+id+"?"+"api_key="+ApiKey+"&language="+lan;
        return res;
    }

}
