import ecs100.*;
import java.util.*;

public class Engine
{
    public List<GameObject> gameObjects = new ArrayList<GameObject>();

    int idCount;

    long timer1=0;
    long timer2=0;
    long timeElapsed;

    protected static final double top=0;
    protected static final double bot=1956;
    protected static final double left=0;
    protected static final double right=2880;

    protected final double width = right-left;
    protected final double height = bot-top;

    private Hero hero;
    private Background background;

    public Engine()
    {   
        UI.initialise();
        UI.setImmediateRepaint(false);

        idCount=0;

        background=new Background(this,"assets/map_test_1.png");
        hero= new Hero(this,"assets/character_main.png",130,130);
        this.gameObjects.add(background);
        this.gameObjects.add(hero);

        UI.setMouseMotionListener(this::doMouseMove);
    }

    private void doMouseMove(String action,double x,double y){

        double op = x -hero.x;
        double ad = y -hero.y;

        double angle = Math.atan(op/ad);
        double vecLength = Math.sqrt(op*op + ad*ad);
        double[] normVec={op/vecLength,ad/vecLength};

        if(action.equals("pressed")){
            hero.setDirection(normVec);
        }

    }

    public int generateId(){
        int id = idCount;
        idCount++;
        return id;
    }

    public void run()
    {   
        while(true){
            
            timer1=System.currentTimeMillis();
            
            timeElapsed+=timer1-timer2;
            
            timer2 = timer1;
            //UI.clearGraphics();
            if(gameObjects.size()>0){

                for(int i=0;i<gameObjects.size();i++){
                    if(gameObjects.get(i) instanceof Moveable){
                        Moveable moveableTemp = (Moveable)(gameObjects.get(i));
                        moveableTemp.move();
                    }
                    if (gameObjects.get(i) instanceof Drawable){
                        Drawable drawableTemp = (Drawable)(gameObjects.get(i));
                        drawableTemp.draw();
                    }
                }


            }
            UI.sleep(30);
             UI.repaintAllGraphics();
            
            //timer2=System.currentTimeMillis();
        }
    }

    public void loadScene(String scene){

    }

    public final static void main(){
        Engine engine = new Engine();
        engine.run();
    }
}

