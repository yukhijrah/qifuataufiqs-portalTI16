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
     * Mendefinisikan route dari web services yang tersedia
     * Jadikan di deskripsi, berarti:
     * http://ti16.herouapp/list.php
     */

    // Melakukan post data mahasiswa baru, jika dideskripsikan berarti di https://herokuapp.com/list_mahasiswa
    @GET("list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    /**
     * Mendefinisikan route dari web services yang tersedia
     * Jadikan di deskripsi berarti:
     * https://ti16.herokuapp.com/add.php
     */

    // Melakukan post data mahasiswa baru, jika dideskripsikan berarti di https://herokuapp.com/post_mahasiswa
    @POST("post_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("Sukmo Afri Ardi Saputro") String nama,
            @Field("0110216060") String nim
    );
}
