package com.example.myapplication.network;
import com.example.myapplication.data.BrandData;
import com.example.myapplication.data.BrandResponse;
import com.example.myapplication.data.BrandSearchData;
import com.example.myapplication.data.BrandSearchResponse;
import com.example.myapplication.data.CatBrandData;
import com.example.myapplication.data.CatBrandResponse;
import com.example.myapplication.data.ComData;
import com.example.myapplication.data.ComResponse;
import com.example.myapplication.data.DetailData;
import com.example.myapplication.data.DetailResponse;
import com.example.myapplication.data.HomeSearchData;
import com.example.myapplication.data.HomeSearchResponse;
import com.example.myapplication.data.IngredientData;
import com.example.myapplication.data.IngredientResponse;
import com.example.myapplication.data.RecomData;
import com.example.myapplication.data.RecomResponse;
import com.example.myapplication.data.SearchData;
import com.example.myapplication.data.SearchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/dry/server")
    Call<SearchResponse> searchWord(@Body SearchData data);

    @POST("/home/search")
    Call<HomeSearchResponse> homeSearchWord(@Body HomeSearchData data);

    @POST("/detail/info")
    Call<DetailResponse> detailWord(@Body DetailData data);

    @POST("/detail/test")
    Call<ComResponse> products_id(@Body ComData data);

    @POST("/detail/ingredient")
    Call<IngredientResponse> products_id(@Body IngredientData data);

    @POST("/home/brand_search")
    Call<BrandSearchResponse> brandSearchWord(@Body BrandSearchData data);



    @POST("/home/brand_dog")
    Call<BrandResponse> brandWord(@Body BrandData data);

    @POST("/home/brand_cat")
    Call<CatBrandResponse> brandWord(@Body CatBrandData data);

    @POST("/dog/server")
    Call<RecomResponse> recomWord(@Body RecomData data);
}

