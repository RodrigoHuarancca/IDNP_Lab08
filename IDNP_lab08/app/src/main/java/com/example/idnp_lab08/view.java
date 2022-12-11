package com.example.idnp_lab08;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class view extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private MyListData[] listData;

    public view (MyListData[] listData) {
        this.listData = listData;
    }
    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewTipe) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {

        final MyListData myListData = listData[position];
        holder.textView.setText(listData[position]);
    }

    @Override
    public int getItemCount() {
        return this.listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (android.widget.TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}