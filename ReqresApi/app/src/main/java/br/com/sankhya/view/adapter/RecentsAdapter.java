package br.com.sankhya.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.sankhya.R;
import br.com.sankhya.databinding.LayoutRecentsItemBinding;
import br.com.sankhya.model.UserItem;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.ViewHolder> {

    private Context context;
    private List<UserItem> recents;
    private RecentesItemListener listener;

    public RecentsAdapter(Context context, List<UserItem> recents, RecentesItemListener listener) {
        this.context = context;
        this.recents = recents;
        this.listener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recents_item, parent, false);
        int height = parent.getMeasuredHeight()/2;
        itemView.setMinimumHeight(height);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, final int position) {
//        UserRecents
        final UserItem item = recents.get(position);
        if (recents != null){
            Picasso.with(context)
                    .load(recents.get(position).getAvatar())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.binding.imgUserItem);
            holder.binding.tvName.setText(item.getFirstName()+" "+item.getLastName());
            holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickSelect(v,item);
                    removeItem(item);
                }
            });
        }else  if (recents.size() == 0){
            listener.onIsEmpty();
        }
    }

    private void removeItem(UserItem user) {

        int currPosition = recents.indexOf(user);
        recents.remove(currPosition);
//        ManagementBO.getInstance().deleteUser(user);
//        notifyItemRemoved(currPosition);
    }


    @Override
    public int getItemCount() {
        return recents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LayoutRecentsItemBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface RecentesItemListener{
        void onClickSelect(View v, UserItem layoutPosition);
        void onIsEmpty();
    }
}
