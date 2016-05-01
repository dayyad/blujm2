

public class Weapon extends GameObject
{
    public int level;
    public double damage;
    
    public Weapon(Engine engine,String name,int level,double damage)
    {
        super(engine,"Weapon");
        this.level=level;
        this.damage=damage;
        this.id=engine.generateId();
    }

}
