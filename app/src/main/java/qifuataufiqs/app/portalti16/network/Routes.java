package qifuataufiqs.app.portalti16.network;

import qifuataufiqs.app.portalti16.entity.DaftarMahasiswa;
import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Routes {

    /**
     * Mendifinisikan route dari web services yang tersedia
     * Jadikan di deskripsi, berarti:
     * http://ti16.herouapp/list.php
     */
    @GET("list.php")
    Call<DaftarMahasiswa> getMahasiswa();

    @POST("add.php")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("nama") String nama,
            @Field("nim") String nim
    );
}
