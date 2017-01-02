package com.vimeo.stag.generated;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.model.Category;
import com.vimeo.networking.model.CategoryList;
import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.ChannelList;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.CommentList;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.Email;
import com.vimeo.networking.model.Embed;
import com.vimeo.networking.model.FeedItem;
import com.vimeo.networking.model.FeedList;
import com.vimeo.networking.model.Group;
import com.vimeo.networking.model.Interaction;
import com.vimeo.networking.model.InteractionCollection;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.Paging;
import com.vimeo.networking.model.Picture;
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.PinCodeInfo;
import com.vimeo.networking.model.Preferences;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.Quota;
import com.vimeo.networking.model.Recommendation;
import com.vimeo.networking.model.Space;
import com.vimeo.networking.model.StatsCollection;
import com.vimeo.networking.model.Tag;
import com.vimeo.networking.model.UploadQuota;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.UserBadge;
import com.vimeo.networking.model.UserList;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoFile;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.VideosPreference;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.VodItem;
import com.vimeo.networking.model.VodList;
import com.vimeo.networking.model.Website;
import com.vimeo.networking.model.playback.Drm;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.PlayProgress;
import com.vimeo.networking.model.playback.VideoLog;
import com.vimeo.networking.model.search.FacetOption;
import com.vimeo.networking.model.search.SearchFacet;
import com.vimeo.networking.model.search.SearchFacetCollection;
import com.vimeo.networking.model.search.SearchResponse;
import com.vimeo.networking.model.search.SearchResult;
import java.io.IOException;
import java.lang.Class;
import java.lang.Override;
import java.util.ArrayList;

public final class Stag {
  static <T> void writeToAdapter(Gson gson, Class<T> clazz, JsonWriter out, T value) throws IOException {
    gson.getAdapter(clazz).write(out, value);
  }

  static <T> T readFromAdapter(Gson gson, Class<T> clazz, JsonReader in) throws IOException {
    return gson.getAdapter(clazz).read(in);
  }

  static <T> void writeListToAdapter(Gson gson, Class<T> clazz, JsonWriter out, ArrayList<T> list) throws IOException {
    com.google.gson.TypeAdapter<T> typeAdapter = gson.getAdapter(clazz);

    for (T object : list) {
    	typeAdapter.write(out, object);
    }
  }

  static <T> ArrayList<T> readListFromAdapter(Gson gson, Class<T> clazz, JsonReader in) throws IOException {
    ArrayList<T> list = new java.util.ArrayList<>();
    com.google.gson.TypeAdapter<T> typeAdapter = gson.getAdapter(clazz);

    while(in.hasNext()){
    	list.add(typeAdapter.read(in));
    }

    return list;
  }

  static class PictureResourceAdapter extends TypeAdapter<PictureResource> {
    private final Gson mGson;

    public PictureResourceAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, PictureResource value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public PictureResource read(JsonReader in) throws IOException {
      return ParseUtils.parsePictureResource(mGson, in);
    }
  }

  static class MetadataAdapter extends TypeAdapter<Metadata> {
    private final Gson mGson;

    public MetadataAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Metadata value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Metadata read(JsonReader in) throws IOException {
      return ParseUtils.parseMetadata(mGson, in);
    }
  }

  static class SearchResultAdapter extends TypeAdapter<SearchResult> {
    private final Gson mGson;

    public SearchResultAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, SearchResult value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public SearchResult read(JsonReader in) throws IOException {
      return ParseUtils.parseSearchResult(mGson, in);
    }
  }

  static class UserAdapter extends TypeAdapter<User> {
    private final Gson mGson;

    public UserAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, User value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public User read(JsonReader in) throws IOException {
      return ParseUtils.parseUser(mGson, in);
    }
  }

  static class SearchResponseAdapter extends TypeAdapter<SearchResponse> {
    private final Gson mGson;

    public SearchResponseAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, SearchResponse value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public SearchResponse read(JsonReader in) throws IOException {
      return ParseUtils.parseSearchResponse(mGson, in);
    }
  }

  static class DrmAdapter extends TypeAdapter<Drm> {
    private final Gson mGson;

