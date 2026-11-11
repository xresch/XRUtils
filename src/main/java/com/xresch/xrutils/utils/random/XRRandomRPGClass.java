package com.xresch.xrutils.utils.random;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.xresch.xrutils.base.XR;

public enum XRRandomRPGClass {

    // --------------------------------------------------
    // Character classes
    // --------------------------------------------------

    KNIGHT       (161, 107, 14, 14, 16,  8, 12, 10,  9, 10, 10, "A heavily armored warrior trained in defensive combat and battlefield protection.", "High defense, vitality, blocking and physical endurance.", "Low agility, limited magical abilities and slow movement."),
    WARRIOR      (177,  89, 16, 17, 14,  7,  9, 11, 12,  8, 10, "A versatile frontline combatant specializing in weapons and physical strength.", "High strength, attack power and stamina.", "Weak magical abilities and limited social skills."),
    FIGHTER      (162,  86, 15, 15, 13,  8, 10, 14, 12,  9, 10, "A disciplined combatant skilled in a variety of weapons and fighting techniques.", "Balanced physical attributes, accuracy and adaptability.", "Lacks specialized abilities and magical power."),
    MAGE         ( 89, 185,  8,  7,  9, 18, 15, 10, 10, 10, 10, "A practitioner of arcane magic who manipulates supernatural energies.", "High magical attack, mana and spellcasting ability.", "Low physical defense, health and stamina."),
    WIZARD       ( 84, 204,  7,  6,  8, 20, 14,  9,  9, 10, 11, "A scholarly spellcaster who masters magic through knowledge and study.", "Exceptional intelligence, magical power and concentration.", "Very fragile and physically weak."),
    SORCERER     ( 92, 183,  9,  7,  9, 19, 12, 11, 12, 12, 11, "A natural wielder of magic whose powers originate from innate supernatural talent.", "High magical damage, casting speed and versatility.", "Limited physical endurance and defensive abilities."),
    WITCH        ( 93, 172,  9,  8, 10, 16, 15, 11, 10, 14, 13, "A mystical practitioner specializing in curses, enchantments and forbidden rituals.", "Strong curses, magical utility, wisdom and luck.", "Moderate physical abilities and limited direct combat strength."),
    WARLOCK      (100, 181, 10,  9, 10, 18, 13, 10, 10, 12, 12, "A spellcaster who draws power from mysterious pacts and supernatural entities.", "High magical damage, curses and destructive spells.", "Dependence on magical resources and moderate defenses."),
    CLERIC       (119, 162, 11, 10, 14, 13, 17,  9,  9, 14, 10, "A divine spellcaster who combines healing magic with protective abilities.", "Healing, magical resistance, support and survivability.", "Low agility and limited offensive physical power."),
    PRIEST       ( 98, 183,  9,  8, 12, 15, 19,  9,  9, 16, 10, "A devoted spiritual practitioner specializing in divine magic and restoration.", "Exceptional healing, wisdom, support and spiritual protection.", "Low physical strength and limited offensive versatility."),
    PALADIN      (157, 124, 13, 14, 16, 10, 15, 10,  9, 14, 10, "A holy warrior combining martial prowess with divine powers.", "Strong defense, healing, physical combat and holy magic.", "Limited agility and slower magical progression."),
    RANGER       (136, 105, 13, 12, 12,  9, 12, 16, 15, 10, 12, "A wilderness specialist skilled in ranged combat, tracking and survival.", "Excellent accuracy, perception, agility and survival.", "Moderate defense and limited magical abilities."),
    HUNTER       (125,  96, 14, 11, 11,  8, 10, 17, 15,  9, 13, "A skilled tracker and predator who specializes in hunting dangerous creatures.", "High accuracy, dexterity, perception and critical attacks.", "Low magical power and limited defensive capabilities."),
    ARCHER       (114,  97, 12, 10, 10,  9, 10, 17, 15, 10, 11, "A ranged combat specialist trained in precision shooting and projectile weapons.", "Exceptional accuracy, critical hits and ranged damage.", "Low vitality, physical defense and close-combat strength."),
    ROGUE        (113,  98, 13, 10, 10, 10, 11, 16, 17, 11, 13, "A cunning opportunist who relies on stealth, agility and precision attacks.", "High evasion, critical chance, stealth and dexterity.", "Low health, defense and magical resistance."),
    THIEF        (102,  89, 12,  9,  9,  9, 10, 17, 18, 10, 14, "A nimble infiltrator specializing in stealing, lockpicking and avoiding detection.", "Exceptional agility, luck, stealth and dexterity.", "Weak physical endurance and limited direct combat power."),
    ASSASSIN     (131,  98, 14, 12, 11,  9, 10, 17, 18,  9, 12, "A deadly specialist trained in eliminating targets through stealth and precision.", "High critical damage, evasion, speed and penetration.", "Fragile defenses and limited sustained combat abilities."),
    BARD         (104, 137, 12,  9, 10, 13, 13, 12, 12, 18, 12, "An entertainer and spellcaster who uses music, stories and inspiration to influence others.", "Exceptional charisma, persuasion, support and social abilities.", "Moderate physical damage and limited defensive specialization."),
    DRUID        (115, 156, 12, 10, 13, 14, 16, 10, 10, 13, 11, "A nature-oriented spellcaster who commands natural forces and living creatures.", "Healing, nature magic, versatility and elemental resistance.", "Limited physical specialization and moderate agility."),
    MONK         (126, 105, 15, 12, 13, 10, 13, 15, 16, 11, 11, "A disciplined martial artist who combines physical mastery with inner energy.", "High agility, dexterity, stamina, evasion and unarmed combat.", "Limited equipment options and moderate magical damage."),
    BARBARIAN    (197,  64, 16, 19, 17,  6,  8,  9, 11,  8, 10, "A fierce warrior who relies on raw physical power and overwhelming aggression.", "Exceptional strength, health, vitality and physical damage.", "Very low intelligence, mana and magical resistance."),
    BERSERKER    (208,  73, 17, 19, 15,  7,  8, 11, 14,  8, 12, "A relentless combatant who channels fury into devastating attacks.", "Extreme attack power, critical damage and stamina.", "Poor defensive discipline, limited magic and unpredictable combat."),
    SAGE         ( 99, 172,  9,  8, 10, 16, 19, 10,  9, 13, 11, "A learned master of knowledge, ancient wisdom and diverse magical disciplines.", "Exceptional wisdom, magical knowledge and concentration.", "Low physical strength and limited agility."),
    ALCHEMIST    ( 90, 151, 11,  9, 10, 16, 15, 12, 10, 12, 13, "A scientific practitioner who transforms materials into potions, compounds and magical substances.", "Strong crafting, intelligence, utility and resourcefulness.", "Limited direct combat abilities and physical endurance."),
    NECROMANCER  ( 97, 192,  9,  8,  9, 19, 16,  9,  9, 11, 12, "A dark spellcaster who manipulates death, spirits and the forces of undeath.", "High magical damage, curses, summoning and death magic.", "Low vitality and limited conventional healing."),
    SUMMONER     ( 96, 173, 10,  8, 10, 17, 16, 10, 10, 13, 12, "A magical specialist who calls forth creatures and supernatural entities to fight.", "Powerful summons, magical versatility and battlefield control.", "Dependent on summoned allies and magical resources."),
    SHAMAN       (115, 154, 12, 10, 13, 14, 17, 10, 10, 12, 12, "A spiritual practitioner who communicates with spirits and harnesses elemental forces.", "Strong elemental magic, healing, wisdom and spiritual resistance.", "Moderate physical damage and limited specialized defenses."),
    HEALER       ( 94, 165, 10,  8, 13, 13, 19,  9,  9, 15, 10, "A dedicated restoration specialist who keeps allies alive through healing abilities.", "Exceptional healing, mana efficiency and supportive magic.", "Very low offensive damage and limited physical combat ability."),
    GUARDIAN     (153, 116, 14, 13, 18, 10, 14, 10,  9, 12, 10, "A protective defender who specializes in shielding allies and absorbing damage.", "Exceptional vitality, defense, blocking and resistance.", "Low mobility, attack speed and offensive specialization."),
    SAMURAI      (152,  97, 14, 15, 12,  9, 11, 16, 14, 10, 11, "A disciplined swordsman who follows a strict martial tradition and values precision.", "High physical attack, dexterity, accuracy and weapon mastery.", "Limited magic and moderate defensive abilities."),
    NINJA        (111, 108, 13, 10, 10, 11, 12, 17, 18, 10, 14, "A covert operative trained in infiltration, deception and unconventional combat.", "Exceptional agility, stealth, critical chance and evasion.", "Low vitality and limited sustained damage."),
    DUELIST      (143,  99, 13, 13, 11,  9, 10, 17, 16, 11, 12, "A refined fighter who excels in one-on-one combat and precise weapon techniques.", "High dexterity, agility, accuracy and parrying.", "Limited area damage and weaker magical defenses."),
    LANCER       (155,  98, 14, 14, 13,  8, 10, 15, 13,  9, 10, "A polearm specialist who uses reach, timing and powerful thrusting attacks.", "Strong physical damage, reach, accuracy and balanced defense.", "Limited magical versatility and moderate agility."),
    WARDEN       (146, 127, 13, 13, 17, 11, 14, 12, 10, 12, 11, "A steadfast protector who guards territories, people and natural places.", "High vitality, defense, resistance and survival.", "Moderate offensive damage and limited mobility."),
    TEMPLAR      (157, 126, 13, 14, 16, 10, 15, 10,  9, 13, 10, "A militant guardian devoted to sacred principles and disciplined combat.", "Strong defense, holy magic, resistance and physical power.", "Low agility and limited offensive magical specialization."),
    CRUSADER     (168, 105, 14, 15, 17,  9, 13, 10,  9, 12, 10, "A zealous warrior who fights through conviction, endurance and martial discipline.", "High health, physical attack, defense and endurance.", "Low agility, limited mana and specialized magical abilities."),
    MYSTIC       ( 99, 174, 10,  8, 10, 17, 17, 11, 11, 12, 12, "A seeker of hidden knowledge who manipulates subtle mystical energies.", "Balanced magical attack, wisdom, arcane power and utility.", "Low physical damage and moderate defensive abilities."),
    ENCHANTER    ( 90, 173,  9,  8, 10, 18, 15, 11, 10, 14, 11, "A magical specialist who imbues objects, creatures and allies with supernatural properties.", "Strong enchantments, buffs, magical control and intelligence.", "Limited physical combat and direct destructive power."),
    ILLUSIONIST  ( 97, 160,  9,  8,  9, 17, 13, 13, 14, 13, 13, "A deceptive spellcaster who manipulates perception, senses and appearances.", "High deception, evasion, agility and illusion magic.", "Low physical endurance and limited direct damage."),
    ELEMENTALIST ( 96, 199,  9,  8, 10, 19, 15, 10, 10, 11, 11, "A powerful spellcaster who commands elemental forces and destructive energies.", "Exceptional elemental damage, mana and magical penetration.", "Low health, physical defense and stamina."),
    BEASTMASTER  (145, 118, 15, 13, 14, 10, 12, 15, 14, 12, 12, "A wilderness warrior who bonds with animals and fights alongside beast companions.", "Strong survival, agility, physical combat and companion support.", "Dependent on companions and moderate magical abilities."),
    ARTIFICER    (114, 157, 13, 11, 12, 17, 13, 13, 10, 11, 12, "An inventive creator who combines engineering, craftsmanship and magical technology.", "High intelligence, crafting, utility and magical ingenuity.", "Limited mobility and moderate direct combat abilities."),
    SPEARMAN     (153,  86, 14, 14, 13,  8, 10, 15, 13,  9, 10, "A disciplined polearm fighter specializing in formation combat and precise strikes.", "High physical attack, reach, accuracy and dexterity.", "Limited magical abilities and moderate mobility."),
    SWORDSMAN    (155,  85, 14, 14, 13,  8, 10, 14, 13,  9, 10, "A dedicated blade wielder who masters sword techniques and close combat.", "Strong physical damage, weapon proficiency and balanced attributes.", "Limited magic and specialized ranged abilities."),
    GUNSLINGER   (116, 104, 13, 10, 10, 11, 10, 17, 15, 10, 13, "A ranged combatant who specializes in firearms, precision and quick reactions.", "High accuracy, dexterity, critical chance and attack speed.", "Low vitality and limited close-combat defense."),
    MUSKETEER    (127, 103, 12, 11, 11, 11, 11, 16, 14, 11, 12, "A trained marksman who combines firearms with disciplined battlefield tactics.", "Strong ranged damage, accuracy and tactical versatility.", "Limited close-combat power and moderate magical abilities."),
    SENTINEL     (148, 112, 13, 13, 18, 10, 13, 11, 10, 11, 10, "A vigilant defender trained to detect threats and protect vulnerable allies.", "High defense, vitality, perception and resistance.", "Low agility, magical damage and offensive specialization."),
    SPELLBLADE   (149, 141, 12, 13, 12, 15, 12, 13, 13, 11, 11, "A hybrid warrior who combines weapon mastery with offensive spellcasting.", "Balanced physical and magical attack, dexterity and versatility.", "Less specialized than dedicated warriors or spellcasters."),
    BATTLEMAGE   (142, 162, 12, 13, 12, 17, 13, 11, 11, 10, 10, "A combat-trained mage who uses destructive spells alongside martial techniques.", "Strong magical damage, physical attack and combat versatility.", "Moderate defenses and higher resource demands.");

