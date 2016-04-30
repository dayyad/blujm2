import ecs100.*;
import java.util.*;

public class MobObject extends GameObject implements Moveable,Drawable
{
    Engine engine;
    double x;
    double y;

    double xVel;
    double yVel;

    public MobObject(Engine engine,double x, double y)
    {
        super(engine,"Mob");
        xVel=0;
        yVel=0;
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
        
    }

    public void draw(){

    }
}
