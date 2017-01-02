package com.vimeo.stag.generated;

import com.google.gson.Gson;
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
import java.util.ArrayList;

final class ParseUtils {
  public static <T> ArrayList<T> parseArray(Gson gson, JsonReader reader, Class<T> clazz) throws IOException {
    if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    	reader.skipValue();
    	return null;
    }
    reader.beginArray();

    ArrayList<T> list = Stag.readListFromAdapter(gson, clazz, reader);

    reader.endArray();
    return list;
  }

  public static <T> void write(Gson gson, JsonWriter writer, Class clazz, ArrayList<T> list) throws IOException {
    if (list == null) {
    	return;
    }
    writer.beginArray();

    Stag.writeListToAdapter(gson, clazz, writer, list);

    writer.endArray();
  }

  public static void write(Gson gson, JsonWriter writer, Channel object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.modifiedTime != null) {
    			writer.name("modified_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.modifiedTime);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.createdTime != null) {
    			writer.name("created_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdTime);
    		}
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    		if (object.header != null) {
    			writer.name("header");
    			ParseUtils.write(gson, writer, object.header);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.privacy != null) {
    			writer.name("privacy");
    			ParseUtils.write(gson, writer, object.privacy);
    		}
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.pictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.pictures);
    		}
    		if (object.description != null) {
    			writer.name("description");
    			writer.value(object.description);
    		}
    	}
    	writer.endObject();
  }

  public static Channel parseChannel(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Channel object = new com.vimeo.networking.model.Channel();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "modified_time":
    				try {
    					object.modifiedTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.modifiedTime JSON!", exception);
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.metadata JSON!", exception);
    				}
    				break;
    			case "created_time":
    				try {
    					object.createdTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.createdTime JSON!", exception);
    				}
    				break;
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.user JSON!", exception);
    				}
    				break;
    			case "header":
    				try {
    					object.header = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.header JSON!", exception);
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "privacy":
    				try {
    					object.privacy = ParseUtils.parsePrivacy(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.privacy JSON!", exception);
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "pictures":
    				try {
    					object.pictures = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Channel.pictures JSON!", exception);
    				}
    				break;
    			case "description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.description = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, StatsCollection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.plays != null) {
    			writer.name("plays");
    			Stag.writeToAdapter(gson, java.lang.Integer.class, writer, object.plays);
    		}
    	}
    	writer.endObject();
  }

  public static StatsCollection parseStatsCollection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.StatsCollection object = new com.vimeo.networking.model.StatsCollection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "plays":
    				try {
    					object.plays = Stag.readFromAdapter(gson, java.lang.Integer.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing StatsCollection.plays JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, CategoryList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Category.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static CategoryList parseCategoryList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.CategoryList object = new com.vimeo.networking.model.CategoryList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Category.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing CategoryList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, ConnectionCollection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.appearances != null) {
    			writer.name("appearances");
    			ParseUtils.write(gson, writer, object.appearances);
    		}
    		if (object.trailer != null) {
    			writer.name("trailer");
    			ParseUtils.write(gson, writer, object.trailer);
    		}
    		if (object.ondemand != null) {
    			writer.name("ondemand");
    			ParseUtils.write(gson, writer, object.ondemand);
    		}
    		if (object.videos != null) {
    			writer.name("videos");
    			ParseUtils.write(gson, writer, object.videos);
    		}
    		if (object.feed != null) {
    			writer.name("feed");
    			ParseUtils.write(gson, writer, object.feed);
    		}
    		if (object.credits != null) {
    			writer.name("credits");
    			ParseUtils.write(gson, writer, object.credits);
    		}
    		if (object.shared != null) {
    			writer.name("shared");
    			ParseUtils.write(gson, writer, object.shared);
    		}
    		if (object.categories != null) {
    			writer.name("categories");
    			ParseUtils.write(gson, writer, object.categories);
    		}
    		if (object.recommendations != null) {
    			writer.name("recommendations");
    			ParseUtils.write(gson, writer, object.recommendations);
    		}
    		if (object.recommendedUsers != null) {
    			writer.name("recommended_users");
    			ParseUtils.write(gson, writer, object.recommendedUsers);
    		}
    		if (object.replies != null) {
    			writer.name("replies");
    			ParseUtils.write(gson, writer, object.replies);
    		}
    		if (object.comments != null) {
    			writer.name("comments");
    			ParseUtils.write(gson, writer, object.comments);
    		}
    		if (object.groups != null) {
    			writer.name("groups");
    			ParseUtils.write(gson, writer, object.groups);
    		}
    		if (object.watchlater != null) {
    			writer.name("watchlater");
    			ParseUtils.write(gson, writer, object.watchlater);
    		}
    		if (object.texttracks != null) {
    			writer.name("texttracks");
    			ParseUtils.write(gson, writer, object.texttracks);
    		}
    		if (object.likes != null) {
    			writer.name("likes");
    			ParseUtils.write(gson, writer, object.likes);
    		}
    		if (object.activities != null) {
    			writer.name("activities");
    			ParseUtils.write(gson, writer, object.activities);
    		}
    		if (object.moderatedChannels != null) {
    			writer.name("moderated_channels");
    			ParseUtils.write(gson, writer, object.moderatedChannels);
    		}
    		if (object.related != null) {
    			writer.name("related");
    			ParseUtils.write(gson, writer, object.related);
    		}
    		if (object.users != null) {
    			writer.name("users");
    			ParseUtils.write(gson, writer, object.users);
    		}
    		if (object.following != null) {
    			writer.name("following");
    			ParseUtils.write(gson, writer, object.following);
    		}
    		if (object.pictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.pictures);
    		}
    		if (object.followers != null) {
    			writer.name("followers");
    			ParseUtils.write(gson, writer, object.followers);
    		}
    		if (object.albums != null) {
    			writer.name("albums");
    			ParseUtils.write(gson, writer, object.albums);
    		}
    		if (object.channels != null) {
    			writer.name("channels");
    			ParseUtils.write(gson, writer, object.channels);
    		}
    		if (object.playbackFailureReason != null) {
    			writer.name("playback");
    			ParseUtils.write(gson, writer, object.playbackFailureReason);
    		}
    		if (object.recommendedChannels != null) {
    			writer.name("recommended_channels");
    			ParseUtils.write(gson, writer, object.recommendedChannels);
    		}
    		if (object.portfolios != null) {
    			writer.name("portfolios");
    			ParseUtils.write(gson, writer, object.portfolios);
    		}
    	}
    	writer.endObject();
  }

  public static ConnectionCollection parseConnectionCollection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.ConnectionCollection object = new com.vimeo.networking.model.ConnectionCollection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "appearances":
    				try {
    					object.appearances = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.appearances JSON!", exception);
    				}
    				break;
    			case "trailer":
    				try {
    					object.trailer = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.trailer JSON!", exception);
    				}
    				break;
    			case "ondemand":
    				try {
    					object.ondemand = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.ondemand JSON!", exception);
    				}
    				break;
    			case "videos":
    				try {
    					object.videos = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.videos JSON!", exception);
    				}
    				break;
    			case "feed":
    				try {
    					object.feed = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.feed JSON!", exception);
    				}
    				break;
    			case "credits":
    				try {
    					object.credits = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.credits JSON!", exception);
    				}
    				break;
    			case "shared":
    				try {
    					object.shared = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.shared JSON!", exception);
    				}
    				break;
    			case "categories":
    				try {
    					object.categories = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.categories JSON!", exception);
    				}
    				break;
    			case "recommendations":
    				try {
    					object.recommendations = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.recommendations JSON!", exception);
    				}
    				break;
    			case "recommended_users":
    				try {
    					object.recommendedUsers = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.recommendedUsers JSON!", exception);
    				}
    				break;
    			case "replies":
    				try {
    					object.replies = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.replies JSON!", exception);
    				}
    				break;
    			case "comments":
    				try {
    					object.comments = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.comments JSON!", exception);
    				}
    				break;
    			case "groups":
    				try {
    					object.groups = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.groups JSON!", exception);
    				}
    				break;
    			case "watchlater":
    				try {
    					object.watchlater = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.watchlater JSON!", exception);
    				}
    				break;
    			case "texttracks":
    				try {
    					object.texttracks = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.texttracks JSON!", exception);
    				}
    				break;
    			case "likes":
    				try {
    					object.likes = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.likes JSON!", exception);
    				}
    				break;
    			case "activities":
    				try {
    					object.activities = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.activities JSON!", exception);
    				}
    				break;
    			case "moderated_channels":
    				try {
    					object.moderatedChannels = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.moderatedChannels JSON!", exception);
    				}
    				break;
    			case "related":
    				try {
    					object.related = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.related JSON!", exception);
    				}
    				break;
    			case "users":
    				try {
    					object.users = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.users JSON!", exception);
    				}
    				break;
    			case "following":
    				try {
    					object.following = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.following JSON!", exception);
    				}
    				break;
    			case "pictures":
    				try {
    					object.pictures = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.pictures JSON!", exception);
    				}
    				break;
    			case "followers":
    				try {
    					object.followers = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.followers JSON!", exception);
    				}
    				break;
    			case "albums":
    				try {
    					object.albums = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.albums JSON!", exception);
    				}
    				break;
    			case "channels":
    				try {
    					object.channels = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.channels JSON!", exception);
    				}
    				break;
    			case "playback":
    				try {
    					object.playbackFailureReason = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.playbackFailureReason JSON!", exception);
    				}
    				break;
    			case "recommended_channels":
    				try {
    					object.recommendedChannels = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.recommendedChannels JSON!", exception);
    				}
    				break;
    			case "portfolios":
    				try {
    					object.portfolios = ParseUtils.parseConnection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ConnectionCollection.portfolios JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VideoFile object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.token != null) {
    			writer.name("token");
    			writer.value(object.token);
    		}
    			writer.name("size");
    			writer.value(object.size);
    		if (object.linkExpirationTime != null) {
    			writer.name("link_expiration_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.linkExpirationTime);
    		}
    			writer.name("fps");
    			writer.value(object.fps);
    			writer.name("height");
    			writer.value(object.height);
    		if (object.quality != null) {
    			writer.name("quality");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.VideoFile.VideoQuality.class, writer, object.quality);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.licenseLink != null) {
    			writer.name("license_link");
    			writer.value(object.licenseLink);
    		}
    			writer.name("width");
    			writer.value(object.width);
    		if (object.md5 != null) {
    			writer.name("md5");
    			writer.value(object.md5);
    		}
    		if (object.log != null) {
    			writer.name("log");
    			ParseUtils.write(gson, writer, object.log);
    		}
    		if (object.createdTime != null) {
    			writer.name("created_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdTime);
    		}
    		if (object.type != null) {
    			writer.name("type");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.VideoFile.MimeType.class, writer, object.type);
    		}
    		if (object.expires != null) {
    			writer.name("expires");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.expires);
    		}
    	}
    	writer.endObject();
  }

  public static VideoFile parseVideoFile(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VideoFile object = new com.vimeo.networking.model.VideoFile();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "token":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.token = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "size":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.size = reader.nextLong();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "link_expiration_time":
    				try {
    					object.linkExpirationTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.linkExpirationTime JSON!", exception);
    				}
    				break;
    			case "fps":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.fps = reader.nextDouble();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "height":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.height = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "quality":
    				try {
    					object.quality = Stag.readFromAdapter(gson, com.vimeo.networking.model.VideoFile.VideoQuality.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.quality JSON!", exception);
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "license_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.licenseLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "width":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.width = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "md5":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.md5 = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "log":
    				try {
    					object.log = ParseUtils.parseVideoLog(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.log JSON!", exception);
    				}
    				break;
    			case "created_time":
    				try {
    					object.createdTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.createdTime JSON!", exception);
    				}
    				break;
    			case "type":
    				try {
    					object.type = Stag.readFromAdapter(gson, com.vimeo.networking.model.VideoFile.MimeType.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.type JSON!", exception);
    				}
    				break;
    			case "expires":
    				try {
    					object.expires = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoFile.expires JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Space object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("free");
    			writer.value(object.free);
    			writer.name("used");
    			writer.value(object.used);
    			writer.name("max");
    			writer.value(object.max);
    	}
    	writer.endObject();
  }

  public static Space parseSpace(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Space object = new com.vimeo.networking.model.Space();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "free":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.free = reader.nextLong();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "used":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.used = reader.nextLong();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "max":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.max = reader.nextLong();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VideoLog object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mLikePressLink != null) {
    			writer.name("like_press_link");
    			writer.value(object.mLikePressLink);
    		}
    		if (object.mLoadLink != null) {
    			writer.name("load_link");
    			writer.value(object.mLoadLink);
    		}
    		if (object.mExitLink != null) {
    			writer.name("exit_link");
    			writer.value(object.mExitLink);
    		}
    		if (object.mPlayLink != null) {
    			writer.name("play_link");
    			writer.value(object.mPlayLink);
    		}
    		if (object.mWatchLaterPressLink != null) {
    			writer.name("watchlater_press_link");
    			writer.value(object.mWatchLaterPressLink);
    		}
    	}
    	writer.endObject();
  }

  public static VideoLog parseVideoLog(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.playback.VideoLog object = new com.vimeo.networking.model.playback.VideoLog();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "like_press_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mLikePressLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "load_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mLoadLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "exit_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mExitLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "play_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mPlayLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "watchlater_press_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mWatchLaterPressLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, User object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.bio != null) {
    			writer.name("bio");
    			writer.value(object.bio);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.emails != null) {
    			writer.name("emails");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Email.class, object.emails);
    		}
    		if (object.pictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.pictures);
    		}
    		if (object.uploadQuota != null) {
    			writer.name("upload_quota");
    			ParseUtils.write(gson, writer, object.uploadQuota);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.createdTime != null) {
    			writer.name("created_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdTime);
    		}
    		if (object.account != null) {
    			writer.name("account");
    			writer.value(object.account);
    		}
    		if (object.preferences != null) {
    			writer.name("preferences");
    			ParseUtils.write(gson, writer, object.preferences);
    		}
    		if (object.location != null) {
    			writer.name("location");
    			writer.value(object.location);
    		}
    		if (object.websites != null) {
    			writer.name("websites");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Website.class, object.websites);
    		}
    		if (object.badge != null) {
    			writer.name("badge");
    			ParseUtils.write(gson, writer, object.badge);
    		}
    	}
    	writer.endObject();
  }

  public static User parseUser(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.User object = new com.vimeo.networking.model.User();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "bio":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.bio = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.metadata JSON!", exception);
    				}
    				break;
    			case "emails":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.emails = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Email.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "pictures":
    				try {
    					object.pictures = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.pictures JSON!", exception);
    				}
    				break;
    			case "upload_quota":
    				try {
    					object.uploadQuota = ParseUtils.parseUploadQuota(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.uploadQuota JSON!", exception);
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "created_time":
    				try {
    					object.createdTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.createdTime JSON!", exception);
    				}
    				break;
    			case "account":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.account = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "preferences":
    				try {
    					object.preferences = ParseUtils.parsePreferences(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.preferences JSON!", exception);
    				}
    				break;
    			case "location":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.location = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "websites":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.websites = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Website.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "badge":
    				try {
    					object.badge = ParseUtils.parseUserBadge(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing User.badge JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, FacetOption object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.mTotal);
    		if (object.mText != null) {
    			writer.name("text");
    			writer.value(object.mText);
    		}
    		if (object.mName != null) {
    			writer.name("name");
    			writer.value(object.mName);
    		}
    	}
    	writer.endObject();
  }

  public static FacetOption parseFacetOption(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.search.FacetOption object = new com.vimeo.networking.model.search.FacetOption();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.mTotal = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "text":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mText = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mName = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Category object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.pictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.pictures);
    		}
    			writer.name("top_level");
    			writer.value(object.topLevel);
    		if (object.subcategories != null) {
    			writer.name("subcategories");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Category.class, object.subcategories);
    		}
    		if (object.parent != null) {
    			writer.name("parent");
    			ParseUtils.write(gson, writer, object.parent);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    	}
    	writer.endObject();
  }

  public static Category parseCategory(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Category object = new com.vimeo.networking.model.Category();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Category.metadata JSON!", exception);
    				}
    				break;
    			case "pictures":
    				try {
    					object.pictures = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Category.pictures JSON!", exception);
    				}
    				break;
    			case "top_level":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.topLevel = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "subcategories":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.subcategories = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Category.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "parent":
    				try {
    					object.parent = ParseUtils.parseCategory(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Category.parent JSON!", exception);
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VodList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.VodItem.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static VodList parseVodList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VodList object = new com.vimeo.networking.model.VodList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.VodItem.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, InteractionCollection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.rent != null) {
    			writer.name("rent");
    			ParseUtils.write(gson, writer, object.rent);
    		}
    		if (object.watchlater != null) {
    			writer.name("watchlater");
    			ParseUtils.write(gson, writer, object.watchlater);
    		}
    		if (object.follow != null) {
    			writer.name("follow");
    			ParseUtils.write(gson, writer, object.follow);
    		}
    		if (object.like != null) {
    			writer.name("like");
    			ParseUtils.write(gson, writer, object.like);
    		}
    		if (object.buy != null) {
    			writer.name("buy");
    			ParseUtils.write(gson, writer, object.buy);
    		}
    		if (object.subscribe != null) {
    			writer.name("subscribe");
    			ParseUtils.write(gson, writer, object.subscribe);
    		}
    	}
    	writer.endObject();
  }

  public static InteractionCollection parseInteractionCollection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.InteractionCollection object = new com.vimeo.networking.model.InteractionCollection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "rent":
    				try {
    					object.rent = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.rent JSON!", exception);
    				}
    				break;
    			case "watchlater":
    				try {
    					object.watchlater = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.watchlater JSON!", exception);
    				}
    				break;
    			case "follow":
    				try {
    					object.follow = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.follow JSON!", exception);
    				}
    				break;
    			case "like":
    				try {
    					object.like = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.like JSON!", exception);
    				}
    				break;
    			case "buy":
    				try {
    					object.buy = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.buy JSON!", exception);
    				}
    				break;
    			case "subscribe":
    				try {
    					object.subscribe = ParseUtils.parseInteraction(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing InteractionCollection.subscribe JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Interaction object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("added");
    			writer.value(object.added);
    		if (object.addedTime != null) {
    			writer.name("added_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.addedTime);
    		}
    		if (object.expiration != null) {
    			writer.name("expires_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.expiration);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.stream != null) {
    			writer.name("stream");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.Interaction.Stream.class, writer, object.stream);
    		}
    	}
    	writer.endObject();
  }

  public static Interaction parseInteraction(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Interaction object = new com.vimeo.networking.model.Interaction();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "added":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.added = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "added_time":
    				try {
    					object.addedTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Interaction.addedTime JSON!", exception);
    				}
    				break;
    			case "expires_time":
    				try {
    					object.expiration = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Interaction.expiration JSON!", exception);
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "stream":
    				try {
    					object.stream = Stag.readFromAdapter(gson, com.vimeo.networking.model.Interaction.Stream.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Interaction.stream JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Video object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.releaseTime != null) {
    			writer.name("release_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.releaseTime);
    		}
    		if (object.description != null) {
    			writer.name("description");
    			writer.value(object.description);
    		}
    		if (object.modifiedTime != null) {
    			writer.name("modified_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.modifiedTime);
    		}
    		if (object.contentRating != null) {
    			writer.name("content_rating");
    			ParseUtils.write(gson, writer, java.lang.String.class, object.contentRating);
    		}
    		if (object.license != null) {
    			writer.name("license");
    			writer.value(object.license);
    		}
    		if (object.tags != null) {
    			writer.name("tags");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Tag.class, object.tags);
    		}
    		if (object.reviewLink != null) {
    			writer.name("review_link");
    			writer.value(object.reviewLink);
    		}
    			writer.name("height");
    			writer.value(object.height);
    		if (object.files != null) {
    			writer.name("files");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.VideoFile.class, object.files);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.stats != null) {
    			writer.name("stats");
    			ParseUtils.write(gson, writer, object.stats);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.language != null) {
    			writer.name("language");
    			writer.value(object.language);
    		}
    		if (object.createdTime != null) {
    			writer.name("created_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdTime);
    		}
    		if (object.password != null) {
    			writer.name("password");
    			writer.value(object.password);
    		}
    		if (object.pictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.pictures);
    		}
    		if (object.embed != null) {
    			writer.name("embed");
    			ParseUtils.write(gson, writer, object.embed);
    		}
    		if (object.status != null) {
    			writer.name("status");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.Video.Status.class, writer, object.status);
    		}
    		if (object.play != null) {
    			writer.name("play");
    			ParseUtils.write(gson, writer, object.play);
    		}
    			writer.name("duration");
    			writer.value(object.duration);
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.resourceKey != null) {
    			writer.name("resource_key");
    			writer.value(object.resourceKey);
    		}
    		if (object.privacy != null) {
    			writer.name("privacy");
    			ParseUtils.write(gson, writer, object.privacy);
    		}
    			writer.name("width");
    			writer.value(object.width);
    		if (object.categories != null) {
    			writer.name("categories");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Category.class, object.categories);
    		}
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    		if (object.download != null) {
    			writer.name("download");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.VideoFile.class, object.download);
    		}
    	}
    	writer.endObject();
  }

  public static Video parseVideo(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Video object = new com.vimeo.networking.model.Video();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "release_time":
    				try {
    					object.releaseTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.releaseTime JSON!", exception);
    				}
    				break;
    			case "description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.description = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "modified_time":
    				try {
    					object.modifiedTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.modifiedTime JSON!", exception);
    				}
    				break;
    			case "content_rating":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.contentRating = ParseUtils.parseArray(gson, reader, java.lang.String.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "license":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.license = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "tags":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.tags = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Tag.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "review_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.reviewLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "height":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.height = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "files":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.files = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.VideoFile.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "stats":
    				try {
    					object.stats = ParseUtils.parseStatsCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.stats JSON!", exception);
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.metadata JSON!", exception);
    				}
    				break;
    			case "language":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.language = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "created_time":
    				try {
    					object.createdTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.createdTime JSON!", exception);
    				}
    				break;
    			case "password":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.password = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "pictures":
    				try {
    					object.pictures = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.pictures JSON!", exception);
    				}
    				break;
    			case "embed":
    				try {
    					object.embed = ParseUtils.parseEmbed(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.embed JSON!", exception);
    				}
    				break;
    			case "status":
    				try {
    					object.status = Stag.readFromAdapter(gson, com.vimeo.networking.model.Video.Status.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.status JSON!", exception);
    				}
    				break;
    			case "play":
    				try {
    					object.play = ParseUtils.parsePlay(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.play JSON!", exception);
    				}
    				break;
    			case "duration":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.duration = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "resource_key":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.resourceKey = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "privacy":
    				try {
    					object.privacy = ParseUtils.parsePrivacy(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.privacy JSON!", exception);
    				}
    				break;
    			case "width":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.width = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "categories":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.categories = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Category.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Video.user JSON!", exception);
    				}
    				break;
    			case "download":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.download = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.VideoFile.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VodItem object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mType != null) {
    			writer.name("type");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.VodItem.VodType.class, writer, object.mType);
    		}
    		if (object.mPublish != null) {
    			writer.name("publish");
    			ParseUtils.write(gson, writer, object.mPublish);
    		}
    		if (object.mFilm != null) {
    			writer.name("film");
    			ParseUtils.write(gson, writer, object.mFilm);
    		}
    		if (object.mName != null) {
    			writer.name("name");
    			writer.value(object.mName);
    		}
    		if (object.mMetadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.mMetadata);
    		}
    		if (object.mDescription != null) {
    			writer.name("description");
    			writer.value(object.mDescription);
    		}
    		if (object.mPictures != null) {
    			writer.name("pictures");
    			ParseUtils.write(gson, writer, object.mPictures);
    		}
    		if (object.mUser != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.mUser);
    		}
    		if (object.mTrailer != null) {
    			writer.name("trailer");
    			ParseUtils.write(gson, writer, object.mTrailer);
    		}
    		if (object.mLink != null) {
    			writer.name("link");
    			writer.value(object.mLink);
    		}
    	}
    	writer.endObject();
  }

  public static VodItem parseVodItem(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VodItem object = new com.vimeo.networking.model.VodItem();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "type":
    				try {
    					object.mType = Stag.readFromAdapter(gson, com.vimeo.networking.model.VodItem.VodType.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mType JSON!", exception);
    				}
    				break;
    			case "publish":
    				try {
    					object.mPublish = ParseUtils.parsePublish(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mPublish JSON!", exception);
    				}
    				break;
    			case "film":
    				try {
    					object.mFilm = ParseUtils.parseVideo(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mFilm JSON!", exception);
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mName = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.mMetadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mMetadata JSON!", exception);
    				}
    				break;
    			case "description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mDescription = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "pictures":
    				try {
    					object.mPictures = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mPictures JSON!", exception);
    				}
    				break;
    			case "user":
    				try {
    					object.mUser = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mUser JSON!", exception);
    				}
    				break;
    			case "trailer":
    				try {
    					object.mTrailer = ParseUtils.parseVideo(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VodItem.mTrailer JSON!", exception);
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Preferences object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.videos != null) {
    			writer.name("videos");
    			ParseUtils.write(gson, writer, object.videos);
    		}
    	}
    	writer.endObject();
  }

  public static Preferences parsePreferences(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Preferences object = new com.vimeo.networking.model.Preferences();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "videos":
    				try {
    					object.videos = ParseUtils.parseVideosPreference(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Preferences.videos JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, ChannelList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Channel.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static ChannelList parseChannelList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.ChannelList object = new com.vimeo.networking.model.ChannelList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Channel.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing ChannelList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Metadata object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.connections != null) {
    			writer.name("connections");
    			ParseUtils.write(gson, writer, object.connections);
    		}
    		if (object.interactions != null) {
    			writer.name("interactions");
    			ParseUtils.write(gson, writer, object.interactions);
    		}
    	}
    	writer.endObject();
  }

  public static Metadata parseMetadata(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Metadata object = new com.vimeo.networking.model.Metadata();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "connections":
    				try {
    					object.connections = ParseUtils.parseConnectionCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Metadata.connections JSON!", exception);
    				}
    				break;
    			case "interactions":
    				try {
    					object.interactions = ParseUtils.parseInteractionCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Metadata.interactions JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, PlayProgress object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mSeconds != null) {
    			writer.name("seconds");
    			Stag.writeToAdapter(gson, java.lang.Float.class, writer, object.mSeconds);
    		}
    	}
    	writer.endObject();
  }

  public static PlayProgress parsePlayProgress(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.playback.PlayProgress object = new com.vimeo.networking.model.playback.PlayProgress();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "seconds":
    				try {
    					object.mSeconds = Stag.readFromAdapter(gson, java.lang.Float.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing PlayProgress.mSeconds JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Recommendation object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mDescription != null) {
    			writer.name("description");
    			writer.value(object.mDescription);
    		}
    		if (object.mRecommendationType != null) {
    			writer.name("type");
    			writer.value(object.mRecommendationType);
    		}
    		if (object.mResourceKey != null) {
    			writer.name("resource_key");
    			writer.value(object.mResourceKey);
    		}
    		if (object.mUser != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.mUser);
    		}
    		if (object.mCategory != null) {
    			writer.name("category");
    			ParseUtils.write(gson, writer, object.mCategory);
    		}
    		if (object.mChannel != null) {
    			writer.name("channel");
    			ParseUtils.write(gson, writer, object.mChannel);
    		}
    	}
    	writer.endObject();
  }

  public static Recommendation parseRecommendation(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Recommendation object = new com.vimeo.networking.model.Recommendation();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mDescription = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "type":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mRecommendationType = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "resource_key":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mResourceKey = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "user":
    				try {
    					object.mUser = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Recommendation.mUser JSON!", exception);
    				}
    				break;
    			case "category":
    				try {
    					object.mCategory = ParseUtils.parseCategory(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Recommendation.mCategory JSON!", exception);
    				}
    				break;
    			case "channel":
    				try {
    					object.mChannel = ParseUtils.parseChannel(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Recommendation.mChannel JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, CommentList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Comment.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static CommentList parseCommentList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.CommentList object = new com.vimeo.networking.model.CommentList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Comment.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing CommentList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Picture object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    			writer.name("height");
    			writer.value(object.height);
    			writer.name("width");
    			writer.value(object.width);
    	}
    	writer.endObject();
  }

  public static Picture parsePicture(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Picture object = new com.vimeo.networking.model.Picture();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "height":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.height = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "width":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.width = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Tag object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.tag != null) {
    			writer.name("tag");
    			writer.value(object.tag);
    		}
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.canonical != null) {
    			writer.name("canonical");
    			writer.value(object.canonical);
    		}
    	}
    	writer.endObject();
  }

  public static Tag parseTag(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Tag object = new com.vimeo.networking.model.Tag();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "tag":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.tag = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Tag.metadata JSON!", exception);
    				}
    				break;
    			case "canonical":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.canonical = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Paging object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.next != null) {
    			writer.name("next");
    			writer.value(object.next);
    		}
    		if (object.previous != null) {
    			writer.name("previous");
    			writer.value(object.previous);
    		}
    		if (object.first != null) {
    			writer.name("first");
    			writer.value(object.first);
    		}
    		if (object.last != null) {
    			writer.name("last");
    			writer.value(object.last);
    		}
    	}
    	writer.endObject();
  }

  public static Paging parsePaging(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Paging object = new com.vimeo.networking.model.Paging();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "next":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.next = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "previous":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.previous = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "first":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.first = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "last":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.last = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Website object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.description != null) {
    			writer.name("description");
    			writer.value(object.description);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    	}
    	writer.endObject();
  }

  public static Website parseWebsite(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Website object = new com.vimeo.networking.model.Website();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.description = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Embed object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.html != null) {
    			writer.name("html");
    			writer.value(object.html);
    		}
    	}
    	writer.endObject();
  }

  public static Embed parseEmbed(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Embed object = new com.vimeo.networking.model.Embed();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "html":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.html = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Play object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mEmbed != null) {
    			writer.name("embed");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.playback.embed.Embed.class, writer, object.mEmbed);
    		}
    		if (object.mHls != null) {
    			writer.name("hls");
    			ParseUtils.write(gson, writer, object.mHls);
    		}
    		if (object.mProgress != null) {
    			writer.name("progress");
    			ParseUtils.write(gson, writer, object.mProgress);
    		}
    		if (object.mStatus != null) {
    			writer.name("status");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.playback.Play.Status.class, writer, object.mStatus);
    		}
    		if (object.mDrm != null) {
    			writer.name("drm");
    			ParseUtils.write(gson, writer, object.mDrm);
    		}
    		if (object.mDash != null) {
    			writer.name("dash");
    			ParseUtils.write(gson, writer, object.mDash);
    		}
    		if (object.mProgressive != null) {
    			writer.name("progressive");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.VideoFile.class, object.mProgressive);
    		}
    	}
    	writer.endObject();
  }

  public static Play parsePlay(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.playback.Play object = new com.vimeo.networking.model.playback.Play();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "embed":
    				try {
    					object.mEmbed = Stag.readFromAdapter(gson, com.vimeo.networking.model.playback.embed.Embed.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mEmbed JSON!", exception);
    				}
    				break;
    			case "hls":
    				try {
    					object.mHls = ParseUtils.parseVideoFile(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mHls JSON!", exception);
    				}
    				break;
    			case "progress":
    				try {
    					object.mProgress = ParseUtils.parsePlayProgress(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mProgress JSON!", exception);
    				}
    				break;
    			case "status":
    				try {
    					object.mStatus = Stag.readFromAdapter(gson, com.vimeo.networking.model.playback.Play.Status.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mStatus JSON!", exception);
    				}
    				break;
    			case "drm":
    				try {
    					object.mDrm = ParseUtils.parseDrm(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mDrm JSON!", exception);
    				}
    				break;
    			case "dash":
    				try {
    					object.mDash = ParseUtils.parseVideoFile(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Play.mDash JSON!", exception);
    				}
    				break;
    			case "progressive":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.mProgressive = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.VideoFile.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, SearchResult object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mVod != null) {
    			writer.name("ondemand");
    			ParseUtils.write(gson, writer, object.mVod);
    		}
    		if (object.mVideo != null) {
    			writer.name("clip");
    			ParseUtils.write(gson, writer, object.mVideo);
    		}
    		if (object.mUser != null) {
    			writer.name("people");
    			ParseUtils.write(gson, writer, object.mUser);
    		}
    			writer.name("is_staffpick");
    			writer.value(object.mIsStaffPick);
    			writer.name("is_featured");
    			writer.value(object.mIsFeatured);
    		if (object.mSearchType != null) {
    			writer.name("type");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.search.SearchType.class, writer, object.mSearchType);
    		}
    		if (object.mChannel != null) {
    			writer.name("channel");
    			ParseUtils.write(gson, writer, object.mChannel);
    		}
    		if (object.mGroup != null) {
    			writer.name("group");
    			ParseUtils.write(gson, writer, object.mGroup);
    		}
    	}
    	writer.endObject();
  }

  public static SearchResult parseSearchResult(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.search.SearchResult object = new com.vimeo.networking.model.search.SearchResult();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "ondemand":
    				try {
    					object.mVod = ParseUtils.parseVodItem(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mVod JSON!", exception);
    				}
    				break;
    			case "clip":
    				try {
    					object.mVideo = ParseUtils.parseVideo(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mVideo JSON!", exception);
    				}
    				break;
    			case "people":
    				try {
    					object.mUser = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mUser JSON!", exception);
    				}
    				break;
    			case "is_staffpick":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.mIsStaffPick = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "is_featured":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.mIsFeatured = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "type":
    				try {
    					object.mSearchType = Stag.readFromAdapter(gson, com.vimeo.networking.model.search.SearchType.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mSearchType JSON!", exception);
    				}
    				break;
    			case "channel":
    				try {
    					object.mChannel = ParseUtils.parseChannel(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mChannel JSON!", exception);
    				}
    				break;
    			case "group":
    				try {
    					object.mGroup = ParseUtils.parseGroup(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResult.mGroup JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, PictureCollection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("default");
    			writer.value(object.isDefault);
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.sizes != null) {
    			writer.name("sizes");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Picture.class, object.sizes);
    		}
    			writer.name("active");
    			writer.value(object.active);
    	}
    	writer.endObject();
  }

  public static PictureCollection parsePictureCollection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.PictureCollection object = new com.vimeo.networking.model.PictureCollection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "default":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.isDefault = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "sizes":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.sizes = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Picture.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "active":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.active = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, FeedItem object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    		if (object.time != null) {
    			writer.name("time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.time);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.clip != null) {
    			writer.name("clip");
    			ParseUtils.write(gson, writer, object.clip);
    		}
    		if (object.type != null) {
    			writer.name("type");
    			writer.value(object.type);
    		}
    		if (object.category != null) {
    			writer.name("category");
    			ParseUtils.write(gson, writer, object.category);
    		}
    		if (object.group != null) {
    			writer.name("group");
    			ParseUtils.write(gson, writer, object.group);
    		}
    		if (object.tag != null) {
    			writer.name("tag");
    			ParseUtils.write(gson, writer, object.tag);
    		}
    		if (object.channel != null) {
    			writer.name("channel");
    			ParseUtils.write(gson, writer, object.channel);
    		}
    	}
    	writer.endObject();
  }

  public static FeedItem parseFeedItem(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.FeedItem object = new com.vimeo.networking.model.FeedItem();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.user JSON!", exception);
    				}
    				break;
    			case "time":
    				try {
    					object.time = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.time JSON!", exception);
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.metadata JSON!", exception);
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "clip":
    				try {
    					object.clip = ParseUtils.parseVideo(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.clip JSON!", exception);
    				}
    				break;
    			case "type":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.type = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "category":
    				try {
    					object.category = ParseUtils.parseCategory(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.category JSON!", exception);
    				}
    				break;
    			case "group":
    				try {
    					object.group = ParseUtils.parseGroup(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.group JSON!", exception);
    				}
    				break;
    			case "tag":
    				try {
    					object.tag = ParseUtils.parseTag(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.tag JSON!", exception);
    				}
    				break;
    			case "channel":
    				try {
    					object.channel = ParseUtils.parseChannel(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedItem.channel JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Comment object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.type != null) {
    			writer.name("type");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.Comment.CommentType.class, writer, object.type);
    		}
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    		if (object.text != null) {
    			writer.name("text");
    			writer.value(object.text);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.createdOn != null) {
    			writer.name("created_on");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdOn);
    		}
    	}
    	writer.endObject();
  }

  public static Comment parseComment(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Comment object = new com.vimeo.networking.model.Comment();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "type":
    				try {
    					object.type = Stag.readFromAdapter(gson, com.vimeo.networking.model.Comment.CommentType.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Comment.type JSON!", exception);
    				}
    				break;
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Comment.user JSON!", exception);
    				}
    				break;
    			case "text":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.text = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Comment.metadata JSON!", exception);
    				}
    				break;
    			case "created_on":
    				try {
    					object.createdOn = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Comment.createdOn JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, FeedList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.FeedItem.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static FeedList parseFeedList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.FeedList object = new com.vimeo.networking.model.FeedList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.FeedItem.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing FeedList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VimeoAccount object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.accessToken != null) {
    			writer.name("access_token");
    			writer.value(object.accessToken);
    		}
    		if (object.tokenType != null) {
    			writer.name("token_type");
    			writer.value(object.tokenType);
    		}
    		if (object.scope != null) {
    			writer.name("scope");
    			writer.value(object.scope);
    		}
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    	}
    	writer.endObject();
  }

  public static VimeoAccount parseVimeoAccount(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VimeoAccount object = new com.vimeo.networking.model.VimeoAccount();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "access_token":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.accessToken = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "token_type":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.tokenType = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "scope":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.scope = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VimeoAccount.user JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VodItem.Publish object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mTime != null) {
    			writer.name("time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.mTime);
    		}
    	}
    	writer.endObject();
  }

  public static VodItem.Publish parsePublish(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VodItem.Publish object = new com.vimeo.networking.model.VodItem.Publish();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "time":
    				try {
    					object.mTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Publish.mTime JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, UploadQuota object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.quota != null) {
    			writer.name("quota");
    			ParseUtils.write(gson, writer, object.quota);
    		}
    		if (object.space != null) {
    			writer.name("space");
    			ParseUtils.write(gson, writer, object.space);
    		}
    	}
    	writer.endObject();
  }

  public static UploadQuota parseUploadQuota(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.UploadQuota object = new com.vimeo.networking.model.UploadQuota();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "quota":
    				try {
    					object.quota = ParseUtils.parseQuota(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing UploadQuota.quota JSON!", exception);
    				}
    				break;
    			case "space":
    				try {
    					object.space = ParseUtils.parseSpace(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing UploadQuota.space JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, PictureResource object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    			writer.name("active");
    			writer.value(object.active);
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    	}
    	writer.endObject();
  }

  public static PictureResource parsePictureResource(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.PictureResource object = new com.vimeo.networking.model.PictureResource();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "active":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.active = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, PinCodeInfo object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mAuthorizeLink != null) {
    			writer.name("authorize_link");
    			writer.value(object.mAuthorizeLink);
    		}
    			writer.name("expires_in");
    			writer.value(object.expiresIn);
    		if (object.mUserCode != null) {
    			writer.name("user_code");
    			writer.value(object.mUserCode);
    		}
    		if (object.mActivateLink != null) {
    			writer.name("activate_link");
    			writer.value(object.mActivateLink);
    		}
    		if (object.mDeviceCode != null) {
    			writer.name("device_code");
    			writer.value(object.mDeviceCode);
    		}
    			writer.name("interval");
    			writer.value(object.interval);
    	}
    	writer.endObject();
  }

  public static PinCodeInfo parsePinCodeInfo(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.PinCodeInfo object = new com.vimeo.networking.model.PinCodeInfo();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "authorize_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mAuthorizeLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "expires_in":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.expiresIn = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "user_code":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mUserCode = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "activate_link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mActivateLink = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "device_code":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mDeviceCode = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "interval":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.interval = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, SearchFacetCollection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mTypeFacet != null) {
    			writer.name("type");
    			ParseUtils.write(gson, writer, object.mTypeFacet);
    		}
    		if (object.mUserTypeFacet != null) {
    			writer.name("user_type");
    			ParseUtils.write(gson, writer, object.mUserTypeFacet);
    		}
    		if (object.mDurationFacet != null) {
    			writer.name("duration");
    			ParseUtils.write(gson, writer, object.mDurationFacet);
    		}
    		if (object.mCategoryFacet != null) {
    			writer.name("category");
    			ParseUtils.write(gson, writer, object.mCategoryFacet);
    		}
    		if (object.mLicenseFacet != null) {
    			writer.name("license");
    			ParseUtils.write(gson, writer, object.mLicenseFacet);
    		}
    		if (object.mUploadedFacet != null) {
    			writer.name("uploaded");
    			ParseUtils.write(gson, writer, object.mUploadedFacet);
    		}
    	}
    	writer.endObject();
  }

  public static SearchFacetCollection parseSearchFacetCollection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.search.SearchFacetCollection object = new com.vimeo.networking.model.search.SearchFacetCollection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "type":
    				try {
    					object.mTypeFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mTypeFacet JSON!", exception);
    				}
    				break;
    			case "user_type":
    				try {
    					object.mUserTypeFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mUserTypeFacet JSON!", exception);
    				}
    				break;
    			case "duration":
    				try {
    					object.mDurationFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mDurationFacet JSON!", exception);
    				}
    				break;
    			case "category":
    				try {
    					object.mCategoryFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mCategoryFacet JSON!", exception);
    				}
    				break;
    			case "license":
    				try {
    					object.mLicenseFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mLicenseFacet JSON!", exception);
    				}
    				break;
    			case "uploaded":
    				try {
    					object.mUploadedFacet = ParseUtils.parseSearchFacet(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchFacetCollection.mUploadedFacet JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Drm object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mWidevine != null) {
    			writer.name("widevine");
    			ParseUtils.write(gson, writer, object.mWidevine);
    		}
    		if (object.mPlayReady != null) {
    			writer.name("playready");
    			ParseUtils.write(gson, writer, object.mPlayReady);
    		}
    	}
    	writer.endObject();
  }

  public static Drm parseDrm(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.playback.Drm object = new com.vimeo.networking.model.playback.Drm();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "widevine":
    				try {
    					object.mWidevine = ParseUtils.parseVideoFile(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Drm.mWidevine JSON!", exception);
    				}
    				break;
    			case "playready":
    				try {
    					object.mPlayReady = ParseUtils.parseVideoFile(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Drm.mPlayReady JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VideosPreference object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.privacy != null) {
    			writer.name("privacy");
    			writer.value(object.privacy);
    		}
    		if (object.password != null) {
    			writer.name("password");
    			writer.value(object.password);
    		}
    	}
    	writer.endObject();
  }

  public static VideosPreference parseVideosPreference(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VideosPreference object = new com.vimeo.networking.model.VideosPreference();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "privacy":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.privacy = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "password":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.password = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, SearchResponse object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    			writer.name("mature_hidden_count");
    			writer.value(object.mMatureHiddenCount);
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    		if (object.mFacetCollection != null) {
    			writer.name("facets");
    			ParseUtils.write(gson, writer, object.mFacetCollection);
    		}
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.search.SearchResult.class, object.data);
    		}
    	}
    	writer.endObject();
  }

  public static SearchResponse parseSearchResponse(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.search.SearchResponse object = new com.vimeo.networking.model.search.SearchResponse();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "mature_hidden_count":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.mMatureHiddenCount = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResponse.paging JSON!", exception);
    				}
    				break;
    			case "facets":
    				try {
    					object.mFacetCollection = ParseUtils.parseSearchFacetCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing SearchResponse.mFacetCollection JSON!", exception);
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.search.SearchResult.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, UserList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.User.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static UserList parseUserList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.UserList object = new com.vimeo.networking.model.UserList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.User.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing UserList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, UserBadge object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mText != null) {
    			writer.name("text");
    			writer.value(object.mText);
    		}
    		if (object.mAlternateText != null) {
    			writer.name("alt_text");
    			writer.value(object.mAlternateText);
    		}
    		if (object.mBadgeType != null) {
    			writer.name("type");
    			writer.value(object.mBadgeType);
    		}
    		if (object.mUrl != null) {
    			writer.name("url");
    			writer.value(object.mUrl);
    		}
    	}
    	writer.endObject();
  }

  public static UserBadge parseUserBadge(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.UserBadge object = new com.vimeo.networking.model.UserBadge();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "text":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mText = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "alt_text":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mAlternateText = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "type":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mBadgeType = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "url":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mUrl = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Quota object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("sd");
    			writer.value(object.sd);
    			writer.name("hd");
    			writer.value(object.hd);
    	}
    	writer.endObject();
  }

  public static Quota parseQuota(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Quota object = new com.vimeo.networking.model.Quota();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "sd":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.sd = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "hd":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.hd = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Connection object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("extra_total");
    			writer.value(object.extraTotal);
    			writer.name("viewable_total");
    			writer.value(object.viewableTotal);
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.options != null) {
    			writer.name("options");
    			ParseUtils.write(gson, writer, java.lang.String.class, object.options);
    		}
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("main_total");
    			writer.value(object.mainTotal);
    	}
    	writer.endObject();
  }

  public static Connection parseConnection(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Connection object = new com.vimeo.networking.model.Connection();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "extra_total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.extraTotal = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "viewable_total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.viewableTotal = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "options":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.options = ParseUtils.parseArray(gson, reader, java.lang.String.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "main_total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.mainTotal = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Privacy object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.comments != null) {
    			writer.name("comments");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.Privacy.PrivacyValue.class, writer, object.comments);
    		}
    		if (object.view != null) {
    			writer.name("view");
    			Stag.writeToAdapter(gson, com.vimeo.networking.model.Privacy.PrivacyValue.class, writer, object.view);
    		}
    		if (object.embed != null) {
    			writer.name("embed");
    			writer.value(object.embed);
    		}
    			writer.name("add");
    			writer.value(object.add);
    			writer.name("download");
    			writer.value(object.download);
    	}
    	writer.endObject();
  }

  public static Privacy parsePrivacy(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Privacy object = new com.vimeo.networking.model.Privacy();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "comments":
    				try {
    					object.comments = Stag.readFromAdapter(gson, com.vimeo.networking.model.Privacy.PrivacyValue.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Privacy.comments JSON!", exception);
    				}
    				break;
    			case "view":
    				try {
    					object.view = Stag.readFromAdapter(gson, com.vimeo.networking.model.Privacy.PrivacyValue.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Privacy.view JSON!", exception);
    				}
    				break;
    			case "embed":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.embed = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "add":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.add = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "download":
    				if(jsonToken == com.google.gson.stream.JsonToken.BOOLEAN) {
    					object.download = reader.nextBoolean();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, VideoList object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    			writer.name("total");
    			writer.value(object.total);
    			writer.name("per_page");
    			writer.value(object.perPage);
    			writer.name("page");
    			writer.value(object.page);
    		if (object.data != null) {
    			writer.name("data");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.Video.class, object.data);
    		}
    		if (object.paging != null) {
    			writer.name("paging");
    			ParseUtils.write(gson, writer, object.paging);
    		}
    	}
    	writer.endObject();
  }

  public static VideoList parseVideoList(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.VideoList object = new com.vimeo.networking.model.VideoList();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "total":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.total = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "per_page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.perPage = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "page":
    				if(jsonToken == com.google.gson.stream.JsonToken.NUMBER) {
    					object.page = reader.nextInt();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "data":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.data = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.Video.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "paging":
    				try {
    					object.paging = ParseUtils.parsePaging(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing VideoList.paging JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, SearchFacet object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.mOptions != null) {
    			writer.name("options");
    			ParseUtils.write(gson, writer, com.vimeo.networking.model.search.FacetOption.class, object.mOptions);
    		}
    		if (object.mName != null) {
    			writer.name("name");
    			writer.value(object.mName);
    		}
    	}
    	writer.endObject();
  }

  public static SearchFacet parseSearchFacet(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.search.SearchFacet object = new com.vimeo.networking.model.search.SearchFacet();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "options":
    				if(jsonToken == com.google.gson.stream.JsonToken.BEGIN_ARRAY) {
    					object.mOptions = ParseUtils.parseArray(gson, reader, com.vimeo.networking.model.search.FacetOption.class);
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.mName = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Group object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.user != null) {
    			writer.name("user");
    			ParseUtils.write(gson, writer, object.user);
    		}
    		if (object.createdTime != null) {
    			writer.name("created_time");
    			Stag.writeToAdapter(gson, java.util.Date.class, writer, object.createdTime);
    		}
    		if (object.groupDescription != null) {
    			writer.name("group_description");
    			writer.value(object.groupDescription);
    		}
    		if (object.link != null) {
    			writer.name("link");
    			writer.value(object.link);
    		}
    		if (object.metadata != null) {
    			writer.name("metadata");
    			ParseUtils.write(gson, writer, object.metadata);
    		}
    		if (object.uri != null) {
    			writer.name("uri");
    			writer.value(object.uri);
    		}
    		if (object.name != null) {
    			writer.name("name");
    			writer.value(object.name);
    		}
    		if (object.pictureCollection != null) {
    			writer.name("picture_collection");
    			ParseUtils.write(gson, writer, object.pictureCollection);
    		}
    		if (object.privacy != null) {
    			writer.name("privacy");
    			ParseUtils.write(gson, writer, object.privacy);
    		}
    	}
    	writer.endObject();
  }

  public static Group parseGroup(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Group object = new com.vimeo.networking.model.Group();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "user":
    				try {
    					object.user = ParseUtils.parseUser(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Group.user JSON!", exception);
    				}
    				break;
    			case "created_time":
    				try {
    					object.createdTime = Stag.readFromAdapter(gson, java.util.Date.class, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Group.createdTime JSON!", exception);
    				}
    				break;
    			case "group_description":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.groupDescription = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "link":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.link = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "metadata":
    				try {
    					object.metadata = ParseUtils.parseMetadata(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Group.metadata JSON!", exception);
    				}
    				break;
    			case "uri":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.uri = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "name":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.name = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			case "picture_collection":
    				try {
    					object.pictureCollection = ParseUtils.parsePictureCollection(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Group.pictureCollection JSON!", exception);
    				}
    				break;
    			case "privacy":
    				try {
    					object.privacy = ParseUtils.parsePrivacy(gson, reader);
    				} catch(Exception exception) {
    					throw new IOException("Error parsing Group.privacy JSON!", exception);
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }

  public static void write(Gson gson, JsonWriter writer, Email object) throws IOException {
    	writer.beginObject();
    	if (object == null) {
    		return;
    	} else {
    		if (object.email != null) {
    			writer.name("email");
    			writer.value(object.email);
    		}
    	}
    	writer.endObject();
  }

  public static Email parseEmail(Gson gson, JsonReader reader) throws IOException {
    	if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
    		reader.nextNull();
    		return null;
    	}
    	if(reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
    		reader.skipValue();
    		return null;
    	}
    	reader.beginObject();

    	com.vimeo.networking.model.Email object = new com.vimeo.networking.model.Email();
    	while (reader.hasNext()) {
    		String name = reader.nextName();
    		com.google.gson.stream.JsonToken jsonToken = reader.peek();
    		if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
    			reader.skipValue();
    			continue;
    		}
    		switch (name) {
    			case "email":
    				if(jsonToken == com.google.gson.stream.JsonToken.STRING) {
    					object.email = reader.nextString();
    				} else {
    					reader.skipValue();
    				}
    				break;
    			default:
    				reader.skipValue();
    				break;
    		}
    	}

    	reader.endObject();
    	return object;
  }
}
