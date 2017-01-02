package Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CommonUtils.CommonVaraibles;
import CommonUtils.SharedFunctions;

/**
 * Created by Xain on 23/09/2016.
 */
public class AlbumModel {
    private int id;
    private String name;
    private String date;
    private List<PictureModel> pictureModelList;

    public AlbumModel (){
        name = "";
        date = "";
        pictureModelList = new ArrayList<PictureModel>();
    }

    public AlbumModel (String pName, String pDate, List<PictureModel> pList){
        name = pName;
        date = pDate;
        pictureModelList = pList;
    }

    public String getDate() {
        String dateTime = "";
        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date _date = null;
            _date = format.parse(date);
            Format dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            dateTime = dateFormatter.format(_date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<PictureModel> getPictureModelList() {
        return pictureModelList;
    }

    public void setPictureModelList(List<PictureModel> pictureModelList) {
        this.pictureModelList = pictureModelList;
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


    public void parseJSON(JSONObject json){
        try {
            id = json.getInt(CommonVaraibles.CONSTANT_PARAM_ID);
            name = json.getString(CommonVaraibles.CONSTANT_PARAM_NAME);
            date = json.getString(CommonVaraibles.CONSTANT_PARAM_CREATION);
            JSONArray jArray = json.getJSONArray(CommonVaraibles.CONSTANT_PARAM_PICTURES);
            for (int i = 0; i<jArray.length(); i++ ){
                PictureModel pModel = new PictureModel();
                pModel.parseJSON(jArray.getJSONObject(i));
                pictureModelList.add(pModel);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dummyFilled(int i){
        name = "Title " + i;
        date = "";
        for(int j =0; j<30; j++){
            if(j%3==0) {
//                pictureModelList.add(new PictureModel("Picture" + j, "http://www.qqxxzx.com/images/images/images-7.jpg"));
                pictureModelList.add(new PictureModel("Picture" + j, "https://upload.wikimedia.org/wikipedia/en/9/9f/Toronto_roadrunners_200x200.png"));
            }else if(j%3==1) {
//                pictureModelList.add(new PictureModel("Picture" + j, "http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg"));
                pictureModelList.add(new PictureModel("Picture" + j, "http://piq.codeus.net/static/media/userpics/piq_66223.png"));
            }else if(j%3==2) {
//                pictureModelList.add(new PictureModel("Picture" + j, "http://www.qqxxzx.com/images/jennifer-lawrence/jennifer-lawrence-24.jpg"));
                pictureModelList.add(new PictureModel("Picture" + j, "http://www.danmorris.com/wp-content/uploads/2010/06/Skype-application-e1309419614540-200x200.jpgz"));
            }

        }
    }
}
