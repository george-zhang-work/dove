package com.dove.lib.oeb;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by george on 5/10/14.
 */
public interface Serializerable {
    public void onSrerialize(OutputStream outputStream) throws IOException;

    public void onSerialize(XmlSerializer serializer) throws IOException;
}
