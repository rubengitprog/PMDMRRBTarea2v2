package com.example.pmdmrrbtarea2v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmdmrrbtarea2v2.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private static final String PREF_NAME = "LanguagePreferences";
    private static final String KEY_LANGUAGE = "language";  // Clave para almacenar el idioma

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperar el idioma guardado en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedLanguage = sharedPreferences.getString(KEY_LANGUAGE, "es"); // Por defecto en español

        // Aplicar el idioma antes de inflar cualquier vista
        setLanguage(savedLanguage);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Establecer las acciones de los botones
        Button btnStart = binding.btnStart;
        btnStart.setOnClickListener(this::acceptPressed);

        Button btnExit = binding.btnExit;
        btnExit.setOnClickListener(this::exitPressed);
    }

    private void setLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);

        // Cambiar la configuración global de la actividad
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void acceptPressed(View view) {
        Intent intent = new Intent(this, CharactersActivity.class);
        startActivity(intent);
    }

    private void exitPressed(View view) {
        finish();
    }
}
