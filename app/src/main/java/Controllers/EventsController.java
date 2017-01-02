package Controllers;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import Models.EventModel;
import Models.PartnerModel;

/**
 * Created by Xain on 28/10/2016.
 */

public class EventsController {

    public List<EventModel> eventsList;

    private static EventsController _instance;

    public static EventsController Instance() {
        if(_instance == null){
            _instance = new EventsController();
        }
        return _instance;
    }

    public EventsController() {
        this.eventsList = new ArrayList<EventModel>();
    }

    public void parseEvents(JSONArray json){
        try{
            eventsList.clear();
            for (int i=0; i<json.length(); i++) {
                EventModel model = new EventModel();
                model.parseJSON(json.getJSONObject(i));
                eventsList.add(model);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