    public DrmAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Drm value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Drm read(JsonReader in) throws IOException {
      return ParseUtils.parseDrm(mGson, in);
    }
  }

  static class PinCodeInfoAdapter extends TypeAdapter<PinCodeInfo> {
    private final Gson mGson;

    public PinCodeInfoAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, PinCodeInfo value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public PinCodeInfo read(JsonReader in) throws IOException {
      return ParseUtils.parsePinCodeInfo(mGson, in);
    }
  }

  static class InteractionCollectionAdapter extends TypeAdapter<InteractionCollection> {
    private final Gson mGson;

    public InteractionCollectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, InteractionCollection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public InteractionCollection read(JsonReader in) throws IOException {
      return ParseUtils.parseInteractionCollection(mGson, in);
    }
  }

  static class SearchFacetAdapter extends TypeAdapter<SearchFacet> {
    private final Gson mGson;

    public SearchFacetAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, SearchFacet value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public SearchFacet read(JsonReader in) throws IOException {
      return ParseUtils.parseSearchFacet(mGson, in);
    }
  }

  static class EmailAdapter extends TypeAdapter<Email> {
    private final Gson mGson;

    public EmailAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Email value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Email read(JsonReader in) throws IOException {
      return ParseUtils.parseEmail(mGson, in);
    }
  }

  static class PlayProgressAdapter extends TypeAdapter<PlayProgress> {
    private final Gson mGson;

    public PlayProgressAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, PlayProgress value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public PlayProgress read(JsonReader in) throws IOException {
      return ParseUtils.parsePlayProgress(mGson, in);
    }
  }

  static class CommentAdapter extends TypeAdapter<Comment> {
    private final Gson mGson;

    public CommentAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Comment value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Comment read(JsonReader in) throws IOException {
      return ParseUtils.parseComment(mGson, in);
    }
  }

  static class VideoLogAdapter extends TypeAdapter<VideoLog> {
    private final Gson mGson;

    public VideoLogAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VideoLog value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VideoLog read(JsonReader in) throws IOException {
      return ParseUtils.parseVideoLog(mGson, in);
    }
  }

  static class VideoListAdapter extends TypeAdapter<VideoList> {
    private final Gson mGson;

    public VideoListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VideoList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VideoList read(JsonReader in) throws IOException {
      return ParseUtils.parseVideoList(mGson, in);
    }
  }

  static class WebsiteAdapter extends TypeAdapter<Website> {
    private final Gson mGson;

    public WebsiteAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Website value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Website read(JsonReader in) throws IOException {
      return ParseUtils.parseWebsite(mGson, in);
    }
  }

  static class UserBadgeAdapter extends TypeAdapter<UserBadge> {
    private final Gson mGson;

    public UserBadgeAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, UserBadge value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public UserBadge read(JsonReader in) throws IOException {
      return ParseUtils.parseUserBadge(mGson, in);
    }
  }

  static class CategoryAdapter extends TypeAdapter<Category> {
    private final Gson mGson;

    public CategoryAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Category value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Category read(JsonReader in) throws IOException {
      return ParseUtils.parseCategory(mGson, in);
    }
  }

  static class PictureCollectionAdapter extends TypeAdapter<PictureCollection> {
    private final Gson mGson;

    public PictureCollectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, PictureCollection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public PictureCollection read(JsonReader in) throws IOException {
      return ParseUtils.parsePictureCollection(mGson, in);
    }
  }

  static class FeedListAdapter extends TypeAdapter<FeedList> {
    private final Gson mGson;

    public FeedListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, FeedList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public FeedList read(JsonReader in) throws IOException {
      return ParseUtils.parseFeedList(mGson, in);
    }
  }

  static class QuotaAdapter extends TypeAdapter<Quota> {
    private final Gson mGson;

    public QuotaAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Quota value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Quota read(JsonReader in) throws IOException {
      return ParseUtils.parseQuota(mGson, in);
    }
  }

  static class SpaceAdapter extends TypeAdapter<Space> {
    private final Gson mGson;

    public SpaceAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Space value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Space read(JsonReader in) throws IOException {
      return ParseUtils.parseSpace(mGson, in);
    }
  }

