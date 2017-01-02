package Models;

import org.json.JSONObject;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 23/09/2016.
 */
public class PictureModel {

    private int id;
    private String name;
    private String url;
    private String thumbUrl;

    public PictureModel(){
        name = "";
        url = "";
    }

    public PictureModel(String pName, String pUrl){
        name = pName;
        url = pUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void parseJSON(JSONObject json){
        try{
            id = json.getInt(CommonVaraibles.CONSTANT_PARAM_ID);
            thumbUrl = json.getString(CommonVaraibles.CONSTANT_PARAM_IMAGE_THUMB_URL);
            url = json.getString(CommonVaraibles.CONSTANT_PARAM_IMAGE_URL);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
