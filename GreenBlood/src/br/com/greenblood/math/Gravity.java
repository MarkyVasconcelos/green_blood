package br.com.greenblood.math;

import br.com.greenblood.pieces.movable.MovableEntity;

public class Gravity {
    private static final Vector2D direction = new Vector2D(0, 2.1f);

    public static void apply(MovableEntity ent, long uptime) {
        if(uptime == 0)
            return;
        
        ent.dir().plusMe(direction.div(uptime));
    }
}
