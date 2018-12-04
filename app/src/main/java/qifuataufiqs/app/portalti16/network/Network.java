package qifuataufiqs.app.portalti16.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Retrofit request() {
        return new Retrofit.Builder()

                // Melakukan url dari web servies yang tersedia
                .baseUrl("https://ti16.herouapp.com/")

                // Melakukan konversi dari json string ke java object
                .addConverterFactory(GsonConverterFactory.create())

                // Membangun
                .build();
    }
}
