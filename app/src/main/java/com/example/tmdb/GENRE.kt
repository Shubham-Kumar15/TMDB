package com.example.tmdb

data class GENRE(val id:Int,val name:String);
data class ProductionCOMPANY(val name:String,val id:Int);
data class MoviesDetail(val budget:Int,
                        val genres:List<GENRE>,
                        val id:Int,
                        val original_language:String,
                        val original_title:String,
                        val overview:String,
                        val release_date:String,
                        val revenue:Int,
                        val tagline:String,
                        val poster_path:String,
                        val production_companies:List<ProductionCOMPANY>)