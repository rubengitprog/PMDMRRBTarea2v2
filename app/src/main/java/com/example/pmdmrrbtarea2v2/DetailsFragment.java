package com.example.pmdmrrbtarea2v2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;


public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(getString(R.string.fragment_details));
        }
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        // Inflate the layout for this fragment
        // Obtener referencias a los elementos de la interfaz
        ImageView imageCharacter = view.findViewById(R.id.image_character);
        TextView textTitle = view.findViewById(R.id.text_title);
        TextView textDescription = view.findViewById(R.id.text_description);
        TextView textHabilidad = view.findViewById(R.id.text_habilidad);
        TextView textVida = view.findViewById(R.id.text_vida);

        // Obtener los datos del Bundle
        if (getArguments() != null) {
            String title = getArguments().getString("char_title", getString(R.string.no_title));
            String description = getArguments().getString("char_des", getString(R.string.no_description));
            int imageResId = getArguments().getInt("char_image", R.drawable.ic_dashboard); // Si no hay imagen
            String habilidad = getArguments().getString("char_habilidad", getString(R.string.no_ability));
            int vida = getArguments().getInt("char_hp", 0);

            // Asignar los datos a los elementos de la interfaz
            imageCharacter.setImageResource(imageResId);
            textTitle.setText(title);
            textDescription.setText(description);
            textHabilidad.setText(habilidad);
            textVida.setText(String.valueOf(vida));
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }


}