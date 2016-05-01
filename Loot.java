import ecs100.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Loot extends GameObject implements Lootable,Drawable
{   
    public GameObject object;
    public double x;
    public double y;
    private Engine engine;

    public Loot(Engine engine,GameObject object,double x,double y) 
    {
        super(engine,"loot");
        this.engine = engine;
        this.object = object;
        this.x=x;
        this.y=y;
    }
    
    public boolean pickup(Hero hero){
        return hero.inventory.add(this.object);
    }
    
    public void draw(){
        
        Weapon tempWeapon = (Weapon)(object);
        UI.drawImage(tempWeapon.imageFname,x,y);
    }
}
