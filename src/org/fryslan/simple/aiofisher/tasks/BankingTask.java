package org.fryslan.simple.aiofisher.tasks;

import net.runelite.api.coords.WorldPoint;
import org.fryslan.simple.aiofisher.data.Fish;
import org.fryslan.simple.aiofisher.data.Location;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

import java.util.Arrays;
import java.util.Collections;

public class BankingTask extends Task {
	private ClientContext ctx;
	private final Fish fish;
	private final Location location;
	private boolean banking;
	private String status = "Banking Task";
	private final int[] BOOTHS = {6943, 6944, 6945, 6946};


	public BankingTask(ClientContext ctx, Fish fish, Location location, boolean banking) {
		super(ctx);
		this.ctx = ctx;
		this.fish = fish;
		this.location = location;
		this.banking = banking;
	}

	@Override
	public boolean condition() {
		return ctx.inventory.inventoryFull() && banking;
	}

	@Override
	public void run() {
		int[] gear = {fish.getBait(), fish.getEquipment()};
		WorldPoint[] path = location.getReversedPath();

		SimpleObject booth = ctx.objects.populate().filter(BOOTHS).nearest().next();


		if (booth != null) {
			if (booth.visibleOnScreen()) {
				if (ctx.bank.bankOpen()) {
					status = "Depositing fish.";
					ctx.bank.depositAllExcept(gear);
				} else {
					status = "Opening Bank";
					booth.click(0);
					ctx.onCondition(() -> ctx.bank.bankOpen(), 2000);
				}
			} else {
				if (ctx.players.getLocal().distanceTo(booth) < 7) {
					status = "Turning Camera to Bank";
					booth.turnTo();
					ctx.onCondition(() -> booth.visibleOnScreen(), 2000);
				} else {
					status = "Walking to Bank";
					ctx.pathing.step(booth.getLocation());
					ctx.onCondition(() -> booth.visibleOnScreen(), 2000);
				}
			}
		} else {
			status = "Walking to Bank";
			ctx.pathing.walkPath(path);
			//ctx.onCondition(() -> ctx.players.getLocal().getLocation().distanceTo(ctx.pathing.destination()) < 4, 5000);
		}
	}

	@Override
	public String status() {
		return status;
	}
}
