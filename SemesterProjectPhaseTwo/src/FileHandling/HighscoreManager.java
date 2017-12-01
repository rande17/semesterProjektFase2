/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHandling;

import FileHandling.CompareScore;
import Game.Score;
import java.util.*;
import java.io.*;

/**
 *
 * @author Nicolai
 */
public class HighscoreManager {

    // An arraylist of the type "score" we will use to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "highscores.txt";

    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }

    //Method to get the arraylist, where it first loader the score file and then sorts the different score after the condition set in the CompareScore class 
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    private void sort() {
        //make object comparator of CompareScore
        CompareScore comparator = new CompareScore();
        //sort the different scores with comparator algorithm set in the CompareScore class
        Collections.sort(scores, comparator);
    }

    //add score to the arraylist
    public void addHighscore(String name, int score) {
        loadScoreFile(); //load the curret score list
        scores.add(new Score(name, score)); // add new Score with a name and a score
        updateScoreFile(); //update the score list
    }

    public void loadScoreFile() {
        try {
            //load the highscore file that is in a form of arraylist and put it in the scores arraylist
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
            //everytime we load score file and a there is a error, it will catch it and depending on the type of error it will print out a message
        } catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Load] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Load] IO Error: " + e.getMessage());
            }
        }
    }

    public void updateScoreFile() {
        try {
            //It will take the new current score arraylist and write it to the file
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
            //everytime we update score file and a there is a error, it will catch it and depending on the type of error it will print out a message
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    //mehtod to get highscore list
    public String getHighscoreList() {
        String highscoreList = "";
        //set length of the highscore list
        int highscoreMax = 10;
        //get the arraylist scores
        ArrayList<Score> scores;
        //set score = the method getScore where it first load the scorefile and then sort the list and return scores
        scores = getScores();

        int x = scores.size();
        //if x is bigger than highscoreMax it sets x = highscoreMax
        if (x > highscoreMax) {
            x = highscoreMax;
        }
        //for loop that print out score name and score
        for (int i = 0; i < x; i++) {
            highscoreList += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getCurrentScore() + "\n";
        }

        return highscoreList;
    }

}
