import ecs100.*;
import java.util.*;

public class GameObject
{
   public int id;
    
    public GameObject(Engine engine,String name)
    {
        id = engine.generateId();
        engine.gameObjects.add(this);
    }
    
    public int id(){
        return id;
    }

}
