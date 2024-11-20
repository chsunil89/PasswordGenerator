package org.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardUtil {

    // Method to retrieve text from the clipboard
    public static String getClipboardContent() {
        String clipboardText = "";
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            System.err.println("Error retrieving clipboard content: " + e.getMessage());
        }
        return clipboardText;
    }
}
