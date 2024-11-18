package com.example.pmdmrrbtarea2v2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pmdmrrbtarea2v2.databinding.ActivityCharactersBinding;

import java.util.Locale;
import java.util.Objects;

public class CharactersActivity extends AppCompatActivity {
    private NavController navController;
    private ActivityCharactersBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        configure();

    }

    private void configure() {
        // Obtiene el NavHostFragment desde el fragment_container_view y el NavController
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);

            // Vincula el BottomNavigationView con el NavController
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

            // Vincula la ActionBar con el NavController para habilitar la navegación
            NavigationUI.setupActionBarWithNavController(this, navController);

            // Añade el listener para cambios de destino
            navController.addOnDestinationChangedListener(this::onChangeView);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(this::onMenuItemSelected);

        configureToggleMenu();
        setupNavigationView();
    }

    private void setupNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home_drawer) {
                navController.navigate(R.id.blankFragment);
            } else if (id == R.id.navigation_profile_drawer) {
                navController.navigate(R.id.settingsFragment);
            } else if (id == R.id.language_drawer) {
                navController.navigate(R.id.languageFragment);
            } else if (id == R.id.exit_drawer) {
                finish();
            } else {
                return false;
            }

            // Cierra el Drawer después de manejar el clic
            binding.drawerLayout.closeDrawer(binding.navigationView);
            return true;
        });
    }


    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        // Obtén el ActionBar de manera segura
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;  // Si no hay ActionBar, no hacemos nada

        boolean isDetailsFragment = navDestination.getId() == R.id.detailsFragment;

        // Configura el comportamiento del ActionBar según el destino
        actionBar.setDisplayHomeAsUpEnabled(isDetailsFragment);

        // Configura el comportamiento del Drawer según el destino
        int lockMode = isDetailsFragment ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED : DrawerLayout.LOCK_MODE_UNLOCKED;
        binding.drawerLayout.setDrawerLockMode(lockMode);

        // Sincroniza el estado del DrawerToggle solo si es necesario
        if (toggle != null && !isDetailsFragment) {
            toggle.syncState();  // Sincronizamos el estado solo si no estamos en el Fragmento de detalles
        }
    }


    private void configureToggleMenu() {
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Método para controlar la navegación entre fragmentos
    private boolean onMenuItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.navigation_home)
            navController.navigate(R.id.blankFragment);
        else if (menuItem.getItemId() == R.id.navigation_profile)
            navController.navigate(R.id.charactersFragment);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d("Navigation", "Retrocediendo hacia: " + navController.getCurrentDestination().getLabel());
        // Mostrar el Toast cuando se presiona la flecha de retroceso
        Toast.makeText(this, "Retrocediendo...", Toast.LENGTH_SHORT).show();

        // Verifica si el NavController está disponible y navega hacia atrás
        if (navController != null && navController.navigateUp()) {
            return true;  // Si la navegación hacia atrás fue exitosa, devuelve true
        }

        return super.onSupportNavigateUp();  // Si no se puede navegar hacia atrás, se llama al super
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú que hemos definido
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_about) {
            showAppInfoDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void showAppInfoDialog() {
        // Crear el AlertDialog
        new AlertDialog.Builder(this)
                .setTitle(R.string.about_title)
                .setMessage(R.string.about_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Acción cuando se presiona el botón "OK"
                        dialog.dismiss();  // Cierra el diálogo
                    }
                })
                .setCancelable(false)  // No se puede cancelar tocando fuera del diálogo
                .show();
    }

}