package com.example.pmdmrrbtarea2v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pmdmrrbtarea2v2.databinding.FragmentLanguageBinding;

import java.util.Locale;
import java.util.Objects;

public class LanguageFragment extends Fragment {

    private FragmentLanguageBinding binding;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "LanguagePreferences";
    private static final String KEY_LANGUAGE = "language";  // Clave para almacenar el idioma

    public LanguageFragment() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Usar View Binding para inflar el diseño
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Cambiar el título de la Toolbar
        if (getActivity() instanceof AppCompatActivity) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.language_title); // Usar el título desde los recursos
        }
        // Inicializar SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Obtener el idioma guardado, si no existe, usa el idioma actual del sistema
        String savedLanguage = sharedPreferences.getString(KEY_LANGUAGE, getResources().getConfiguration().getLocales().get(0).getLanguage());

        // Establecer el idioma de la aplicación
        setLanguage(savedLanguage);

        // Obtener el idioma actual y establecer el estado del Switch
        String currentLanguage = getResources().getConfiguration().getLocales().get(0).getLanguage();
        binding.switchLanguage.setChecked(currentLanguage.equals("en")); // Inglés seleccionado si el idioma es "en"

        // Listener del Switch para cambiar el idioma
        binding.switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                changeLanguage("en");
            } else {
                changeLanguage("es");
            }
        });
    }

    private void changeLanguage(String languageCode) {
        // Cambia el idioma de la aplicación
        setLanguage(languageCode);

        // Guardar el idioma seleccionado en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LANGUAGE, languageCode);
        editor.apply();

        // Mostrar un mensaje al usuario indicando el cambio de idioma
        Toast.makeText(requireContext(), getString(R.string.language_change), Toast.LENGTH_SHORT).show();

        // Llamar a recreate() para reiniciar la actividad y aplicar el cambio global
        requireActivity().recreate();
    }

    private void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Cambiar la configuración global de la actividad y los fragmentos
        requireActivity().getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Limpia el binding al destruir la vista
    }
}
