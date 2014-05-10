package com.dove.lib.oeb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

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

    private static final Map<String, MediaType> sCORE_MEDIA_TYPE_MAP;

    static {
        final MediaType[] mediaTypes = new MediaType[]{
            XHTML, EPUB, JPG, PNG, GIF, CSS, SVG, TTF, NCX, XPGT, OPENTYPE, WOFF, SMIL, PLS,
            JAVASCRIPT, MP3, MP4, OGG,
        };
        sCORE_MEDIA_TYPE_MAP = ImmutableMap.of();
        for (MediaType mediaType : mediaTypes) {
            sCORE_MEDIA_TYPE_MAP.put(mediaType.mName, mediaType);
        }
    }

    private final String mName;
    private final List<String> mExtensions;

    public MediaType(String name, String defaultExtension, String... extensions) {
        mName = name;
        mExtensions = Lists.asList(defaultExtension, extensions);
    }

    public MediaType(Parcel in, ClassLoader loader) {
        mName = in.readString();
        mExtensions = in.readArrayList(loader);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeList(mExtensions);
    }

    public String getName() {
        return mName;
    }

    public String getDefaultExtension() {
        return mExtensions.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaType)) return false;

        final MediaType mediaType = (MediaType) o;
        return Objects.equal(mName, mediaType.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mName);
    }

    public static final Parcelable.Creator<MediaType> CREATOR = new Parcelable.ClassLoaderCreator<MediaType>() {
        @Override
        public MediaType createFromParcel(Parcel source, ClassLoader loader) {
            return new MediaType(source, loader);
        }

        @Override
        public MediaType createFromParcel(Parcel source) {
            return new MediaType(source, null);
        }

        @Override
        public MediaType[] newArray(int size) {
            return new MediaType[size];
        }
    };
}
