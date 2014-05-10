package com.dove.lib.oeb.ncx;

import android.os.Parcel;

import com.dove.lib.oeb.Direction;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.SimpleElement;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by george on 5/8/14.
 */
public class DocElement extends SimpleElement {
    private String mXmlLang;
    @SerializedName(OEBContract.Attributes.DIR)
    private Direction mDir;

    @SerializedName(OEBContract.Elements.TEXT)
    private Text mText;
    @SerializedName(OEBContract.Elements.AUDIO)
    private Audio mAudio;
    @SerializedName(OEBContract.Elements.IMAGE)
    private Image mImage;

    public DocElement() {
        super();
    }

    public DocElement(Parcel in, ClassLoader loader) {
        super(in, loader);
        mXmlLang = in.readString();
        mDir = Direction.fromValue(in.readString());

        mText = in.readParcelable(loader);
        mAudio = in.readParcelable(loader);
        mImage = in.readParcelable(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mXmlLang);
        dest.writeString(mDir.toString());

        dest.writeParcelable(mText, flags);
        dest.writeParcelable(mAudio, flags);
        dest.writeParcelable(mImage, flags);
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.DOC_ELEMENT;
    }

    @Override
    protected void onParseAttributes(XmlPullParser parser) throws XmlPullParserException, IOException {
        super.onParseAttributes(parser);
        mXmlLang = parser.getAttributeValue(OEBContract.Namespaces.XML, OEBContract.Attributes.LANG);
        mDir = Direction.fromValue(parser.getAttributeValue("", OEBContract.Attributes.DIR));
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.TEXT:
                        mText = new Text();
                        mText.onParse(parser);
                        break;

                    case OEBContract.Elements.AUDIO:
                        mAudio = new Audio();
                        mAudio.onParse(parser);
                        break;

                    case OEBContract.Elements.IMAGE:
                        mImage = new Image();
                        mImage.onParse(parser);
                        break;
                }

            } else if (eventType == XmlPullParser.END_TAG) {
                if (Objects.equal(parser.getName(), getElementName())) {
                    return;
                }
            }
            eventType = parser.next();
        }
    }

    public static final ClassLoaderCreator<DocElement> CREATOR = new ClassLoaderCreator<DocElement>() {
        @Override
        public DocElement createFromParcel(Parcel source, ClassLoader loader) {
            return new DocElement(source, loader);
        }

        @Override
        public DocElement createFromParcel(Parcel source) {
            return new DocElement(source, null);
        }

        @Override
        public DocElement[] newArray(int size) {
            return new DocElement[size];
        }
    };
}