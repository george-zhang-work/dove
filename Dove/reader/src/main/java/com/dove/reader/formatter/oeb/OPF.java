package com.dove.reader.formatter.oeb;

/**
 * Created by george on 4/29/14.
 */
public class OPF {
    interface DCTags {
        String title = "title";
        String creator = "creator";
        String subject = "subject";
        String description = "description";
        String publisher = "publisher";
        String contributor = "contributor";
        String date = "date";
        String type = "type";
        String format = "format";
        String identifier = "identifier";
        String source = "source";
        String language = "language";
        String relation = "relation";
        String coverage = "coverage";
        String rights = "rights";
    }

    interface DCAttrs {
        String id = "id";
        String scheme = "scheme";
    }

    interface OPFTags {
        String metadata = "metadata";
        String meta = "meta";
        String manifest = "manifest";
        String packageTag = "package";
        String itemref = "itemref";
        String spine = "spine";
        String reference = "reference";
        String guide = "guide";
        String item = "item";
    }

    interface OPFAttrs {
        String uniqueIdentifier = "unique-identifier";
        String idref = "idref";
        String name = "name";
        String content = "content";
        String type = "type";
        String href = "href";
        String linear = "linear";
        String event = "event";
        String role = "role";
        String file_as = "file-as";
        String id = "id";
        String media_type = "media-type";
        String title = "title";
        String toc = "toc";
        String version = "version";
        String scheme = "scheme";
        String property = "property";
    }

    interface OPFValues {
        String meta_cover = "cover";
        String reference_cover = "cover";
        String no = "no";
        String generator = "generator";
    }

    interface Tags extends DCTags, OPFTags {
    }

    interface Attrs extends DCAttrs, OPFAttrs {
    }

}
