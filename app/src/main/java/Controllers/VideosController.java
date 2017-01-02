package Controllers;

import com.vimeo.networking.model.Video;

import java.util.ArrayList;
import java.util.List;

import Models.AlbumModel;
import Models.VideosModel;

/**
 * Created by Xain on 20/10/2016.
 */

public class VideosController {

    public List<VideosModel>  videoList;

    private static VideosController _instance;

    public static VideosController Instance() {
        if(_instance == null){
            _instance = new VideosController();
        }
        return _instance;
    }

    public VideosController(){
        videoList = new ArrayList<VideosModel>();
    }

    public void addVideo(Video pVideo){
        videoList.add(new VideosModel(pVideo));
    }
}
