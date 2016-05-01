import ecs100.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Inventory
{
    Hero owner;
    int maxSize;
    List<GameObject> slot = new ArrayList<GameObject>();

    public Inventory(Hero owner,int maxSize)
    {
        this.owner=owner;
        this.maxSize=maxSize;
    }

    public void add(GameObject object){
        
        if (slot.size()<maxSize){
          slot.add(object);
        }
    }

}
