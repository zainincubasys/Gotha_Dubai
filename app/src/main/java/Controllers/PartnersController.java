package Controllers;

import com.vimeo.networking.model.Video;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import Models.AlbumModel;
import Models.PartnerModel;
import Models.VideosModel;



public class PartnersController {

    public List<PartnerModel> partnersList;

    private static PartnersController _instance;

    public static PartnersController Instance() {
        if(_instance == null){
            _instance = new PartnersController();
        }
        return _instance;
    }

    public PartnersController() {
        this.partnersList = new ArrayList<PartnerModel>();
    }

    public void parsePartners(JSONArray json){
        try{
            partnersList.clear();
            for (int i=0; i<json.length(); i++) {
                PartnerModel model = new PartnerModel();
                model.parseJSON(json.getJSONObject(i));
                partnersList.add(model);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
