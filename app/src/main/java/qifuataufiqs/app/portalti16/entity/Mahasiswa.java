package qifuataufiqs.app.portalti16.entity;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    private String name;
    private String nim;

    public Mahasiswa(String name, String nim) {
        this.name = name;
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }
}
