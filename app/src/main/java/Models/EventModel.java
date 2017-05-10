package Models;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 28/10/2016.
 */

public class EventModel {

    private int id;
    private String name;
    private String eventDate;
    private boolean isAvailable;
    private String banner;
    private String area_id;
    private String place_id;
    private String country_id;
    private String fb_event_url;
    private String partnerId;


    public EventModel() {
    }

    public EventModel(int id, String name, String eventDate, boolean isAvailable, String banner, String area_id, String place_id, String country_id, String fb_event_url) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.isAvailable = isAvailable;
        this.banner = banner;
        this.area_id = area_id;
        this.place_id = place_id;
        this.country_id = country_id;
        this.fb_event_url = fb_event_url;
    }

    public void parseJSON(JSONObject json){
        try {
            id = json.getInt(CommonVaraibles.CONSTANT_PARAM_ID);
            name = json.getString(CommonVaraibles.CONSTANT_PARAM_NAME);
            banner = json.getString(CommonVaraibles.CONSTANT_PARAM_BANNER);
            isAvailable = json.getBoolean(CommonVaraibles.CONSTANT_PARAM_AVAILABLE);
            eventDate = json.getString(CommonVaraibles.CONSTANT_PARAM_EVENT_DATE);
            area_id = json.getString(CommonVaraibles.CONSTANT_PARAM_GET_AREA_ID);
            place_id = json.getString(CommonVaraibles.CONSTANT_PARAM_GET_PLACE_ID);
            country_id = json.getString(CommonVaraibles.CONSTANT_PARAM_GET_COUNTRY_ID);
            fb_event_url = json.getString(CommonVaraibles.CONSTANT_PARAM_GET_FB_EVENT_URL);
            partnerId = json.getString(CommonVaraibles.CONSTANT_PARAM_GET_PARTNER_ID);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getFb_event_url() {
        return fb_event_url;
    }

    public void setFb_event_url(String fb_event_url) {
        this.fb_event_url = fb_event_url;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isAvailable() {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = null;
            date = format.parse(eventDate);

            if(!date.after(new Date())){
                isAvailable = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String formatedDateTime(){
        String dateTime = "";
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = null;
            date = format.parse(eventDate);
            Format dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            dateTime = dateFormatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateTime;
    }
}
