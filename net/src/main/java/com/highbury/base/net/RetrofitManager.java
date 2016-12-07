package com.highbury.base.net;

import android.os.Build;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by han on 2016/12/7.
 */

public class RetrofitManager {
    private static volatile RetrofitManager sRetrofitManager = null;

    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    public static String UUID_URL = "";
    public static String BASE_URL = "";

    private RetrofitManager() {
        mOkHttpClient=getClient();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance() {
        if (sRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (sRetrofitManager == null) {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }

    public <T> T createCustomService(Class<T> serviceClazz, long timeout) {
        OkHttpClient client=mOkHttpClient.newBuilder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout,TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder=new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        Retrofit retrofit=builder.build();
        return retrofit.create(serviceClazz);
    }


    public <T> T createService(Class<T> serviceClazz) {
        return mRetrofit.create(serviceClazz);
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        X509TrustManager trustManager=getX509TrustManager();
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging);
//            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(getSSLSocketFactory(trustManager),trustManager)
                .build();
    }

    private X509TrustManager getX509TrustManager() {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init((KeyStore) null);
            TrustManager[] trustManagers = factory.getTrustManagers();
            return (X509TrustManager) trustManagers[0];
        } catch (NoSuchAlgorithmException | KeyStoreException exception) {
//            Log.e(getClass().getSimpleName(), "not trust manager available", exception);
        }

        return null;
    }


    private SSLSocketFactory getSSLSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException exception) {
//            Log.e(getClass().getSimpleName(), "not tls ssl socket factory available", exception);
        }

        return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }

    private String assembleUserAgent(){
        StringBuilder sb=new StringBuilder();
        sb.append("os/").append("Android").append(" ");
        sb.append("model/").append(Build.MODEL).append(" ");
        sb.append("brand/").append(Build.BRAND).append(" ");
        sb.append("sdk/").append(Build.VERSION.SDK_INT).append(" ");
        sb.append("applicationID/").append(BuildConfig.APPLICATION_ID).append(" ");
        sb.append("version/").append(BuildConfig.VERSION_NAME);
        return sb.toString();
    }
}
