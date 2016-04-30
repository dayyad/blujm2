import ecs100.*;
import java.util.*;

public class Engine
{
    public List<GameObject> gameObjects = new ArrayList<GameObject>();

    int idCount;

    protected static final double top=0;
    protected static final double bot=200;
    protected static final double left=0;
    protected static final double right=400;

    protected final double width = right-left;
    protected final double height = bot-top;

    public Engine()
    {   
        UI.initialise();
        UI.setImmediateRepaint(false);
        idCount=0;
        
    }

    public int generateId(){
        int id = idCount;
        idCount++;
        return id;
    }

    public void run()
    {   
        while(true){
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
            UI.repaintGraphics();
        }
    }

    public void loadScene(String scene){

    }

    public final static void main(){
        Engine engine = new Engine();
        
        MobObject mob1= new MobObject(engine,"assets/character_main.png",30,30);
        engine.gameObjects.add(mob1);
        UI.println(engine.gameObjects.size());
        
        engine.run();
        
    }
}

