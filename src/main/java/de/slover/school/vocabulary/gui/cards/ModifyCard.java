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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class ModifyCard extends JPanel implements ActionListener, KeyListener {

    JLabel labelv1;
    JLabel labelv2;
    JTextField i1;
    JTextField i2;
    JButton btnsave;
    JButton btnback;
    BrowserCard card;

    public ModifyCard(BrowserCard card) {
        super();
        this.card = card;
        this.setLayout(new GridLayout(3, 2));
        labelv1 = new JLabel("VOC1:");
        labelv2 = new JLabel("VOC2:");
        i1 = new JTextField();
        i2 = new JTextField();
        i2.addKeyListener(this);
        btnsave = new JButton("SAVE");
        btnsave.addActionListener(this);
        btnback = new JButton("BACK");
        btnback.addActionListener(this);

        this.add(labelv1);
        this.add(i1);
        this.add(labelv2);
        this.add(i2);
        this.add(btnsave);
        this.add(btnback);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnsave) {
            save();
        } else if (e.getSource() == btnback) {
            card.changeCard(BrowserCard.EDITC);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            save();
        }
    }

    public void save() {
        if (!(i1.getText().isEmpty() || i2.getText().isEmpty())) {
            card.modifySelected(i1.getText(), i2.getText());
            i1.setText("");
            i2.setText("");
            i1.requestFocus();
        }
    }
}
