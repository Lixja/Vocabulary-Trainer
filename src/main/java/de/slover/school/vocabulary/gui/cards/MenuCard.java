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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MenuCard extends JPanel implements ActionListener {

    JButton CreateVocabulary;
    JButton Teacher;
    JButton Trainer;

    JSplitPane splitPaneBase, splitPaneT;

    Window window;

    public MenuCard(Window window) {
        this.window = window;

        this.setLayout(new BorderLayout(0, 0));
        CreateVocabulary = new JButton("Browse Vocabulary");
        CreateVocabulary.addActionListener(this);
        Teacher = new JButton("Learn Vocabulary");
        Teacher.addActionListener(this);
        Trainer = new JButton("Train Vocabulary");
        Trainer.addActionListener(this);
        splitPaneBase = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneBase.setResizeWeight(0.5);
        splitPaneBase.setLeftComponent(CreateVocabulary);
        splitPaneT = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneT.setResizeWeight(0.5);
        splitPaneBase.setRightComponent(splitPaneT);
        splitPaneT.setLeftComponent(Teacher);
        splitPaneT.setRightComponent(Trainer);
        this.add(splitPaneBase, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CreateVocabulary) {
            window.changeCard(window.BROWSERC);
        } else if(e.getSource() == Teacher){
            window.changeCard(window.LEARNC);
        }else if (e.getSource() == Trainer) {
            window.changeCard(window.TSETTINGSC);
        }

    }

}
