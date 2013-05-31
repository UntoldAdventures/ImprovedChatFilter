package com.untoldadventures.improvedchatfilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ImprovedChatFilter extends JavaPlugin
{
	// Config Files
	public static File pluginFolder;
	public static File configFile;
	public static File wordsFile;
	public static FileConfiguration config;
	public static FileConfiguration wordsConfig;

	@Override
	public void onEnable()
	{
		// Events
		getServer().getPluginManager().registerEvents(new EventListner(), this);
		// Commands
		getCommand("cf").setExecutor(new ImprovedChatFilterCommandExecutor(this));
		// Config File
		pluginFolder = getDataFolder();
		configFile = new File(pluginFolder, "config.yml");
		wordsFile = new File(pluginFolder, "words.yml");
		config = new YamlConfiguration();
		wordsConfig = new YamlConfiguration();
		if (!pluginFolder.exists())
		{
			try
			{
				pluginFolder.mkdir();
			} catch (Exception ex)
			{
			}
		}
		if (!configFile.exists())
		{
			try
			{
				configFile.createNewFile();
				
				ImprovedChatFilter.config.set("announce-to-ops", true);
				ImprovedChatFilter.config.set("announce-to-ops-message", "The player, [player], tried to say [message]!");
				ImprovedChatFilter.config.set("caps-message", "Watch the Caps!");
				ImprovedChatFilter.config.set("ip-message", "IP's are not allowed!");
				ImprovedChatFilter.config.set("url-message", "URL's are not allowed!");
				ImprovedChatFilter.config.set("watch-commands", true);	
				ImprovedChatFilter.config.set("add-word", "The word, [word] has been added!");
				ImprovedChatFilter.config.set("word-already-exists", "The word, [word] is already on the list!");
				ImprovedChatFilter.config.set("delete-word", "The word, [word], has been deleted!");
				ImprovedChatFilter.config.set("word-doesnt-exist", "The word, [word] isn't on the list!");
				ImprovedChatFilter.config.set("list", "Currently, the word(s), [words], are not allowed!");
				ImprovedChatFilter.config.set("no-words-on-list", "There aren't any words on the list!");
				ImprovedChatFilter.config.set("reload-message", "Config Reloaded!");
				ImprovedChatFilter.config.set("incorrect-usage", "Incorrect Usage!");
				
			
			} catch (Exception ex)
			{
			}
		}
		if (!wordsFile.exists())
		{
			try
			{
				wordsFile.createNewFile();
				List<String> words = new ArrayList<String>();
				words.add("fuck");
				words.add("bitch");
				ImprovedChatFilter.wordsConfig.set("words", words);
			} catch (Exception ex)
			{
			}
		}
		try
		{
			config.load(configFile);
			wordsConfig.load(wordsFile);
		} catch (Exception ex)
		{
		}
		this.saveConfig();
	}
	@Override
	public void onDisable()
	{
	}
	public void saveConfig()
	{
		try
		{
			config.save(configFile);
			wordsConfig.save(wordsFile);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void loadConfig()
	{
		try
		{
			config.load(configFile);
			wordsConfig.load(wordsFile);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static String editString(String string, int pos, char replacement)
	{
		StringBuilder sb = new StringBuilder(string);
		sb.setCharAt(pos,replacement);
		string = sb.toString();
		return string;
	}
}
