package qifuataufiqs.app.portalti16.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import qifuataufiqs.app.portalti16.R;

public class MahasiswaHolder extends RecyclerView.ViewHolder {

    public TextView txtNama;
    public TextView txtNim;
    public Button btnHapus;

    public MahasiswaHolder(View itemView) {
        super(itemView);
        txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
        txtNim = (TextView) itemView.findViewById(R.id.txt_nim);
        btnHapus = (Button) itemView.findViewById(R.id.btn_hapus);
    }
}
