package com.xresch.xrutils.utils.random;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**************************************************************************************************************
 * Used to calculate character stats for random RPG characters.
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 * 
 **************************************************************************************************************/
public enum XRRandomRPGStat {

	// Core stats
	HP        ("Health Points", "The character's total health before being defeated.", 1, 1.0, 1.30, true, "HP", 1),
	MP        ("Mana Points", "The resource used to cast spells and perform magical abilities.", 1, 1.0, 1.10, true, "MP", 1),
	SP        ("Stamina Points", "The resource used for physical abilities and strenuous actions.", 1, 1.0, 0.75, true, "SP", 1),
	STR       ("Strength", "Physical power affecting melee damage and carrying capacity.", 1, 1.0, 0.35, true, "STR", 1),
	VIT       ("Vitality", "Physical endurance affecting health, defense, and resistance.", 1, 1.0, 0.35, true, "VIT", 1),
	INT       ("Intelligence", "Mental ability affecting magical power, knowledge, and crafting.", 1, 1.0, 0.35, true, "INT", 1),
	WIS       ("Wisdom", "Understanding and insight affecting magical defense and perception.", 1, 1.0, 0.35, true, "WIS", 1),
	DEX       ("Dexterity", "Precision and coordination affecting accuracy and skilled actions.", 1, 1.0, 0.35, true, "DEX", 1),
	AGI       ("Agility", "Speed and reflexes affecting evasion, movement, and attack speed.", 1, 1.0, 0.35, true, "AGI", 1),
	CHA       ("Charisma", "Personal presence affecting persuasion, leadership, and social interactions.", 1, 1.0, 0.30, true, "CHA", 1),
	LUK       ("Luck", "Fortune affecting critical hits, random outcomes, and discoveries.", 1, 1.0, 0.30, true, "LUK", 1),

	// Combat
	ATK       ("Attack", "Determines the damage dealt by physical attacks.", 1, 2.0, 1.2, false, "STR", 2, "VIT", 1),
	DEF       ("Defense", "Reduces incoming physical damage.", 1, 2.0, 1.2, false, "VIT", 2, "STR", 1),
	MATK      ("Magic Attack", "Determines the damage dealt by offensive spells.", 1, 2.0, 1.2, false, "INT", 2, "WIS", 1),
	MDEF      ("Magic Defense", "Reduces incoming magical damage.", 1, 2.0, 1.2, false, "WIS", 2, "INT", 1),
	ACC       ("Accuracy", "Determines the chance of successfully hitting a target.", 1, 2.0, 0.8, false, "DEX", 2, "AGI", 1),
	EVA       ("Evasion", "Determines the chance of avoiding incoming attacks.", 1, 2.0, 0.8, false, "AGI", 2, "DEX", 1),
	CRIT      ("Critical Hit", "Determines the chance of dealing a critical hit.", 1, 1.5, 0.7, false, "LUK", 2, "DEX", 1),
	CDMG      ("Critical Damage", "Determines the additional damage dealt by critical hits.", 1, 2.0, 1.0, false, "STR", 1, "LUK", 2),
	ASPD      ("Attack Speed", "Determines how quickly physical attacks can be performed.", 1, 2.0, 1.0, false, "AGI", 2, "DEX", 1),
	CAST      ("Casting Speed", "Determines how quickly magical abilities can be cast.", 1, 2.0, 1.0, false, "INT", 2, "WIS", 1),
	PARRY     ("Parry", "Determines the ability to deflect incoming attacks with a weapon.", 1, 1.5, 0.7, false, "DEX", 2, "STR", 1),
	BLOCK     ("Block", "Determines the ability to block incoming attacks with a shield or defense.", 1, 1.5, 0.7, false, "VIT", 2, "STR", 1),
	PEN       ("Armor Penetration", "Ignores a portion of an opponent's physical defense.", 1, 1.5, 0.8, false, "STR", 2, "DEX", 1),
	MPEN      ("Magic Penetration", "Ignores a portion of an opponent's magical defense.", 1, 1.5, 0.8, false, "INT", 2, "WIS", 1),
	ARM       ("Armor", "Reduces physical damage through protective equipment and natural toughness.", 1, 2.0, 1.0, false, "VIT", 2, "STR", 1),
	LIFESTEAL ("Life Steal", "Restores health based on damage dealt to enemies.", 1, 1.0, 0.4, false, "STR", 1, "LUK", 1),
	REFLECT   ("Damage Reflection", "Returns a portion of incoming damage to the attacker.", 1, 1.0, 0.4, false, "VIT", 2, "WIS", 1),

