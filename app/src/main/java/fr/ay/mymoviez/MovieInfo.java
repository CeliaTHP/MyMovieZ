package fr.ay.mymoviez;

public class MovieInfo {
    //infos
    private String title;
    private String date;
    private String syn;

    //constructor
    public MovieInfo(String title, String syn, String date) {
        this.title = title;
        this.date = date;
        this.syn = syn;
    }

    //methods

    public String getTitle() {return  title; }
    public String getDate() {return date; }
    public String getSyn() {return syn; }
}

