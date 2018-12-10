package qifuataufiqs.app.portalti16.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Retrofit request() {

        // Instence interceptor dengan cara
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        // Setiap ada request ke Network, mentoring BODY dengan cara,
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Membuat Clinet bisa menggunakan Interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder()
                // Melakukan url dari web servies yang tersedia
                .baseUrl("http://35.186.145.167:1337/")

                // Menambahkan Clent OkHttp
                .client(client)

                // Melakukan konversi dari json string ke java object
                .addConverterFactory(GsonConverterFactory.create())

                // Membangun
                .build();
    }
}
