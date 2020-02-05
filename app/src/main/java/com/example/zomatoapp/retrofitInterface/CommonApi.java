package com.example.zomatoapp.retrofitInterface;

import com.example.zomatoapp.dataModel.CategoryListModel;
import com.example.zomatoapp.dataModel.CollectionListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommonApi {

    @GET("{apiVersion}/categories")
    Call<CategoryListModel> getCategories(
            @Path("apiVersion") String apiVersion
    );

    @GET("{apiVersion}/collections")
    Call<CollectionListModel> getCollections(
            @Path("apiVersion") String apiVersion,
            @Query("city_id") int cityId,
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("count") int count
    );
}
