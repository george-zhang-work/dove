package com.dove.lib.oeb.opf;

import android.os.Parcel;

import com.dove.lib.oeb.Element;
import com.dove.lib.oeb.OEBContract;
import com.google.common.base.Objects;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

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
    private final Multimap<String, Meta20> mMeta20s;

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
        mMeta20s = LinkedHashMultimap.create();

    }

    public Metadata(Parcel in, ClassLoader loader) {
        super(in, loader);
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

        final int size = in.readInt();
        mMetas = LinkedHashMultimap.create();
        for (int i = 0; i < size; i++) {
            final String key = in.readString();
            final Meta meta = in.readParcelable(loader);
            mMetas.put(key, meta);
        }

        final int size2 = in.readInt();
        mMeta20s = LinkedHashMultimap.create();
        for (int i = 0; i < size2; i++) {
            final String key = in.readString();
            final Meta20 meta20 = in.readParcelable(loader);
            mMeta20s.put(key, meta20);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
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

        dest.writeInt(mMetas.size());
        final Iterator<Map.Entry<String, Meta>> it = mMetas.entries().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Meta> entry = it.next();
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }

        dest.writeInt(mMeta20s.size());
        final Iterator<Map.Entry<String, Meta20>> it2 = mMeta20s.entries().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, Meta> entry = it.next();
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    @Override
    protected String getElementName() {
        return OEBContract.Elements.METADATA;
    }

    @Override
    protected String getElementNamespace() {
        return OEBContract.Namespaces.DC;
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

    public static final ClassLoaderCreator<Metadata> CREATOR = new ClassLoaderCreator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel source, ClassLoader loader) {
            return new Metadata(source, loader);
        }

        @Override
        public Metadata createFromParcel(Parcel source) {
            return new Metadata(source, null);
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };

    @Override
    protected void onParseContent(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG) {
                switch (parser.getName()) {

                    case OEBContract.Elements.IDENTIFIER:
                        final Identifier identifier = new Identifier();
                        identifier.onParse(parser);
                        mIdentifiers.add(identifier);
                        break;

                    case OEBContract.Elements.TITLE:
                        final Title title = new Title();
                        title.onParse(parser);
                        mTitles.add(title);
                        break;

                    case OEBContract.Elements.LANGUAGES:
                        final Language language = new Language();
                        language.onParse(parser);
                        mLanguages.add(language);
                        break;

                    case OEBContract.Elements.CONTRIBUTOR:
                        final Contributor contributor = new Contributor();
                        contributor.onParse(parser);
                        mContributors.add(contributor);
                        break;

                    case OEBContract.Elements.CREATOR:
                        final Author author = new Author();
                        author.onParse(parser);
                        mAuthors.add(author);
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
                        final Meta meta = new Meta();
                        meta.onParse(parser);
                        mMetas.put(meta.getRefines(), meta);
                        break;

                    case OEBContract.Elements.LINK:
                        final Link link = new Link();
                        link.onParse(parser);
                        mLinks.add(link);
                        break;

                }
            }
        }
    }
}
