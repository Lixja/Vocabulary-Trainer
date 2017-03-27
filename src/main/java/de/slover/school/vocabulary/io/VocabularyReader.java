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
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class VocabularyReader {

    JAXBContext jaxbContext;
    Unmarshaller jaxbUnmarshaller;

    public VocabularyReader() {
        try {
            jaxbContext = JAXBContext.newInstance(Group.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(VocabularyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Group read(File f) {
        try {
            Group g = (Group) jaxbUnmarshaller.unmarshal(f);
            return g;
        } catch (JAXBException ex) {
            Logger.getLogger(VocabularyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Group chooseGroup() {
        try {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("VocabularyFile", "xml"));
            fc.setCurrentDirectory(new File(new File(".").getCanonicalPath()));
            int state = fc.showOpenDialog(null);
            if (state == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Group group = read(file);
                return group;
            } else {
                System.out.println("Something went wrong while reading the file.");
            }
        } catch (IOException ex) {
            Logger.getLogger(VocabularyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
