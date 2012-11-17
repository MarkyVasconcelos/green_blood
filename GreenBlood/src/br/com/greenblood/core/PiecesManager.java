package br.com.greenblood.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.Character;
import br.com.greenblood.pieces.Player;

public class PiecesManager {
    private final List<Entity> pieces = new ArrayList<Entity>();
    private final Player player;

    public PiecesManager(Player player){
        this.player = player;
    }
    
    public void add(Entity ent) {
        pieces.add(ent);
    }

    public void proccessAI(long uptime) {
        player.processLogics(uptime);
        
        for (Entity ent : pieces)
            ent.processLogics(uptime);
        
        Iterator<Entity> it = pieces.iterator();
        while(it.hasNext())
            if(it.next().isDead())
                it.remove();
    }

    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
        for(Entity ent : pieces)
            ent.draw(canvas, surfaceSize, offset);
        
        player.draw(canvas, surfaceSize, offset);
    }
    
    public Character punchCollidableEntityAt(int x, int y){
        if(player.currentBoundingBounds().contains(x, y))
            return player;
        
        for(Entity ent : pieces)
            if(ent instanceof Character && ent.currentBoundingBounds().contains(x, y))
                return (Character) ent;
        
        return null;
    }

	public void addAll(List<Entity> objects) {
		pieces.addAll(objects);
	}
}
