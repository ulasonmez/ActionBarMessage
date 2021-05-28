package me.blume.actionbarmessage.commands;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import me.blume.actionbarmessage.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;





public class Message implements CommandExecutor{
	String[] msg1;
	int a;
	@SuppressWarnings("unused")
	private Main plugin;
	public Message(Main plugin) {
		this.plugin=plugin;
	}
	public BukkitTask task;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player msgreciever = Bukkit.getPlayer(args[0]);
		Player msgsender = (Player) sender;
		if(label.equals("/msg")) {
			if(args.length>=2) {
				if(msgreciever!=null) {
					msg1= new String[args.length-1];
					for(int i =1,j=0;i<args.length;i++,j++) {
						msg1[j] = args[i];
					}
					
					a=plugin.getConfig().getInt("msgtime");
					task=Bukkit.getScheduler().runTaskTimer(plugin, new Runnable(){
						@Override
						public void run() {
							if(a<0 ) {
								task.cancel();
							}
							else {
								msgreciever.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA+msgsender.getName()+": "+ChatColor.WHITE+String.join(" ",msg1)));	
								a--;}
						}}, 0L, 20L);
				}
				else {
					msgsender.sendMessage("Could not find a player with that name");
				}
			}
		}
		
		return false;
	}
}
