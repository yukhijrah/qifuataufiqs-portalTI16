package qifuataufiqs.app.portalti16;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import qifuataufiqs.app.portalti16.adapter.MahasiswaAdapter;
import qifuataufiqs.app.portalti16.entity.DaftarMahasiswa;
import qifuataufiqs.app.portalti16.network.Network;
import qifuataufiqs.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView lstMahasiswa;
    Button btnMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Casting recyclerview - nya dari ID lst_mahasiswa yang ada di activity_main
        lstMahasiswa = (RecyclerView) findViewById(R.id.lst_mahasiswa);

        // Set layout manager untuk lstMahasiswa
        lstMahasiswa.setLayoutManager(new LinearLayoutManager(this));

        btnMahasiswa = (Button) findViewById(R.id.btn_to_add);

        requestDaftarMahasiswa();
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
        onButtonMahasiswa();
    }

    private void requestDaftarMahasiswa() {

        // Memanggil request() dari retrofit yang sudah dibuat
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

                    // Tampilkan daftar mahasiswa di recyclerview
                    MahasiswaAdapter adapter = new MahasiswaAdapter(mahasiswas.getData());

                    lstMahasiswa.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {
                onMahasiswaError();
            }
        });
    }

    private void onMahasiswaError() {
        Toast.makeText(
                MainActivity.this,
                "Gagal, Silahkan periksa konksi internet anda",
                Toast.LENGTH_LONG).show();
    }

    private void onButtonMahasiswa() {
        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, AddMahasiswaActivity.class);
                startActivity(pindah);
            }
        });
    }
}