    // --------------------------------------------------
    // Core stats and class descriptions
    // --------------------------------------------------

    private final String description;
    private final String strengths;
    private final String weaknesses;

    private final LinkedHashMap<String, Integer> coreStats;
    private final LinkedHashMap<String, Integer> baseStats;
    private final LinkedHashMap<String, Double> growthRates;
    private final LinkedHashMap<String, String> formulas;

    // --------------------------------------------------
    // Constructor
    // --------------------------------------------------

    XRRandomRPGClass(
            int HP, int MP, int SP,
            int STR, int VIT, int INT, int WIS,
            int DEX, int AGI, int CHA, int LUK,
            String description,
            String strengths,
            String weaknesses) {

        this.description = description;
        this.strengths = strengths;
        this.weaknesses = weaknesses;

        coreStats = new LinkedHashMap<>();

        coreStats.put("HP", HP);
        coreStats.put("MP", MP);
        coreStats.put("SP", SP);
        coreStats.put("STR", STR);
        coreStats.put("VIT", VIT);
        coreStats.put("INT", INT);
        coreStats.put("WIS", WIS);
        coreStats.put("DEX", DEX);
        coreStats.put("AGI", AGI);
        coreStats.put("CHA", CHA);
        coreStats.put("LUK", LUK);

        baseStats = new LinkedHashMap<>(coreStats);
        growthRates = new LinkedHashMap<>();
        formulas = new LinkedHashMap<>();

        // Calculate growth for every core and derived stat.
        for (XRRandomRPGStat stat : XRRandomRPGStat.values()) {
            double average = stat.average(coreStats);
            double growth = Math.max(0.1, average / 10.0 * stat.getGrowthScale());
            String statName = stat.name();

            if (stat.isCoreStat()) {
                // Core stats retain their supplied level-one values.
                int base = coreStats.get(statName);
                growthRates.put(statName, growth);
                formulas.put(
                        statName,
                        "round(" + base + " + (level - 1) * "
                        + String.format("%.2f", growth)
                        + " * rarity.multiplier)"
                );
            } else {
                double base = Math.max(stat.getMinimum(), average * stat.getBaseScale() );

                baseStats.put(statName, (int) Math.round(base));
                growthRates.put(statName, growth);

                formulas.put(
                        statName,
                        "round(round(max(" + stat.getMinimum() + ", ("
                        + stat.expression() + ") * "
                        + stat.getBaseScale() + ")) + (level - 1) * "
                        + String.format("%.2f", growth)
                        + " * rarity.multiplier)"
                );
            }
        }
    }

