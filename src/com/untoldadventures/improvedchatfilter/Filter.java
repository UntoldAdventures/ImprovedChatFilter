package com.untoldadventures.improvedchatfilter;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Filter
{
	public static String clean(String word)
	{
		word = word.toLowerCase();
		word = word.trim();
		word = word.replace("2", "");
		word = word.replace("6", "");
		word = word.replace("8", "b");
		word = word.replace("9", "");
		word = word.replace("5", "");
		word = word.replaceAll(" ", "");
		word = word.replace("3", "e");
		word = word.replace("0", "o");
		word = word.replace("4", "a");
		word = word.replace("7", "t");
		word = word.replace("1", "i");
		word = word.replace("@", "a");
		word = word.replace("`", "");
		word = word.replace("~", "");
		word = word.replace("!", "i");
		word = word.replace("#", "");
		word = word.replace("$", "s");
		word = word.replace("%", "");
		word = word.replace("^", "");
		word = word.replace("&", "");
		word = word.replace("*", "");
		word = word.replace("(", "");
		word = word.replace(")", "");
		word = word.replace("-", "");
		word = word.replace("_", "");
		word = word.replace("+", "");
		word = word.replace("=", "");
		word = word.replace("{", "");
		word = word.replace("}", "");
		word = word.replace("[", "");
		word = word.replace("]", "");
		word = word.replace("|", "");
		word = word.replace(":", "");
		word = word.replace(";", "");
		word = word.replace("'", "");
		word = word.replace(",", "");
		word = word.replace("<", "");
		word = word.replace(".", "");
		word = word.replace(">", "");
		word = word.replace("/", "");
		word = word.replace("?", "");
		word = Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		word = word.replaceAll("(.)\\1+", "$1");
		return word;
	}
	public static String clean2(String word)
	{
		word = word.toLowerCase();
		word = word.trim();
		word = word.replace("2", "");
		word = word.replace("6", "");
		word = word.replace("8", "");
		word = word.replace("9", "");
		word = word.replace("5", "");
		word = word.replace(" ", "");
		word = word.replaceAll(" ", "");
		word = word.replace("3", "");
		word = word.replace("0", "");
		word = word.replace("4", "");
		word = word.replace("7", "");
		word = word.replace("1", "");
		word = word.replace("@", "");
		word = word.replace("`", "");
		word = word.replace("~", "");
		word = word.replace("!", "");
		word = word.replace("#", "");
		word = word.replace("$", "");
		word = word.replace("%", "");
		word = word.replace("^", "");
		word = word.replace("&", "");
		word = word.replace("*", "");
		word = word.replace("(", "");
		word = word.replace(")", "");
		word = word.replace("-", "");
		word = word.replace("_", "");
		word = word.replace("+", "");
		word = word.replace("=", "");
		word = word.replace("{", "");
		word = word.replace("}", "");
		word = word.replace("[", "");
		word = word.replace("]", "");
		word = word.replace("|", "");
		word = word.replace(":", "");
		word = word.replace(";", "");
		word = word.replace("'", "");
		word = word.replace(",", "");
		word = word.replace("<", "");
		word = word.replace(".", "");
		word = word.replace(">", "");
		word = word.replace("/", "");
		for (int i = 0;i < word.length(); i++)
		{
			char letter = word.charAt(i);
			String spacer = " ";
			char space = spacer.charAt(0);
			if (letter == space)
			{
				Player player = Bukkit.getPlayer("masons123456");
				player.sendMessage("i");
				String emptyer = " ";
				char empty = emptyer.charAt(0);
				word = ImprovedChatFilter.editString(word, i, empty);
			}
		}
		word = word.replace("?", "");
		word = Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		word = word.replace(" ", "");
		return word;
	}
	public static String clean3(String word)
	{
		word = word.toLowerCase();
		word = word.trim();
		word = word.replaceAll(" ", "");
		word = word.replace("2", "");
		word = word.replace("6", "");
		word = word.replace("8", "");
		word = word.replace("9", "");
		word = word.replace("5", "");
		word = word.replace(" ", "");
		word = word.replace("3", "");
		word = word.replace("0", "");
		word = word.replace("4", "");
		word = word.replace("7", "");
		word = word.replace("1", "");
		word = word.replace("@", "");
		word = word.replace("`", "");
		word = word.replace("~", "");
		word = word.replace("!", "");
		word = word.replace("#", "");
		word = word.replace("$", "");
		word = word.replace("%", "");
		word = word.replace("^", "");
		word = word.replace("&", "");
		word = word.replace("*", "");
		word = word.replace("(", "");
		word = word.replace(")", "");
		word = word.replace("-", "");
		word = word.replace("_", "");
		word = word.replace("+", "");
		word = word.replace("=", "");
		word = word.replace("{", "");
		word = word.replace("}", "");
		word = word.replace("[", "");
		word = word.replace("]", "");
		word = word.replace("|", "");
		word = word.replace(":", "");
		word = word.replace(";", "");
		word = word.replace("'", "");
		word = word.replace(",", "");
		word = word.replace("<", "");
		word = word.replace(".", "");
		word = word.replace(">", "");
		word = word.replace("/", "");
		word = word.replace("?", "");
		word = word.replace("i", "");
		word = Normalizer.normalize(word, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		return word;
	}
	public static String clean4(String word)
	{
		word = word.toLowerCase();
		word = word.trim();
		word = word.replace("@", "a");
		word = word.replace("$", "s");
		word = word.replace("3", "e");
		word = word.replace("^", "a");
		return word;
	}
//	public static String oneCheck(String message)
//	{
//		for (int x = 0;x < message.length(); x++)
//		{
//			String nothing = "";
//			char nothingness  = nothing.charAt(0);
//			message = ImprovedChatFilter.editString(message, x, nothingness);
//			if (message.contains(swear))
//		}
//	}
}
