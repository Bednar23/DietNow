package com.bednarek.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MealsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ExpandableListView)findViewById(R.id.List_meals);
        initData();
        listAdapter = new MealsListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MealsActivity.this, AddMealActivity.class));

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Śniadanie");
        listDataHeader.add("Drugie śniadanie");
        listDataHeader.add("Obiad");
        listDataHeader.add("Kolacja");

        List<String> firstmeal = new ArrayList<>();
        firstmeal.add("chleb pszenny");
        firstmeal.add("jajko");
        firstmeal.add("pomidor");

        List<String> secondmeal = new ArrayList<>();
        secondmeal.add("jabłko");
        secondmeal.add("banan");

        List<String> thirdmeal = new ArrayList<>();
        thirdmeal.add("pierś z kurczaka");
        thirdmeal.add("ziemniaki");
        thirdmeal.add("sałata");

        List<String> fourthmeal = new ArrayList<>();
        fourthmeal.add("spaghetti carbonara");

        listHash.put(listDataHeader.get(0), firstmeal);
        listHash.put(listDataHeader.get(1), secondmeal);
        listHash.put(listDataHeader.get(2), thirdmeal);
        listHash.put(listDataHeader.get(3), fourthmeal);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_profile:
                Intent profileAct = new Intent(MealsActivity.this, ProfileActivity.class);
                startActivity(profileAct);
                break;
            case R.id.nav_meals:
                Intent mealsAct = new Intent(MealsActivity.this, MealsActivity.class);
                startActivity(mealsAct);
                break;
            case R.id.nav_recipes:
                Intent recipesAct = new Intent(MealsActivity.this, RecipesActivity.class);
                startActivity(recipesAct);
                break;
            case R.id.nav_products:
                Intent productsAct = new Intent(MealsActivity.this, ProductsActivity.class);
                startActivity(productsAct);
                break;
            case R.id.nav_shopping_list:
                Intent shopping_listAct = new Intent(MealsActivity.this, ShoppingListActivity.class);
                startActivity(shopping_listAct);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
