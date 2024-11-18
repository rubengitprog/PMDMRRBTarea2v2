package com.example.pmdmrrbtarea2v2;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdmrrbtarea2v2.databinding.ItemCardviewBinding;


public class GameViewHolder extends RecyclerView.ViewHolder {
    private final ItemCardviewBinding binding;
    private final GameAdapter.OnItemClickListener onItemClickListener;

    // Constructor que recibe el binding y el listener
    public GameViewHolder(ItemCardviewBinding binding, GameAdapter.OnItemClickListener onItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.onItemClickListener = onItemClickListener;


        // Dentro de GameViewHolder:
        binding.getRoot().setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(position); // Llamamos al listener con la posición
            } else {
                Log.d("GameViewHolder", "Posición no válida: " + position);
            }
        });
    }

    // Método para bindear los datos al ViewHolder
    public void bind(GameData game) {
        binding.txtName.setText(game.getTitle());
        binding.txtDescription.setText(game.getDescription());
        binding.image.setImageResource(game.getImage());
        binding.executePendingBindings(); // Asegura que las vistas estén actualizadas
    }
}

