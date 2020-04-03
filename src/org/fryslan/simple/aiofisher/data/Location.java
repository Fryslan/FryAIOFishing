package org.fryslan.simple.aiofisher.data;

import net.runelite.api.coords.WorldPoint;

import java.util.Arrays;
import java.util.Collections;

public enum Location {
	POWERFISH(Fish.values(),null),
	CATHERBY(new Fish[]{Fish.SHRIMP, Fish.TUNA, Fish.LOBSTER, Fish.SWORDFISH, Fish.SHARK}, new WorldPoint[]{new WorldPoint(2808, 3440, 0), new WorldPoint(2810, 3435, 0), new WorldPoint(2820, 3438, 0), new WorldPoint(2828, 3437, 0), new WorldPoint(2837, 3434, 0), new WorldPoint(2846, 3433, 0), new WorldPoint(2850, 3430, 0)});


	private final Fish[] fish;
	private final WorldPoint[] path;

	Location(Fish[] fish, WorldPoint[] path) {
		this.fish = fish;
		this.path = path;
	}

	public Fish[] getFish() {
		return fish;
	}

	public WorldPoint[] getPath() {
		return path;
	}

	public WorldPoint[] getReversedPath() {
		WorldPoint[] path = getPath();
		for(int i=0; i<path.length/2; i++){
			WorldPoint temp = path[i];
			path[i] = path[path.length -i -1];
			path[path.length -i -1] = temp;
		}
		return path;
	}
}
