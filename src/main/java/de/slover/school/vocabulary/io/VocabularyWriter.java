/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.slover.school.vocabulary.io;

import de.slover.school.vocabulary.data.Group;
import de.slover.school.vocabulary.handler.Handler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class VocabularyWriter {

    StringWriter sw;
    JAXBContext jaxbContext;
    Marshaller jaxbMarshaller;
    Writer out;

    public VocabularyWriter() {
        try {
            jaxbContext = JAXBContext.newInstance(Group.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(VocabularyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(Group g, File file) {
        try {
            sw = new StringWriter();
            file.createNewFile();
            jaxbMarshaller.marshal(g, sw);
            String xmlString = sw.toString();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile(), false), "UTF-8"));
            out.write(xmlString);
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VocabularyWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VocabularyWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VocabularyWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(VocabularyWriter.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public File createGroup() {
        try {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("VocabularyFile", "xml"));
            fc.setCurrentDirectory(new File(new File(".").getCanonicalPath()));
            fc.showSaveDialog(null);
            return fc.getSelectedFile();
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
