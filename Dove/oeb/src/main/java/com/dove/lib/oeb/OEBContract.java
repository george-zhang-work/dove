package com.dove.lib.oeb;

/**
 * Created by george on 5/5/14.
 */
public final class OEBContract {

    public interface ElementsAndAttributes {
        public static final String TITLE = "title";
        public static final String MEDIA_TYPE = "media-type";
    }

    public interface Attributes extends ElementsAndAttributes {
        public static final String ID = "id";
        public static final String DIR = "dir";
        public static final String LANG = "lang";
        public static final String XML_LANG = "xml:lang";
        public static final String VERSION = "version";
        public static final String UNIQUE_IDENTIFIER = "unique-identifier";
        public static final String PREFIX = "prefix";
        public static final String HREF = "href";
        public static final String FALLBACK = "fallback";
        public static final String PROPERTY = "property";
        public static final String PROPERTIES = "properties";
        public static final String MEDIA_OVERLAY = "media-overlay";
        public static final String TOC = "toc";
        public static final String PAGE_PROGRESSION_DIRECTION = "page-progression-direction";
        public static final String LINEAR = "linear";
        public static final String IDREF = "idref";
        public static final String TYPE = "type";

        public static final String SITES = "sites";
        public static final String REFINES = "refines";
        public static final String SCHEME = "scheme";
        public static final String NAME = "name";
        public static final String CONTENT = "content";
        public static final String REL = "rel";
        public static final String HANDLER = "handler";
        public static final String SRC = "src";
        public static final String CLIP_BEGIN = "clipBegin";
        public static final String CLIP_END = "clipEnd";
        public static final String CLASS = "class";
        public static final String PLAY_ORDER = "playOrder";
        public static final String VALUE = "value";
    }

    public interface ElementType extends ElementsAndAttributes {
        public static final String ELEMENT = "element";
        public static final String SIMPLE_ELEMENT = "simple-element";
        public static final String TEXT_ELEMENT = "text-element";
        public static final String SIMPLE_TEXT_ELEMENT = "simple-text-element";
        public static final String COMPLEX_TEXT_ELEMENT = "complex-text-element";
    }

    public interface Elements extends ElementType {
        public static final String TEXT = "text";
        public static final String METADATA = "metadata";

        public static final String DATE = "date";
        public static final String TYPE = "type";
        public static final String SOURCE = "source";

        public static final String IDENTIFIER = "identifier";
        public static final String IDENTIFIERS = "identifiers";

        public static final String TITLES = "titles";

        public static final String LANGUAGE = "language";
        public static final String LANGUAGES = "languages";

        public static final String CREATOR = "creator";
        public static final String CREATORS = "creators";

        public static final String CONTRIBUTOR = "contributor";
        public static final String CONTRIBUTORS = "contributors";

        public static final String COVERAGE = "coverage";
        public static final String COVERAGES = "coverages";

        public static final String DESCRIPTION = "description";
        public static final String DESCRIPTIONS = "descriptions";

        public static final String PUBLISHER = "publisher";
        public static final String PUBLISHERS = "publishers";

        public static final String FORMAT = "format";
        public static final String FORMATS = "formats";

        public static final String RELATION = "relation";
        public static final String RELATIONS = "relations";

        public static final String RIGHTS = "rights";
        public static final String RIGHTSES = "rightses";

        public static final String SUBJECT = "subject";
        public static final String SUBJECTS = "subjects";

        public static final String RESOURCE = "resource";
        public static final String RESOURCES = "resources";

        public static final String ITEM = "item";
        public static final String ITEMS = "items";

        public static final String MANIFEST = "manifest";
        public static final String ITEM_REF = "itemref";
        public static final String ITEM_REFS = "itemrefs";
        public static final String SPINE = "spine";

        public static final String REFERENCE = "reference";
        public static final String REFERENCES = "references";
        public static final String GUIDE = "guide";
        public static final String SITE = "site";
        public static final String TOUR = "tour";
        public static final String TOURS = "tours";
        public static final String META = "meta";
        public static final String METAS = "metas";
        public static final String META20S = "meta20s";

        public static final String LINK = "link";
        public static final String LINKS = "links";
        public static final String MEDIA_TYPES = "media-types";
        public static final String BINDINGS = "bindings";
        public static final String PACKAGE = "package";
        public static final String HEAD = "head";
        public static final String AUDIO = "audio";
        public static final String IMAGE = "img";
        public static final String DOC_ELEMENT = "doc-element";
        public static final String DOC_TITLE = "docTitle";
        public static final String DOC_AUTHOR = "docAuthor";
        public static final String NAV_MAP = "navMap";
        public static final String NAV_INFO = "navInfo";
        public static final String NAV_INFOS = "navInfos";
        public static final String NAV_POINT = "navPoint";
        public static final String NAV_POINTS = "navPoints";
        public static final String NAV_LABEL = "navLabel";
        public static final String NAV_LABELS = "navLabels";
        public static final String NAV_TARGET = "navTarget";
        public static final String NAV_TARGETS = "navTargets";
        public static final String CONTENT = "content";
        public static final String PAGE_TARGET = "pageTarget";
        public static final String PAGE_LIST = "pageList";
        public static final String NAV_LIST = "navList";
        public static final String NCX = "ncx";
    }

    public interface Namespaces {
        public static final String ANY = null;
        public static final String XML = "http://www.w3.org/XML/1998/namespace";
        public static final String OPF = "http://www.idpf.org/2007/opf";
        public static final String DC = "http://purl.org/dc/elements/1.1/";
        public static final String NCX = "http://www.daisy.org/z3986/2005/ncx/";
    }
}