    // --------------------------------------------------
    // Class getters
    // --------------------------------------------------

    public String getDescription() {
        return description;
    }

    public String getStrengths() {
        return strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public Map<String, Integer> getLevelOneStats() {
        return Map.copyOf(baseStats);
    }

    public Map<String, String> getAllFormulas() {
        return Map.copyOf(formulas);
    }

    public String getFormula(String stat) {
        return formulas.get(stat.toUpperCase(java.util.Locale.ROOT));
    }

    
    /****************************************************************
     * 
     ****************************************************************/
    public static XRRandomRPGClass getRandom() {
    	return XR.Random.fromArray( XRRandomRPGClass.values() );
    }
    	
    /****************************************************************
     * 
     ****************************************************************/
    public RGPClassData atLevel(XRRandomRPGRarity rarity, int level) {

        if (rarity == null) {
            throw new IllegalArgumentException("Rarity must not be null");
        }

        if (level < 1) {
            throw new IllegalArgumentException("Level must be at least 1");
        }

        LinkedHashMap<String, Integer> stats = new LinkedHashMap<>(baseStats);

        for (var entry : growthRates.entrySet()) {
            double value = baseStats.get(entry.getKey())
                    + entry.getValue() * rarity.multiplier() * (level - 1);

            stats.put(entry.getKey(), (int) Math.round(value));
        }

        return new RGPClassData(
                name(),
                description,
                strengths,
                weaknesses,
                rarity,
                level,
                stats,
                formulas
        );
    }

    // --------------------------------------------------
    // Character sheet record
    // --------------------------------------------------

    public record RGPClassData(
            String className,
            String description,
            String strengths,
            String weaknesses,
            XRRandomRPGRarity rarity,
            int level,
            LinkedHashMap<String, Integer> stats,
            LinkedHashMap<String, String> formulas
            ){

        /****************************************************************
         * Constructor
         ****************************************************************/
        public RGPClassData {
        	
            stats = new LinkedHashMap<String, Integer>(stats);
            formulas = new LinkedHashMap<String, String>(formulas);
        }

        /****************************************************************
         * 
         ****************************************************************/
        public int getStat(String stat) {

            return stats.getOrDefault( stat.toUpperCase(), 0);
        }
        
        /****************************************************************
         * 
         ****************************************************************/
        public String getFormula(String stat) {

            return formulas.get(stat.toUpperCase());
        }

        /****************************************************************
         * 
         ****************************************************************/
        public JsonObject toJsonObject() {

            JsonObject statsJson = new JsonObject();

            stats.forEach( (key, value) -> statsJson.addProperty(key, value) );

            JsonObject formulasJson = new JsonObject();

            formulas.forEach( (key, value) -> formulasJson.addProperty(key, value) );

            JsonObject json = new JsonObject();

            json.addProperty("level", level);
            json.addProperty("rarity", rarity.name());
            json.addProperty("class", className);
            json.addProperty("description", description);
            json.addProperty("strengths", strengths);
            json.addProperty("weaknesses", weaknesses);
            json.addProperty("rarityMultiplier", rarity.multiplier());
            json.add("stats", statsJson);
            json.add("formulas", formulasJson);

            return json;
        }
        
        /****************************************************************
         * 
         ****************************************************************/
        public JsonObject toJsonObjectFlat() {
            return appendToJsonObject(new JsonObject());
        }
        
        /****************************************************************
         * 
         ****************************************************************/
        public JsonObject appendToJsonObject(JsonObject json) {

        	json.addProperty("level", level);
        	json.addProperty("rarity", rarity.name());
            json.addProperty("class", className);
            json.addProperty("description", description);
            json.addProperty("strengths", strengths);
            json.addProperty("weaknesses", weaknesses);
            json.addProperty("rarityMultiplier", rarity.multiplier());

            stats.forEach( (key, value) -> json.addProperty(key, value) );
            formulas.forEach( (key, value) -> json.addProperty("MATH_"+key, value) );

            return json;
        }
    }
}