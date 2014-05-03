package com.dove.reader.provider.book;

import android.os.Parcel;

import com.dove.common.log.LogUtils;
import com.google.common.collect.Lists;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by george on 4/25/14.
 */
public class Metadata implements ParcelableElement {

    private TextElement mDate;
    private TextElement mType;
    private TextElement mFormat;
    private TextElement mSource;
    private TextElement mCreated;
    private TextElement mModified;

    private final List<SimpleTextElement> mTitles;
    private final List<SimpleTextElement> mRights;
    private final List<SimpleTextElement> mSubjects;
    private final List<SimpleTextElement> mCreators;
    private final List<SimpleTextElement> mRelations;
    private final List<SimpleTextElement> mCoverages;
    private final List<SimpleTextElement> mLanguages;
    private final List<SimpleTextElement> mPublishers;
    private final List<SimpleTextElement> mDescriptions;
    private final List<SimpleTextElement> mContributors;

    private final List<TextElement> mIdentifiers;

    private final List<Meta> mMetas;
    private final List<Meta20> mMeta20s;
    private final List<Link> mLinks;

    public Metadata() {
        mDate = null;
        mType = null;
        mFormat = null;
        mSource = null;
        mCreated = null;
        mModified = null;

        mTitles = Lists.newArrayList();
        mRights = Lists.newArrayList();
        mSubjects = Lists.newArrayList();
        mCreators = Lists.newArrayList();
        mRelations = Lists.newArrayList();
        mCoverages = Lists.newArrayList();
        mLanguages = Lists.newArrayList();
        mPublishers = Lists.newArrayList();
        mDescriptions = Lists.newArrayList();
        mContributors = Lists.newArrayList();
        mIdentifiers = Lists.newArrayList();
        mMetas = Lists.newArrayList();
        mMeta20s = Lists.newArrayList();
        mLinks = Lists.newArrayList();
    }

    public Metadata(Parcel in, ClassLoader loader) {
        mDate = in.readParcelable(loader);
        mType = in.readParcelable(loader);
        mFormat = in.readParcelable(loader);
        mSource = in.readParcelable(loader);
        mCreated = in.readParcelable(loader);
        mModified = in.readParcelable(loader);

        mTitles = in.readArrayList(loader);
        mRights = in.readArrayList(loader);
        mSubjects = in.readArrayList(loader);
        mCreators = in.readArrayList(loader);
        mRelations = in.readArrayList(loader);
        mCoverages = in.readArrayList(loader);
        mLanguages = in.readArrayList(loader);
        mPublishers = in.readArrayList(loader);
        mDescriptions = in.readArrayList(loader);
        mContributors = in.readArrayList(loader);
        mIdentifiers = in.readArrayList(loader);
        mMetas = in.readArrayList(loader);
        mMeta20s = in.readArrayList(loader);
        mLinks = in.readArrayList(loader);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mDate, flags);
        dest.writeParcelable(mType, flags);
        dest.writeParcelable(mFormat, flags);
        dest.writeParcelable(mSource, flags);
        dest.writeParcelable(mCreated, flags);
        dest.writeParcelable(mModified, flags);

