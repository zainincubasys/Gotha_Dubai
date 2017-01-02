package Models;

import org.json.JSONObject;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 08/11/2016.
 */

public class NotificationModel {

    private String title;
    private String msg;
    private String dateCreated;

    public NotificationModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void parseJSON(JSONObject json){
        try {
            title = json.getString(CommonVaraibles.CONSTANT_PARAM_TITLE);
            msg = json.getString(CommonVaraibles.CONSTANT_PARAM_MESSAGE);
            dateCreated = json.getString(CommonVaraibles.CONSTANT_PARAM_CREATED_AT);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
