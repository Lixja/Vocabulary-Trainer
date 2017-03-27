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
package de.slover.school.vocabulary.gui;

import de.slover.school.vocabulary.gui.cards.BrowserCard;
import de.slover.school.vocabulary.gui.cards.MenuCard;
import de.slover.school.vocabulary.gui.cards.TrainerCard;
import de.slover.school.vocabulary.gui.cards.TrainerSettingsCard;
import de.slover.school.vocabulary.handler.Handler;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Window {

    private JFrame frame;

    private final CardLayout clayout;
    private final JPanel card;
    private final TrainerCard tcard;
    private final TrainerSettingsCard tscard;
    private final BrowserCard bcard;
    private final MenuCard mcard;

    public final String MENUC = "menu";
    public final String TRAINERC = "trainer";
    public final String TSETTINGSC = "settings";
    public final String BROWSERC = "browser";

    private final Handler handler;

    public Window() {
        mcard = new MenuCard(this);
        tcard = new TrainerCard(this);
        tscard = new TrainerSettingsCard(this);
        bcard = new BrowserCard(this);
        clayout = new CardLayout();
        card = new JPanel();
        card.setLayout(clayout);
        card.add(mcard, MENUC);
        card.add(bcard, BROWSERC);
        card.add(tcard, TRAINERC);
        card.add(tscard, TSETTINGSC);
        handler = new Handler(this);
    }

    public void init() {
        frame = new JFrame("VOCABULARY-TRAINER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(225, 125);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(card);
        frame.setVisible(true);

        clayout.show(card, MENUC);
    }

    public void changeCard(String card) {
        switch (card) {
            case TSETTINGSC:
                frame.resize(225, 125);
                handler.loadGroup();
                tscard.setVocs(handler.getGroup().getVoc1(), handler.getGroup().getVoc2());
                break;
            case TRAINERC:
                frame.resize(400, 400);
                break;
            case BROWSERC:
                frame.resize(500, 500);
                break;
            case MENUC:
                frame.resize(225, 125);
                break;
            default:
                break;
        }

        clayout.show(this.card, card);

    }

    public Handler getHandler() {
        return handler;
    }

    public TrainerCard gettcard() {
        return tcard;
    }

}
