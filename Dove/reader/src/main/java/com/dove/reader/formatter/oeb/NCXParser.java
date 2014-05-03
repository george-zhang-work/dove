package com.dove.reader.formatter.oeb;

import android.util.Xml;

import com.dove.common.log.LogUtils;
import com.dove.reader.formatter.XMLParser;
import com.dove.reader.formatter.oeb.NCX.Attrs;
import com.dove.reader.formatter.oeb.NCX.Entity;
import com.dove.reader.formatter.oeb.NCX.NavPoint;
import com.dove.reader.formatter.oeb.NCX.Tags;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class NCXParser extends XMLParser {

    private final Entity mNavMap = new Entity(new NavPoint(0));

    @Override
    public void parse(final InputStream inputStream) throws XmlPullParserException, IOException {
        final XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, getInputEncoding());

        final Stack<Entity> entityStack = new Stack<>();
        final Stack<String> tagStack = new Stack<>();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    onStartTag(parser, entityStack, tagStack);
                    break;

                case XmlPullParser.END_TAG:
                    onEndTag(parser, entityStack, tagStack);
                    break;
            }
            eventType = parser.next();
        }
    }

    private void onStartTag(XmlPullParser parser, Stack<Entity> entityStack, Stack<String> tagStack)
        throws IOException, XmlPullParserException {

        final String tagName = parser.getName();
        final String preTagName = tagStack.isEmpty() ? "" : tagStack.peek();
        final Entity entity = entityStack.isEmpty() ? null : entityStack.peek();
        boolean needPushTag = false;
        switch (tagName) {
            case Tags.navMap:
                // Put the root entry into the tree.
                entityStack.push(mNavMap);
                needPushTag = true;
                break;

            case Tags.navPoint:
                needPushTag = true;
                final int playOrder = Integer.parseInt(parser.getAttributeValue("", Attrs.playOrder));
                final Entity curEntity = new Entity(new NavPoint(playOrder));
                entity.add(curEntity);
                entityStack.push(curEntity);
                break;

            case Tags.navLabel:
                if (Tags.navPoint.equals(preTagName)) {
                    needPushTag = true;
                }
                break;

            case Tags.text:
                if (Tags.navLabel.equals(preTagName)) {
                    entity.navPoint.text = parser.nextText();
                    // swallow the parent's navLabel.
                    tagStack.pop();
                }
                break;

            case Tags.content:
                if (Tags.navPoint.equals(preTagName)) {
                    final String src = parser.getAttributeValue("", Attrs.src);
                    entity.navPoint.src = src;
                    break;
                }
        }
        if (needPushTag) {
            tagStack.push(tagName);
        }
    }

    private void onEndTag(XmlPullParser parser, Stack<Entity> entityStack, Stack<String> tagStack)
        throws XmlPullParserException {
        final String tagName = parser.getName();
        final String preTagName = tagStack.isEmpty() ? "" : tagStack.peek();
        boolean needPopTag = false;
        switch (tagName) {
            case Tags.navMap:
                needPopTag = true;
                entityStack.pop();
                break;

            case Tags.navPoint:
                if (Tags.navPoint.equals(preTagName)) {
                    needPopTag = true;
                    entityStack.pop();
                }
                break;
        }
        if (needPopTag && !tagStack.isEmpty()) {
            tagStack.pop();
        }
    }

    public void showTocTree() {
        final Stack<Entity> stack = new Stack<>();
        final Set<Entity> set = new HashSet<>();
        stack.push(mNavMap);
        while (!stack.isEmpty()) {
            final Entity entity = stack.peek();
            for (final Entity child : entity.children.toArray(new Entity[0])) {
                if (!set.contains(child)) {
                    LogUtils.i(LOG_TAG, child.navPoint.toString());
                    stack.push(child);
                    set.add(child);
                    break;
                }
            }
            if (entity.equals(stack.peek())) {
                stack.pop();
            }
        }
    }
}
