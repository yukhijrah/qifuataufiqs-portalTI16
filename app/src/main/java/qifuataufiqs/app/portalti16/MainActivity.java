package qifuataufiqs.app.portalti16;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import qifuataufiqs.app.portalti16.entity.DaftarMahasiswa;
import qifuataufiqs.app.portalti16.network.Network;
import qifuataufiqs.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestDaftarMahasiswa();
    }

    private void requestDaftarMahasiswa() {

        // Pertama, untuk memanggil request() dari retrofit yang sudah dibuat
        Routes services = Network.request().create(Routes.class);

        // Melakukan request terhadap getMahasiswa()
        services.getMahasiswa().enqueue(new Callback<DaftarMahasiswa>() {
            @Override
            public void onResponse(Call<DaftarMahasiswa> call, Response<DaftarMahasiswa> response) {

                // Mengecek request yang dilakukan, berhasil / gagal
                if (response.isSuccessful()) {

                    // Casting data yang didapatkan, menjadikan DaftarMahasiswa
                    DaftarMahasiswa mahasiswas = response.body();

                    // Mendapatkan gelar
                    Log.d("qifuataufiqs", mahasiswas.getTitle());
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {

            }
        });
    }
}
