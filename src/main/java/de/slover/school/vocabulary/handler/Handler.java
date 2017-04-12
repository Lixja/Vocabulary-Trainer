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
package de.slover.school.vocabulary.handler;

import de.slover.school.vocabulary.data.Group;
import de.slover.school.vocabulary.data.Voc;
import de.slover.school.vocabulary.gui.Window;
import de.slover.school.vocabulary.io.VocabularyReader;
import de.slover.school.vocabulary.io.VocabularyWriter;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Handler {

    Group group;
    VocabularyReader reader;
    VocabularyWriter writer;
    Window window;

    Integer position;
    Integer correct;
    Integer incorrect;
    Group falseAnswers;
    List<Integer> notAsked;
    private Boolean FromToTo;

    public Handler(Window window) {
        writer = new VocabularyWriter();
        reader = new VocabularyReader();
        this.window = window;
    }

    public void handleTrain(String answer) {
        String word;
        LinkedList<String> solutions;
        boolean right = false;

        if (FromToTo) {
            word = group.getVocabulary().get(notAsked.get(position)).getVoc1();
        } else {
            word = group.getVocabulary().get(notAsked.get(position)).getVoc2();
        }
        solutions = group.getAnswers(word, FromToTo);

        for (String solution : solutions) {
            if (right) {
                break;
            }
            if (answer.equals(solution)) {
                right = true;
            } else {
                right = false;
            }

        }
        
        if (right) {
            correct++;
            window.gettcard().setStatus(correct, incorrect);
            removePosition();
            newQuestion();
        } else {
            incorrect++;
            window.gettcard().setStatus(correct, incorrect);
            window.gettcard().addToFalseList(word + " -> "
                    + solutions.get(0));
            Voc fA = new Voc();
            if (FromToTo) {
                fA.setVoc1(word);
                fA.setVoc2(solutions.get(0));
            } else {
                fA.setVoc1(solutions.get(0));
                fA.setVoc2(word);
            }
            falseAnswers.getVocabulary().add(fA);
            window.gettcard().showAlert("Your answer \"" + answer + "\" is false. The right answer would be \"" + solutions.get(0) + "\".");
            removePosition();
            newQuestion();
        }
    }

    public void removePosition() {
        notAsked.remove(notAsked.get(position));
        if (notAsked.size() == 0) {
            if (falseAnswers.getVocabulary().size() > 0) {
                Integer doSave = window.gettcard().showYesNoAlert("Trainer",
                        "You have answered every vocabulary of this group. Do you wanna save your false answers for training?");
                if (doSave == 0) {
                    saveGroup(falseAnswers);
                }
            } else {
                window.gettcard().showAlert("You have completed this group. The group will be restarted now.");
            }
            initializeTrainer(FromToTo);
        }
    }

    public void newQuestion() {
        Random r = new Random();
        position = r.nextInt(notAsked.size());
        if (FromToTo) {
            window.gettcard().setQuestion(group.getVocabulary().get(notAsked.get(position)).getVoc1());
        } else {
            window.gettcard().setQuestion(group.getVocabulary().get(notAsked.get(position)).getVoc2());
        }
    }

    public void saveGroup(Group group) {
        File f = writer.createGroup();
        String fileName = f.toString();
        if (!fileName.endsWith(".xml")) {
            fileName += ".xml";
        }
        writer.write(group, new File(fileName));
    }

    public Group loadGroup() {
        try {
            group = reader.chooseGroup();
        } catch (NullPointerException e) {

        }
        window.gettcard().clearFalseList();
        window.gettcard().setStatus(0, 0);
        falseAnswers = new Group();
        falseAnswers.setName("False Answers To " + group.getName());
        falseAnswers.setVoc1(group.getVoc1());
        falseAnswers.setVoc2(group.getVoc2());
        return group;
    }

    public void initializeTrainer(Boolean b) {
        FromToTo = b;
        if (b == true) {
            window.gettcard().setFromTo(group.getVoc1(), group.getVoc2());
        } else {
            window.gettcard().setFromTo(group.getVoc2(), group.getVoc1());
        }
        notAsked = new LinkedList<>();
        for (int i = 0; i < group.getVocabulary().size(); i++) {
            notAsked.add(i);
        }
        clear();
    }

    public void clear() {
        correct = 0;
        incorrect = 0;
        window.gettcard().setStatus(correct, incorrect);
        window.gettcard().clearFalseList();
        newQuestion();
    }

    public Group getGroup() {
        return group;
    }

    public Group getFalseAnswersGroup() {
        return falseAnswers;
    }

}
