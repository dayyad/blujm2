import ecs100.*;
import java.util.*;
import java.awt.event.*;

public class Background extends GameObject implements Drawable
{   
    String image;
   
    
    public Background(Engine engine,String image)
    {
      super(engine,"Background");
      this.image=image;
    }
    
    public void draw()
    {
        UI.drawImage(image,0,0);
    }
}