  static class ChannelListAdapter extends TypeAdapter<ChannelList> {
    private final Gson mGson;

    public ChannelListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, ChannelList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public ChannelList read(JsonReader in) throws IOException {
      return ParseUtils.parseChannelList(mGson, in);
    }
  }

  static class InteractionAdapter extends TypeAdapter<Interaction> {
    private final Gson mGson;

    public InteractionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Interaction value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Interaction read(JsonReader in) throws IOException {
      return ParseUtils.parseInteraction(mGson, in);
    }
  }

  static class TagAdapter extends TypeAdapter<Tag> {
    private final Gson mGson;

    public TagAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Tag value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Tag read(JsonReader in) throws IOException {
      return ParseUtils.parseTag(mGson, in);
    }
  }

  static class FeedItemAdapter extends TypeAdapter<FeedItem> {
    private final Gson mGson;

    public FeedItemAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, FeedItem value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public FeedItem read(JsonReader in) throws IOException {
      return ParseUtils.parseFeedItem(mGson, in);
    }
  }

  static class PagingAdapter extends TypeAdapter<Paging> {
    private final Gson mGson;

    public PagingAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Paging value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Paging read(JsonReader in) throws IOException {
      return ParseUtils.parsePaging(mGson, in);
    }
  }

  static class SearchFacetCollectionAdapter extends TypeAdapter<SearchFacetCollection> {
    private final Gson mGson;

    public SearchFacetCollectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, SearchFacetCollection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public SearchFacetCollection read(JsonReader in) throws IOException {
      return ParseUtils.parseSearchFacetCollection(mGson, in);
    }
  }

  static class RecommendationAdapter extends TypeAdapter<Recommendation> {
    private final Gson mGson;

    public RecommendationAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Recommendation value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Recommendation read(JsonReader in) throws IOException {
      return ParseUtils.parseRecommendation(mGson, in);
    }
  }

  static class GroupAdapter extends TypeAdapter<Group> {
    private final Gson mGson;

    public GroupAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Group value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Group read(JsonReader in) throws IOException {
      return ParseUtils.parseGroup(mGson, in);
    }
  }

  static class StatsCollectionAdapter extends TypeAdapter<StatsCollection> {
    private final Gson mGson;

    public StatsCollectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, StatsCollection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public StatsCollection read(JsonReader in) throws IOException {
      return ParseUtils.parseStatsCollection(mGson, in);
    }
  }

  static class PlayAdapter extends TypeAdapter<Play> {
    private final Gson mGson;

    public PlayAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Play value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Play read(JsonReader in) throws IOException {
      return ParseUtils.parsePlay(mGson, in);
    }
  }

  static class EmbedAdapter extends TypeAdapter<Embed> {
    private final Gson mGson;

    public EmbedAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Embed value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Embed read(JsonReader in) throws IOException {
      return ParseUtils.parseEmbed(mGson, in);
    }
  }

  static class CategoryListAdapter extends TypeAdapter<CategoryList> {
    private final Gson mGson;

    public CategoryListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, CategoryList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public CategoryList read(JsonReader in) throws IOException {
      return ParseUtils.parseCategoryList(mGson, in);
    }
  }

  static class PreferencesAdapter extends TypeAdapter<Preferences> {
    private final Gson mGson;

    public PreferencesAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Preferences value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Preferences read(JsonReader in) throws IOException {
      return ParseUtils.parsePreferences(mGson, in);
    }
  }

  static class FacetOptionAdapter extends TypeAdapter<FacetOption> {
    private final Gson mGson;

    public FacetOptionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, FacetOption value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public FacetOption read(JsonReader in) throws IOException {
      return ParseUtils.parseFacetOption(mGson, in);
    }
  }

  static class CommentListAdapter extends TypeAdapter<CommentList> {
    private final Gson mGson;

    public CommentListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, CommentList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public CommentList read(JsonReader in) throws IOException {
      return ParseUtils.parseCommentList(mGson, in);
    }
  }

  static class UserListAdapter extends TypeAdapter<UserList> {
    private final Gson mGson;

