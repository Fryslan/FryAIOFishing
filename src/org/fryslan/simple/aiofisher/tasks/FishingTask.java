package org.fryslan.simple.aiofisher.tasks;

import org.fryslan.simple.aiofisher.Debug;
import org.fryslan.simple.aiofisher.data.Fish;
import org.fryslan.simple.aiofisher.data.Location;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class FishingTask extends Task {
	private ClientContext ctx;
	private Fish fish;
	private Location location;
	private String status = "Fishing Task";

	public FishingTask(ClientContext ctx, Fish fish, Location location) {
		super(ctx);
		this.ctx = ctx;
		this.fish = fish;
		this.location = location;
	}

	@Override
	public boolean condition() {
		return !ctx.inventory.inventoryFull();
	}

	@Override
	public void run() {
		SimpleNpc spot = ctx.npcs.populate().filter(fish.getSpotId()).next();
		if (ctx.players.getLocal().getAnimation() == -1) {
			if (spot != null) {
				if (spot.visibleOnScreen()) {
					status = "Interacting with fishing spot.";
					spot.click("Small Net");
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1, 2000);
					if(ctx.getClient().isMenuOpen()){
						ctx.mouse.moveMouse(0,0);
					}
				} else {
					Debug.debug("Distance to Spot", "" + ctx.players.getLocal().getLocation().distanceTo(spot.getLocation()));
					if (ctx.players.getLocal().getLocation().distanceTo(spot.getLocation()) < 7) {
						status = "Turning camera to fishing spot.";
						ctx.onCondition(() -> spot.turnTo(), 1000);
					} else {
						status = "Walking to fishing spot.";
						ctx.pathing.step(spot.getLocation());
						ctx.onCondition(() -> ctx.players.getLocal().getLocation().distanceTo(spot.getLocation()) < 7, 2000);
					}
				}
			}else{
				status = "Walking route to fishing spot.";
				ctx.pathing.walkPath(location.getPath());
				//ctx.onCondition(() -> ctx.players.getLocal().getLocation().distanceTo(ctx.pathing.destination()) < 4,5000);
			}
		} else {
			status = "We are fishing!";
		}

	}

	@Override
	public String status() {
		return status;
	}
}
