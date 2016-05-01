import ecs100.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Hero extends GameObject implements Moveable,Drawable
{
    Engine engine;
    public double x;
    public double y;

    public double xVel;
    public double yVel;

    private int currentFrame=0;
    public Inventory inventory;
    private int inventorySize =10;

    long timer1=0;
    long timer2=0;
    long timeElapsed;

    private double[] vec = {0,0};
    private String[] frameFnames ={"assets/character_main_walk_1.png","assets/character_main_walk_2.png","assets/character_main_walk_1.png","assets/character_main_walk_3.png","assets/character_main_walk_4.png","assets/character_main_walk_3.png"};
    private BufferedImage[] frames;
    private BufferedImage[] renderFrames;
    private Weapon equipped;
    
    public double lootRange=150;

    double drag;

    String image;

    public Hero(Engine engine,String image,double x, double y)
    {
        super(engine,"Hero");
        xVel=4;
        yVel=4;
        drag=0.01;
        
        this.inventory=new Inventory(this,inventorySize);
        this.image=image;
        bufferFrames();

    }

    private void bufferFrames(){
        frames=new BufferedImage[frameFnames.length];
        renderFrames=new BufferedImage[frameFnames.length];
        for (int i =0;i<frameFnames.length;i++){
            try{
                frames[i]=ImageIO.read(new File(frameFnames[i]));
                renderFrames[i]=ImageIO.read(new File(frameFnames[i]));
            } catch (Exception e){

            }

        }
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
    
    public void equip(Weapon weapon){
        equipped = weapon;
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
            if(timeElapsed>150){
                nextFrame();
                timeElapsed=0;
            }
        }
        if(vec[0]!=0&&vec[1]!=0){
            double rotationRequired;
            if(vec[1]<0){
                 rotationRequired = Math.atan(-1*vec[0]/vec[1])-Math.PI;
            } else {
                 rotationRequired =(Math.atan(vec[0]/vec[1]));
                 rotationRequired+=(Math.PI/2 - rotationRequired) * 2 +Math.PI;
            }
            
            double locationX = x-frames[currentFrame].getWidth()/2;
            double locationY = y-frames[currentFrame].getHeight()/2;
            AffineTransform at = AffineTransform.getTranslateInstance(x,y);
            at.rotate(rotationRequired, 100,100);

            UI.getGraphics().drawImage(frames[currentFrame], at,null);
        }
        timer2=System.currentTimeMillis();
    }
}
