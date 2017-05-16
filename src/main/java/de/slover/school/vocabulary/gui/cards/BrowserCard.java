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

import de.slover.school.vocabulary.data.Group;
import de.slover.school.vocabulary.data.Voc;
import de.slover.school.vocabulary.gui.Window;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class BrowserCard extends JPanel implements ActionListener, FocusListener, ListSelectionListener {

    private JLabel labelname;
    private JTextField gname;
    private JTextField ivoc1;
    private JTextField ivoc2;
    private JList voc1;
    private JList voc2;
    private JPanel card;
    private CardLayout clayout;
    private JSplitPane splitPaneBase;
    private JSplitPane splitPaneLeft;
    private JSplitPane splitPaneLeftNorth;
    private JSplitPane splitPaneLeftNorth1;
    private JSplitPane splitPaneLeftNorth2;
    private JSplitPane splitPaneLeftSouth;
    private JScrollPane sc1;
    private JScrollPane sc2;
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmOpenFile;
    private String[] ghostText;

    private DefaultListModel<String> modelvoc1 = new DefaultListModel<String>();
    private DefaultListModel<String> modelvoc2 = new DefaultListModel<String>();

    EditCard ecard;
    ModifyCard mcard;
    NewCard ncard;

    public static final String EDITC = "edit";
    public static final String MODIFYC = "modify";
    public static final String NEWC = "new";

    private Window window;

    public BrowserCard(Window window) {
        super();
        this.ghostText = new String[]{"VOC1", "VOC2"};
        this.window = window;
        ecard = new EditCard(this);
        mcard = new ModifyCard(this);
        ncard = new NewCard(this);
        this.setLayout(new BorderLayout(0, 0));

        splitPaneBase = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneBase.setResizeWeight(0.5);
        this.add(splitPaneBase, BorderLayout.CENTER);

        splitPaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeft.setResizeWeight(0.1);
        splitPaneBase.setLeftComponent(splitPaneLeft);

        splitPaneLeftNorth = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeftNorth.setResizeWeight(0.5);
        splitPaneLeft.setLeftComponent(splitPaneLeftNorth);

        splitPaneLeftNorth1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneLeftNorth1.setResizeWeight(0.5);
        splitPaneLeftNorth.setLeftComponent(splitPaneLeftNorth1);

        splitPaneLeftNorth2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneLeftNorth2.setResizeWeight(0.5);
        splitPaneLeftNorth.setRightComponent(splitPaneLeftNorth2);

        splitPaneLeftSouth = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneLeftSouth.setResizeWeight(0.5);
        splitPaneLeft.setRightComponent(splitPaneLeftSouth);

        labelname = new JLabel("Group-Name:");
        splitPaneLeftNorth1.setLeftComponent(labelname);

        gname = new JTextField("");
        splitPaneLeftNorth1.setRightComponent(gname);

        ivoc1 = new JTextField(ghostText[0]);
        ivoc1.addFocusListener(this);
        ivoc1.setForeground(Color.GRAY);
        splitPaneLeftNorth2.setLeftComponent(ivoc1);

        ivoc2 = new JTextField(ghostText[1]);
        ivoc2.addFocusListener(this);
        ivoc2.setForeground(Color.GRAY);
        splitPaneLeftNorth2.setRightComponent(ivoc2);

        voc1 = new JList<String>(modelvoc1);
        voc1.addListSelectionListener(this);
        sc1 = new JScrollPane();
        sc1.setViewportView(voc1);
        splitPaneLeftSouth.setLeftComponent(sc1);

        voc2 = new JList<String>(modelvoc2);
        voc2.addListSelectionListener(this);
        sc2 = new JScrollPane();
        sc2.setViewportView(voc2);
        splitPaneLeftSouth.setRightComponent(sc2);

        menuBar = new JMenuBar();
        mnFile = new JMenu("File");
        mntmOpenFile = new JMenuItem("Load");
        mntmOpenFile.addActionListener(this);
        mnFile.add(mntmOpenFile);
        menuBar.add(mnFile);
        this.add(menuBar, BorderLayout.NORTH);

        clayout = new CardLayout();
        card = new JPanel();
        card.setLayout(clayout);
        card.add(ecard, EDITC);
        card.add(mcard, MODIFYC);
        card.add(ncard, NEWC);
        clayout.show(card, EDITC);
        splitPaneBase.setRightComponent(card);

    }

    public void changeCard(String card) {
        if (card.equals(MODIFYC)) {
            try {
                mcard.i1.setText(modelvoc1.get(voc1.getSelectedIndex()));
                mcard.i2.setText(modelvoc2.get(voc1.getSelectedIndex()));
            } catch (Exception e) {

            }
        }
        clayout.show(this.card, card);
    }

    public Window getWindow() {
        return window;
    }

    public void addVoc(String voc1, String voc2) {
        for (int i = 0; i < modelvoc1.getSize(); i++) {
            if (modelvoc1.get(i).equals(voc1)) {
                for (int i2 = 0; i2 < modelvoc2.getSize(); i2++) {
                    if (modelvoc2.get(i2).equals(voc2)) {
                        JOptionPane.showMessageDialog(null, "Already added!", "VOC-INFO", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                }
            }
        }
        this.modelvoc1.addElement(voc1);
        this.modelvoc2.addElement(voc2);
    }

    public void deleteSelected() {
        this.modelvoc2.remove(this.voc1.getSelectedIndex());
        this.modelvoc1.remove(this.voc1.getSelectedIndex());
    }

    public void modifySelected(String voc1, String voc2) {
        this.modelvoc2.set(this.voc1.getSelectedIndex(), voc2);
        this.modelvoc1.set(this.voc1.getSelectedIndex(), voc1);

    }

    public void Save() {
        Group group = new Group();
        group.setName(this.gname.getText());
        group.setVoc1(ivoc1.getText());
        group.setVoc2(ivoc2.getText());
        List<Voc> vocs = new LinkedList<>();
        for (int i = 0; i < this.modelvoc1.size(); i++) {
            Voc tmp = new Voc();
            tmp.setVoc1(modelvoc1.get(i));
            tmp.setVoc2(modelvoc2.get(i));
            vocs.add(tmp);
        }
        group.setVocabulary(vocs);
        window.getHandler().saveGroup(group);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmOpenFile) {
            Group g = window.getHandler().loadGroup();
            if (g == null) {
                return;
            }
            gname.setText(g.getName());
            ivoc1.setText(g.getVoc1());
            ivoc1.setForeground(Color.BLACK);
            ivoc2.setText(g.getVoc2());
            ivoc2.setForeground(Color.BLACK);
            modelvoc1.clear();
            modelvoc2.clear();
            for (int i = 0; i < g.getVocabulary().size(); i++) {
                this.modelvoc1.addElement(g.getVocabulary().get(i).getVoc1());
                this.modelvoc2.addElement(g.getVocabulary().get(i).getVoc2());
            }
        }

    }

    @Override
    public void focusLost(FocusEvent arg0) {
        if (arg0.getSource() == ivoc1) {
            if (ivoc1.getText().equals(ghostText[0]) || ivoc2.getText().equals("")) {
                ivoc1.setText(ghostText[0]);
                ivoc1.setForeground(Color.GRAY);
            }
        }
        if (arg0.getSource() == ivoc2) {
            if (ivoc2.getText().equals(ghostText[1]) || ivoc2.getText().equals("")) {
                ivoc2.setText(ghostText[1]);
                ivoc2.setForeground(Color.GRAY);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent arg0) {
        if (arg0.getSource() == ivoc1) {
            if (ivoc1.getText().equals(ghostText[0])) {
                ivoc1.setText("");
                ivoc1.setForeground(Color.BLACK);
            }
        }
        if (arg0.getSource() == ivoc2) {
            if (ivoc2.getText().equals(ghostText[1])) {
                ivoc2.setText("");
                ivoc2.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == voc1) {
            voc2.scrollRectToVisible(voc1.getVisibleRect());
            voc2.setSelectedIndex(voc1.getSelectedIndex());
        } else if (e.getSource() == voc2) {
            voc1.scrollRectToVisible(voc2.getVisibleRect());
            voc1.setSelectedIndex(voc2.getSelectedIndex());
        }

    }

    public boolean windowClosing() {
        if (!ecard.isSaved()) {
            int decision = JOptionPane.showConfirmDialog(null, "You did not save the vocabularyfile.\n Do you wanna save it before leaving?", "SAVE?", JOptionPane.YES_NO_CANCEL_OPTION);
            if (decision == JOptionPane.YES_OPTION) {
                Save();
            }else if(decision == JOptionPane.CANCEL_OPTION){
                return false;
            }
        }
        return true;
    }

}
