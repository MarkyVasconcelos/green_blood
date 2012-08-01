package br.com.greenblood.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.math.Gravity;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.MovableEntity;
import br.com.greenblood.world.WorldMap;

public class PiecesManager {
    private final List<Entity> pieces = new ArrayList<Entity>();
    private final WorldMap worldMap;

    public PiecesManager(WorldMap worldMap){
        this.worldMap = worldMap;
    }
    
    public void add(Entity ent) {
        pieces.add(ent);
    }

    public void proccessAI(long uptime) {
        long now = System.currentTimeMillis();

        for (Entity ent : pieces) {
            ent.processLogics(uptime);
            applyGravity(uptime, (MovableEntity) ent);
        }
        
        Iterator<Entity> it = pieces.iterator();
        while(it.hasNext())
            if(it.next().isDead())
                it.remove();
        
        long after = System.currentTimeMillis();
        System.out.println("proccessAI finished in:" + (after - now) + "ms");
    }

    private void applyGravity(long uptime, MovableEntity ent) {
        int tileX = ent.tileX();
        int tileY = GameCore.pixelsToTiles(ent.bottom());
        boolean collids = worldMap.collids(ent, tileX, tileY);

        if (!collids)
            Gravity.apply(ent, uptime);
        else
            ent.dir().setY(0);
    }

    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
        for(Entity ent : pieces)
            ent.draw(canvas, surfaceSize, offset);        
    }
    
    public Entity entityAt(int x, int y){
        for(Entity ent : pieces)
            if(ent.currentBounds().contains(x, y))
                return ent;
        return null;
    }
}
