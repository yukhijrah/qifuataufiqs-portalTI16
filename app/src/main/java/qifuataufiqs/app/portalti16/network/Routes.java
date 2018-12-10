package qifuataufiqs.app.portalti16.network;

import qifuataufiqs.app.portalti16.entity.DaftarMahasiswa;
import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Routes {

    /**
     * Mendefinisikan route dari web services yang tersedia
     */
    // Melakukan post data mahasiswa baru, jika dideskripsikan berarti di https://herokuapp.com/list_mahasiswa
    @GET("dev/list_mahasiswa")
    Call<DaftarMahasiswa> getMahasiswa();

    /**
     * Mendefinisikan route dari web services yang tersedia
     * @param name
     * @param nim
     */
    // Melakukan post data mahasiswa baru, jika dideskripsikan berarti di https://herokuapp.com/post_mahasiswa
    @POST("dev/insert_mahasiswa")
    @FormUrlEncoded
    Call<Mahasiswa> postMahasiswa(
            @Field("name") String name,
            @Field("nim") String nim
    );

    /**
     * Menghapus mahasiswa berdasarkan ID
     * @param mhsId
     * @return
     */
    @DELETE("mahasiswatest/{mhsId}")
    Call<Mahasiswa> deleteMahasiswa(
            @Path("mhsId") String mhsId
    );
}
