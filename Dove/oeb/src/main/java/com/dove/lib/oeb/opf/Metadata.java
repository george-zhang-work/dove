package com.dove.lib.oeb.opf;

import android.os.Parcel;
import android.text.TextUtils;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.dove.lib.oeb.ParcelableCreator;
import com.dove.lib.oeb.Serializerable;
import com.dove.lib.oeb.SimpleElement;
import com.dove.lib.oeb.SimpleTextElement;
import com.google.common.base.Objects;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by george on 5/6/14.
 */
public class Metadata extends Element {

    @SerializedName(OEBContract.Elements.DATE)
    private Date mDate;
    @SerializedName(OEBContract.Elements.SOURCE)
    private Source mSource;
    @SerializedName(OEBContract.Elements.TYPE)
    private Type mType;

    @SerializedName(OEBContract.Elements.IDENTIFIERS)
    private final List<Identifier> mIdentifiers;
    @SerializedName(OEBContract.Elements.TITLES)
    private final List<Title> mTitles;
    @SerializedName(OEBContract.Elements.LANGUAGES)
    private final List<Language> mLanguages;
    @SerializedName(OEBContract.Elements.CONTRIBUTORS)
    private final List<Contributor> mContributors;
    @SerializedName(OEBContract.Elements.CREATORS)
    private final List<Author> mAuthors;
    @SerializedName(OEBContract.Elements.COVERAGES)
    private final List<Coverage> mCoverages;
    @SerializedName(OEBContract.Elements.DESCRIPTIONS)
    private final List<Description> mDescriptions;
    @SerializedName(OEBContract.Elements.FORMATS)
    private final List<Format> mFormats;
    @SerializedName(OEBContract.Elements.PUBLISHERS)
    private final List<Publisher> mPublishers;
    @SerializedName(OEBContract.Elements.RELATIONS)
    private final List<Relation> mRelations;
    @SerializedName(OEBContract.Elements.RIGHTSES)
    private final List<Rights> mRightses;
    @SerializedName(OEBContract.Elements.SUBJECTS)
    private final List<Subject> mSubjects;
    @SerializedName(OEBContract.Elements.METAS)
    private final Multimap<String, Meta> mMetas;
    @SerializedName(OEBContract.Elements.META20S)
    private final List<Meta20> mMeta20s;
    @SerializedName(OEBContract.Elements.LINKS)
    private final List<Link> mLinks;

    public Metadata() {
        mIdentifiers = Lists.newArrayList();
        mTitles = Lists.newArrayList();
        mLanguages = Lists.newArrayList();
        mContributors = Lists.newArrayList();
        mAuthors = Lists.newArrayList();
        mCoverages = Lists.newArrayList();
        mDescriptions = Lists.newArrayList();
        mFormats = Lists.newArrayList();
        mPublishers = Lists.newArrayList();
        mRelations = Lists.newArrayList();
        mRightses = Lists.newArrayList();
        mSubjects = Lists.newArrayList();
        mLinks = Lists.newArrayList();
        mMetas = LinkedHashMultimap.create();
        mMeta20s = Lists.newArrayList();
    }