    public UserListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, UserList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public UserList read(JsonReader in) throws IOException {
      return ParseUtils.parseUserList(mGson, in);
    }
  }

  static class VodItemAdapter extends TypeAdapter<VodItem> {
    private final Gson mGson;

    public VodItemAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VodItem value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VodItem read(JsonReader in) throws IOException {
      return ParseUtils.parseVodItem(mGson, in);
    }
  }

  static class UploadQuotaAdapter extends TypeAdapter<UploadQuota> {
    private final Gson mGson;

    public UploadQuotaAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, UploadQuota value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public UploadQuota read(JsonReader in) throws IOException {
      return ParseUtils.parseUploadQuota(mGson, in);
    }
  }

  static class VideosPreferenceAdapter extends TypeAdapter<VideosPreference> {
    private final Gson mGson;

    public VideosPreferenceAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VideosPreference value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VideosPreference read(JsonReader in) throws IOException {
      return ParseUtils.parseVideosPreference(mGson, in);
    }
  }

  static class ChannelAdapter extends TypeAdapter<Channel> {
    private final Gson mGson;

    public ChannelAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Channel value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Channel read(JsonReader in) throws IOException {
      return ParseUtils.parseChannel(mGson, in);
    }
  }

  static class PrivacyAdapter extends TypeAdapter<Privacy> {
    private final Gson mGson;

    public PrivacyAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Privacy value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Privacy read(JsonReader in) throws IOException {
      return ParseUtils.parsePrivacy(mGson, in);
    }
  }

  static class VodListAdapter extends TypeAdapter<VodList> {
    private final Gson mGson;

    public VodListAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VodList value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VodList read(JsonReader in) throws IOException {
      return ParseUtils.parseVodList(mGson, in);
    }
  }

  static class ConnectionAdapter extends TypeAdapter<Connection> {
    private final Gson mGson;

    public ConnectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Connection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Connection read(JsonReader in) throws IOException {
      return ParseUtils.parseConnection(mGson, in);
    }
  }

  static class PublishAdapter extends TypeAdapter<VodItem.Publish> {
    private final Gson mGson;

    public PublishAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VodItem.Publish value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VodItem.Publish read(JsonReader in) throws IOException {
      return ParseUtils.parsePublish(mGson, in);
    }
  }

  static class VimeoAccountAdapter extends TypeAdapter<VimeoAccount> {
    private final Gson mGson;

    public VimeoAccountAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VimeoAccount value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VimeoAccount read(JsonReader in) throws IOException {
      return ParseUtils.parseVimeoAccount(mGson, in);
    }
  }

  static class VideoAdapter extends TypeAdapter<Video> {
    private final Gson mGson;

    public VideoAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Video value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Video read(JsonReader in) throws IOException {
      return ParseUtils.parseVideo(mGson, in);
    }
  }

  static class PictureAdapter extends TypeAdapter<Picture> {
    private final Gson mGson;

    public PictureAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, Picture value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public Picture read(JsonReader in) throws IOException {
      return ParseUtils.parsePicture(mGson, in);
    }
  }

  static class ConnectionCollectionAdapter extends TypeAdapter<ConnectionCollection> {
    private final Gson mGson;

    public ConnectionCollectionAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, ConnectionCollection value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public ConnectionCollection read(JsonReader in) throws IOException {
      return ParseUtils.parseConnectionCollection(mGson, in);
    }
  }

  static class VideoFileAdapter extends TypeAdapter<VideoFile> {
    private final Gson mGson;

    public VideoFileAdapter(Gson gson) {
      mGson = gson;
    }

    @Override
    public void write(JsonWriter out, VideoFile value) throws IOException {
      ParseUtils.write(mGson, out, value);
    }

    @Override
    public VideoFile read(JsonReader in) throws IOException {
      return ParseUtils.parseVideoFile(mGson, in);
    }
  }

  public static class Factory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      Class<? super T> clazz = type.getRawType();

