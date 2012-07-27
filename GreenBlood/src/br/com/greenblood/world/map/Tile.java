package br.com.greenblood.world.map;

public class Tile {
    private boolean solid;
    
    public Tile(boolean solid){
        this.solid = solid;
    }

    public boolean isSolid() {
        return solid;
    }
    
    
}
