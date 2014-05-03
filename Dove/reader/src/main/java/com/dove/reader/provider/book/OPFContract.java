package com.dove.reader.provider.book;

/**
 * Created by george on 4/26/14.
 */
public class OPFContract {

    public interface AuthorColumns {
        public static final String DISPLAY_NAME = "display_name";
        public static final String RELATOR = "relator";
    }

    public interface SchemeTypes {
        public static final String UUID = "UUID";
        public static final String ISBN = "ISBN";
        public static final String URL = "URL";
        public static final String URI = "URI";
    }

    public interface IndentifierColumns {
        public static final String SCHEME = "scheme";
        public static final String VALUE = "value";
    }

    public interface ResourceColumns {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String HREF = "href";
        public static final String MEDIA_TYPE = "media-type";
        public static final String ENCODING = "encoding";
        public static final String DATA = "data";
        public static final String LINEAR = "linear";
        public static final String TYPE = "type";
        public static final String PROPERTY = "property";
        public static final String TEXT = "text";
        public static final String DIRECTORY = "directory";
        public static final String XML_LANG = "xml:lang";
        public static final String REFINES = "refines";
        public static final String CONTENT = "content";
        public static final String NAME = "name";
        public static final String REL = "rel";

    }

    public interface OPF extends AuthorColumns, IndentifierColumns, ResourceColumns {
        String DATE = "date";
        String FORMAT = "format";
        String SOURCE = "source";
        String CREATED = "created";
        String MODIFIED = "modified";
        String TITLES = "titles";
        String RIGHTS = "rights";
        String SUBJECTS = "subjects";
        String CREATORS = "creators";
        String RELATIONS = "relations";
        String COVERAGES = "coverages";
        String LANGUAGES = "languages";
        String PUBLISHERS = "publishers";
        String DESCRIPTION = "descriptions";
        String CONTRIBUTORS = "contributors";
        String IDENTIFIERS = "identifiers";
        String METAS = "metas";
        String META20s = "meta20s";
        String LINKS = "links";
        String FALLBACK = "fallback";
        String PROPERTIES = "properties";
        String MEDIA_OVERLAY = "media-overlay";
        String TOC = "toc" ;

        String ITEMS = "items";
        String DIRECTION = "direction";
    }
}
