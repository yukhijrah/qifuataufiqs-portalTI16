package qifuataufiqs.app.portalti16.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import qifuataufiqs.app.portalti16.DetailMahasiswaActivity;
import qifuataufiqs.app.portalti16.R;
import qifuataufiqs.app.portalti16.entity.Mahasiswa;
import qifuataufiqs.app.portalti16.holder.MahasiswaHolder;
import qifuataufiqs.app.portalti16.util.Consts;

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
        final MahasiswaHolder holder = new MahasiswaHolder(view);

        final Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendefinisikan position untuk getMahasiswa.
                int adapterPosition = holder.getAdapterPosition();
                Mahasiswa mahasiswa = mahasiswas.get(adapterPosition);

                Intent detailIntent = new Intent(context, DetailMahasiswaActivity.class);
                detailIntent.putExtra("mahasiswa", mahasiswa);
                detailIntent.putExtra(Consts.KEY_ACTION_DETAIL, Consts.INTENT_EDIT);
                context.startActivity(detailIntent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MahasiswaHolder holder, final int position) {
        holder.txtNama.setText(mahasiswas.get(position).getName());
        holder.txtNim.setText(mahasiswas.get(position).getNim());
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public interface MahasiswaListener {
        void onDelete(int mhsId);
    }
}
