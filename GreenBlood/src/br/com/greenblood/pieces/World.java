package br.com.greenblood.pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.movable.Character;
import br.com.greenblood.pieces.movable.Player;

/**
 * World is the whole entity that holds all others
 *  
 * TODO: Create universe
 * 
 * @author Marcos Vasconcelos
 */
public class World extends Entity {
	private final List<Entity> pieces = new ArrayList<Entity>();
	private final Player player;
	private final ArrayBlockingQueue postAdd = new ArrayBlockingQueue(20);

	public World(Player player) {
		super(new Rect()); //This is the world, has no dimensions
		this.player = player;
	}

	public void add(Entity ent) {
		pieces.add(ent);
	}
	
	public void onProcessPhaseStarted(){
		if(postAdd.size() > 0)
			postAdd.drainTo(pieces);
	}

	@Override
	public void processLogics(long uptime) {
		player.processLogics(uptime);
		
		for (Entity ent : pieces)
			ent.processLogics(uptime);

		Iterator<Entity> it = pieces.iterator();
		while (it.hasNext())
			if (it.next().isDead())
				it.remove();
	}

	@Override
	public void processAnimationLogics(long uptime) {
		player.processAnimationLogics(uptime);

		for (Entity ent : pieces)
			ent.processAnimationLogics(uptime);
	}

	public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
		for (Entity ent : pieces)
			ent.draw(canvas, surfaceSize, offset);

		player.draw(canvas, surfaceSize, offset);
	}


	public Entity collidableObjectAt(int x, int y) {
		for (Entity ent : pieces)
			if (ent.isCollidable() && !(ent instanceof Character))
				if (ent.currentBounds().contains(x, y))
					return ent;

		return null;
	}
	
	public Character punchCollidableEntityAt(int x, int y) {
		if (player.currentBoundingBounds().contains(x, y))
			return player;

		for (Entity ent : pieces)
			if (ent instanceof Character)
				if (((Character) ent).currentBoundingBounds().contains(x, y))
					return (Character) ent;

		return null;
	}

	public void addAll(List<Entity> objects) {
		pieces.addAll(objects);
	}

	public void postAdd(Entity ent) {
		postAdd.add(ent);
	}

}
