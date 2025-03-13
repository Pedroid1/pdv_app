package com.pedroid.pdv_app.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pedroid.pdv_app.data.remote.api.OrderAPI;
import com.pedroid.pdv_app.data.repository.OrderRepository;
import com.pedroid.pdv_app.domain.repository.IOrderRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7190/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OrderAPI provideOrderApi(Retrofit retrofit) {
        return retrofit.create(OrderAPI.class);
    }

    @Provides
    @Singleton
    public IOrderRepository provideOrderRepository(OrderAPI orderAPI) {
        return new OrderRepository(orderAPI);
    }
}

