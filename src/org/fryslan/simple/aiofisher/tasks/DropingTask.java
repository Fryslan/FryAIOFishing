package org.fryslan.simple.aiofisher.tasks;

import org.fryslan.simple.aiofisher.data.Fish;
import org.fryslan.simple.aiofisher.data.Location;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class DropingTask extends Task {
	private final ClientContext ctx;
	private final Fish fish;
	private final Location location;
	private final boolean banking;
	private String status = "Dropping Task";

	public DropingTask(ClientContext ctx, Fish fish, Location location, boolean banking) {
		super(ctx);
		this.ctx = ctx;
		this.fish = fish;
		this.location = location;
		this.banking = banking;
	}

	@Override
	public boolean condition() {
		return ctx.inventory.inventoryFull() && !banking;
	}

	@Override
	public void run() {

	}

	@Override
	public String status() {
		return status;
	}
}
