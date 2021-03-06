package qifuataufiqs.app.portalti16;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import qifuataufiqs.app.portalti16.adapter.MahasiswaAdapter;
import qifuataufiqs.app.portalti16.entity.DaftarMahasiswa;
import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import qifuataufiqs.app.portalti16.network.Network;
import qifuataufiqs.app.portalti16.network.Routes;
import qifuataufiqs.app.portalti16.util.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Deklarasikan recyclerviewnya
    private RecyclerView lstMahasiswa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Casting recyclerview - nya dari ID lst_mahasiswa yang ada di activity_main
        lstMahasiswa = (RecyclerView) findViewById(R.id.lst_mahasiswa);

        // Set Layout Manager untuk lstMahasiswa
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstMahasiswa.setLayoutManager(linearLayoutManager);

        // RequestDaftarMahasiswa();
        findViewById(R.id.btn_to_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, DetailMahasiswaActivity.class);
                addIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_ADD);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestDaftarMahasiswa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Menampilkan Menu di Activity
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:

                // Ketika Icon refresh di click, maka memanggil
                requestDaftarMahasiswa();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestDaftarMahasiswa() {

        // Memanggil request() dari retrofit yang sudah dibuat
        final Routes services = Network.request().create(Routes.class);

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

                    // Menghandel tompol hapus di item_mahasiswa, fungsinya untuk menghapus data yang ada di API
                    adapter.setListener(new MahasiswaAdapter.MahasiswaListener() {
                        @Override
                        public void onDelete(int mhsId) {

                            // Konversi int ke string
                            String id = String.valueOf(mhsId);
                            deleteMahasiswa(services, id);
                        }
                    });

                    lstMahasiswa.setAdapter(adapter);
                } else {

                    // Ketika data tidak berhasil di load
                    onMahasiswaError();
                }
            }

            @Override
            public void onFailure(Call<DaftarMahasiswa> call, Throwable t) {
                // Ketika data tidak berhasil di load
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

    private void deleteMahasiswa(final Routes servies, final String mhsId) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.app_name);
        alert.setMessage("Apakah kamu yakin?");
        alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                servies.deleteMahasiswa(mhsId).enqueue(new Callback<Mahasiswa>() {
                    @Override
                    public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                        if(response.isSuccessful()) {
                            requestDaftarMahasiswa();
                        } else {
                            onMahasiswaError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mahasiswa> call, Throwable t) {
                        onMahasiswaError();
                    }
                });
            }
        });
        alert.show();
    }
}
