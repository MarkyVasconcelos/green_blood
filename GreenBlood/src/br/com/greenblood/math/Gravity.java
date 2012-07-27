package br.com.greenblood.math;

import br.com.greenblood.pieces.Entity;

public class Gravity {
    private static final float strenght = 40;
    private static final Vector2D direction = new Vector2D(0, 1);

    public static void apply(Entity ent, long uptime) {
        if(uptime == 0)
            return;
        
        float stepSpeed = strenght / (float) uptime;
        Vector2D step = direction.multiply(stepSpeed);
        ent.pos().plusMe(step);
    }
}
