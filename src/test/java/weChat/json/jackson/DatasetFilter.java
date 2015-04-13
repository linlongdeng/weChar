package weChat.json.jackson;

import java.util.HashMap;
import java.util.Map;
 
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 
// ignore the property with name 'tags'.
@JsonIgnoreProperties({ "tags" })
public class DatasetFilter {
    private String album_id;
    private String album_title;
    private Map<String , Object> otherProperties = new HashMap<String , Object>();
    private String album_comments;
 
    @JsonCreator
    public DatasetFilter(@JsonProperty("album_id") String album_id, @JsonProperty("album_title") String album_title) {
        this.album_id = album_id;
        this.album_title = album_title;
    }
 
    // ignore the property specified by this getter.
    @JsonIgnore
    public String getAlbum_comments() {
        return album_comments;
    }
 
    public String getAlbum_id() {
        return album_id;
    }
 
    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
 
    public String getAlbum_title() {
        return album_title;
    }
 
    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }
 
    public Object get(String name) {
        return otherProperties.get(name);
    }
 
    // this method is used to get all properties not specified earlier.
    @JsonAnyGetter
    public Map<String , Object> any() {
        return otherProperties;
    }
 
    @JsonAnySetter
    public void set(String name, Object value) {
        otherProperties.put(name, value);
    }
}