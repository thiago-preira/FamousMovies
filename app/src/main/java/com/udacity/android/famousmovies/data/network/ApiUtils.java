package com.udacity.android.famousmovies.data.network;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.util.DisplayMetrics;

import com.udacity.android.famousmovies.BuildConfig;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface ApiUtils {

    String BASE_URL = "http://api.themoviedb.org/3/movie/";
    String POPULAR_MOVIES_FILTER = "popular";
    String TOP_RATED_MOVIES_FILTER = "top_rated";
    String FAVORITE_MOVIES_FILTER = "favorite";


    class ImageSize {

        public static final String W92 = "w92";
        public static final String W154 = "w154";
        public static final String W185 = "w185";
        public static final String W342 = "w342";
        public static final String W500 = "w500";
        public static final String W780 = "w780";


        @StringDef({W92, W154, W185, W342, W500, W780})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Size {
        }

        public ImageSize(@Size String size) {
        }

        public static String getSizeByDensity(int density) {
            switch (density) {
                case DisplayMetrics.DENSITY_LOW:
                    return W92;
                case DisplayMetrics.DENSITY_MEDIUM:
                    return W154;
                case DisplayMetrics.DENSITY_HIGH:
                    return W185;
                case DisplayMetrics.DENSITY_XHIGH:
                    return W342;
                case DisplayMetrics.DENSITY_XXHIGH:
                    return W500;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    return W780;
                default:
                    return W780;
            }
        }
    }


    class Client {
        private static OkHttpClient client;

        static {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request request = chain.request();
                            HttpUrl originalHttpUrl = request.url();
                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter("api_key", BuildConfig.MovieDBApiKey)
                                    .build();
                            return chain.proceed(request.newBuilder().url(url).build());
                        }
                    })
                    //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }

        public static MovieWebservice getInstance() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(MovieWebservice.class);
        }
    }

}
