package org.fryslan.simple.aiofisher.data;

public enum Fish {
	SHRIMP(317,1518,303,-1,1),
	TUNA(-1,-1,-1,-1,35),
	LOBSTER(-1,-1,-1,-1,40),
	SWORDFISH(-1,-1,-1,-1,50),
	SHARK(-1,-1,-1,-1,76);

	private final int fishiId;
	private int spotId;
	private final int equipment;
	private final int bait;
	private final int level;

	Fish(int fishiId,int spotId, int equipment, int bait, int level) {
		this.fishiId = fishiId;
		this.spotId = spotId;
		this.equipment = equipment;
		this.bait = bait;
		this.level = level;
	}

	public int getFishiId() {
		return fishiId;
	}

	public int getEquipment() {
		return equipment;
	}

	public int getBait() {
		return bait;
	}

	public int getLevel() {
		return level;
	}

	public int getSpotId() {
		return spotId;
	}
}
