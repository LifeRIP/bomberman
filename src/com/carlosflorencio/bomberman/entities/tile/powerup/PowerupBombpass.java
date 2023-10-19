package com.carlosflorencio.bomberman.entities.tile.powerup;

import com.carlosflorencio.bomberman.entities.Entity;
import com.carlosflorencio.bomberman.entities.bomb.Bomb;
import com.carlosflorencio.bomberman.entities.mob.Player;
import com.carlosflorencio.bomberman.graphics.Sprite;

public class PowerupBombpass extends Powerup {

	public PowerupBombpass(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			((Player) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		_active = true;
		Bomb.setBombpass(true);
	}
	


}
