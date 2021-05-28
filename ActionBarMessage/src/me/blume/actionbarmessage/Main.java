package me.blume.actionbarmessage;

import org.bukkit.plugin.java.JavaPlugin;

import me.blume.actionbarmessage.commands.Message;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getCommand("/msg").setExecutor(new Message(this));
		loadConfig();
	}
	@Override
	public void onDisable() {
		
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
