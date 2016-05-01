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
        UI.setDivider(0);
        //UI.setImmediateRepaint(false);

        idCount=0;

        background=new Background(this,"assets/map_test_1.png");
        hero= new Hero(this,"assets/character_main.png",130,130);
        this.gameObjects.add(background);
        this.gameObjects.add(hero);
        this.spawnLoot();
        UI.setMouseMotionListener(this::doMouseMove);
    }
    
    private void spawnLoot(){
        Loot loot1 = new Loot(this,new Weapon(this,"Weapon","pipe","assets/Weapons/Pipe.png",1,20),500,500);
        
    }

    private void doMouseMove(String action,double x,double y){
        x-=100;
        y-=100;
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
    
    private void removeGameObject(int id){
        for(int i=0;i<gameObjects.size();i++){
            if(gameObjects.get(i).id==id){
                gameObjects.remove(i);
                return;
            }
        }
    }

    public void run()
    {   
        while(true){

            timer1=System.currentTimeMillis();
            timeElapsed+=timer1-timer2;

            timer2 = timer1;
            if(gameObjects.size()>0){

                for(int i=0;i<gameObjects.size();i++){
                    if(gameObjects.get(i) instanceof Moveable){
                        Moveable moveableTemp = (Moveable)(gameObjects.get(i));
                        moveableTemp.move();
                    }

                    if(gameObjects.get(i) instanceof Lootable){
                        Loot lootTemp=(Loot)(gameObjects.get(i));
                        double xDif = lootTemp.x-hero.x;
                        double yDif = lootTemp.y-hero.y;

                        if (xDif<0){xDif=xDif*-1;}
                        if (yDif<0){yDif=yDif*-1;}

                        if (xDif+yDif<hero.lootRange){
                            if (lootTemp.pickup(this.hero)){
                                this.hero.equip((Weapon)(lootTemp.object));
                                removeGameObject(lootTemp.id);
                                this.run();
                            }
                        }
                    }
                    if (gameObjects.get(i) instanceof Drawable){
                        Drawable drawableTemp = (Drawable)(gameObjects.get(i));
                        drawableTemp.draw();
                    }
                }

            }
            UI.sleep(20);
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

