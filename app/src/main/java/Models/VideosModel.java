package Models;

import com.vimeo.networking.model.Video;

import CommonUtils.CommonVaraibles;
import Handlers.NetworkHandler;

/**
 * Created by Xain on 20/10/2016.
 */

public class VideosModel  {

    private Video video;
    private String videoPlayerUrl;
    private String videoAutoPlayUrl;
    private String name;
    private String configUrl;
    private String urlMP4;

    public VideosModel() {

    }

    public VideosModel(Video videoDetails) {
        this.video = videoDetails;
//        String[] splitString = video.embed.html.split("\"");
//        String url = "";
//        for(int i=0; i<splitString.length; i++){
//             if(splitString[i].contains("https://")){
//                 url = splitString[i];
//             }
//        }
//        this.videoPlayerUrl = url;
//        this.videoAutoPlayUrl = url + "&autoplay=1";
        this.name = video.name;
        this.configUrl = "https://player.vimeo.com" + video.uri.replace("videos", "video") + "/config" ;
        this.urlMP4 = "";
    }

    public String getUrlMP4() {
        return urlMP4;
    }

    public void setUrlMP4(String urlMP4) {
        this.urlMP4 = urlMP4;
    }

    public String getConfigUrl() {
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getVideoPlayerUrl() {
        return videoPlayerUrl;
    }

    public void setVideoPlayerUrl(String videoPlayerUrl) {
        this.videoPlayerUrl = videoPlayerUrl;
    }

    public String getVideoAutoPlayUrl() {
        return videoAutoPlayUrl;
    }

    public void setVideoAutoPlayUrl(String videoAutoPlayUrl) {
        this.videoAutoPlayUrl = videoAutoPlayUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
