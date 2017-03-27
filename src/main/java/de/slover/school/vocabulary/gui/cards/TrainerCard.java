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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class TrainerCard extends JPanel implements ActionListener, KeyListener {

    JLabel status;
    JLabel labelFromTo;
    JLabel labelQuestion;
    JList falseAnswersList;
    JTextField input;
    JButton submit;
    JButton btnSaveF;
    JButton btnBack;

    JSplitPane splitPaneBase;
    JSplitPane splitPaneLeft;
    JSplitPane splitPaneRight;
    JSplitPane splitPaneLeftNorth;
    JSplitPane splitPaneLeftNorth1;
    JSplitPane splitPaneLeftSouth;
    JSplitPane splitPaneRightSouth;

    private DefaultListModel<String> modelFalseAnswers = new DefaultListModel<String>();
    Window window;

    public TrainerCard(Window window) {
        super();
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));

        splitPaneBase = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneBase.setResizeWeight(0.5);
        this.add(splitPaneBase, BorderLayout.CENTER);

        splitPaneRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneRight.setResizeWeight(0.9);
        splitPaneBase.setRightComponent(splitPaneRight);

        splitPaneLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeft.setResizeWeight(0.8);
        splitPaneBase.setLeftComponent(splitPaneLeft);

        splitPaneLeftNorth = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeftNorth.setResizeWeight(0.5);
        splitPaneLeft.setLeftComponent(splitPaneLeftNorth);

        splitPaneLeftNorth1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeftNorth1.setResizeWeight(0.5);
        splitPaneLeftNorth.setLeftComponent(splitPaneLeftNorth1);

        splitPaneLeftSouth = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneLeftSouth.setResizeWeight(0.5);
        splitPaneLeft.setRightComponent(splitPaneLeftSouth);

        falseAnswersList = new JList<String>(modelFalseAnswers);
        splitPaneRight.setLeftComponent(falseAnswersList);

        status = new JLabel("Correct: 0, False: 0");
        splitPaneLeftNorth1.setLeftComponent(status);

        labelFromTo = new JLabel("Voc1 -> Voc2");
        splitPaneLeftNorth1.setRightComponent(labelFromTo);

        labelQuestion = new JLabel("");
        splitPaneLeftNorth.setRightComponent(labelQuestion);

        splitPaneRightSouth = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneRightSouth.setResizeWeight(0.5);
        splitPaneRight.setRightComponent(splitPaneRightSouth);

        btnBack = new JButton("BACK");
        btnBack.addActionListener(this);
        splitPaneRightSouth.setRightComponent(btnBack);

        btnSaveF = new JButton("SAVE");
        btnSaveF.addActionListener(this);
        splitPaneRightSouth.setLeftComponent(btnSaveF);

        input = new JTextField("");
        input.addKeyListener(this);
        splitPaneLeftSouth.setLeftComponent(input);

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        splitPaneLeftSouth.setRightComponent(submit);

        modelFalseAnswers.addElement("False Answers:");
    }

    public void setStatus(Integer rightC, Integer falseC) {
        status.setText("Correct: " + rightC + " False: " + falseC);
    }

    public void setFromTo(String from, String To) {
        labelFromTo.setText(from + " -> " + To);
    }

    public void setQuestion(String text) {
        labelQuestion.setText(text);
    }

    public void addToFalseList(String falseA) {
        modelFalseAnswers.addElement(falseA);
    }

    public void clearFalseList() {
        modelFalseAnswers.clear();
        modelFalseAnswers.addElement("False Answers:");
    }

    public void showAlert(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public Integer showYesNoAlert(String title, String msg) {
        return JOptionPane.showConfirmDialog(null, msg, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            window.getHandler().handleTrain(input.getText());
            input.setText("");
        } else if (e.getSource() == btnSaveF) {
            window.getHandler().saveGroup(window.getHandler().getFalseAnswersGroup());
        } else if (e.getSource() == btnBack) {
            window.changeCard(window.MENUC);
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
            if (!input.getText().equals("")) {
                window.getHandler().handleTrain(input.getText());
                input.setText("");
            }
        }
    }

}
