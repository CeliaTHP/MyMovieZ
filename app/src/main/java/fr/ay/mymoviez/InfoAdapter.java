package fr.ay.mymoviez;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class InfoAdapter extends BaseAdapter {

    private Context context;
    private List<MovieInfo> movieInfoList;
    private LayoutInflater inflater;

    //constructor
    public InfoAdapter(Context context, List<MovieInfo> movieInfoList)
    {
        this.context = context;
        this.movieInfoList = movieInfoList;
        this.inflater =LayoutInflater.from(context);
    }
    //methods
    @Override
    public int getCount() {
        return movieInfoList.size();
    }

    @Override
    public MovieInfo getItem(int pos) {
        return movieInfoList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_info, null);

        //get attributes
        MovieInfo currentInfo = getItem(i);
        String movieTitle = currentInfo.getTitle();
        String movieDate = currentInfo.getDate();
        String movieSyn = currentInfo.getSyn();

        //get view and update title
        TextView movieNameView = view.findViewById(R.id.title);
        movieNameView.setText(movieTitle);

        //get view and update date
        TextView movieDateView = view.findViewById(R.id.date);
        movieDateView.setText(movieDate);

        //get view and update synopsis
        TextView movieSynView = view.findViewById(R.id.syn);
        movieSynView.setText(movieSyn);





        return view;
    }
}
