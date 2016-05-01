

public class Weapon extends GameObject
{
    public int level;
    public double damage;
    public String imageFname;
    
    public Weapon(Engine engine,String name,String imageFname,int level,double damage)
    {
        super(engine,"Weapon");
        this.level=level;
        this.damage=damage;
        this.id=engine.generateId();
        this.imageFname=imageFname;
    }

}
