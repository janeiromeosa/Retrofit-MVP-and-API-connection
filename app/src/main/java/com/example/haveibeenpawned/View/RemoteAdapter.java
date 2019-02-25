package com.example.haveibeenpawned.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawned.R;

import java.util.ArrayList;
import java.util.List;

public class RemoteAdapter extends RecyclerView.Adapter<RemoteAdapter.PawnedViewHolder> {

    private final List<HaveIBeenPawnedRepo> data;

    public RemoteAdapter() {
        this.data = new ArrayList<>();
    }

    public void setData(List<HaveIBeenPawnedRepo> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PawnedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new PawnedViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull PawnedViewHolder pawnedViewHolder, int position) {
        HaveIBeenPawnedRepo haveIBeenPawnedDataClass = data.get(position);

        pawnedViewHolder.tvName.setText(haveIBeenPawnedDataClass.getName());
        pawnedViewHolder.tvTitle.setText(haveIBeenPawnedDataClass.getTitle());
        pawnedViewHolder.tvDomain.setText(haveIBeenPawnedDataClass.getDomain());
        pawnedViewHolder.tvBreachDate.setText(haveIBeenPawnedDataClass.getBreachDate());
        pawnedViewHolder.tvAddedDate.setText(haveIBeenPawnedDataClass.getAddedDate());
        pawnedViewHolder.tvModifiedDate.setText(haveIBeenPawnedDataClass.getModifiedDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PawnedViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvTitle;
        TextView tvDomain;
        TextView tvBreachDate;
        TextView tvDescription;
        TextView tvAddedDate;
        TextView tvModifiedDate;

        public PawnedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDomain = itemView.findViewById(R.id.tvDomain);
            tvBreachDate = itemView.findViewById(R.id.tvBreachDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAddedDate = itemView.findViewById(R.id.tvAddedDate);
            tvModifiedDate = itemView.findViewById(R.id.tvModifiedDate);
        }
    }
}
