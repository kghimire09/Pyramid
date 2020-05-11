//Pyramid: This exercise is a slightly modified version of Exercise 4.11 from Roberts. Your stack of blocks should fit "exactly"
//        into a centered rectangle that is 80% of the window's width and height. Use a constant to indicates the number of rows
//        to be placed in the rectangle. Unlike the book's version, the width and height of each brick are directly related to the
//        current size of the window. You may start at either the top or bottom of the pyramid – it can be built in either direction.
//        Make the background white, the background centered rectangle blue and the bricks orange with red outlines. Note that the
//        pyramid should resize itself as the windows is resized i.e. the number of rows will stay fixed, but the block will change
//        size. See the examples Gaps and NoGaps for an example of how to use Swing's Graphics2D class to avoid gaps in the shapes
//        you are drawing.


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Pyramid extends JFrame {
    public static class DrawingPanel extends JPanel{
        private Rectangle Rect;
        private int ROWS=25;
        private int Base=25;
        private double height,width;
        public DrawingPanel(){
            setBackground(Color.white);
            setForeground(Color.blue);
        }
        @Override
        protected void paintComponent(Graphics graphic) {
            super.paintComponent(graphic);
            rectangleDrawing(graphic);
            bricksDrawing(graphic);
        }
        public void rectangleDrawing(Graphics graphic){
            Rect=new Rectangle((int)(getWidth()*0.1),(int)(getHeight()*0.1),(int)(getWidth()*0.8),(int)(getHeight()*0.8));
            width=(float)Rect.getWidth()/Base;
            height=(float)Rect.getHeight()/ROWS;
            graphic.fillRect((int)(getWidth()*0.1),(int)(getHeight()*0.1),(int)(getWidth()*0.8),(int)(getHeight()*0.8));
        }
        public void bricksDrawing(Graphics graphic){
            Rect=new Rectangle((int)(getWidth()*0.1),(int)(getHeight()*0.1),(int)(getWidth()*0.8),(int)(getHeight()*0.8));
            Graphics2D brick = (Graphics2D)graphic.create();
            double x = getWidth()*0.1;
            double y = getHeight()*0.9-height;
            for (int i=1;i<=ROWS;i++){
                for (int j=Base;j>=i;j--){
                    brick.setColor(Color.ORANGE);
                    Rectangle2D.Double rect = new Rectangle2D.Double(x,y,width,height);
                    brick.fill(rect);
                    brick.setColor(Color.RED);
                    Rectangle2D.Double shape = new Rectangle2D.Double(x,y,width,height);
                    brick.draw(shape);
                    x+=width;
                }
                y-=height;
                x=getWidth()*0.1+i*width*0.5;
            }
        }
    }
    public Pyramid(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        add(new DrawingPanel());
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Pyramid().setVisible(true);
            }
        });
    }

}
