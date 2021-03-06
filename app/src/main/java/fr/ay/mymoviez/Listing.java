package fr.ay.mymoviez;

import android.content.Intent;
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
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import fr.ay.mymoviez.ui.main.SectionsPagerAdapter;

public class Listing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int num = 0;

       String url = " https://api.themoviedb.org/3/movie/550?api_key=e4a9d54204f8ee1d8121e867e9a8a5a5";

      //  String url = "https://api.themoviedb.org/3/movie/" + num +
        //        "550?api_key=e4a9d54204f8ee1d8121e867e9a8a5a5";


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



        //fill listview with api


       //for(num = 541; num < 10; num++ ) {

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    TextView titleView = findViewById(R.id.titleview);
                    TextView synView = findViewById(R.id.synview);
                    TextView dateView = findViewById(R.id.dateview);
                    try {
                        titleView.setText("Title : " + response.getString("title"));
                        synView.setText("Synopsis : " + response.getString("overview"));
                        dateView.setText(response.getString("release_date"));
                        /*
                        String newTitle = response.getString("title");
                        String newDate = response.getString("release_date");
                        String newSyn = response.getString("overview");*/

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


        //list of infos
        List<MovieInfo> movieInfoList = new ArrayList<>();
        movieInfoList.add(new MovieInfo("Best Movie World","C'est la fin du monde omg où sont les super zéros call 007", "29-02-2023"));
        movieInfoList.add(new MovieInfo("La La Land",   "City of stars... are your shining just for me ? ", "12-06-2018"));
        movieInfoList.add(new MovieInfo("Cyber James Bond","Literally James Bond...but with a PROJECT skin ! ", "00-007-2007"));
        movieInfoList.add(new MovieInfo("Moana","What can I say except your welcome ! ", "02-30-2018"));
        movieInfoList.add(new MovieInfo("Dracula","Need mooar blood AHAHAHAHAHHAHAHHAAHAHAHAHAAAHAHAHA", "10-30-1953"));
        movieInfoList.add(new MovieInfo("NeverendingTitlelikeveryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyylong","Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis Verylongsynopsis ", "10-30-195333"));
        movieInfoList.add(new MovieInfo("La La Land 2 ",   "Another day of sun is coming on La La Land", "12-06-2024"));

      //  movieInfoList.add(new MovieInfo(findViewById(R.id.titleview).toString(), findViewById(R.id.dateview).toString(), findViewById(R.id.synview).toString()));

        //get list view
       ListView movieListView = findViewById(R.id.movie_list_view);
       movieListView.setAdapter(new InfoAdapter(this,movieInfoList));



        //next activity custom moviepage

        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent otherActivity = new Intent(getApplicationContext(), MoviePage.class);
                startActivity(otherActivity);
                finish();
            }
        });

    }
}