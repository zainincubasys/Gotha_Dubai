package Controllers;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import Models.AlbumModel;

/**
 * Created by Xain on 15/10/2016.
 */

public class AlbumsControllers {
    private static AlbumsControllers _instance;
    private List<AlbumModel> albumModelList;

    public static AlbumsControllers Instance() {
        if(_instance == null){
                _instance = new AlbumsControllers();
            }
        return _instance;

    }

    public AlbumsControllers(){
        albumModelList = new ArrayList<AlbumModel>();
    }

    public void parseAlbums(JSONArray json){
        try{
            albumModelList.clear();
            for (int i=0; i<json.length(); i++) {
                AlbumModel model = new AlbumModel();
                model.parseJSON(json.getJSONObject(i));
                if(model.getPictureModelList().size() > 0){
                    albumModelList.add(model);
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<AlbumModel> getAlbumModelList() {
        return albumModelList;
    }

    public void setAlbumModelList(List<AlbumModel> albumModelList) {
        this.albumModelList = albumModelList;
    }
}
