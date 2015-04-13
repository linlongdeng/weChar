package weChat.json.jackson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
 
import java.net.URLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class DataBindingFilter {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=2";
        URLConnection connection	 = new URL(url).openConnection();
        connection.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while((line = br.readLine()) != null){
        	sb.append(line);
        }
        System.out.println(sb.toString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        AlbumsFilter albums = mapper.readValue(sb.toString(), AlbumsFilter.class);
        System.out.println(albums.getTotal_pages());
        System.out.println(albums.getTitle());
        for (DatasetFilter dataset : albums.getDatasetFilter()) {
            System.out.println(dataset.getAlbum_comments());
            System.out.println(dataset.get("album_images"));
            System.out.println(dataset.get("tags"));
            System.out.println(dataset.get("album_listens"));
            break;
        }
    }
 
}