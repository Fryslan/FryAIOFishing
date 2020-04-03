package org.fryslan.simple.aiofisher;

import net.runelite.api.coords.WorldPoint;
import org.fryslan.simple.aiofisher.data.Fish;
import org.fryslan.simple.aiofisher.data.Location;
import org.fryslan.simple.aiofisher.tasks.BankingTask;
import org.fryslan.simple.aiofisher.tasks.DropingTask;
import org.fryslan.simple.aiofisher.tasks.FishingTask;
import org.fryslan.simple.aiofisher.ui.UserInterface;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

import java.awt.*;
import java.util.*;
import java.util.List;

@ScriptManifest(author = "Fryslan", name = "FryAIOFisher", category = Category.FISHING, version = "0.10D",
		description = "Fishes every fish you want!", discord = "myDiscord#0000", servers = {"Zenyte"})
public class FryAIOFisher extends TaskScript {

	private List<Task> tasks = new ArrayList<>();
	public static HashMap<String, String> debugMap = new HashMap<>();
	public static Fish fish = Fish.SHRIMP;
	public static Location location = Location.CATHERBY;
	public static boolean banking = true;
	public static boolean loaded = false;

	@Override
	public void onExecute() {

		UserInterface.initilize();
		ctx.onCondition(() -> loaded, Integer.MAX_VALUE);

		tasks.addAll(Arrays.asList(
				new DropingTask(ctx, fish, location, banking),
				new BankingTask(ctx, fish, location, banking),
				new FishingTask(ctx, fish, location)
		));
	}

	@Override
	public List<Task> tasks() {
		return tasks;
	}

	@Override
	public boolean prioritizeTasks() {
		return true;
	}

	@Override
	public void onProcess() {
		super.onProcess();
	}

	@Override
	public void onTerminate() {
	}

	@Override
	public void onChatMessage(ChatMessage e) {
	}

	@Override
	public void paint(Graphics g) {
		Debug.debug("Script Status", this.getScriptStatus());
		Debug.debug("Loaded Tasks", "" + tasks.size());
		for (int i = 0; i < debugMap.entrySet().size(); i++) {
			String[] array = debugMap.keySet().toArray(new String[debugMap.keySet().size()]);
			String item = array[i];

			g.drawString(item + " = " + debugMap.get(item), 10, 50 + (15 * i));
		}

		for(WorldPoint point : Location.CATHERBY.getPath()){
			ctx.paint.drawTileMatrix((Graphics2D) g,point,Color.CYAN);
		}


	}

}