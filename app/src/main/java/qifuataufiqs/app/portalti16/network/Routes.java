package qifuataufiqs.app.portalti16.network;

import retrofit2.http.GET;

public interface Routes {

    /**
     * Mendifinisikan route dari web services yang tersedia
     * Jadikan di deskripsi, berarti:
     * http://ti16.herouapp/list.php
     */
    @GET("list.php")
    void getMahasiswa();
}
