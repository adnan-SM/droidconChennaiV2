package `in`.droidcon.base.repo.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {
    /**
     * Provide "make" methods to create instances of [ServiceClass]
     * and its related dependencies, such as OkHttpClient, Gson, etc.
     */
    inline fun <reified ServiceClass> makeService(isDebug: Boolean): ServiceClass = makeService(
        isDebug,
        makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        ), makeGson()
    )

    inline fun <reified ServiceClass> makeService(
        isDebug: Boolean,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ServiceClass = makeRetrofit(isDebug, okHttpClient, gson).create(ServiceClass::class.java)

    fun makeRetrofit(
        isDebug: Boolean,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(
            if (isDebug) {
                "https://sessionize.com/api/v2/jl4ktls0/view/"
            } else {
                "https://sessionize.com/api/v2/e94o5exw/view/"
            }
        )
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    fun makeGson(): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
}