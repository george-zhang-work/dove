package com.dove.lib.oeb.opf;

/**
 * Created by george on 5/1/14.
 */
public enum Property {
    EMPTY(""),
    /*--------------------------------------------------------
     * Spine itemref Properties
     *--------------------------------------------------------*/
    PAGE_SPREAD_LEFT("page-spread-left"),
    PAGE_SPREAD_RIGHT("page-spread-right"),

    /*--------------------------------------------------------
     * Metadata link Properties
     *--------------------------------------------------------*/
    ALTERNATE_SCRIPT("alternate-script"),
    DISPLAY_SEQ("display-seq"),
    FILE_AS("file-as"),
    GROUP_POSITION("group-position"),
    IDENTIFIER_TYPE("identifier-type"),
    META_AUTH("meta-auth"),
    ROLE("role"),
    TITLE_TYPE("title-type"),

    /*--------------------------------------------------------
    * Metadata link Properties
    *--------------------------------------------------------*/
    MARC21XML_RECORD("marc21xml-record"),
    MODS_RECORD("mods-record"),
    ONIX_RECORD("onix-record"),
    XML_SIGNATURE("xml-signature"),
    XMP_RECORD("xmp-record"),
    MODIFIED("dcterms:modified"),

    /*--------------------------------------------------------
     * Manifest item Properties
     *--------------------------------------------------------*/
    COVER_IMAGE("cover-image"),
    MATHML("mathml"),
    NAV("nav"),
    REMOTE_RESOURCES("remote-resources"),
    SCRIPTED("scripted"),
    SVG("svg"),
    SWITCH("switch");

    private final String mValue;

    Property(String value) {
        mValue = value;
    }

    public static Property fromValue(String value) {
        for (Property property : values()) {
            if (property.mValue.equalsIgnoreCase(value)) {
                return property;
            }
        }
        return EMPTY;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
