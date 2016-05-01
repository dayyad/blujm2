import ecs100.*;
import java.util.*;

public class GameObject
{
   int id;
    
    public GameObject(Engine engine,String name)
    {
        id = engine.generateId();
    }
    
    public int id(){
        return id;
    }

}