	// Elemental damage
	FIRE      ("Fire Damage", "Increases damage dealt through fire-based attacks and spells.", 1, 2.0, 1.0, false, "INT", 2, "STR", 1),
	WATER     ("Water Damage", "Increases damage dealt through water-based attacks and spells.", 1, 2.0, 1.0, false, "WIS", 2, "INT", 1),
	ICE       ("Ice Damage", "Increases damage dealt through ice-based attacks and spells.", 1, 2.0, 1.0, false, "INT", 1, "WIS", 2),
	EARTH     ("Earth Damage", "Increases damage dealt through earth-based attacks and spells.", 1, 2.0, 1.0, false, "VIT", 2, "INT", 1),
	WIND      ("Wind Damage", "Increases damage dealt through wind-based attacks and spells.", 1, 2.0, 1.0, false, "AGI", 2, "INT", 1),
	LIGHT     ("Light Damage", "Increases damage dealt through light-based attacks and spells.", 1, 2.0, 1.0, false, "WIS", 2, "CHA", 1),
	DARK      ("Dark Damage", "Increases damage dealt through darkness-based attacks and spells.", 1, 2.0, 1.0, false, "INT", 2, "LUK", 1),
	LIGHTNING ("Lightning Damage", "Increases damage dealt through lightning-based attacks and spells.", 1, 2.0, 1.0, false, "INT", 1, "AGI", 2),
	POISON    ("Poison Damage", "Increases damage dealt through poison-based attacks and effects.", 1, 2.0, 1.0, false, "INT", 1, "DEX", 2),
	HOLY      ("Holy Damage", "Increases damage dealt through holy and divine attacks and spells.", 1, 2.0, 1.0, false, "WIS", 2, "CHA", 1),
	ARCANE    ("Arcane Damage", "Increases damage dealt through arcane and pure magical attacks.", 1, 2.0, 1.0, false, "INT", 2, "WIS", 2),

	// Elemental resistance
	FRES      ("Fire Resistance", "Reduces damage received from fire-based attacks.", 1, 1.5, 0.7, false, "VIT", 2, "INT", 1),
	WRES      ("Water Resistance", "Reduces damage received from water-based attacks.", 1, 1.5, 0.7, false, "WIS", 2, "VIT", 1),
	IRES      ("Ice Resistance", "Reduces damage received from ice-based attacks.", 1, 1.5, 0.7, false, "VIT", 2, "WIS", 1),
	ERES      ("Earth Resistance", "Reduces damage received from earth-based attacks.", 1, 1.5, 0.7, false, "VIT", 2, "STR", 1),
	WIRES     ("Wind Resistance", "Reduces damage received from wind-based attacks.", 1, 1.5, 0.7, false, "AGI", 1, "WIS", 2),
	LRES      ("Light Resistance", "Reduces damage received from light-based attacks.", 1, 1.5, 0.7, false, "WIS", 2, "INT", 1),
	DRES      ("Dark Resistance", "Reduces damage received from darkness-based attacks.", 1, 1.5, 0.7, false, "WIS", 2, "LUK", 1),
	LIRES     ("Lightning Resistance", "Reduces damage received from lightning-based attacks.", 1, 1.5, 0.7, false, "WIS", 2, "VIT", 1),
	PRES      ("Poison Resistance", "Reduces damage received from poison and toxic effects.", 1, 1.5, 0.7, false, "VIT", 2, "WIS", 1),
	HRES      ("Holy Resistance", "Reduces damage received from holy and divine attacks.", 1, 1.5, 0.7, false, "WIS", 2, "CHA", 1),
	ARES      ("Arcane Resistance", "Reduces damage received from arcane and pure magical attacks.", 1, 1.5, 0.7, false, "INT", 1, "WIS", 2),

