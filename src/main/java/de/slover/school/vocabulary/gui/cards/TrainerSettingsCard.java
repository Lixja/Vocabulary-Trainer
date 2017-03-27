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
package de.slover.school.vocabulary.gui.cards;

import de.slover.school.vocabulary.gui.Window;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class TrainerSettingsCard extends JPanel implements ActionListener {

    JButton FromToTo;
    JButton ToToFrom;
    JButton Back;
    Window window;

    public TrainerSettingsCard(Window window) {
        this.window = window;

        this.setLayout(new GridLayout(3, 1));
        FromToTo = new JButton("VOC1 -> VOC2");
        FromToTo.addActionListener(this);
        this.add(FromToTo);
        ToToFrom = new JButton("VOC2 -> VOC1");
        ToToFrom.addActionListener(this);
        this.add(ToToFrom);
        Back = new JButton("BACK");
        Back.addActionListener(this);
        this.add(Back);

    }

    public void setVocs(String voc1, String voc2) {
        FromToTo.setText(voc1 + " -> " + voc2);
        ToToFrom.setText(voc2 + " -> " + voc1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            window.changeCard(window.MENUC);
        } else if (e.getSource() == FromToTo) {
            window.changeCard(window.TRAINERC);
            window.getHandler().initializeTrainer(true);
        } else if (e.getSource() == ToToFrom) {
            window.changeCard(window.TRAINERC);
            window.getHandler().initializeTrainer(false);
        }
    }
}
