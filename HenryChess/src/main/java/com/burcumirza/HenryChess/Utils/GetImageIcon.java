/*
 * Created on February 24, 2008, 10:58 PM by MHenry
 *
 */

package com.burcumirza.HenryChess.Utils;

import java.io.*;
import javax.swing.*;
import java.util.jar.*;

public class GetImageIcon {

    /**
     * @param fileName the full qualified name of the file from the root of your
     *                 application. use a "/" before the fileName, eg.
     *                 /images/warning.gif
     */
    public ImageIcon getImageIcon(String fileName, String jarName) {
        int c, i = 0;
        byte buffer[];
        JarFile jFile;
        JarEntry jEntry;
        InputStream in;
        ImageIcon mImage;

        try {
            // Create Jar-File object from JarFile
            jFile = new JarFile(jarName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "JarFile-Problem " + "\n" + ex);
            return null;
        }

        try {
            // create Jar-Entry object from File-Name in JarFile
            jEntry = jFile.getJarEntry(fileName);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "JarEntry-Problem " + "\n" + ex);
            return close(jFile);
        }

        try {
            // create InputStream from JarEntry
            in = jFile.getInputStream(jEntry);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "JarEntry-Problem " + "\n" + ex);
            return close(jFile);
        }

        try {
            // get uncompressed size of entry data in Jar-File and create byte-array with
            // this size
            buffer = new byte[(int) jEntry.getSize()];

            // write int-value 'c' (casted to byte) with read() and while-loop in byte-Array
            while ((c = in.read()) != -1) {
                buffer[i] = (byte) c;
                i++;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "in.read()-Problem " + "\n" + ex);
            return close(jFile);
        }

        try {
            // create ImageIcon with byte-Array 'buffer'
            mImage = new ImageIcon(buffer);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ImageIcon-Problem " + "\n" + ex);
            return close(jFile);
        }

        // return the created ImageIcon
        close(jFile);
        return mImage;
    }

    private ImageIcon close(JarFile jFile) {
        try {
            jFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
