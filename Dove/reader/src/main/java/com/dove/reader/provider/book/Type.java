package com.dove.reader.provider.book;

/**
 * Created by george on 4/26/14.
 */
public enum Type {
    /**
     * the book cover(s), jacket information, etc.
     */
    COVER("cover"),
    /**
     * page with possibly title, author, publisher, and other metadata
     */
    TITLE_PAGE("title-page"),
    /**
     * table of contents
     */
    TOC("toc"),
    /**
     * back-of-book style index
     */
    INDEX("index"),
    COLOPHON("colophon"),
    COPYRIGHT_PAGE("copyright-page"),
    /**
     * list of illustrations
     */
    LOI("loi"),
    /**
     * list of tables
     */
    LOT("lot"),
    FOREWORD("foreword"),
    PREFACE("preface"),
    /**
     * First "real" page of content (e.g. "Chapter 1")
     */
    TEXT("text"),
    ACKNOWLEDGEMENTS("acknowledgements"),
    DEDICATION("dedication"),
    EPIGRAPH("epigraph"),
    NOTES("notes"),
    GLOSSARY("glossary"),
    BIBLIOGRAPHY("bibliography");

    private final String mValue;

    Type(String value) {
        mValue = value;
    }

    public static Type fromValue(String value) {
        for (Type type : values()) {
            if (type.mValue.equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
