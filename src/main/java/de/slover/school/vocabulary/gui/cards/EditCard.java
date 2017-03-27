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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class EditCard extends JPanel implements ActionListener {

    JButton btnNew;
    JButton btnmod;
    JButton btndel;
    JButton btnsave;
    JButton btnback;

    BrowserCard card;

    public EditCard(BrowserCard card) {
        super();
        this.card = card;
        this.setLayout(new GridLayout(5, 1));
        btnNew = new JButton("NEW VOC");
        btnNew.addActionListener(this);
        this.add(btnNew);
        btnmod = new JButton("MODIFY");
        btnmod.addActionListener(this);
        this.add(btnmod);
        btndel = new JButton("DELETE");
        btndel.addActionListener(this);
        this.add(btndel);
        btnsave = new JButton("SAVE");
        btnsave.addActionListener(this);
        this.add(btnsave);
        btnback = new JButton("BACK");
        btnback.addActionListener(this);
        this.add(btnback);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNew) {
            card.changeCard(BrowserCard.NEWC);
        } else if (e.getSource() == btnmod) {
            card.changeCard(BrowserCard.MODIFYC);
        } else if (e.getSource() == btndel) {
            card.deleteSelected();
        } else if (e.getSource() == btnsave) {
            card.Save();
        } else if (e.getSource() == btnback) {
            card.getWindow().changeCard(card.getWindow().MENUC);

        }
    }

}
