package br.com.sankhya.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.sankhya.R;
import br.com.sankhya.databinding.LayoutHomeItemBinding;
import br.com.sankhya.network.response.Datum;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {
    private HomeItemListener listener;
    private List<Datum> users;
    private Context context;

    public HomeItemAdapter(HomeItemListener listener, List<Datum> users, Context context) {
        this.context = context;
        this.listener = listener;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_item, null, false);
        return new HomeItemAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Datum item = users.get(position);
        holder.binding.tvName.setText(item.getFirstName()+" "+item.getLastName());

        Picasso.with(context)
                .load(item.getAvatar())
                .into(holder.binding.imgWallpaper);

        holder.binding.imgWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSelect(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public Datum getSelectedCountry(int position) {
        return users.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LayoutHomeItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onClick(View v) {
            binding.imgWallpaper.setOnClickListener(this);
        }
    }

    public interface HomeItemListener{
        void onClickSelect(View v, int layoutPosition);
    }
}
