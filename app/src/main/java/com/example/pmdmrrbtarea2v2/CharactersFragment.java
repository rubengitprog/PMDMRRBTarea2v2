package com.example.pmdmrrbtarea2v2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pmdmrrbtarea2v2.databinding.FragmentCharactersBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharactersFragment extends Fragment implements GameAdapter.OnItemClickListener {

    private FragmentCharactersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(getString(R.string.fragment_title2));
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Crear datos
        List<GameData> gameList = new ArrayList<>();
        gameList.add(new GameData("Mario", getString(R.string.descripcionMario), R.drawable.mario,getString(R.string.mario_ability),200));
        gameList.add(new GameData("Peach", getString(R.string.descripcionPeach), R.drawable.peach,getString(R.string.peach_ability),150));
        gameList.add(new GameData("Toad", getString(R.string.descripcionToad), R.drawable.toad,getString(R.string.toad_ability),170));
        gameList.add(new GameData("Luigi", getString(R.string.descripcionLuigi), R.drawable.luigi,getString(R.string.luigi_ability),180));
        gameList.add(new GameData("Bowser", getString(R.string.descripcionBowser), R.drawable.finalbowser,getString(R.string.bowser_ability),300));

        // Configurar el RecyclerView con el listener
        GameAdapter gameAdapter = new GameAdapter(gameList, this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(gameAdapter);
        showSnackbar();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onItemClick(int position) {
        // Asegurarse de que el adaptador no sea nulo
        GameAdapter gameAdapter = (GameAdapter) binding.recyclerView.getAdapter();
        if (gameAdapter != null) {
            // Obtener el objeto GameData basado en la posición
            GameData game = gameAdapter.gameList.get(position);
            // Mostrar un Toast con el nombre del personaje
            Toast.makeText(getContext(), getString(R.string.click_in) + game.getTitle(), Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("char_title", game.getTitle());
            bundle.putString("char_des", game.getDescription());
            bundle.putInt("char_image", game.getImage());
            bundle.putString("char_habilidad", game.getHabilidad());
            bundle.putInt("char_hp", game.getVida());
            NavController navController = Navigation.findNavController(getActivity(), R.id.fragment_container_view);
            navController.navigate(R.id.detailsFragment, bundle);

        }
    }

    private void showSnackbar() {
        // Obtenemos la vista de la actividad para mostrar el Snackbar
        View rootView = getView();

        // Mostrar el Snackbar con el mensaje
        Snackbar.make(rootView, getString(R.string.welcome), Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acción cuando se presiona el botón "OK"
                    }
                })
                .show();  // Mostrar el Snackbar
    }
}