import ecs100.*;
import java.util.*;
import java.awt.event.*;

public class Hero extends GameObject implements Moveable,Drawable
{
    Engine engine;
    public double x;
    public double y;

    public double xVel;
    public double yVel;
    
    private int currentFrame=0;
    
    long timer1=0;
    long timer2=0;
    long timeElapsed;
    
    
    private double[] vec = {0,0};
    private String[] frames ={"assets/character_main_walk_1.png","assets/character_main_walk_2.png","assets/character_main_walk_1.png","assets/character_main_walk_3.png","assets/character_main_walk_4.png","assets/character_main_walk_3.png"};

    double drag;
    
    String image;
     

    public Hero(Engine engine,String image,double x, double y)
    {
        super(engine,"Mob");
        xVel=0.4;
        yVel=0.4;
        
        drag=0.01;
        this.image=image;

    }


    private boolean canMove(double x, double y){
        if (x<engine.right&&x>engine.left&&y>engine.top&&y<engine.bot){
            return true;
        } else{
            return false;
        }
    }
    
    public void setDirection(double[] vec){
        this.vec=vec;
    }
    
    private void nextFrame(){
        if(currentFrame< frames.length-1){
            currentFrame++;
        } else{
            currentFrame=0;
        }
    }

    public void move(){
        double xOrig=x;
        double yOrig=y;

        x+=xVel*vec[0];
        y+=yVel*vec[1];

        if (x>engine.right){ // checks if placed outside of map then moves back within the map
            x=engine.right-1;
        } else if(x<engine.left){
            x=engine.left-1;
        }

        if (y<engine.top){
            y=engine.top+1;
        } else if (y>engine.bot){
            y=engine.bot-1;
        }
       
    }

    public void draw(){
        if(timer2!=0){
            timer1=System.currentTimeMillis();
        }
        
        if(xVel!=0 || yVel!=0){
            timeElapsed+=(timer1-timer2);
            if(timeElapsed>1000){
                nextFrame();
                timeElapsed=0;
            }
        }
        
        
        UI.drawImage(frames[currentFrame],x,y);
        timer2=System.currentTimeMillis();
    }
}
