import cards.Card;
import stacks.Stack;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Start {

    private static int score = 0;
    private static long second = 600;

    private static JFrame window;
    private static JMenuBar menuBar;
    private static JMenu menuAbout;
    private static JPanel Panel;
    private static JPanel infoArea;

    private static JLabel remainTimeLabel= new JLabel("remain time (s):");
    private static JLabel remainTime= new JLabel("600");
    private static JLabel scoreLabel= new JLabel("score:");
    private static JLabel curScore= new JLabel("0");

    private static int originIndex =0;


    private static final int[] cardNumPool = {2,4,8,16,32,64};
    private static final String emptySlotImg = "src/res/image/slot.png";
    private static final String[] cardFaces={
            "src/res/image/2.png",
            "src/res/image/4.png",
            "src/res/image/8.png",
            "src/res/image/16.png",
            "src/res/image/32.png",
            "src/res/image/64.png",
            "src/res/image/128.png",
            "src/res/image/256.png",
            "src/res/image/512.png",
            "src/res/image/1024.png"
    };

    private static List<JLabel> placedSlots = new ArrayList<JLabel>();
    private static List<JLabel> candidateSlots = new ArrayList<JLabel>();

    private static List<Integer> candidateNumList = new ArrayList<Integer>();

    private static List<Stack> placedStackList = new ArrayList<Stack>();

    private static MyMouseInputAdapter0 myMouseInputAdapter0;
    private static MyMouseInputAdapter1 myMouseInputAdapter1;
    private static MyMouseInputAdapter2 myMouseInputAdapter2;

    public Start()
    {


        initSlots();
        initView();
        myMouseInputAdapter0 = new MyMouseInputAdapter0();
        myMouseInputAdapter1 = new MyMouseInputAdapter1();
        myMouseInputAdapter2 = new MyMouseInputAdapter2();

        candidateSlots.get(0).addMouseListener(myMouseInputAdapter0);
        candidateSlots.get(0).addMouseMotionListener(myMouseInputAdapter0);
        candidateSlots.get(1).addMouseListener(myMouseInputAdapter1);
        candidateSlots.get(1).addMouseMotionListener(myMouseInputAdapter1);
        candidateSlots.get(2).addMouseListener(myMouseInputAdapter2);
        candidateSlots.get(2).addMouseMotionListener(myMouseInputAdapter2);


    }

    public static void main(String[] args){

        new Start();




    }

    class MyMouseInputAdapter0 extends MouseInputAdapter
    {

        JLabel sourceComponent = candidateSlots.get(0);
        int originalX=sourceComponent.getX();
        int originalY=sourceComponent.getY();
        Point newPoint;

        Point point = new Point(0,0);

        public void mousePressed(MouseEvent e)
        {
            point = SwingUtilities.convertPoint(sourceComponent,e.getPoint(),sourceComponent.getParent());
        }

        public void mouseDragged(MouseEvent e)
        {
            newPoint  = SwingUtilities.convertPoint(sourceComponent,e.getPoint(),sourceComponent.getParent());
            sourceComponent.setLocation(sourceComponent.getX()+(newPoint.x-point.x),sourceComponent.getY()+(newPoint.y-point.y));
            point = newPoint;
        }

        public void mouseReleased(MouseEvent e)
        {

            if (newPoint.getX()>=placedSlots.get(0).getX()&& newPoint.getX()<=placedSlots.get(0).getX()+placedSlots.get(0).getWidth()
            && newPoint.getY()>=placedSlots.get(0).getY()&&newPoint.getY()<=placedSlots.get(0).getY()+placedSlots.get(0).getHeight())
            {


                placedSlots.get(0).setIcon(sourceComponent.getIcon());

                placedStackList.get(0).pushCard(new Card(candidateNumList.get(0)));

                sourceComponent.setLocation(originalX,originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(0, cardNumIndex);

                if(placedStackList.get(0).getCursor()>=2){
                    while (placedStackList.get(0).judge())
                    {
                        originIndex = placedStackList.get(0).getTopCardNum();
                        placedStackList.get(0).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex+1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(0).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(0).getTopCardNum() == 9)
                    {
                        score ++;
                        placedStackList.get(0).popCard();
                        if(placedStackList.get(0).getCursor() > 0){
                            ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(0).getTopCardNum()]);
                            imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                            placedSlots.get(0).setIcon(imageIcon2);
                        }
                        else
                        {
                            placedSlots.get(0).setIcon(new ImageIcon(emptySlotImg));
                        }
                        
                        curScore.setText(score+"");
                    }

                }


            }

            else if (newPoint.getX()>=placedSlots.get(1).getX()&& newPoint.getX()<=placedSlots.get(1).getX()+placedSlots.get(1).getWidth()
                    && newPoint.getY()>=placedSlots.get(1).getY()&&newPoint.getY()<=placedSlots.get(1).getY()+placedSlots.get(1).getHeight())
            {


                placedSlots.get(1).setIcon(sourceComponent.getIcon());

                placedStackList.get(1).pushCard(new Card(candidateNumList.get(0)));

                sourceComponent.setLocation(originalX,originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(0, cardNumIndex);

                if(placedStackList.get(1).getCursor()>=2){
                    while (placedStackList.get(1).judge())
                    {
                        int originIndex = placedStackList.get(1).getTopCardNum();
                        placedStackList.get(1).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex+1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(1).getTopCardNum() == 9)
                    {
                        score ++;
                        placedStackList.get(1).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(1).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon2);
                        curScore.setText(score+"");
                    }

                }


            }

            else if (newPoint.getX()>=placedSlots.get(2).getX()&& newPoint.getX()<=placedSlots.get(2).getX()+placedSlots.get(2).getWidth()
                    && newPoint.getY()>=placedSlots.get(2).getY()&&newPoint.getY()<=placedSlots.get(2).getY()+placedSlots.get(2).getHeight())
            {


                placedSlots.get(2).setIcon(sourceComponent.getIcon());

                placedStackList.get(2).pushCard(new Card(candidateNumList.get(0)));

                sourceComponent.setLocation(originalX,originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(0, cardNumIndex);

                if(placedStackList.get(2).getCursor()>=2){
                    while (placedStackList.get(2).judge())
                    {
                        originIndex = placedStackList.get(2).getTopCardNum();
                        placedStackList.get(2).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex+1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(2).getTopCardNum() == 9)
                    {
                        score ++;
                        placedStackList.get(2).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(2).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon2);
                        curScore.setText(score+"");
                    }

                }


            }

            else if (newPoint.getX()>=placedSlots.get(3).getX()&& newPoint.getX()<=placedSlots.get(3).getX()+placedSlots.get(3).getWidth()
                    && newPoint.getY()>=placedSlots.get(3).getY()&&newPoint.getY()<=placedSlots.get(3).getY()+placedSlots.get(3).getHeight())
            {


                placedSlots.get(3).setIcon(sourceComponent.getIcon());

                placedStackList.get(3).pushCard(new Card(candidateNumList.get(0)));

                sourceComponent.setLocation(originalX,originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(0, cardNumIndex);

                if(placedStackList.get(3).getCursor()>=2){
                    while (placedStackList.get(3).judge())
                    {
                        originIndex = placedStackList.get(2).getTopCardNum();
                        placedStackList.get(3).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex+1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(3).getTopCardNum() == 9)
                    {
                        score ++;
                        placedStackList.get(3).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(3).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon2);
                        curScore.setText(score+"");
                    }

                }


            }

            else
            {
                sourceComponent.setLocation(originalX,originalY);
            }


        }
    }

    class MyMouseInputAdapter1 extends MouseInputAdapter {

        JLabel sourceComponent = candidateSlots.get(1);
        int originalX = sourceComponent.getX();
        int originalY = sourceComponent.getY();
        Point newPoint;

        Point point = new Point(0, 0);

        public void mousePressed(MouseEvent e) {
            point = SwingUtilities.convertPoint(sourceComponent, e.getPoint(), sourceComponent.getParent());
        }

        public void mouseDragged(MouseEvent e) {
            newPoint = SwingUtilities.convertPoint(sourceComponent, e.getPoint(), sourceComponent.getParent());
            sourceComponent.setLocation(sourceComponent.getX() + (newPoint.x - point.x), sourceComponent.getY() + (newPoint.y - point.y));
            point = newPoint;
        }

        public void mouseReleased(MouseEvent e) {

            if (newPoint.getX() >= placedSlots.get(0).getX() && newPoint.getX() <= placedSlots.get(0).getX() + placedSlots.get(0).getWidth()
                    && newPoint.getY() >= placedSlots.get(0).getY() && newPoint.getY() <= placedSlots.get(0).getY() + placedSlots.get(0).getHeight()) {


                placedSlots.get(0).setIcon(sourceComponent.getIcon());

                placedStackList.get(0).pushCard(new Card(candidateNumList.get(1)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(1, cardNumIndex);

                if (placedStackList.get(0).getCursor() >= 2) {
                    while (placedStackList.get(0).judge()) {
                        originIndex = placedStackList.get(0).getTopCardNum();
                        placedStackList.get(0).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(0).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(0).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(0).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(0).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(0).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(1).getX() && newPoint.getX() <= placedSlots.get(1).getX() + placedSlots.get(1).getWidth()
                    && newPoint.getY() >= placedSlots.get(1).getY() && newPoint.getY() <= placedSlots.get(1).getY() + placedSlots.get(1).getHeight()) {


                placedSlots.get(1).setIcon(sourceComponent.getIcon());

                placedStackList.get(1).pushCard(new Card(candidateNumList.get(1)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(1, cardNumIndex);

                if (placedStackList.get(1).getCursor() >= 2) {
                    while (placedStackList.get(1).judge()) {
                        originIndex = placedStackList.get(1).getTopCardNum();
                        placedStackList.get(1).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(1).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(1).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(1).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(2).getX() && newPoint.getX() <= placedSlots.get(2).getX() + placedSlots.get(2).getWidth()
                    && newPoint.getY() >= placedSlots.get(2).getY() && newPoint.getY() <= placedSlots.get(2).getY() + placedSlots.get(2).getHeight()) {


                placedSlots.get(2).setIcon(sourceComponent.getIcon());

                placedStackList.get(2).pushCard(new Card(candidateNumList.get(1)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(1, cardNumIndex);

                if (placedStackList.get(2).getCursor() >= 2) {
                    while (placedStackList.get(2).judge()) {
                        originIndex = placedStackList.get(2).getTopCardNum();
                        placedStackList.get(2).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(2).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(2).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(2).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(3).getX() && newPoint.getX() <= placedSlots.get(3).getX() + placedSlots.get(3).getWidth()
                    && newPoint.getY() >= placedSlots.get(3).getY() && newPoint.getY() <= placedSlots.get(3).getY() + placedSlots.get(3).getHeight()) {


                placedSlots.get(3).setIcon(sourceComponent.getIcon());

                placedStackList.get(3).pushCard(new Card(candidateNumList.get(1)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(1, cardNumIndex);

                if (placedStackList.get(3).getCursor() >= 2) {
                    while (placedStackList.get(3).judge()) {
                        originIndex = placedStackList.get(3).getTopCardNum();
                        placedStackList.get(3).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(3).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(3).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(3).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else
            {
                sourceComponent.setLocation(originalX,originalY);
            }


        }
    }

    class MyMouseInputAdapter2 extends MouseInputAdapter {

        JLabel sourceComponent = candidateSlots.get(2);
        int originalX = sourceComponent.getX();
        int originalY = sourceComponent.getY();
        Point newPoint;

        Point point = new Point(0, 0);

        public void mousePressed(MouseEvent e) {
            point = SwingUtilities.convertPoint(sourceComponent, e.getPoint(), sourceComponent.getParent());
        }

        public void mouseDragged(MouseEvent e) {
            newPoint = SwingUtilities.convertPoint(sourceComponent, e.getPoint(), sourceComponent.getParent());
            sourceComponent.setLocation(sourceComponent.getX() + (newPoint.x - point.x), sourceComponent.getY() + (newPoint.y - point.y));
            point = newPoint;
        }

        public void mouseReleased(MouseEvent e) {

            if (newPoint.getX() >= placedSlots.get(0).getX() && newPoint.getX() <= placedSlots.get(0).getX() + placedSlots.get(0).getWidth()
                    && newPoint.getY() >= placedSlots.get(0).getY() && newPoint.getY() <= placedSlots.get(0).getY() + placedSlots.get(0).getHeight()) {


                placedSlots.get(0).setIcon(sourceComponent.getIcon());

                placedStackList.get(0).pushCard(new Card(candidateNumList.get(2)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(2, cardNumIndex);

                if (placedStackList.get(0).getCursor() >= 2) {
                    while (placedStackList.get(0).judge()) {
                        originIndex = placedStackList.get(0).getTopCardNum();
                        placedStackList.get(0).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(0).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(0).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(0).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(0).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(0).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(1).getX() && newPoint.getX() <= placedSlots.get(1).getX() + placedSlots.get(1).getWidth()
                    && newPoint.getY() >= placedSlots.get(1).getY() && newPoint.getY() <= placedSlots.get(1).getY() + placedSlots.get(1).getHeight()) {


                placedSlots.get(1).setIcon(sourceComponent.getIcon());

                placedStackList.get(1).pushCard(new Card(candidateNumList.get(2)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(2, cardNumIndex);

                if (placedStackList.get(1).getCursor() >= 2) {
                    while (placedStackList.get(1).judge()) {
                        originIndex = placedStackList.get(1).getTopCardNum();
                        placedStackList.get(1).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(1).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(1).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(1).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(1).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(2).getX() && newPoint.getX() <= placedSlots.get(2).getX() + placedSlots.get(2).getWidth()
                    && newPoint.getY() >= placedSlots.get(2).getY() && newPoint.getY() <= placedSlots.get(2).getY() + placedSlots.get(2).getHeight()) {


                placedSlots.get(2).setIcon(sourceComponent.getIcon());

                placedStackList.get(2).pushCard(new Card(candidateNumList.get(2)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(2, cardNumIndex);

                if (placedStackList.get(2).getCursor() >= 2) {
                    while (placedStackList.get(2).judge()) {
                        originIndex = placedStackList.get(2).getTopCardNum();
                        placedStackList.get(2).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(2).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(2).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(2).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(2).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else if (newPoint.getX() >= placedSlots.get(3).getX() && newPoint.getX() <= placedSlots.get(3).getX() + placedSlots.get(3).getWidth()
                    && newPoint.getY() >= placedSlots.get(3).getY() && newPoint.getY() <= placedSlots.get(3).getY() + placedSlots.get(3).getHeight()) {


                placedSlots.get(3).setIcon(sourceComponent.getIcon());

                placedStackList.get(3).pushCard(new Card(candidateNumList.get(2)));

                sourceComponent.setLocation(originalX, originalY);
                int cardNumIndex = getRandomCardNum();
                ImageIcon imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                sourceComponent.setIcon(imageIcon);
                candidateNumList.set(2, cardNumIndex);

                if (placedStackList.get(3).getCursor() >= 2) {
                    while (placedStackList.get(3).judge()) {
                        originIndex = placedStackList.get(3).getTopCardNum();
                        placedStackList.get(3).mergeCard();
                        ImageIcon imageIcon1 = new ImageIcon(cardFaces[originIndex + 1]);
                        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon1);
                    }


                    if (placedStackList.get(3).getTopCardNum() == 9) {
                        score++;
                        placedStackList.get(3).popCard();
                        ImageIcon imageIcon2 = new ImageIcon(cardFaces[placedStackList.get(3).getTopCardNum()]);
                        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(124, 200, Image.SCALE_DEFAULT));
                        placedSlots.get(3).setIcon(imageIcon2);
                        curScore.setText(score + "");
                    }

                }


            }

            else
            {
                sourceComponent.setLocation(originalX,originalY);
            }


        }
    }

    private static void initSlots() {
        ImageIcon imageIcon;
        JLabel jLabel;
        for(int i=0; i<4; i++)
        {
            imageIcon = new ImageIcon(emptySlotImg);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
            jLabel = new JLabel(imageIcon);

            placedStackList.add(new Stack());

            placedSlots.add(jLabel);
        }

        for(int j=0; j<3; j++)
        {
            int cardNumIndex = getRandomCardNum();
            candidateNumList.add(cardNumIndex);
            System.out.println(candidateNumList.get(j));

            imageIcon = new ImageIcon(cardFaces[cardNumIndex]);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(124,200,Image.SCALE_DEFAULT));
            jLabel = new JLabel(imageIcon);


            candidateSlots.add(jLabel);
        }




    }

    private static void initView() {

        Panel = new JPanel();
        infoArea = new JPanel();

        Panel.add(placedSlots.get(0));
        Panel.add(placedSlots.get(1));
        Panel.add(placedSlots.get(2));
        Panel.add(placedSlots.get(3));

        Panel.add(candidateSlots.get(0));
        Panel.add(candidateSlots.get(1));
        Panel.add(candidateSlots.get(2));


        //infoArea.add(remainTimeLabel);
        //infoArea.add(remainTime);
        infoArea.add(scoreLabel);
        infoArea.add(curScore);

        window = new JFrame("1024box");

        GridLayout gridLayout = new GridLayout(2,1);
        window.setLayout(gridLayout);

        //menuAbout = new JMenu("about");
        //menuBar = new JMenuBar();
        //menuBar.add(menuAbout);
        //window.setJMenuBar(menuBar);

        Panel.setComponentZOrder(candidateSlots.get(0),4);
        window.add(Panel);
        window.add(infoArea);


        window.setSize(600,900);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static int getRandomCardNum()
    {
        Random r = new Random();
        return r.nextInt(6);
    }

}
