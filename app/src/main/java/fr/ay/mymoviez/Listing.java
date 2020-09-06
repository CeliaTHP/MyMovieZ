package fr.ay.mymoviez;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.sax.TextElementListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import fr.ay.mymoviez.ui.main.MoviePage;
import fr.ay.mymoviez.ui.main.SectionsPagerAdapter;

public class Listing extends AppCompatActivity {


    int num = 550;

    String url = "https://api.themoviedb.org/3/movie/" + num +"?api_key=e4a9d54204f8ee1d8121e867e9a8a5a5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        //next activity


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //list of infos
        final List<MovieInfo>  movieInfoList = new ArrayList<>();
        movieInfoList.add(new MovieInfo("Best Movie World","C'est la fin du monde omg où sont les super zéros call 007", "29-02-2023"));
        movieInfoList.add(new MovieInfo("La La Land",   "City of stars... are your shining just for me ? ", "12-06-2018"));
        movieInfoList.add(new MovieInfo("Cyber James Bond","Literally James Bond...but with a PROJECT skin ! ", "00-07-2007"));
        movieInfoList.add(new MovieInfo("Moana","What can I say except your welcome ! ", "02-30-2018"));
        movieInfoList.add(new MovieInfo("Dracula","Need mooar blood AHAHAHAHAHHAHAHHAAHAHAHAHAAAHAHAHA", "10-30-1953"));
        movieInfoList.add(new MovieInfo("NeverendingTitlelikeveryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyylong","Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis ", "10-30-1953"));
        movieInfoList.add(new MovieInfo("La La Land 2 ",   "Another day of sun is coming on La La Land", "12-06-2024"));


        // REQUEST

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView titleTextView = findViewById(R.id.titleview);
                TextView synTextView = findViewById(R.id.synview);
                TextView dateTextView = findViewById(R.id.dateview);



                try {
                    movieInfoList.add(new MovieInfo(response.getString("title"),"blblblbl","1985"));
                  //*titleTextView.setText("Title : " + response.getString("title"));
                  //movieInfoList.add(new MovieInfo(titleTextView.getText().toString(),synTextView.getText().toString(), dateTextView.getText().toString()));



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);


        //update list view




        //get list view

        ListView movieListView = findViewById(R.id.movie_list_view);
        movieListView.setAdapter(new InfoAdapter(this,movieInfoList));

        //go to movie custom page

    }
}

