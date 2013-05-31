package com.untoldadventures.improvedchatfilter;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EventListner implements Listener
{
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChatEvent(AsyncPlayerChatEvent event)
	{
		String origMessage = event.getMessage();
		boolean kick = false, ban = false, ops = ImprovedChatFilter.config.getBoolean("announce-to-ops"), cancel = false;
		List<String> words = ImprovedChatFilter.wordsConfig.getStringList("words");
		Player player = event.getPlayer();
		if (!player.hasPermission("cf.swear"))
		{
			String message = event.getMessage().toLowerCase();
			String messageFil = Filter.clean(message);
			String messageFil2 = Filter.clean2(message);
			String messageFil3 = Filter.clean3(message);

			{
				if (message.matches("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b"))
				{
					event.setCancelled(true);
					if (kick)
					{
						player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
					} else if (ban)
					{
						player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
						player.setBanned(true);
						return;
					}
					String ipmessage = ImprovedChatFilter.config.getString("ip-message");
					this.pm(ipmessage, player);
					if (ops)
					{
						for (Player p : Bukkit.getServer().getOnlinePlayers())
						{
							if (p.isOp())
							{
								String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
								Opmessage = Opmessage.replace("[player]", player.getName());
								Opmessage = Opmessage.replace("[message]", origMessage);
								this.pm(Opmessage, p);
							}
						}
					}
					if (message.matches("\\b(https?|ftp|file)://[-A-Z0-9+&@#/%?=~_|!:,.;]*[-A-Z0-9+&@#/%=~_|]"))
					{
						event.setCancelled(true);
						if (kick)
						{
							player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
						} else if (ban)
						{
							player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
							player.setBanned(true);
							return;
						}
						String urlmessage = ImprovedChatFilter.config.getString("ip-message");
						this.pm(urlmessage, player);
						if (ops)
						{
							for (Player p : Bukkit.getServer().getOnlinePlayers())
							{
								if (p.isOp())
								{
									String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
									Opmessage = Opmessage.replace("[player]", player.getName());
									Opmessage = Opmessage.replace("[message]", origMessage);
									this.pm(Opmessage, p);
								}
							}
						}
					}
				}
				for (int t = 0; t < words.size(); t++)
				{
					message = message.replace("@", "a");
					messageFil = Filter.clean4(messageFil);
					messageFil2 = Filter.clean4(messageFil2);
					messageFil3 = Filter.clean4(messageFil3);
					String swear = words.get(t);

					if (messageFil.contains(swear) || messageFil2.contains(swear) || messageFil3.contains(swear) || messageFil.replace("@", "a").contains(swear))
					{
						if (swear.contains("a"))
						{
							message = message.replace("@", "a");
						}
						if (swear.contains("s"))
						{
							message = message.replace("$", "s");
						}
						if (!cancel)
						{
							char first = swear.charAt(0);
							char last = swear.charAt(swear.length() - 1);

							for (int p = 0; p < message.length(); p++)
							{
								if (message.charAt(p) == first)
								{

									for (int e = p + 1; e <= message.length() - 1 && e > p; e++)
									{
										for (int z = p; z <= e; z++)
										{
											if (swear.contains("e"))
											{
												String letter3 = "3";
												char let3 = letter3.charAt(0);
												if (message.charAt(e) == let3)
												{
													String letterE = "e";
													char letE = letterE.charAt(0);
													message = ImprovedChatFilter.editString(message, e, letE);
												}
											}
											if (swear.contains("s"))
											{
												String letter$ = "$";
												char let$ = letter$.charAt(0);
												if (message.charAt(e) == let$)
												{
													String letterS = "s";
													char letS = letterS.charAt(0);
													message = ImprovedChatFilter.editString(message, e, letS);
												}
											}
										}
										if (message.charAt(e) == last)
										{

											for (int n = p + 1; n < e && n > p; n++)
											{

												if (message.charAt(n) == swear.charAt(1) || message.length() <= 3)
												{
													for (int i = e - 1; i < e && i >= n; i--)
													{

														if (message.charAt(i) == swear.charAt(swear.length() - 2) || message.length() <= 3)
														{

															double intensity = swear.length() * 2;
															if (e - p < intensity)
															{
																for (int s = p; s <= e && s >= p; s++)
																{
																	String starS = "*";
																	char starC = starS.charAt(0);
																	message = ImprovedChatFilter.editString(message, s, starC);
																}

																if (kick)
																{
																	player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
																} else if (ban)
																{
																	player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
																	player.setBanned(true);
																	return;
																}
																if (ops)
																{
																	for (Player q : Bukkit.getServer().getOnlinePlayers())
																	{
																		if (q.isOp())
																		{
																			String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
																			Opmessage = Opmessage.replace("[player]", player.getName());
																			Opmessage = Opmessage.replace("[message]", origMessage);
																			this.pm(Opmessage, q);
																		}
																	}
																}
																event.setMessage(message);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}

						if (cancel)
						{

							event.setCancelled(true);
							String cancelMessage = ImprovedChatFilter.config.getString("block-message");
							cancelMessage = cancelMessage.replace("[word]", swear);
							this.pm(cancelMessage, player);
						}

					}

				}
			}
			// CAPS Blocking
			double capsCountt = 0.0D;
			double msglength = message.length();
			for (char c : message.toCharArray())
			{
				if (Character.isUpperCase(c))
				{
					capsCountt += 1.0D;
				}
				if (!Character.isLetterOrDigit(c))
				{
					msglength -= 1.0D;
				}
			}
			if (!player.hasPermission("cf.caps"))
			{
				message = event.getMessage();
				if (message.startsWith("i ") || message.startsWith("I "))
				{
					message = message.replace("i ", " I ").replace("i'm ", " I'm ").replace("im ", " I'm ").replace("Im ", "I'm ");
				}
				message = message.replace(" i ", " I ").replace(" i'm ", " I'm ").replace(" im ", " I'm ").replace(" Im ", " I'm ");
				event.setMessage(message);

				if (message.length() >= 2)
				{

					int percent = 66;
					double calc = capsCountt / msglength * 100.0D;
					if (calc >= percent)
					{
						if (!(message.equalsIgnoreCase(":D") || message.equalsIgnoreCase(":P") || message.equalsIgnoreCase(":(") || message.equalsIgnoreCase(":)") || message.equalsIgnoreCase("D:")))
						{
							String CapsMessage = ImprovedChatFilter.config.getString("caps-message");
							this.pm(CapsMessage, player);
							message = message.toLowerCase();
							event.setMessage(message);
						}
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event)
	{
		String[] Fullcommand = event.getMessage().replace("/", "").split(" ");
		String origMessage = event.getMessage();
		Player player = event.getPlayer();
		if (ImprovedChatFilter.config.getBoolean("watch-commands"))
		{

			boolean kick = false, ban = false, ops = ImprovedChatFilter.config.getBoolean("announce-to-ops"), cancel = false;
			List<String> words = ImprovedChatFilter.wordsConfig.getStringList("words");
			if (!player.hasPermission("cf.swear"))
			{
				String message = event.getMessage().toLowerCase();
				String messageFil = Filter.clean(message);
				String messageFil2 = Filter.clean2(message);
				String messageFil3 = Filter.clean3(message);

				{
					if (message.matches("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b"))
					{
						event.setCancelled(true);
						if (kick)
						{
							player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
						} else if (ban)
						{
							player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
							player.setBanned(true);
							return;
						}
						String ipmessage = ImprovedChatFilter.config.getString("ip-message");
						this.pm(ipmessage, player);
						if (ops)
						{
							for (Player p : Bukkit.getServer().getOnlinePlayers())
							{
								if (p.isOp())
								{
									String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
									Opmessage = Opmessage.replace("[player]", player.getName());
									Opmessage = Opmessage.replace("[message]", origMessage);
									this.pm(Opmessage, p);
								}
							}
						}
						if (message.matches("\\b(https?|ftp|file)://[-A-Z0-9+&@#/%?=~_|!:,.;]*[-A-Z0-9+&@#/%=~_|]"))
						{
							event.setCancelled(true);
							if (kick)
							{
								player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
							} else if (ban)
							{
								player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
								player.setBanned(true);
								return;
							}
							String urlmessage = ImprovedChatFilter.config.getString("ip-message");
							this.pm(urlmessage, player);
							if (ops)
							{
								for (Player p : Bukkit.getServer().getOnlinePlayers())
								{
									if (p.isOp())
									{
										String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
										Opmessage = Opmessage.replace("[player]", player.getName());
										Opmessage = Opmessage.replace("[message]", origMessage);
										this.pm(Opmessage, p);
									}
								}
							}
						}
					}
					for (int t = 0; t < words.size(); t++)
					{
						message = message.replace("@", "a");
						messageFil = Filter.clean4(messageFil);
						messageFil2 = Filter.clean4(messageFil2);
						messageFil3 = Filter.clean4(messageFil3);
						String swear = words.get(t);

						if (messageFil.contains(swear) || messageFil2.contains(swear) || messageFil3.contains(swear) || messageFil.replace("@", "a").contains(swear))
						{
							message = message.replace("@", "a");

							message = message.replace("$", "s");

							if (!cancel)
							{
								char first = swear.charAt(0);
								char last = swear.charAt(swear.length() - 1);
								for (int p = 0; p < message.length(); p++)
								{
									if (message.charAt(p) == first)
									{
										for (int e = p + 1; e <= message.length() - 1 && e >= p; e++)
										{
											for (int z = p; z <= e; z++)
											{
												if (swear.contains("s"))
												{
													int splitLength = Fullcommand.length;
													for (int v = 0; v < splitLength; v++)
													{
														for (Player players : Bukkit.getServer().getOnlinePlayers())
														{
															if (!Fullcommand[v].equalsIgnoreCase(players.getName()))
															{
																int stringLength = Fullcommand[v].length();
																for (int aa = 0; aa >= stringLength; aa++)
																{
																	String letter3 = "$";
																	char let3 = letter3.charAt(0);
																	if (message.charAt(aa) == let3)
																	{
																		String letterE = "s";
																		char letE = letterE.charAt(0);
																		message = ImprovedChatFilter.editString(message, e, letE);
																	}
																}
															}
														}
													}
												}
												if (swear.contains("a"))
												{
													int splitLength = Fullcommand.length;
													for (int v = 0; v < splitLength; v++)
													{
														for (Player players : Bukkit.getServer().getOnlinePlayers())
														{
															if (!Fullcommand[v].equalsIgnoreCase(players.getName()))
															{
																int stringLength = Fullcommand[v].length();
																for (int aa = 0; aa >= stringLength; aa++)
																{
																	String letter3 = "4";
																	char let3 = letter3.charAt(0);
																	if (message.charAt(aa) == let3)
																	{
																		String letterE = "a";
																		char letE = letterE.charAt(0);
																		message = ImprovedChatFilter.editString(message, e, letE);
																	}
																}
															}
														}
													}
												}
												if (swear.contains("i"))
												{
													int splitLength = Fullcommand.length;
													for (int v = 0; v < splitLength; v++)
													{
														for (Player players : Bukkit.getServer().getOnlinePlayers())
														{
															if (!Fullcommand[v].equalsIgnoreCase(players.getName()))
															{
																int stringLength = Fullcommand[v].length();
																for (int aa = 0; aa >= stringLength; aa++)
																{
																	String letter1 = "1";
																	char let3 = letter1.charAt(0);
																	if (message.charAt(aa) == let3)
																	{
																		String letterI = "i";
																		char letI = letterI.charAt(0);
																		message = ImprovedChatFilter.editString(message, e, letI);
																	}
																}
															}
														}
													}
												}
												if (swear.contains("e"))
												{
													int splitLength = Fullcommand.length;
													for (int v = 0; v < splitLength; v++)
													{
														for (Player players : Bukkit.getServer().getOnlinePlayers())
														{
															if (!Fullcommand[v].equalsIgnoreCase(players.getName()))
															{
																int stringLength = Fullcommand[v].length();
																for (int aa = 0; aa >= stringLength; aa++)
																{
																	String letter3 = "3";
																	char let3 = letter3.charAt(0);
																	if (message.charAt(aa) == let3)
																	{
																		String letterE = "e";
																		char letE = letterE.charAt(0);
																		message = ImprovedChatFilter.editString(message, e, letE);
																	}
																}
															}
														}
													}
												}
											}

											if (message.charAt(e) == last)
											{

												for (int n = p + 1; n < e && n > p; n++)
												{

													if (message.charAt(n) == swear.charAt(1) || message.length() <= 3)
													{
														for (int i = e - 1; i < e && i >= n; i--)
														{

															if (message.charAt(i) == swear.charAt(swear.length() - 2) || message.length() <= 3)
															{

																double intensity = swear.length() * 2;
																if (e - p < intensity)
																{

																	for (int s = p; s <= e && s >= p; s++)
																	{
																		String starS = "*";
																		char starC = starS.charAt(0);
																		message = ImprovedChatFilter.editString(message, s, starC);
																	}

																	if (kick)
																	{
																		player.kickPlayer(ImprovedChatFilter.config.getString("kick-message"));
																	} else if (ban)
																	{
																		player.kickPlayer(ImprovedChatFilter.config.getString("ban-message"));
																		player.setBanned(true);
																		return;
																	}
																	if (ops)
																	{
																		for (Player q : Bukkit.getServer().getOnlinePlayers())
																		{
																			if (q.isOp())
																			{
																				String Opmessage = ImprovedChatFilter.config.getString("announce-to-ops-message");
																				Opmessage = Opmessage.replace("[player]", player.getName());
																				Opmessage = Opmessage.replace("[message]", origMessage);
																				this.pm(Opmessage, q);
																			}
																		}
																	}
																	event.setMessage(message);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}

							if (cancel)
							{

								event.setCancelled(true);
								String cancelMessage = ImprovedChatFilter.config.getString("block-message");
								cancelMessage = cancelMessage.replace("[word]", swear);
								this.pm(cancelMessage, player);
							}

						}

					}
				}
				// CAPS Blocking
				double capsCountt = 0.0D;
				double msglength = message.length();
				for (char c : message.toCharArray())
				{
					if (Character.isUpperCase(c))
					{
						capsCountt += 1.0D;
					}
					if (!Character.isLetterOrDigit(c))
					{
						msglength -= 1.0D;
					}
				}
				if (!player.hasPermission("cf.caps"))
				{
					message = event.getMessage();
					if (message.startsWith("i ") || message.startsWith("I "))
					{
						message = message.replace("i ", " I ").replace("i'm ", " I'm ").replace("im ", " I'm ").replace("Im ", "I'm ");
					}
					message = message.replace(" i ", " I ").replace(" i'm ", " I'm ").replace(" im ", " I'm ").replace(" Im ", " I'm ");
					event.setMessage(message);

					if (message.length() >= 2)
					{

						int percent = 66;
						double calc = capsCountt / msglength * 100.0D;
						if (calc >= percent)
						{
							if (!(message.equalsIgnoreCase(":D") || message.equalsIgnoreCase(":P") || message.equalsIgnoreCase(":(") || message.equalsIgnoreCase(":)") || message.equalsIgnoreCase("D:")))
							{
								String CapsMessage = ImprovedChatFilter.config.getString("caps-message");
								this.pm(CapsMessage, player);
								message = message.toLowerCase();
								event.setMessage(message);
							}
						}
					}
				}
			}
		}

	}
	public void pm(String message, Player player)
	{
		player.sendMessage(ChatColor.GRAY + message);

	}
	public String capitalizeFirstLetterOfEachSentence(String str)
	{
		char[] arr = str.toCharArray();
		boolean cap = true;
		boolean space_found = true;
		for (int i = 0; i < arr.length; i++)
		{
			if (cap)
			{
				// white space includes \n, space
				if (Character.isWhitespace(arr[i]))
				{
					space_found = true;
				} else
				{
					if (space_found && !Character.isUpperCase(arr[i]))
					{
						arr[i] = Character.toUpperCase(arr[i]);
					}

					cap = false;
					space_found = false;
				}
			} else
			{
				if (arr[i] == '.' || arr[i] == '?' || arr[i] == '!')
				{
					cap = true;
				}
			}
		}
		return new String(arr);
	}
}
