package qifuataufiqs.app.portalti16.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import qifuataufiqs.app.portalti16.R;
import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import qifuataufiqs.app.portalti16.holder.MahasiswaHolder;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaHolder> {

    private List<Mahasiswa> mahasiswas;
    private MahasiswaListener listener;

    public MahasiswaAdapter(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public void setListener(MahasiswaListener listener) {
        this.listener = listener;
    }

    @Override
    public MahasiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        MahasiswaHolder mahasiswaHolder = new MahasiswaHolder(view);
        return mahasiswaHolder;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, final int position) {
        holder.txtNama.setText(mahasiswas.get(position).getName());
        holder.txtNim.setText(mahasiswas.get(position).getNim());

        // Menambahkan Tombol Hapus
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(mahasiswas.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public interface MahasiswaListener {
        void onDelete(int mhsId);
    }
}
