package com.dove.lib.oeb;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;

public interface Serializerable {
    public void onSrerialize(OutputStream outputStream)
        throws IOException, IllegalArgumentException, IllegalStateException;

    public void onSerialize(XmlSerializer serializer)
        throws IOException, IllegalArgumentException, IllegalStateException;
}
