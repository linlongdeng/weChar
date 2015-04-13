package weChat.json.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
 
// Do not use fields to autodetect. use the public getter methods to autodetect properties
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.PUBLIC_ONLY)
public class AlbumsFilter {
 
    private String title;
    private DatasetFilter[] datasetFilter;
    public String total_pages;
 
    public String getTotal_pages() {
        return total_pages;
    }
 
    public String getTitle() {
        return title;
    }
 
    // this getter method is for the 'dataset' property
    @JsonProperty("dataset")
    public DatasetFilter[] getDatasetFilter() {
        return datasetFilter;
    }
}