        dest.writeList(mTitles);
        dest.writeList(mRights);
        dest.writeList(mSubjects);
        dest.writeList(mCreators);
        dest.writeList(mRelations);
        dest.writeList(mCoverages);
        dest.writeList(mLanguages);
        dest.writeList(mPublishers);
        dest.writeList(mDescriptions);
        dest.writeList(mContributors);
        dest.writeList(mIdentifiers);
        dest.writeList(mMetas);
        dest.writeList(mMeta20s);
        dest.writeList(mLanguages);
    }

    public TextElement getDate() {
        return mDate;
    }

    public void setDate(TextElement date) {
        mDate = date;
    }

    public TextElement getType() {
        return mType;
    }

    public void setType(TextElement type) {
        mType = type;
    }

    public TextElement getFormat() {
        return mFormat;
    }

    public void setFormat(TextElement format) {
        mFormat = format;
    }

    public TextElement getSource() {
        return mSource;
    }

    public void setSource(TextElement source) {
        mSource = source;
    }

    public TextElement getCreated() {
        return mCreated;
    }

    public void setCreated(TextElement created) {
        mCreated = created;
    }

    public TextElement getModified() {
        return mModified;
    }

    public void setModified(TextElement modified) {
        mModified = modified;
    }

    public List<SimpleTextElement> getTitles() {
        return mTitles;
    }

    public void addTitle(SimpleTextElement title) {
        mTitles.add(title);
    }

    public List<SimpleTextElement> getRights() {
        return mRights;
    }

    public void addRights(SimpleTextElement rights) {
        mRights.add(rights);
    }

    public List<SimpleTextElement> getSubjects() {
        return mSubjects;
    }

    public void addSubject(SimpleTextElement subject) {
        mSubjects.add(subject);
    }

    public List<SimpleTextElement> getCreators() {
        return mCreators;
    }

    public void addCreator(SimpleTextElement creator) {
        mCreators.add(creator);
    }

    public List<SimpleTextElement> getRelations() {
        return mRelations;
    }

    public void addRelation(SimpleTextElement relation) {
        mRelations.add(relation);
    }

    public List<SimpleTextElement> getCoverages() {
        return mCoverages;
    }

    public void addCoverage(SimpleTextElement coverage) {
        mCoverages.add(coverage);
    }

    public List<SimpleTextElement> getLanguages() {
        return mLanguages;
    }

    public void addLanguage(SimpleTextElement language) {
        mLanguages.add(language);
    }

    public List<SimpleTextElement> getPublishers() {
        return mPublishers;
    }

    public void addPublisher(SimpleTextElement publisher) {
        mPublishers.add(publisher);
    }

    public List<SimpleTextElement> getDescriptions() {
        return mDescriptions;
    }

    public void addDescription(SimpleTextElement description) {
        mDescriptions.add(description);
    }

    public List<SimpleTextElement> getContributors() {
        return mContributors;
    }

    public void addContributor(SimpleTextElement contributor) {
        mContributors.add(contributor);
    }

    public List<TextElement> getIdentifiers() {
        return mIdentifiers;
    }

    public void addIdentifier(TextElement identifier) {
        mIdentifiers.add(identifier);
    }

    public List<Meta> getMetas() {
        return mMetas;
    }

    public void addMeta(Meta meta) {
        mMetas.add(meta);
    }

    public List<Meta20> getMeta20s() {
        return mMeta20s;
    }

    public void addMeta20(Meta20 meta20) {
        mMeta20s.add(meta20);
    }

    public List<Link> getLinks() {
        return mLinks;
    }

    public void addLink(Link link) {
        mLinks.add(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject("Metadata")
                .put(OPFContract.OPF.DATE, mDate)
                .put(OPFContract.OPF.TYPE, mType)
                .put(OPFContract.OPF.FORMAT, mFormat)
                .put(OPFContract.OPF.SOURCE, mSource)
                .put(OPFContract.OPF.CREATED, mCreated)
                .put(OPFContract.OPF.MODIFIED, mModified)
                .put(OPFContract.OPF.TITLES, mTitles)
                .put(OPFContract.OPF.RIGHTS, mRights)
                .put(OPFContract.OPF.SUBJECTS, mSubjects)
                .put(OPFContract.OPF.CREATORS, mCreators)
                .put(OPFContract.OPF.RELATIONS, mRelations)
                .put(OPFContract.OPF.COVERAGES, mCoverages)
                .put(OPFContract.OPF.LANGUAGES, mLanguages)
                .put(OPFContract.OPF.PUBLISHERS, mPublishers)
                .put(OPFContract.OPF.DESCRIPTION, mDescriptions)
                .put(OPFContract.OPF.CONTRIBUTORS, mContributors)
                .put(OPFContract.OPF.IDENTIFIERS, mIdentifiers)
                .put(OPFContract.OPF.METAS, mMetas)
                .put(OPFContract.OPF.META20s, mMeta20s)
                .put(OPFContract.OPF.LINKS, mLinks)
                .toString();
        } catch (JSONException e) {
            LogUtils.wtf(LOG_TAG, "Could not serialize the MetaData ");
        }
        return "";
    }

    public static final ClassLoaderCreator<Metadata> CREATOR = new ClassLoaderCreator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel source, ClassLoader loader) {
            return new Metadata(source, loader);
        }

        @Override
        public Metadata createFromParcel(Parcel source) {
            return new Metadata(source, Metadata.class.getClassLoader());
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };
}
