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
package de.slover.school.vocabulary.data;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
@XmlRootElement
public class Group {

    private String name;
    private String voc1;
    private String voc2;
    private List<Voc> vocabulary;

    public Group() {
        vocabulary = new LinkedList<>();
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getVoc1() {
        return voc1;
    }

    public void setVoc1(String voc1) {
        this.voc1 = voc1;
    }

    @XmlElement
    public String getVoc2() {
        return voc2;
    }

    public void setVoc2(String voc2) {
        this.voc2 = voc2;
    }

    @XmlElement
    public List<Voc> getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(List<Voc> vocabulary) {
        this.vocabulary = vocabulary;
    }

    public LinkedList<String> getAnswers(String voc, boolean fromtoto) {
        LinkedList<String> res = new LinkedList<>();
        for (Voc v : vocabulary) {
            if (fromtoto) {
                if (v.getVoc1().equals(voc)) {
                    res.add(v.getVoc2());
                }
            } else {
                if (v.getVoc2().equals(voc)) {
                    res.add(v.getVoc1());
                }
            }
        }

        return res;
    }

}
