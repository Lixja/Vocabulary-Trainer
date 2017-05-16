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
import de.slover.school.vocabulary.gui.cards.TeacherCard;
import de.slover.school.vocabulary.gui.cards.TrainerCard;
import de.slover.school.vocabulary.gui.cards.TrainerSettingsCard;
import de.slover.school.vocabulary.handler.Handler;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Window extends WindowAdapter{

    private JFrame frame;

    private final CardLayout clayout;
    private final JPanel card;
    private final TrainerCard tcard;
    private final TrainerSettingsCard tscard;
    private final TeacherCard lcard;
    private final BrowserCard bcard;
    private final MenuCard mcard;

    public final String MENUC = "menu";
    public final String TRAINERC = "trainer";
    public final String TSETTINGSC = "settings";
    public final String LEARNC = "learn";
    public final String BROWSERC = "browser";

    public String currentCard = MENUC;
    
    private final Handler handler;

    public Window() {
        mcard = new MenuCard(this);
        tcard = new TrainerCard(this);
        tscard = new TrainerSettingsCard(this);
        bcard = new BrowserCard(this);
        lcard = new TeacherCard(this);
        clayout = new CardLayout();
        card = new JPanel();
        card.setLayout(clayout);
        card.add(mcard, MENUC);
        card.add(bcard, BROWSERC);
        card.add(tcard, TRAINERC);
        card.add(lcard, LEARNC);
        card.add(tscard, TSETTINGSC);
        handler = new Handler(this);
    }

    public void init() {
        this.setLookAndFeel();
        frame = new JFrame("VOCABULARY-TRAINER");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(225, 125);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(this);
        frame.getContentPane().add(card);
        frame.setVisible(true);

        clayout.show(card, MENUC);
    }

    public void changeCard(String card) {
        switch (card) {
            case TSETTINGSC:
                handler.loadGroup();
                if(handler.getGroup() == null){
                    return;
                }
                frame.resize(225, 125);
                tscard.setVocs(handler.getGroup().getVoc1(), handler.getGroup().getVoc2());
                break;
            case TRAINERC:
                frame.resize(400, 400);
                break;
            case LEARNC:
                handler.loadGroup();
                if(handler.getGroup() == null){
                    return;
                }
                frame.resize(400, 125);
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
        currentCard = card;
        clayout.show(this.card, card);

    }
    
    private void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void windowClosing(WindowEvent e){
        boolean shutdown = false;
        if(currentCard == BROWSERC){
            shutdown = bcard.windowClosing();
        }else if(currentCard == TRAINERC){
            shutdown = tcard.windowClosing();
        }else{
            System.exit(0);
        }
        if(shutdown){
            System.exit(0);
        }
    }
    
    public Handler getHandler() {
        return handler;
    }

    public TrainerCard gettcard() {
        return tcard;
    }

}
