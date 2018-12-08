package qifuataufiqs.app.portalti16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import qifuataufiqs.app.portalti16.network.Network;
import qifuataufiqs.app.portalti16.network.Routes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMahasiswaActivity extends AppCompatActivity {

    private EditText edtNama, edtNim;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        //casting untuk semua view
        edtNama = (EditText) findViewById(R.id.edt_name);
        edtNim = (EditText) findViewById(R.id.edt_nim);
        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = edtNama.getText().toString();
                String nim = edtNim.getText().toString();
                addNewMahasiswa(nama, nim);
            }
        });
    }
    private void addNewMahasiswa(String name, String nim){
        Routes services = Network.request().create(Routes.class);

        //lalu lakukan post terhadap post pada mahasiswa baru terhadap API /add.php
        services.postMahasiswa(name, nim).enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                if (response.isSuccessful()){
                    //kembalik ke aktifitas sebelumnya
                    finish();
                } else {
                    Toast.makeText(AddMahasiswaActivity.this, "Afwan, terjadinya kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable tgit) {
                Toast.makeText(AddMahasiswaActivity.this, "Afwan, terjadinya kesalahan", Toast.LENGTH_SHORT).show();
            }

        });
    }
}