    public Metadata(Parcel in, ClassLoader loader) {
        super(in, loader);
        mDate = in.readParcelable(loader);
        mSource = in.readParcelable(loader);
        mType = in.readParcelable(loader);
        mIdentifiers = in.readArrayList(loader);
        mTitles = in.readArrayList(loader);
        mLanguages = in.readArrayList(loader);
        mContributors = in.readArrayList(loader);
        mAuthors = in.readArrayList(loader);
        mCoverages = in.readArrayList(loader);
        mDescriptions = in.readArrayList(loader);
        mFormats = in.readArrayList(loader);
        mPublishers = in.readArrayList(loader);
        mRelations = in.readArrayList(loader);
        mRightses = in.readArrayList(loader);
        mSubjects = in.readArrayList(loader);
        mLinks = in.readArrayList(loader);
        mMeta20s = in.readArrayList(loader);

        final int size = in.readInt();
        mMetas = LinkedHashMultimap.create();
        for (int i = 0; i < size; i++) {
            final String key = in.readString();
            final Meta meta = in.readParcelable(loader);
            mMetas.put(key, meta);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mDate, flags);
        dest.writeParcelable(mSource, flags);
        dest.writeParcelable(mType, flags);
        dest.writeList(mIdentifiers);
        dest.writeList(mTitles);
        dest.writeList(mLanguages);
        dest.writeList(mContributors);
        dest.writeList(mAuthors);
        dest.writeList(mCoverages);
        dest.writeList(mDescriptions);
        dest.writeList(mFormats);
        dest.writeList(mPublishers);
        dest.writeList(mRelations);
        dest.writeList(mRightses);
        dest.writeList(mSubjects);
        dest.writeList(mLinks);
        dest.writeList(mMeta20s);

        dest.writeInt(mMetas.size());
        final Iterator<Map.Entry<String, Meta>> it = mMetas.entries().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Meta> entry = it.next();
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metadata)) return false;

        final Metadata metadata = (Metadata) o;
        return Objects.equal(mIdentifiers, metadata.mIdentifiers)
            && Objects.equal(mTitles, metadata.mTitles)
            && Objects.equal(mLanguages, metadata.mLanguages)
            && Objects.equal(mContributors, metadata.mContributors)
            && Objects.equal(mAuthors, metadata.mAuthors)
            && Objects.equal(mCoverages, metadata.mCoverages)
            && Objects.equal(mDescriptions, metadata.mDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mIdentifiers, mTitles, mLanguages, mContributors, mAuthors);
    }

    public static final ClassLoaderCreator<Metadata> CREATOR = new ParcelableCreator<>(Metadata.class);

    @Override
    protected String getElementName() {
        return OEBContract.Elements.METADATA;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.OPF;
    }

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.IDENTIFIER:
                        onParseIdentifier(parser);
                        break;

                    case OEBContract.Elements.TITLE:
                        onParseTitle(parser);
                        break;

                    case OEBContract.Elements.LANGUAGES:
                        final Language language = new Language();
                        language.onParse(parser);
                        mLanguages.add(language);
                        break;

                    case OEBContract.Elements.CONTRIBUTOR:
                        onParseContributor(parser);
                        break;

                    case OEBContract.Elements.CREATOR:
                        onParseCreator(parser);
                        break;

                    case OEBContract.Elements.COVERAGE:
                        final Coverage coverage = new Coverage();
                        coverage.onParse(parser);
                        mCoverages.add(coverage);
                        break;

                    case OEBContract.Elements.DESCRIPTION:
                        final Description description = new Description();
                        description.onParse(parser);
                        mDescriptions.add(description);
                        break;

                    case OEBContract.Elements.FORMAT:
                        final Format format = new Format();
                        format.onParse(parser);
                        mFormats.add(format);
                        break;

                    case OEBContract.Elements.PUBLISHER:
                        final Publisher publisher = new Publisher();
                        publisher.onParse(parser);
                        mPublishers.add(publisher);
                        break;

                    case OEBContract.Elements.RELATION:
                        final Relation relation = new Relation();
                        relation.onParse(parser);
                        mRelations.add(relation);
                        break;

                    case OEBContract.Elements.RIGHTSES:
                        final Rights rights = new Rights();
                        rights.onParse(parser);
                        mRightses.add(rights);
                        break;

                    case OEBContract.Elements.SUBJECTS:
                        final Subject subject = new Subject();
                        subject.onParse(parser);
                        mSubjects.add(subject);
                        break;

                    case OEBContract.Elements.DATE:
                        mDate = new Date();
                        mDate.onParse(parser);
                        break;

                    case OEBContract.Elements.SOURCE:
                        mSource = new Source();
                        mSource.onParse(parser);
                        break;

                    case OEBContract.Elements.TYPE:
                        mType = new Type();
                        mType.onParse(parser);
                        break;

                    case OEBContract.Elements.META:
                        onSerializeMeta(parser);
                        break;

                    case OEBContract.Elements.LINK:
                        final Link link = new Link();
                        link.onParse(parser);
                        mLinks.add(link);
                        break;

                }
            } else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case OEBContract.Elements.METADATA:
                        return;
                }

            }
            eventType = parser.next();
        }
    }

    private void onParseIdentifier(XmlPullParser parser) throws XmlPullParserException, IOException {
        final Identifier identifier = new Identifier() {
            @Override
            protected void onParseAttributes(XmlPullParser parser)
                throws XmlPullParserException, IOException {
                super.onParseAttributes(parser);
                String scheme = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.SCHEME);
                if (!TextUtils.isEmpty(scheme)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.IDENTIFIER_TYPE.toString(), scheme);
                    mMetas.put(meta.getRefines(), meta);
                }
            }
        };
        identifier.onParse(parser);
        mIdentifiers.add(identifier);
    }

    private void onParseTitle(XmlPullParser parser) throws XmlPullParserException, IOException {
        final Title title = new Title() {
            @Override
            protected void onParseAttributes(XmlPullParser parser)
                throws XmlPullParserException, IOException {
                super.onParseAttributes(parser);
                String role = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.ROLE);
                if (!TextUtils.isEmpty(role)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.ROLE.toString(), role);
                    mMetas.put(meta.getRefines(), meta);
                }
            }
        };
        title.onParse(parser);
        mTitles.add(title);
    }

    private void onParseContributor(XmlPullParser parser) throws XmlPullParserException, IOException {
        final Contributor contributor = new Contributor() {
            @Override
            protected void onParseAttributes(XmlPullParser parser)
                throws XmlPullParserException, IOException {
                super.onParseAttributes(parser);
                String role = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.ROLE);
                if (!TextUtils.isEmpty(role)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.ROLE.toString(), role);
                    mMetas.put(meta.getRefines(), meta);
                }

                String fileAs = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.FILE_AS);
                if (!TextUtils.isEmpty(fileAs)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.FILE_AS.toString(), role);
                    mMetas.put(meta.getRefines(), meta);
                }
            }
        };
        contributor.onParse(parser);
        mContributors.add(contributor);
    }

    private void onParseCreator(XmlPullParser parser) throws XmlPullParserException, IOException {
        final Author author = new Author() {
            @Override
            protected void onParseAttributes(XmlPullParser parser)
                throws XmlPullParserException, IOException {
                super.onParseAttributes(parser);
                String role = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.ROLE);
                if (!TextUtils.isEmpty(role)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.ROLE.toString(), role);
                    mMetas.put(meta.getRefines(), meta);
                }

                String fileAs = parser.getAttributeValue(OEBContract.Namespaces.OPF, OEBContract.Attributes.FILE_AS);
                if (!TextUtils.isEmpty(fileAs)) {
                    if (TextUtils.isEmpty(mId)) {
                        mId = generateId();
                    }
                    final Meta meta = new Meta("#".concat(mId), Property.FILE_AS.toString(), role);
                    mMetas.put(meta.getRefines(), meta);
                }
            }
        };
        author.onParse(parser);
        mAuthors.add(author);
    }

    private void onSerializeMeta(XmlPullParser parser) throws XmlPullParserException, IOException {
        final Meta meta = new Meta() {
            @Override
            protected void onParseAttributes(XmlPullParser parser)
                throws XmlPullParserException, IOException {
                super.onParseAttributes(parser);
                final String name = parser.getAttributeValue("", OEBContract.Attributes.NAME);
                if (!TextUtils.isEmpty(name)) {
                    final Meta20 meta20 = new Meta20();
                    meta20.onParse(parser);
                    mMeta20s.add(meta20);
                }
            }
        };
        meta.onParse(parser);
        if (!TextUtils.isEmpty(meta.getRefines())) {
            mMetas.put(meta.getRefines(), meta);
        }
    }

    @Override
    protected void setPrefix(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.setPrefix(serializer);
        serializer.setPrefix(OEBContract.Namespaces.Prefix.DC, OEBContract.Namespaces.DC);
    }

    @Override
    protected void onSerializeContent(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException {
        super.onSerializeContent(serializer);
        serialize(serializer, mDate);
        serialize(serializer, mSource);
        serialize(serializer, mType);
        serializeCollection(serializer, mIdentifiers);
        serializeCollection(serializer, mTitles);
        serializeCollection(serializer, mLanguages);
        serializeCollection(serializer, mContributors);
        serializeCollection(serializer, mAuthors);
        serializeCollection(serializer, mCoverages);
        serializeCollection(serializer, mDescriptions);
        serializeCollection(serializer, mFormats);
        serializeCollection(serializer, mPublishers);
        serializeCollection(serializer, mRightses);
        serializeCollection(serializer, mSubjects);
        serializeCollection(serializer, mLinks);
        serializeCollection(serializer, mMetas.get(null));
        serializeCollection(serializer, mMeta20s);
    }

    @Override
    protected void serialize(XmlSerializer serializer, Serializerable serializerable)
        throws IOException, IllegalArgumentException, IllegalStateException {
        if (serializerable != null) {
            serializerable.onSerialize(serializer);
            String id = null;
            if (serializerable instanceof SimpleElement) {
                id = ((SimpleElement) serializerable).getId();
            }
            if (serializerable instanceof SimpleTextElement) {
                id = ((SimpleTextElement) serializerable).getId();
            }
            if (!TextUtils.isEmpty(id)) {
                for (Meta meta : mMetas.get("#".concat(id))) {
                    meta.onSerialize(serializer);
                }
            }
        }
    }
}
