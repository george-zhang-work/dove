package com.dove.reader.formatter.oeb;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by george on 4/28/14.
 */
public class NCX {
    public static final String NAMESPACE = "http://www.daisy.org/z3986/2005/ncx/";

    interface Tags {
        String ncx = "ncx";
        String meta = "meta";
        String navPoint = "navPoint";
        String navMap = "navMap";
        String navLabel = "navLabel";
        String content = "content";
        String text = "text";
        String docTitle = "docTitle";
        String docAuthor = "docAuthor";
        String head = "head";
    }

    interface Attrs {
        String src = "src";
        String name = "name";
        String content = "content";
        String id = "id";
        String playOrder = "playOrder";
        String clazz = "class";
        String version = "version";
    }

    public static class NavPoint {
        final int playOrder;
        String text;
        String src;

        public NavPoint(int playOrder) {
            this.playOrder = playOrder;
        }

        public String getNavLabelText() {
            return text;
        }

        public String getContentSrc() {
            return src;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof NavPoint)) {
                return false;
            }
            final NavPoint navPoint = (NavPoint) o;
            if (playOrder != navPoint.playOrder) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return playOrder;
        }

        @Override
        public String toString() {
            return "NavPoint{" +
                "playOrder=" + playOrder +
                ", text='" + text + '\'' +
                ", src='" + src + '\'' +
                '}';
        }
    }

    public static class Entity {
        final NavPoint navPoint;
        final List<Entity> children;

        Entity(NavPoint navPoint) {
            this.navPoint = navPoint;
            children = Lists.newArrayList();
        }

        void add(Entity entity) {
            children.add(entity);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Entity)) {
                return false;
            }
            final Entity entity = (Entity) o;
            if (!Objects.equal(navPoint, entity.navPoint)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(navPoint.hashCode());
        }
    }
}
