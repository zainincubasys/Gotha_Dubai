package CommonUtils;

import android.graphics.Typeface;

/**
 * Created by Xain on 29/09/2016.
 */
public class CommonVaraibles {

    public static boolean isPhotoOpened = false;

    public static final String NOTIFICATION_REGISTER_URL = "subscribe";
    public static final String BASE_URL = "http://appadmingothadubai.gothaclubdubai.com/gotha_api/api/";   //PRODUCTION SERVER  http://35.164.153.48
//    public static final String BASE_URL = "http://35.161.151.140/gotha_test/api/";   // DEV SERVER

    public static final String TABLE_BOOKING_BASE_URL = "http://www.staging-reserveout-api.elasticbeanstalk.com/";
    public static final String METHOD_GALLERY = "galleries";
    public static final String METHOD_PARTNERS = "partners";
    public static final String METHOD_EVENTS = "events";
    public static final String METHOD_NOTIFICATIONS = "notifications";
    public static final String METHOD_RESERVATION = "partner/v1/reservation/reserve?";

    public static final int COMMON_TAG_GALLERY = 1;
    public static final int COMMON_TAG_VIDEO_CONFIG = 2;
    public static final int COMMON_TAG_PARTNER = 3;
    public static final int COMMON_TAG_EVENTS = 4;
    public static final int COMMON_TAG_NOTIFICATIONS = 5;
    public static final int COMMON_TAG_RESERVATION = 6;

    public static final String CONSTANT_PARAM_ID = "id";
    public static final String CONSTANT_PARAM_NAME = "name";
    public static final String CONSTANT_PARAM_CREATION = "creation";
    public static final String CONSTANT_PARAM_PICTURES = "pictures";
    public static final String CONSTANT_PARAM_IMAGE_THUMB_URL =  "image_thumb";
    public static final String CONSTANT_PARAM_IMAGE_URL = "image";
    public static final String CONSTANT_PARAM_STATUS = "status";
    public static final String CONSTANT_PARAM_GALLERIES = "galleries";
    public static final String CONSTANT_PARAM_PARTNERS = "partners";
    public static final String CONSTANT_PARAM_EVENTS = "events";
    public static final String CONSTANT_PARAM_SUCCESS = "success";
    public static final String CONSTANT_PARAM_REQUEST = "request";
    public static final String CONSTANT_PARAM_FILES = "files";
    public static final String CONSTANT_PARAM_HLS = "hls";
    public static final String CONSTANT_PARAM_URL = "url";
    public static final String CONSTANT_PARAM_BANNER = "banner";
    public static final String CONSTANT_PARAM_EVENT_DATE = "event_date";
    public static final String CONSTANT_PARAM_AVAILABLE = "available";
    public static final String CONSTANT_PARAM_DEVICE_ID = "device_id";
    public static final String CONSTANT_PARAM_DEVICE_TYPE = "device_type";
    public static final String CONSTANT_PARAM_TITLE = "title";
    public static final String CONSTANT_PARAM_MESSAGE = "message";
    public static final String CONSTANT_PARAM_NOTIFICATIONS = "notifications";
    public static final String CONSTANT_PARAM_CREATED_AT = "created_at";
    public static final String CONSTANT_PARAM_CURRENT_PAGE = "currentPage";
    public static final String CONSTANT_PARAM_TOTAL_PAGE = "totalPages";
    public static final String CONSTANT_PARAM_RESULT = "result";

    public static final String CONSTANT_PARAM_GET_AREA_ID = "area_id";
    public static final String CONSTANT_PARAM_GET_PLACE_ID = "place_id";
    public static final String CONSTANT_PARAM_GET_COUNTRY_ID = "country_id";
    public static final String CONSTANT_PARAM_GET_FB_EVENT_URL = "fb_event_url";
    public static final String CONSTANT_PARAM_GET_PARTNER_ID = "partner_id";

    public static final String CONSTANT_PARAM_PLACE_ID = "placeId=";
    public static final String CONSTANT_PARAM_COVERS = "&covers=";
    public static final String CONSTANT_PARAM_DATE = "&date=";
    public static final String CONSTANT_PARAM_TIME = "&time=";
    public static final String CONSTANT_PARAM_COMMENT = "&comment=";
    public static final String CONSTANT_PARAM_PHONE = "&phone=";
    public static final String CONSTANT_PARAM_COUNTRY_ID = "&countryId=";
    public static final String CONSTANT_PARAM_EMAIL = "&email=";
    public static final String CONSTANT_PARAM_PARTNER_ID = "&partnerId=";
    public static final String CONSTANT_PARAM_AREA_ID = "&areaId=";
    public static final String CONSTANT_PARAM_BOOKING_NAME = "&name=";
    public static final String CONSTANT_PARAM_HOURS = "&hours=";
    public static final String CONSTANT_PARAM_NUMBER_OF_PEOPLE = "&numberOfPeople=";

    public static final String CONSTANT_PARAM_INTENT_CURRENT_BOOKING_EVENT = "currentBookingEvent";

    public static final String CONSTANT_STR_FIREBASE_REGISTERED = "registered";
    public static final String CONSTANT_STR_FIREBASE_APP = "YOUR FIREBASE URL";
    public static final String CONSTANT_STR_SHARED_PREF = "notificationapp";
    public static final String CONSTANT_STR_UNIQUE_ID = "uniqueid";

    public static final String CONSTANT_STR_EVENT_SHARING_TEXT = "Hey! Check this out. Gotha Club Dubai is hosting \"";
    public static final String CONSTANT_STR_EVENT_SHARING_TEXT_ON = "\" on ";
    public static final String CONSTANT_STR_EVENT_SHARING_TEXT_END = ". Check out details of the event at ";


    public static final long DELAY_IN_MS = 500;
    public static final long DELAY_IN_MS_ONE = 100;

    public enum API_TYPE{
        POST, GET, PUT
    }
}