      if (clazz == com.vimeo.networking.model.PictureResource.class) {
      	return (TypeAdapter<T>) new PictureResourceAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Metadata.class) {
      	return (TypeAdapter<T>) new MetadataAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.search.SearchResult.class) {
      	return (TypeAdapter<T>) new SearchResultAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.User.class) {
      	return (TypeAdapter<T>) new UserAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.search.SearchResponse.class) {
      	return (TypeAdapter<T>) new SearchResponseAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.playback.Drm.class) {
      	return (TypeAdapter<T>) new DrmAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.PinCodeInfo.class) {
      	return (TypeAdapter<T>) new PinCodeInfoAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.InteractionCollection.class) {
      	return (TypeAdapter<T>) new InteractionCollectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.search.SearchFacet.class) {
      	return (TypeAdapter<T>) new SearchFacetAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Email.class) {
      	return (TypeAdapter<T>) new EmailAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.playback.PlayProgress.class) {
      	return (TypeAdapter<T>) new PlayProgressAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Comment.class) {
      	return (TypeAdapter<T>) new CommentAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.playback.VideoLog.class) {
      	return (TypeAdapter<T>) new VideoLogAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VideoList.class) {
      	return (TypeAdapter<T>) new VideoListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Website.class) {
      	return (TypeAdapter<T>) new WebsiteAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.UserBadge.class) {
      	return (TypeAdapter<T>) new UserBadgeAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Category.class) {
      	return (TypeAdapter<T>) new CategoryAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.PictureCollection.class) {
      	return (TypeAdapter<T>) new PictureCollectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.FeedList.class) {
      	return (TypeAdapter<T>) new FeedListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Quota.class) {
      	return (TypeAdapter<T>) new QuotaAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Space.class) {
      	return (TypeAdapter<T>) new SpaceAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.ChannelList.class) {
      	return (TypeAdapter<T>) new ChannelListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Interaction.class) {
      	return (TypeAdapter<T>) new InteractionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Tag.class) {
      	return (TypeAdapter<T>) new TagAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.FeedItem.class) {
      	return (TypeAdapter<T>) new FeedItemAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Paging.class) {
      	return (TypeAdapter<T>) new PagingAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.search.SearchFacetCollection.class) {
      	return (TypeAdapter<T>) new SearchFacetCollectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Recommendation.class) {
      	return (TypeAdapter<T>) new RecommendationAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Group.class) {
      	return (TypeAdapter<T>) new GroupAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.StatsCollection.class) {
      	return (TypeAdapter<T>) new StatsCollectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.playback.Play.class) {
      	return (TypeAdapter<T>) new PlayAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Embed.class) {
      	return (TypeAdapter<T>) new EmbedAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.CategoryList.class) {
      	return (TypeAdapter<T>) new CategoryListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Preferences.class) {
      	return (TypeAdapter<T>) new PreferencesAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.search.FacetOption.class) {
      	return (TypeAdapter<T>) new FacetOptionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.CommentList.class) {
      	return (TypeAdapter<T>) new CommentListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.UserList.class) {
      	return (TypeAdapter<T>) new UserListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VodItem.class) {
      	return (TypeAdapter<T>) new VodItemAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.UploadQuota.class) {
      	return (TypeAdapter<T>) new UploadQuotaAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VideosPreference.class) {
      	return (TypeAdapter<T>) new VideosPreferenceAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Channel.class) {
      	return (TypeAdapter<T>) new ChannelAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Privacy.class) {
      	return (TypeAdapter<T>) new PrivacyAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VodList.class) {
      	return (TypeAdapter<T>) new VodListAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Connection.class) {
      	return (TypeAdapter<T>) new ConnectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VodItem.Publish.class) {
      	return (TypeAdapter<T>) new PublishAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VimeoAccount.class) {
      	return (TypeAdapter<T>) new VimeoAccountAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Video.class) {
      	return (TypeAdapter<T>) new VideoAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.Picture.class) {
      	return (TypeAdapter<T>) new PictureAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.ConnectionCollection.class) {
      	return (TypeAdapter<T>) new ConnectionCollectionAdapter(gson);
      }
      if (clazz == com.vimeo.networking.model.VideoFile.class) {
      	return (TypeAdapter<T>) new VideoFileAdapter(gson);
      }

      return null;
    }
  }
}
