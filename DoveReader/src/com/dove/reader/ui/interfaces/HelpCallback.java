
package com.dove.reader.ui.interfaces;

/**
 * Every menu action needs to have a help string attached to it. This interface
 * specifies a help method that provides that help string.
 */
public interface HelpCallback {
    /**
     * Get the contextual help parameter for this action.
     * 
     * @return The help contextual.
     */
    String getHelpContext();
}
