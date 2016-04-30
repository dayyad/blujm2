import ecs100.*;
import java.util.*;

public class MobObject extends GameObject implements Moveable,Drawable
{
    Engine engine;
    double x;
    double y;

    double xVel;
    double yVel;

    double drag;

    String image;

    public MobObject(Engine engine,String image,double x, double y)
    {
        super(engine,"Mob");
        xVel=0;
        yVel=0;

        drag=0.01;
        this.image=image;

        UI.setKeyListener(this::changeVel);
    }

    private void changeVel(String action){
        if(action.equals("w")){
            yVel=-1;
        } else if(action.equals("s")){
            yVel=1;
        } 

        if(action.equals("a")){
            xVel=-1;
        } else if(action.equals("d")){
            xVel=1;
        }
    }

    private boolean canMove(double x, double y){
        if (x<engine.right&&x>engine.left&&y>engine.top&&y<engine.bot){
            return true;
        } else{
            return false;
        }
    }

    public void move(){
        double xOrig=x;
        double yOrig=y;

        x+=xVel;
        y+=yVel;

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

        if (xVel>0){ //Applies drag
            xVel-=drag;
            if(xVel<0){
                xVel=0;
            }
        } else if(xVel<0){
            xVel+=drag;
            if(xVel>0){
                xVel=0;
            }
        } 

        if (yVel>0){
            yVel-=drag;
            if(yVel<0){
                yVel=0;
            }
        } else if (yVel<0){
            yVel+=drag;
            if(yVel>0){
                yVel=0;
            }
        }
    }

    public void draw(){
        UI.drawImage(image,x,y);
    }
}
