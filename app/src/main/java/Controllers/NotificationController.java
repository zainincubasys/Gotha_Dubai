package Controllers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import CommonUtils.CommonVaraibles;
import Models.EventModel;
import Models.NotificationModel;


public class NotificationController {

    public List<NotificationModel> notifList;
    public int totalPages;
    public int currentPage;

    private static NotificationController _instance;

    public static NotificationController Instance() {
        if(_instance == null){
            _instance = new NotificationController();
        }
        return _instance;
    }

    public NotificationController() {
        this.notifList = new ArrayList<NotificationModel>();
    }

    public void parseNotifications(JSONObject json){
        try{
            notifList.clear();
            totalPages = json.getInt(CommonVaraibles.CONSTANT_PARAM_TOTAL_PAGE);
            currentPage = json.getInt(CommonVaraibles.CONSTANT_PARAM_CURRENT_PAGE);
            JSONArray jArray = json.getJSONArray(CommonVaraibles.CONSTANT_PARAM_NOTIFICATIONS);
            for (int i=0; i<jArray.length(); i++) {
                NotificationModel model = new NotificationModel();
                model.parseJSON(jArray.getJSONObject(i));
                notifList.add(model);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
