package com.pragma.android_java.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.pragma.android_java.R;
import com.pragma.android_java.databinding.FragmentDetailBinding;
import com.pragma.android_java.models.Cat;

import java.util.Locale;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Cat cat = bundle.getParcelable("cat");
            ((TextView)view.findViewById(R.id.catName)).setText(cat.name);
            ImageView iv = view.findViewById(R.id.imagen);

            if(cat.image != null){
                Glide.with(this).load(cat.image.url).error(R.mipmap.ic_launcher).into(iv);
            }else{
                Glide.with(this).load(R.mipmap.ic_launcher).into(iv);
            }

            Locale loc = new Locale("", cat.countryCode );


            ((TextView)view.findViewById(R.id.catPais)).setText(loc.getDisplayCountry()) ;
            ((TextView)view.findViewById(R.id.catIntely)).setText(String.format("%d", cat.intelligence));
            ((TextView)view.findViewById(R.id.catWeight)).setText("Imperial("+cat.weight.imperial+")\nMetric ("+cat.weight.metric+")");
            ((TextView)view.findViewById(R.id.cfaUrl)).setText(""+cat.cfaUrl);
            ((TextView)view.findViewById(R.id.vetstreetUrl)).setText(""+cat.vetstreetUrl);
            ((TextView)view.findViewById(R.id.vcahospitalsUrl)).setText(""+cat.vcahospitalsUrl);
            ((TextView)view.findViewById(R.id.temperament)).setText(""+cat.temperament);
            ((TextView)view.findViewById(R.id.origin)).setText(""+cat.origin);
            ((TextView)view.findViewById(R.id.description)).setText(""+cat.description);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}