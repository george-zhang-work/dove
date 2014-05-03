package com.dove.reader.provider.book;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by george on 4/26/14.
 */
public class MediaType implements Parcelable {
    // Application Types
    public static final MediaType XHTML = new MediaType("application/xhtml+xml", ".xhtml", new String[]{".htm", ".html", ".xhtml"});
    public static final MediaType EPUB = new MediaType("application/epub+zip", ".epub");
    public static final MediaType NCX = new MediaType("application/x-dtbncx+xml", ".ncx");

    // Text Types
    public static final MediaType JAVASCRIPT = new MediaType("text/javascript", ".js");
    public static final MediaType CSS = new MediaType("text/css", ".css");

    // Image Types
    public static final MediaType JPG = new MediaType("image/jpeg", ".jpg", new String[]{".jpg", ".jpeg"});
    public static final MediaType PNG = new MediaType("image/png", ".png");
    public static final MediaType GIF = new MediaType("image/gif", ".gif");
    public static final MediaType SVG = new MediaType("image/svg+xml", ".svg");

    // fonts
    public static final MediaType TTF = new MediaType("application/x-truetype-font", ".ttf");
    public static final MediaType OPENTYPE = new MediaType("application/vnd.ms-opentype", ".otf");
    public static final MediaType WOFF = new MediaType("application/font-woff", ".woff");

    // Audio Types
    public static final MediaType MP3 = new MediaType("audio/mpeg", ".mp3");
    public static final MediaType MP4 = new MediaType("audio/mp4", ".mp4");
    public static final MediaType OGG = new MediaType("audio/ogg", ".ogg");

    public static final MediaType SMIL = new MediaType("application/smil+xml", ".smil");
    public static final MediaType XPGT = new MediaType("application/adobe-page-template+xml", ".xpgt");
    public static final MediaType PLS = new MediaType("application/pls+xml", ".pls");

    private static final Map<String, MediaType> sSTRING_MEDIA_TYPE_MAP;

    static {
        final MediaType[] mediaTypes = new MediaType[]{
            XHTML, EPUB, JPG, PNG, GIF, CSS, SVG, TTF, NCX, XPGT, OPENTYPE, WOFF, SMIL, PLS,
            JAVASCRIPT, MP3, MP4, OGG,
        };
        sSTRING_MEDIA_TYPE_MAP = Maps.newHashMapWithExpectedSize(mediaTypes.length);
        for (MediaType mediaType : mediaTypes) {
            sSTRING_MEDIA_TYPE_MAP.put(mediaType.mName, mediaType);
        }
    }

    private final String mName;
    private final String mDefaultExtension;
    private final List<String> mExtensions;

    public MediaType(String name, String defaultExtension, String... extensions) {
        mName = name;
        mDefaultExtension = defaultExtension;
        mExtensions = Arrays.asList(extensions);
    }

    public MediaType(Parcel in, ClassLoader loader) {
        mName = in.readString();
        mDefaultExtension = in.readString();
        mExtensions = in.readArrayList(loader);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mDefaultExtension);
        dest.writeList(mExtensions);
    }

    public static final Creator<MediaType> CREATOR = new ClassLoaderCreator<MediaType>() {
        @Override
        public MediaType createFromParcel(Parcel source, ClassLoader loader) {
            return new MediaType(source, loader);
        }

        @Override
        public MediaType createFromParcel(Parcel source) {
            return new MediaType(source, MediaType.class.getClassLoader());
        }

        @Override
        public MediaType[] newArray(int size) {
            return new MediaType[size];
        }
    };

}
