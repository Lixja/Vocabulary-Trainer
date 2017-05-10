/*
 * Copyright (C) 2017 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
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

import de.slover.school.vocabulary.data.Voc;
import de.slover.school.vocabulary.gui.Window;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class TeacherCard extends JPanel implements ActionListener, KeyListener {

    private Window window;

    private JLabel lpos;
    private JLabel lvoc1, lvoc2;
    private JButton btnl, btnr, btnback;
    private JSplitPane splitBase, splitL, splitR, splitT, splitA;

    public TeacherCard(Window window) {
        super();
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));
        lvoc1 = new JLabel("VOC1");
        lvoc2 = new JLabel("VOC2");
        btnl = new JButton("<");
        btnl.addActionListener(this);
        btnr = new JButton(">");
        btnr.addActionListener(this);
        splitL = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitL.setResizeWeight(0.5);
        splitL.setLeftComponent(btnl);
        splitL.setRightComponent(lvoc1);
        splitR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitR.setResizeWeight(0.5);
        splitR.setLeftComponent(lvoc2);
        splitR.setRightComponent(btnr);
        lpos = new JLabel("0/0", JLabel.CENTER);
        btnback = new JButton("Back");
        btnback.addActionListener(this);
        splitT = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitT.setResizeWeight(0.5);
        splitT.setLeftComponent(splitL);
        splitT.setRightComponent(splitR);
        splitA = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitA.setResizeWeight(0.5);
        splitA.setLeftComponent(lpos);
        splitA.setRightComponent(btnback);

        splitBase = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitBase.setResizeWeight(0.5);
        splitBase.setLeftComponent(splitT);
        splitBase.setRightComponent(splitA);
        this.add(splitBase);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnl) {
            Voc voc = window.getHandler().getPrevious();
            setVoc(voc);
            lpos.setText(window.getHandler().getPosition() + "/" + window.getHandler().getMax());
        } else if (ae.getSource() == btnr) {
            Voc voc = window.getHandler().getNext();
            setVoc(voc);
            lpos.setText(window.getHandler().getPosition() + "/" + window.getHandler().getMax());
        }else if(ae.getSource() == btnback){
            window.changeCard(window.MENUC);
        }
    }

    private void setVoc(Voc voc) {
        lvoc1.setText(voc.getVoc1());
        lvoc2.setText(voc.getVoc2());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
