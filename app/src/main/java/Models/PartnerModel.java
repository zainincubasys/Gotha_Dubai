package Models;

import org.json.JSONArray;
import org.json.JSONObject;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 28/10/2016.
 */

public class PartnerModel {

    private int id;
    private String name;
    private String pageUrl;
    private String banner;

    public PartnerModel() {
    }

    public PartnerModel(int pId, String pName, String pPageUrl, String pBanner){
        id = pId;
        name = pName;
        pageUrl = pPageUrl;
        banner = pBanner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void parseJSON(JSONObject json){
        try {
            id = json.getInt(CommonVaraibles.CONSTANT_PARAM_ID);
            name = json.getString(CommonVaraibles.CONSTANT_PARAM_NAME);
            if(json.getString(CommonVaraibles.CONSTANT_PARAM_URL).equals("null")) {
                pageUrl = "";
            }else{
                pageUrl = json.getString(CommonVaraibles.CONSTANT_PARAM_URL);
            }
            banner = json.getString(CommonVaraibles.CONSTANT_PARAM_BANNER);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
