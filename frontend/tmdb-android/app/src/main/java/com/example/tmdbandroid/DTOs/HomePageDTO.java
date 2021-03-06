package com.example.tmdbandroid.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomePageDTO {
    @SerializedName("carousel_list")
    public List<Item> movieCarouselList;
    @SerializedName("popular_movies")
    public List<Item> popularMovies;
    @SerializedName("topRated_movies")
    public List<Item> topRatedMovies;

    @SerializedName("trending_tvs")
    public List<Item> tvCarouselList;
    @SerializedName("popular_tvs")
    public List<Item> popularTvs;
    @SerializedName("topRated_tvs")
    public List<Item> topRatedTvs;
}
