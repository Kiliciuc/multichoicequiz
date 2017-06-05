import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class mcquiz {

    // This is the constructor. Whenever a new instance of mcquiz is created, this code is run below.

    public mcquiz() throws FileNotFoundException {
        String[] questions = new String[10];
        String line;
        Scanner fileScan;
        File myFile = new File("questionaire.txt");
        fileScan = new Scanner(myFile);
        int counter = 0;
        while (fileScan.hasNext()) {
            line = fileScan.nextLine();
            System.out.println(line.toUpperCase());
            questions[counter] = line;
            counter++;
        }


        String[] answer = new String[10];
        String[] wrong_answer1 = new String[10];
        String[] wrong_answer2 = new String[10];
        String[] wrong_answer3 = new String[10];

        File myFile1 = new File("answer.txt");
        fileScan = new Scanner(myFile1);
        int counter1 = 0;
        int currentQuestionNumber = 0;
        while (fileScan.hasNext()) {
            line = fileScan.nextLine();
            System.out.println(line.toUpperCase());
            String[] theLineButSplit = line.split(",");

            //First answer in the line in the text file - DKK
            answer[counter1] = theLineButSplit[0];

            //second answer in the line in the text file - DZD
            wrong_answer1[counter1] = theLineButSplit[1];

            //third answer in the line in the text file - DKR
            wrong_answer2[counter1] = theLineButSplit[2];

            //fourth answer in the line in the text file - EUR
            wrong_answer3[counter1] = theLineButSplit[3];


            counter1++;
        }


        for (int i = 0; i < questions.length; i++) {
            //Goes through each question and checks the answer when they click OK
            score += checkanswer(questions[i], answer, 10, wrong_answer1, wrong_answer2, wrong_answer3, currentQuestionNumber);
            currentQuestionNumber = currentQuestionNumber + 1;
        }

        //Once they have answered every question, display their score
        JOptionPane.showMessageDialog(null, "Your total score is   " + score + "!\nWell Done!");
        System.out.println(Arrays.deepToString(answer));
    }

    int score = 0;


    public static void main(String args[]) throws IOException
    {
        mcquiz myQuiz = new mcquiz();
    }


    String theirAnswer = "";

    public int checkanswer(String question, String[] answer, int points, String[] wrong_answer1, String[] wrong_answer2, String[] wrong_answer3, int currentQuestionNumber) {
        String answerString = "";
        JPanel panel = new JPanel();

        ButtonGroup bg1 = new ButtonGroup();
        panel.add(new JLabel(question));

        JRadioButton button1 = new JRadioButton(answer[currentQuestionNumber]);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theirAnswer = button1.getText();
            }
        });
        bg1.add(button1);
        panel.add(button1);

        JRadioButton button2 = new JRadioButton(wrong_answer1[currentQuestionNumber]);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theirAnswer = button2.getText();
            }
        });

        bg1.add(button2);
        panel.add(button2);

        JRadioButton button3 = new JRadioButton(wrong_answer2[currentQuestionNumber]);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theirAnswer = button3.getText();
            }
        });

        bg1.add(button3);
        panel.add(button3);

        JRadioButton button4 = new JRadioButton(wrong_answer3[currentQuestionNumber]);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theirAnswer = button4.getText();
            }
        });

        bg1.add(button4);
        panel.add(button4);
        String[] buttons = {"Skip", "Ok"};
        int result = JOptionPane.showOptionDialog(null, panel, "Question:", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, buttons, null);
        if (result == -1)
        {
            System.exit(0);
        }
        if (result == 0){
            theirAnswer="skip";
            //skip question
        }

                //This code runs after they press the OK button:

            if (theirAnswer.equals(answer[currentQuestionNumber]))
            {
                    //Return 10 which is added to the score variable
                    return points;
            }
                else
                    {
                    //Return 0 since they got the answer wrong
                    return 0;
                    }


    }
}

