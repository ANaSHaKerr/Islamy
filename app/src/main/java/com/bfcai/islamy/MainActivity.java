package com.bfcai.islamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bfcai.islamy.Screens.Notes.NoteHomeActivity;
import com.bfcai.islamy.Screens.Stories.StoriesActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView textView;
    FirebaseAuth auth;
    CardView islamicStories,islamicNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu nv = navigationView.getMenu();
        MenuItem item = nv.findItem(R.id.darkMode);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            item.setTitle("الوضع النهاري");

        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            item.setTitle("الوضع الليلي");
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        islamicStories = findViewById(R.id.islamicStories);
        islamicNote = findViewById(R.id.islamicNote);

        auth = FirebaseAuth.getInstance();
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        if(auth.getCurrentUser() == null){
            auth.signInAnonymously();
        }
        islamicStories.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), StoriesActivity.class));
            finish();
        });
        islamicNote.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), NoteHomeActivity.class));
            finish();
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.aboutApp:
                showDialogMenu();
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "وَأَمَّا بِنِعْمَةِ رَبِّكَ فَحَدِّث , حمل الان تطبيق اسلامي و ستجد كل ما تحتاجه لاي مسلم  :  https://play.google.com/store/apps/details?id=com.bfcai.islamy ";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.nav_rate:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.bfcai.islamy")));
                break;

            case R.id.darkMode:
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPreferences.edit();
                    final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
                    if (isDarkModeOn) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                if (isDarkModeOn) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();


                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();

                }
                break;
            case R.id.translate:
                showchangelanguagedialog();
                break;


        }
        return true;
    }

    private void showDialogMenu() {
        Button btn;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
      //  dialog.setView(R.layout.dialog_layout).create().show();
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_layout,viewGroup,false);
        btn = view.findViewById(R.id.dialog_btn);
        dialog.setCancelable(false);
        dialog.setView(view);

       final AlertDialog alert = dialog.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
        btn.setOnClickListener(v-> alert.dismiss());
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("اغلاق التطبيق")
                    .setMessage("هل انت متاكد انك تريد اغلاق التطبيق ؟")
                    .setPositiveButton("نعم", (dialog, which) -> finish())
                    .setNegativeButton("لا", null)
                    .show();
        }
    }
    private void showchangelanguagedialog() {
        final String[] listitems={"Arabic","English"};
        final AlertDialog.Builder mbuilder=new AlertDialog.Builder((MainActivity.this));
        mbuilder.setTitle("Change Languages");
        mbuilder.setSingleChoiceItems(listitems, -1, (dialog, i) -> {
            if (i == 0) {
                setLocale("ar");
                recreate();

            } else {
                setLocale("en");
                recreate();
            }
            dialog.dismiss();
        });
        AlertDialog malertDialog=mbuilder.create();
        malertDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }

}