	// Utility
	MOV       ("Movement Speed", "Determines how quickly the character moves.", 1, 1.5, 0.7, false, "AGI", 2, "VIT", 1),
	CARRY     ("Carrying Capacity", "Determines how much weight the character can carry.", 1, 3.0, 1.0, false, "STR", 2, "VIT", 1),
	STEALTH   ("Stealth", "Determines the ability to move and act without being detected.", 1, 2.0, 0.8, false, "AGI", 2, "DEX", 1),
	PER       ("Perception", "Determines the ability to notice hidden details, threats, and clues.", 1, 2.0, 0.8, false, "WIS", 1, "DEX", 1, "LUK", 1),
	SURV      ("Survival", "Determines the ability to endure and navigate dangerous environments.", 1, 2.0, 0.8, false, "VIT", 1, "WIS", 1, "AGI", 1),
	CRAFT     ("Crafting", "Determines skill and effectiveness when creating items and equipment.", 1, 2.0, 0.8, false, "DEX", 1, "INT", 2),
	GATHER    ("Gathering", "Determines effectiveness when collecting natural resources and materials.", 1, 2.0, 0.8, false, "WIS", 1, "DEX", 1, "LUK", 1),
	REGEN     ("Health Regeneration", "Determines how quickly health is restored over time.", 1, 1.5, 0.7, false, "VIT", 2, "WIS", 1),
	MREGEN    ("Mana Regeneration", "Determines how quickly mana is restored over time.", 1, 1.5, 0.7, false, "WIS", 2, "INT", 1),
	THREAT    ("Threat Generation", "Determines how effectively the character attracts enemy attention.", 1, 1.5, 0.7, false, "STR", 2, "CHA", 1),
	FORT      ("Fortune", "Influences favorable random outcomes and rare discoveries.", 1, 2.0, 0.8, false, "LUK", 2, "CHA", 1),
	TEN       ("Tenacity", "Determines resistance to interruptions, disabling effects, and control.", 1, 1.5, 0.7, false, "VIT", 1, "WIS", 2),
	POISE     ("Poise", "Determines the ability to maintain balance and resist being staggered.", 1, 1.5, 0.7, false, "VIT", 2, "STR", 1),
	WILL      ("Willpower", "Determines mental resilience against fear, coercion, and control.", 1, 2.0, 0.8, false, "WIS", 2, "INT", 1),
	FOCUS     ("Focus", "Determines the ability to concentrate and perform precise actions.", 1, 2.0, 0.8, false, "INT", 2, "WIS", 1),
	CONC      ("Concentration", "Determines the ability to maintain attention during prolonged tasks.", 1, 2.0, 0.8, false, "WIS", 2, "INT", 1),

	// Social
	INTIM     ("Intimidation", "Determines the ability to frighten or pressure others.", 1, 2.0, 0.8, false, "CHA", 1, "STR", 1),
	PERS      ("Persuasion", "Determines the ability to convince others through argument and influence.", 1, 2.0, 0.8, false, "CHA", 2, "WIS", 1),
	DECEP     ("Deception", "Determines the ability to mislead others through lies and disguise.", 1, 2.0, 0.8, false, "CHA", 1, "DEX", 1, "INT", 1),
	INS       ("Insight", "Determines the ability to understand motives, emotions, and intentions.", 1, 2.0, 0.8, false, "WIS", 2, "CHA", 1),
	EMP       ("Empathy", "Determines the ability to recognize and understand other people's feelings.", 1, 2.0, 0.8, false, "CHA", 2, "WIS", 1),
	LEAD      ("Leadership", "Determines the ability to inspire, guide, and coordinate others.", 1, 2.0, 0.8, false, "CHA", 2, "STR", 1),
	BAR       ("Bartering", "Determines the ability to negotiate prices and trade favorable deals.", 1, 2.0, 0.8, false, "CHA", 2, "LUK", 1),
	MERC      ("Mercantile", "Determines knowledge and skill in commerce, trade, and business.", 1, 2.0, 0.8, false, "INT", 1, "CHA", 2);

	private final int minimum;
	private final double baseScale;
	private final double growthScale;
	private final Map<String, Integer> weights;
	private final boolean coreStat;

	private final String fullName;
	private final String description;

	XRRandomRPGStat(
			String fullName,
			String description,
			int minimum,
			double baseScale,
			double growthScale,
			boolean coreStat,
			Object... pairs) {

		this.fullName = fullName;
		this.description = description;
		this.minimum = minimum;
		this.baseScale = baseScale;
		this.growthScale = growthScale;
		this.coreStat = coreStat;

		this.weights = new LinkedHashMap<>();

		for (int i = 0; i < pairs.length; i += 2) {

			weights.put(
					(String) pairs[i],
					(Integer) pairs[i + 1]
			);
		}
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}
	
	public double getGrowthScale() {
		return growthScale;
	}
	
	public double getMinimum() {
		return minimum;
	}
	
	public double getBaseScale() {
		return baseScale;
	}

	public boolean isCoreStat() {
		return coreStat;
	}

	/*****************************************************************************
	 * 
	 * @param stats
	 * @return
	 *****************************************************************************/
	public double average(Map<String, Integer> stats) {

		int total = 0;
		int divisor = 0;

		for (var entry : weights.entrySet()) {

			total += stats.get(entry.getKey())
					* entry.getValue();

			divisor += entry.getValue();
		}

		return (double) total / divisor;
	}

	/*****************************************************************************
	 * Returns the formula of the stat.
	 *****************************************************************************/
	public String expression() {

		StringJoiner joiner = new StringJoiner(" + ");

		for (var entry : weights.entrySet()) {

			joiner.add(
					entry.getKey() + "*" + entry.getValue()
			);
		}

		int divisor = weights.values().stream()
				.mapToInt(Integer::intValue)
				.sum();

		return "(" + joiner + ") / " + divisor + ".0";
	}
}