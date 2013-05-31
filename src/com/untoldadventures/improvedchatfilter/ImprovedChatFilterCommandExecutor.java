package com.untoldadventures.improvedchatfilter;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ImprovedChatFilterCommandExecutor implements CommandExecutor
{

	ImprovedChatFilter plugin;

	public ImprovedChatFilterCommandExecutor(ImprovedChatFilter plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length == 0)
		{
			return false;
		}
		if (args[0].equalsIgnoreCase("add"))
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				if (sender.hasPermission("cf.add"))
				{
					if (args.length == 2)
					{
						List<String> words = ImprovedChatFilter.wordsConfig.getStringList("words");
						String newWord = args[1].toLowerCase();
						if (!words.contains(newWord))
						{
							words.add(newWord);
							ImprovedChatFilter.wordsConfig.set("words", words);
							String message = ImprovedChatFilter.config.getString("add-word").replace("[word]", newWord);
							this.pm(message, player);
							plugin.saveConfig();
							return true;
						}
						String alreadyExists = ImprovedChatFilter.config.getString("word-already-exists").replace("[word]", newWord);
						this.pm(alreadyExists, player);
						return true;
					}
					String incorrect = ImprovedChatFilter.config.getString("incorrect-usage");
					this.pm(incorrect, player);
					return false;
				}
				return true;
			}
			sender.sendMessage("[ChatFilter] This command may only be run by a player!");
			return true;
		}
		if (args[0].equalsIgnoreCase("remove"))
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				if (sender.hasPermission("cf.remove"))
				{
					List<String> words = ImprovedChatFilter.wordsConfig.getStringList("words");
					if (args.length == 2)
					{
						String newWord = args[1];
						if (words.contains(newWord))
						{
							words.remove(newWord);
							ImprovedChatFilter.wordsConfig.set("words", words);
							String message = ImprovedChatFilter.config.getString("delete-word").replace("[word]", newWord);
							this.pm(message, player);
							plugin.saveConfig();
							return true;
						}
						String message = ImprovedChatFilter.config.getString("word-doesnt-exist").replace("[word]", newWord);
						this.pm(message, player);
						return true;
					}
					String incorrect = ImprovedChatFilter.config.getString("incorrect-usage");
					this.pm(incorrect, player);
					return false;
				}
				return true;
			}
			sender.sendMessage("[ChatFilter] This command may only be run by a player!");
			return true;
		}
		if (args[0].equalsIgnoreCase("list"))
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				if (sender.hasPermission("cf.list"))
				{
					List<String> words = ImprovedChatFilter.wordsConfig.getStringList("words");
					String wordList = ImprovedChatFilter.wordsConfig.getStringList("words").toString();
					wordList = wordList.replace("[", "");
					wordList = wordList.replace("]", "");
					if (args.length == 1)
					{
						if (words.size() > 0)
						{
							String message = ImprovedChatFilter.config.getString("list").replace("[words]", wordList);
							this.pm(message, player);
							return true;
						}
						String message = ImprovedChatFilter.config.getString("no-words-on-list");
						this.pm(message, player);
						return true;
					}
					String incorrect = ImprovedChatFilter.config.getString("incorrect-usage");
					this.pm(incorrect, player);
					return false;
				}
				return true;
			}
			sender.sendMessage("[ChatFilter] This Command may Only be run by a Player!");
			return true;
		}
		if (args[0].equalsIgnoreCase("reload"))
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				if (sender.hasPermission("cf.reload"))
				{
					if (args.length == 1)
					{
						this.plugin.loadConfig();
						String incorrect = ImprovedChatFilter.config.getString("reload-message");
						this.pm(incorrect, player);
						return true;
					}
					String incorrect = ImprovedChatFilter.config.getString("incorrect-usage");
					this.pm(incorrect, player);
					return false;
				}
				return true;
			}
			sender.sendMessage("[ChatFilter] This Command may Only be run by a Player!");
			return true;
		}
		return false;
	}

	public void pm(String message, Player player)
	{
		player.sendMessage(ChatColor.AQUA  + "[ImprovedCF] " + ChatColor.GRAY + message);
	}
		

}
