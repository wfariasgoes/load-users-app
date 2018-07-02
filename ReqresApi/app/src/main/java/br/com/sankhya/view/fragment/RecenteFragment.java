package br.com.sankhya.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.sankhya.R;
import br.com.sankhya.databinding.FragmentRecenteBinding;
import br.com.sankhya.facade.ManagementBO;
import br.com.sankhya.model.UserItem;
import br.com.sankhya.view.adapter.RecentsAdapter;

@SuppressLint("ValidFragment")
public class RecenteFragment extends Fragment implements RecentsAdapter.RecentesItemListener {
    Context context;
    FragmentRecenteBinding binding;
    RecentsAdapter adapter;
    List<UserItem> recents;


    private static RecenteFragment INSTANCE = null;


    public static RecenteFragment getInstance() {
        Bundle args = new Bundle();
        RecenteFragment fragment = new RecenteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recente, container, false);

        recents = new ArrayList<>();
        recents = ManagementBO.getInstance().getUsers();
        if (recents.size() == 0)
            binding.tvEmpty.setVisibility(View.VISIBLE);
        else
            binding.tvEmpty.setVisibility(View.GONE);

        binding.recyclerRecent.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        //Como o Firebase retorna a lista de classificação crescente, por isso precisamos inverter a exibição Recyclerview para exibir o maior item primeiro
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        binding.recyclerRecent.setLayoutManager(linearLayoutManager);
        adapter = new RecentsAdapter(getContext(), recents, this);
        binding.recyclerRecent.setAdapter(adapter);


        return binding.getRoot();
    }


    @Override
    public void onClickSelect(View view, UserItem item) {
        Toast.makeText(getContext(), "" + item.getFirstName()+ " deletado.", Toast.LENGTH_SHORT).show();
        ManagementBO.getInstance().deleteUser(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onIsEmpty() {
        binding.tvEmpty.setVisibility(View.VISIBLE);
    }


}
