package com.pragma.android_java.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pragma.android_java.R;
import com.pragma.android_java.adapters.CatAdapter;
import com.pragma.android_java.databinding.FragmentListBinding;
import com.pragma.android_java.models.Cat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListFragment extends Fragment {
    private static final String TAG = "pragmax";
    private FragmentListBinding binding;
    private RecyclerView recyclerCat;
    private CatAdapter catAdapter;
    private List<Cat> finalCatList;

    @Override
    public View onCreateView(  LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        setHasOptionsMenu(true);
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarListaGatuna();
    }

    private void cargarListaGatuna(){

        recyclerCat = binding.recycler;
        recyclerCat.setLayoutManager(new LinearLayoutManager(getContext()));

        if(finalCatList == null){
            Log.d(TAG, "Lista cargada por primera vez");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(new Runnable() {
                @Override
                public void run() {

                    //Background work here
                    HttpURLConnection urlConnection = null;
                    List<Cat> response = new ArrayList<>();
                    try {
                        URL url = new URL("https://api.thecatapi.com/v1/breeds");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestProperty("x-api-key", "bda53789-d59e-46cd-9bc4-2936630fde39");

                        int code = urlConnection.getResponseCode();
                        if (code != 200) {
                            throw new IOException("Invalid listaGatuna from server: " + code);
                        }

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                        String line;
                        Gson gson = new Gson();

                        while ((line = bufferedReader.readLine()) != null) {
                            response = gson.fromJson(line, new TypeToken<ArrayList<Cat>>() {}.getType());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }

                    finalCatList = response;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //UI Thread work here
                            catAdapter = new CatAdapter(finalCatList, ListFragment.this);
                            recyclerCat.setAdapter(catAdapter);
                        }
                    });
                }
            });
        }else{
            Log.d(TAG, "Lista cargada en memoria");
            catAdapter = new CatAdapter(finalCatList, ListFragment.this);
            recyclerCat.setAdapter(catAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<Cat> filteredlist = new ArrayList<Cat>();

        for (Cat catito : finalCatList) {
            if (catito.name.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(catito);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getContext(), "No cat Found..", Toast.LENGTH_SHORT).show();
        } else {
            catAdapter.filterList(filteredlist);
        }
    }



}