package com.pragma.android_java.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.pragma.android_java.R;
import com.pragma.android_java.models.Cat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder>{

    private List<Cat> catList;
    private Fragment fragment;

    public CatAdapter(List<Cat> catList, Fragment context) {
        this.catList = catList;
        this.fragment = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNombre.setText(catList.get(position).name);
        holder.txtDetail.setText(catList.get(position).description);
        Locale loc = new Locale("",catList.get(position).countryCode );
        String countryAndIntelligence = loc.getDisplayCountry() +" . "+ catList.get(position).intelligence + fragment.getResources().getString(R.string.by_intelligence);
        holder.txtCountryAndIntelligence.setText(countryAndIntelligence);

        if(catList.get(position).image != null){
            Glide.with(fragment).load(catList.get(position).image.url).error(R.mipmap.ic_launcher).into(holder.imagen);
        }else{
            Glide.with(fragment).load(R.mipmap.ic_launcher).into(holder.imagen);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("cat", catList.get(position));

        holder.btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(fragment).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
    }

    public void filterList(ArrayList<Cat> filterlist) {
        catList = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView txtNombre ;
        private final TextView txtDetail ;
        private final TextView txtCountryAndIntelligence ;
        private final ImageView imagen ;
        private final Button btnMas ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDetail = itemView.findViewById(R.id.txtDetail);
            btnMas = itemView.findViewById(R.id.btn_mas);
            txtCountryAndIntelligence = itemView.findViewById(R.id.txtCountryAndIntelligence);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }

}
