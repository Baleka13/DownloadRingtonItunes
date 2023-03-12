import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItunesMusic {


    void Playsong(String searchRequest) throws IOException {
        String urla = Buildurl(searchRequest);
        String page = downloadWebPage(urla);

        String name = NameArtist(page, "artistName");
        String trackname = NameArtist(page, "trackName");
        String previewUrl = NameArtist(page, "previewUrl");

        try (InputStream in = new URL(previewUrl).openStream()) {
            Files.copy(in, Paths.get(trackname + ".m4a"));
        }
        System.out.println(name);
        System.out.println("Download");

        if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
        {
            System.out.println("not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        File file= new File(trackname+".m4a");
            desktop.open(file);              //opens the specified file


}



private String NameArtist(String page,String tagname){
        int start=page.indexOf(tagname)+tagname.length()+3;
        int end=page.indexOf("\"",start);
        String sub=page.substring(start,end);
        return sub;
        }

private String Buildurl(String searchRequest){
        String term=searchRequest.replaceAll(" ","+");
        String itunesapi="https://itunes.apple.com/search?term=";
        String limitapi="&limit=5";
        String media="&media=music";
        StringBuilder builder=new StringBuilder();
        builder.append(itunesapi);
        builder.append(term);
        builder.append(limitapi);
        builder.append(media);
        return builder.toString();
        }

private static String downloadWebPage(String url)throws IOException{
        StringBuilder result=new StringBuilder();
        String line;

        URLConnection urlConnection=new URL(url).openConnection();


        try(InputStream is=urlConnection.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is))){

        while((line=br.readLine())!=null){
        result.append(line);
        }

        }

        return result.toString();

        }
        }

