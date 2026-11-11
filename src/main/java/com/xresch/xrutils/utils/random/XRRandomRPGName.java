package com.xresch.xrutils.utils.random;

import com.google.gson.JsonObject;
import com.xresch.xrutils.utils.XRRandom;

/**************************************************************************************************************
 * Generates random Fantasy RPG character names.
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 * 
 **************************************************************************************************************/
public final class XRRandomRPGName {

    // =========================================================================
    // Male first name components
    // =========================================================================


	private static final String[] maleNameBeginning = {
	    "Ael", "Aer", "Ald", "Alv", "Ar", "Ard", "As", "Ash", "Bal", "Bel", "Ber", "Bor", "Bran", "Cael", "Cal", "Cas", "Cor", "Dael", "Dar", "Del", "Dra", "Drak", "Eld", "Elr", "Em", "Er", "Fael", "Fal", "Fen", "Gal",
	    "Gar", "Gil", "Gor", "Had", "Hal", "Har", "Hel", "Ith", "Jar", "Kael", "Kal", "Kar", "Kel", "Kor", "Krag", "Laer", "Lan", "Lor", "Mal", "Mar", "Mor", "Nal", "Nar", "Nor", "Ober", "Or", "Rael", "Ral", "Rav", "Ren",
	    "Ror", "Sael", "Sar", "Sel", "Sha", "Tal", "Tar", "Ther", "Thorn", "Tor", "Ul", "Val", "Var", "Vel", "Vor", "Wul", "Xar", "Yar", "Zar", "Zor", "Ad", "Al", "An", "And", "Anton", "Aric", "Arn", "Art", "Bald", "Ben",
	    "Bern", "Bren", "Brun", "Ced", "Cyr", "Dan", "Dav", "Den", "Derr", "Dom", "Ed", "Edm", "Edr", "Eld", "Elric", "Emil", "Erik", "Evan", "Fab", "Fel", "Fer", "Finn", "Flav", "Flor", "Fred", "Gav", "Georg", "Ger", "Gerr", "Gis",
	    "Gust", "Hald", "Hen", "Her", "Hugo", "Hugh", "Igor", "Il", "Ivan", "Jas", "Jens", "Jo", "Jon", "Jor", "Jul", "Kai", "Kas", "Ken", "Klaus", "Kon", "Leon", "Leop", "Lin", "Luc", "Lud", "Magn", "Man", "Marc", "Martin", "Mat",
	    "Max", "Mel", "Mer", "Mik", "Milo", "Mir", "Nath", "Nik", "Nils", "Odin", "Ol", "Omar", "Os", "Ot", "Pas", "Paul", "Petr", "Phil", "Ralf", "Ran", "Rap", "Rik", "Rob", "Rol", "Rom", "Rune", "Sam", "Seb", "Ser", "Stef",
	    "Stein", "Sven", "Theod", "Thom", "Tim", "Tob", "Ulric", "Ulf", "Vald", "Vikt", "Wil", "Wulf", "Yan", "Yor", "Zan", "Ab", "Adel", "Alar", "Alber", "Albin", "Alek", "Am", "Ansel", "Armand", "Aurel", "Axel", "Bastian", "Bened", "Bjorn", "Boris",
	    "Bran", "Caspar", "Clem", "Conrad", "Darian", "Dimit", "Dorian", "Edgar", "Edwin", "Elias", "Elian", "Emmer", "Fabian", "Felix", "Florian", "Gab", "Gareth", "Gregor", "Hadrian", "Harlan", "Hector", "Henrik", "Herman", "Isak", "Johan", "Julius", "Kilian", "Laur", "Leander", "Lorenz",
	    "Lucian", "Marius", "Matth", "Maxim", "Morgan", "Nestor", "Octav", "Orin", "Pascal", "Quent", "Ragnar", "Rainer", "Rein", "Remy", "Roder", "Sander", "Silas", "Stefan", "Taran", "Tavian", "Theron", "Titus", "Tristan", "Valentin", "Viggo", "Wilhelm", "Xander", "Yannik", "Zach", "Zeph"
	};
	
	private static final String[] maleNameEnding = {
	    "adan", "ael", "ain", "ak", "ar", "ard", "as", "ath", "bar", "bek", "bor", "dan", "dar", "dorn", "drin", "dus", "el", "en", "er", "eth", "gar", "gor", "grim", "han", "har", "ik", "ion", "ir", "is", "ius",
	    "kan", "kar", "kas", "kel", "kor", "lan", "lor", "mar", "mir", "mon", "mor", "nar", "nor", "nus", "or", "orn", "os", "oth", "rak", "ran", "ric", "rin", "ris", "ron", "ros", "rus", "sar", "sel", "shan", "stan",
	    "tar", "thar", "ther", "thor", "tor", "tur", "us", "van", "var", "ven", "vor", "wyn", "xar", "yor", "zan", "zar", "zek", "zen", "zor", "zoth", "an", "en", "in", "on", "un", "er", "ar", "or", "el", "al",
	    "bert", "brecht", "den", "der", "dric", "dwin", "fried", "hard", "helm", "hold", "ian", "ick", "iel", "ier", "in", "ing", "kin", "lin", "man", "mund", "nard", "ner", "old", "olf", "olph", "ric", "rick", "rik", "ron", "son",
	    "ston", "ulf", "win", "wood", "ald", "aldor", "am", "and", "aric", "arius", "as", "bert", "brand", "den", "dric", "el", "en", "eric", "es", "et", "eus", "ian", "ias", "iel", "im", "in", "ius", "o", "on", "os",
	    "ald", "ar", "ard", "as", "at", "el", "en", "er", "et", "ian", "ic", "id", "ik", "il", "in", "io", "is", "it", "ius", "o", "on", "or", "os", "ot", "ric", "rik", "us", "an", "en", "or",
	    "aen", "aer", "aeth", "ain", "air", "al", "an", "ar", "as", "ath", "azar", "bel", "bram", "cain", "cal", "car", "cer", "dain", "dane", "dar", "diel", "dric", "drik", "eon", "erion", "eth", "fal", "finn", "gorn", "hael",
	    "ian", "ias", "imar", "ion", "isar", "ith", "ivar", "kael", "khan", "kiel", "mael", "maer", "nar", "nel", "nir", "rael", "rian", "riel", "rion", "roth", "sian", "sor", "tael", "tain", "thiel", "thir", "thran", "trian", "vael", "vian",
	    "vorn", "wald", "weald", "wyn", "xion", "yrian", "zian", "zor", "zorn", "zric"
	};


    // =========================================================================
    // Female first name components
    // =========================================================================

	private static final String[] femaleNameBeginning = {
	    "Ael", "Aen", "Aer", "Ala", "Alis", "Ama", "An", "Ara", "Ari", "Asha", "Ava", "Bel", "Bri", "Cael", "Cal", "Celi", "Ceri", "Dael", "Dae", "Dara", "Del", "Dora", "Eira", "Ela", "Elara", "Eli", "Ely", "Ena", "Era", "Eri",
	    "Fae", "Fara", "Feli", "Fey", "Gaia", "Gala", "Gwen", "Hali", "Hel", "Ila", "Illy", "Ira", "Isil", "Jas", "Kaela", "Kali", "Kara", "Kira", "Lara", "Lena", "Lia", "Lira", "Lora", "Lyra", "Mael", "Mara", "Meli", "Mira", "Mora", "Nae",
	    "Nala", "Nara", "Neri", "Nyla", "Ona", "Ori", "Phae", "Rae", "Rena", "Rhea", "Ria", "Sela", "Selene", "Shae", "Shara", "Sira", "Syl", "Tala", "Tara", "Thalia", "Thea", "Una", "Vala", "Vera", "Veya", "Wyn", "Yara", "Ylva", "Zara", "Zelia",
	    "Ada", "Adel", "Ag", "Agnes", "Aila", "Alba", "Alda", "Alena", "Alina", "Alma", "Amalia", "Anja", "Anna", "Annel", "Astrid", "Aure", "Aurora", "Bea", "Beata", "Berta", "Bianca", "Bruna", "Carla", "Carmen", "Celia", "Clara", "Dalia", "Daria", "Dina", "Dora",
	    "Edda", "Edith", "Elena", "Elisa", "Elise", "Ella", "Elsa", "Emilia", "Emma", "Erika", "Eva", "Fina", "Flora", "Freya", "Frida", "Greta", "Hanna", "Helena", "Ida", "Ilse", "Ines", "Inga", "Irena", "Irma", "Iva", "Jana", "Jasmin", "Jette", "Juna", "Karin",
	    "Katja", "Klara", "Lara", "Laura", "Lea", "Lena", "Lilia", "Lina", "Lisa", "Liv", "Livia", "Lotta", "Louisa", "Lucia", "Luna", "Maja", "Mara", "Maria", "Marta", "Mila", "Mina", "Mira", "Mona", "Nadia", "Nella", "Nina", "Noemi", "Nora", "Olga", "Olivia",
	    "Paula", "Petra", "Raisa", "Rita", "Rosa", "Sabina", "Sara", "Selma", "Sofia", "Sonja", "Stella", "Svenja", "Talia", "Tanja", "Thea", "Tilda", "Tina", "Ulla", "Vera", "Viktoria", "Viola", "Vita", "Wilma", "Ylva", "Zora", "Adara", "Aeris", "Arielle", "Arwen", "Avel",
	    "Bryn", "Calista", "Calia", "Carys", "Cerys", "Cora", "Delya", "Elaria", "Elowen", "Elvina", "Elys", "Enya", "Eowyn", "Faela", "Fiora", "Galia", "Gisela", "Halina", "Isara", "Isolde", "Jora", "Kaia", "Kalina", "Kelda", "Liora", "Lore", "Lyanna", "Maera", "Mirel", "Neria",
	    "Nerissa", "Nerys", "Orla", "Ravena", "Riona", "Rowena", "Sabrina", "Samara", "Seren", "Serena", "Siona", "Soraya", "Sylva", "Tavia", "Thora", "Valeria", "Vanya", "Vesper", "Ysolde", "Zaria", "Zelda", "Zena", "Astra", "Elara", "Evania", "Isla", "Luneth", "Melia", "Nalia", "Sylena"
	};
	
	private static final String[] femaleNameEnding = {
	    "a", "ae", "ala", "ana", "ara", "aria", "asha", "bella", "belle", "da", "dara", "delle", "dora", "ea", "ela", "elle", "ena", "era", "essa", "eth", "ia", "iana", "ielle", "ika", "ila", "ina", "ira", "issa", "ith", "iya",
	    "la", "lara", "lene", "lia", "lina", "lira", "lith", "lya", "ma", "mara", "mia", "mira", "na", "nara", "nella", "nera", "nia", "nora", "nyth", "ora", "oria", "ra", "rella", "ria", "rina", "ris", "sa", "sara", "sel", "sha",
	    "shara", "sia", "sira", "sora", "ssa", "ta", "tara", "thia", "thra", "thys", "tia", "tina", "una", "vara", "vera", "via", "wyn", "ya", "yra", "zara", "zel", "zeth", "zia", "ine", "ette", "ette", "ine", "elle", "enne",
	    "anna", "anne", "berta", "dine", "dis", "ela", "elia", "ella", "ene", "erica", "esia", "etta", "eva", "evia", "ia", "ica", "ilda", "illa", "ina", "inda", "ira", "isa", "ise", "ita", "iva", "iza", "ka", "kira", "la", "lda",
	    "le", "lea", "lene", "lia", "lisa", "lora", "luna", "ma", "mara", "mila", "mira", "na", "nette", "nika", "nora", "ola", "ora", "osa", "otta", "ra", "rena", "rica", "risa", "rita", "sa", "sina", "sonja", "ta", "tina", "tta",
	    "ula", "una", "va", "vera", "vina", "vita", "ya", "za", "zena", "zette", "zia", "zine", "zora", "a", "e", "i", "o", "ia", "ina", "ana", "ara", "ela", "ena", "ira", "ora", "una", "etta", "ella", "enne", "ette",
	    "abeth", "acia", "adia", "adra", "aela", "aena", "aera", "alia", "alyn", "amia", "aneth", "ania", "anya", "ariel", "asha", "avia", "ayne", "elia", "elith", "enya", "eriel", "esia", "eth", "evel", "evia", "eyla", "iara", "ielle", "ilith", "inia",
	    "iora", "iriel", "iselle", "isla", "ithra", "ivana", "iyah", "laya", "leth", "lielle", "lina", "lisse", "lyne", "lyra", "melle", "mira", "myra", "nara", "nella", "neth", "nielle", "nira", "nyra", "oria", "oriel", "raya", "relle", "ria", "rielle", "rissa",
	    "selle", "sira", "syl", "telle", "thena", "thiel", "thira", "thys", "toria", "vaela", "vella", "vienne", "vyra", "wynne", "xara", "yelle", "yra", "zielle", "zora", "zyna", "a", "ana", "ara", "ella", "ena", "ia", "ina", "ira", "ora", "ria"
	};


    // =========================================================================
    // Connectors
    // =========================================================================

    private static final String[] nameConnectors = {
        "", "", "", "", "a", "e", "i", "o", "y", "ae", "ia"
    };

    // =========================================================================
    // Last name components
    // =========================================================================


	private static final String[] lastnameBeginning = {
	    "Ash", "Alder", "Amber", "Arden", "Ashen", "Black", "Blood", "Bright", "Bronze", "Caer", "Cold", "Crow", "Dark", "Dawn", "Deep", "Dread", "Drake", "Dusk", "Ebon", "Elder", "Ever", "Fair", "Falcon", "Fallen", "Frost", "Gold", "Grave", "Gray", "Green", "Grim",
	    "Hallow", "Hawk", "Iron", "Ivory", "Kings", "Light", "Lion", "Long", "Moon", "Night", "Oak", "Raven", "Red", "River", "Rose", "Silver", "Skull", "Snow", "Star", "Storm", "Stone", "Sun", "Swift", "Thorn", "Valley", "Winter", "Wolf", "Wyrm", "White", "Wild",
	    "Abbey", "Ashford", "Atwood", "Baker", "Barrow", "Beck", "Bell", "Birch", "Briar", "Bridge", "Brook", "Burn", "Castle", "Clay", "Cliff", "Cobb", "Cop", "Cross", "Dale", "Dun", "Dane", "Deer", "Dun", "East", "Elm", "Fair", "Field", "Ford", "Fox", "Glen",
	    "Gorse", "Hall", "Hart", "Haven", "Hay", "Heath", "Hill", "Holm", "Hope", "Hurst", "Lake", "Lane", "Lock", "Low", "Marsh", "Mead", "Mere", "Mill", "Moor", "North", "Park", "Pine", "Plum", "Reed", "Shaw", "Shep", "South", "Spring", "Thatch", "West",
	    "Wood", "Barley", "Barton", "Baxter", "Beau", "Bennett", "Bishop", "Black", "Blake", "Blythe", "Bram", "Bran", "Bray", "Brew", "Brindle", "Brock", "Calder", "Carver", "Chase", "Chester", "Clarke", "Clover", "Colt", "Conrad", "Corbin", "Crane", "Crawford", "Dacre", "Darby", "Darcy",
	    "Daven", "Devere", "Doyle", "Drake", "Dunbar", "Dunham", "Eagle", "Easton", "Ellis", "Ember", "Everard", "Farrow", "Fletcher", "Flint", "Fowler", "Gardner", "Garnet", "Garrison", "Gentry", "Grafton", "Granger", "Graves", "Greer", "Hadley", "Harding", "Harlow", "Harper", "Hawke", "Hayward", "Hendrix",
	    "Hollis", "Holton", "Hunt", "Ingram", "Jarvis", "Keats", "Keller", "Kendall", "Kerr", "Kestrel", "King", "Knight", "Larkin", "Lawson", "Leigh", "Locke", "Marlow", "Mercer", "Merrick", "Miller", "Mont", "Morrow", "Nash", "Neville", "Newell", "Noble", "Norton", "Oakley", "Orwell", "Palmer",
	    "Parker", "Payne", "Pember", "Perry", "Preston", "Quill", "Radley", "Raleigh", "Ransom", "Rivers", "Rowan", "Sable", "Sawyer", "Scarlet", "Selby", "Shaw", "Shelby", "Slater", "Sorrell", "Staff", "Sutton", "Talbot", "Tanner", "Thatch", "Thorpe", "Turner", "Under", "Vane", "Vernon", "Warren",
	    "Webb", "Wells", "Whitby", "Whit", "Wick", "Willow", "Winters", "Wolfe", "Wright", "Yardley", "York", "Young", "Zephyr", "Ashby", "Ash", "Bramble", "Briar", "Cinder", "Cloud", "Crom", "Dunwich", "Evers", "Fallow", "Fen", "Fenn", "Fox", "Gallow", "Greyson", "Hawthorn", "Hollow",
	    "Kirk", "Moss", "Rook", "Sallow", "Starling", "Still", "Thorn", "Vale", "Waverly", "Wether", "Wilde", "Wind", "Wolfram", "Wren", "Yew", "Ains", "Alcott", "Aldridge", "Archer", "Ark", "Ashcroft", "Avery", "Balfour", "Banc", "Barclay", "Bard", "Barker", "Briar", "Bromley", "Burke"
	};
	
	private static final String[] lastnameEnding = {
	    "bane", "bark", "beard", "blood", "born", "brand", "brook", "call", "caster", "claw", "cloak", "crest", "crown", "dark", "dawn", "deep", "doom", "drake", "fall", "fang", "fire", "flame", "flight", "foot", "forge", "gard", "gaze", "glade", "gleam", "grave",
	    "guard", "hammer", "hand", "haven", "heart", "helm", "hunt", "keep", "kin", "light", "mane", "mark", "might", "moon", "mourn", "oak", "pike", "raven", "reach", "reave", "ridge", "river", "root", "run", "scale", "shade", "shield", "song", "spear", "spell",
	    "spire", "star", "stone", "storm", "strike", "sword", "thorn", "walk", "ward", "watch", "weave", "whisp", "wind", "wing", "winter", "wood", "wrath", "wyrm", "well", "worth", "ford", "field", "land", "ley", "ton", "ham", "wick", "wich", "stead", "bury",
	    "by", "dale", "mere", "hurst", "combe", "brook", "burn", "beck", "bank", "side", "hill", "mount", "vale", "fell", "moor", "shaw", "croft", "house", "gate", "wall", "bridge", "church", "chapel", "cross", "bell", "smith", "wright", "maker", "monger", "man",
	    "son", "sen", "kin", "wood", "stone", "water", "well", "water", "brook", "stream", "pool", "lake", "shore", "coast", "marsh", "fen", "bog", "heath", "field", "mead", "meadow", "grove", "wood", "forest", "leaf", "bloom", "rose", "thorn", "briar", "root",
	    "branch", "leaf", "bough", "berry", "bloom", "blossom", "bark", "fern", "moss", "reed", "rush", "sedge", "willow", "birch", "oak", "ash", "elm", "pine", "yew", "alder", "haw", "holly", "ivy", "laurel", "maple", "rowan", "hazel", "linden", "thorn", "glen",
	    "vale", "wind", "rain", "snow", "frost", "storm", "cloud", "mist", "haze", "shade", "shadow", "light", "glow", "spark", "flame", "ember", "ash", "coal", "cinder", "iron", "steel", "silver", "gold", "copper", "bronze", "brass", "glass", "crystal", "gem", "stone",
	    "rock", "cliff", "peak", "crest", "cairn", "cave", "deep", "dark", "dusk", "dawn", "star", "moon", "sun", "sky", "heaven", "night", "day", "raven", "crow", "hawk", "eagle", "falcon", "wolf", "fox", "bear", "boar", "hart", "stag", "deer", "hound",
	    "hound", "cat", "lynx", "lion", "tiger", "drake", "wyrm", "serpent", "scale", "wing", "feather", "talon", "claw", "fang", "tooth", "hide", "pelt", "mane", "tail", "horn", "hoof", "spear", "sword", "shield", "helm", "mail", "blade", "edge", "guard", "ward",
	    "watch", "keeper", "hunter", "stalker", "rider", "walker", "runner", "singer", "caller", "speaker", "weaver", "binder", "breaker", "slayer", "tamer", "carver", "smith", "forger", "hewer", "cutter", "fletcher", "archer", "bow", "arrow", "bolt", "quill", "scribe", "lore", "sage", "wise"
	};


    private static final String[] lastnameConnectors = {
        "", "", "", "", "a", "e", "i", "o", "-", "'"
    };

    // =========================================================================
    // Honorifics (100 entries)
    // =========================================================================

    private static final String[] honorifics = {
        "Admiral", "Archbishop", "Archmage", "Baron", "Baroness",
        "Bishop", "Captain", "Cardinal", "Champion", "Chancellor",
        "Commander", "Count", "Countess", "Crusader", "Dame",
        "Duke", "Duchess", "Emperor", "Empress", "Enchantress",
        "General", "Grandmaster", "High Priest", "High Priestess",
        "High King", "High Queen", "Inquisitor", "King", "Queen",
        "Knight", "Lady", "Lord", "Magister", "Magistrate",
        "Mage", "Master", "Mistress", "Mercenary", "Monarch",
        "Necromancer", "Oracle", "Paladin", "Patriarch",
        "Matriarch", "Pharaoh", "Prince", "Princess", "Prophet",
        "Prophetess", "Regent", "Ranger", "Reverend", "Sage",
        "Saint", "Seer", "Sentinel", "Shaman", "Sheikh",
        "Sister", "Sir", "Sorcerer", "Sorceress", "Sovereign",
        "Sultan", "Sultana", "Templar", "Thane", "Thegn",
        "Tzar", "Tzarina", "Viceroy", "Viscount", "Viscountess",
        "Warden", "Warlock", "Warlord", "Warrior", "Witch",
        "Wizard", "Swordmaster", "Spellblade", "Dreadlord",
        "Dragonlord", "Demon Hunter", "Dragon Slayer",
        "Doombringer", "Elder", "Eldritch One", "Farseer",
        "Gravekeeper", "High Warden", "Iron Lord",
        "Shadow Lord", "Stormcaller", "Voidwalker",
        "Witch Hunter", "Worldkeeper", "Emissary",
        "Dread Knight", "High Inquisitor"
    };

    // =========================================================================
    // Titles (100 entries)
    // =========================================================================

    private static final String[] titles = {
    	    "of Terror", "the Fearsome", "the Adorable", "the Unyielding", "the Merciless", "the Magnificent", "the Eternal", "the Forgotten", "the Damned", "the Blessed", "the Cursed", "the Unholy", "the Holy", "the Ruthless", "the Fearless", "the Timeless", "the Nameless", "the Faceless", "the Unseen", "the Unbroken", "the Unforgiven", "the Undying", "the Immortal", "the Ancient", "the Exiled", "the Banished", "the Betrayed", "the Betrayer", "the Conqueror", "the Destroyer",
    	    "the Devourer", "the Dominator", "the Enchanter", "the Enchantress", "the Enlightened", "the Exalted", "the Fallen", "the Flawless", "the Forsaken", "the Glorious", "the Golden", "the Grim", "the Guardian", "the Harbinger", "the Heartless", "the Honorable", "the Invincible", "the Ironhearted", "the Just", "the Kind", "the Last", "the Legendary", "the Lionhearted", "the Lost", "the Mad", "the Merciful", "the Mighty", "the Mysterious", "the Noble", "the Obscure",
    	    "the Omniscient", "the Outcast", "the Overlooked", "the Patient", "the Peculiar", "the Phantom", "the Pious", "the Relentless", "the Renegade", "the Resilient", "the Righteous", "the Savage", "the Seeker", "the Serene", "the Silent", "the Sinister", "the Sorrowful", "the Spectral", "the Splendid", "the Stalwart", "the Strange", "the Terrible", "the Tranquil", "the Treacherous", "the Unfortunate", "the Unstoppable", "the Valiant", "the Vengeful", "the Victorious", "the Vigilant",
    	    "the Wandering", "the Wicked", "the Wise", "the Wretched", "the Zealous", "the Unremarkable", "the Unreasonably Lucky", "the Slightly Annoying", "the Surprisingly Pleasant", "the Unpredictable", "of the Ashen Crown", "of the Last Dawn", "of Forgotten Realms", "of the Crimson Moon", "of Silent Waters", "of Seven Kingdoms", "of the Broken Oath", "of Endless Night", "of the Silver Flame", "of the Hollow Star", "of Distant Shores", "of the Black Thorn", "of Ancient Blood", "of the Golden Vale", "of the Frozen North", "of Lost Memories", "of the Burning Sky", "of the Iron Tower", "of Whispering Woods", "of the First Light",
    	    "of No Fixed Address", "of Many Regrets", "of Questionable Intent", "of Uncertain Origin", "of Dubious Reputation", "of the Unfinished Quest", "of the Empty Purse", "of the Wandering Road", "of Little Consequence", "of Great Misfortune", "from the Far North", "from Beyond the Veil", "from the Old Kingdom", "from Distant Lands", "from the Shadow Realm", "from Nowhere in Particular", "from the Forgotten Coast", "from the Eastern Marches", "from Beneath the Mountain", "from the Last Frontier", "from a Distant Star", "from the Fallen Empire", "from the Misty Isles", "from the Deepwood", "from the Western Wastes", "from the Hidden Valley", "from the Realm of Dreams", "from the Other Side", "from the Edge of Time", "from an Unknown House",
    	    "van the Silverhand", "van the Red", "van der Storm", "van den Berg", "van the Hollow", "van the Ironwood", "van the Raven", "van the Wanderer", "van the Old Road", "van the Moonlit Vale", "von Drachenfels", "von Blackmoor", "von Winterhold", "von Ravencrest", "von Ashenford", "von Grimwald", "von Silberstein", "von Nachtwald", "von Falkenheim", "von Eisenberg", "von Morgenstern", "von Schattenburg", "von Goldhain", "von Wolfsheim", "von Dornheim", "von Sternberg", "von Dunkelwald", "von Sturmfels", "von Rotenburg", "von Hohenfels",
    	    "de la Rose", "de Winter", "de Veyra", "de Montfort", "de Valois", "de Ravenna", "de Bellmont", "de la Nuit", "de Silvermere", "de Ashbourne", "du Ciel", "du Sang", "du Corbeau", "du Val", "du Lac", "du Bois", "du Soleil", "du Crépuscule", "du Fer", "du Vent", "del Mar", "del Fuego", "del Norte", "del Alba", "del Bosque", "di Ravenna", "di Ferro", "di Luna", "di Vento", "di Sangue",
    	    "without a Shadow", "without a Name", "without a Kingdom", "without Mercy", "without Fear", "without Equal", "without a Past", "without a Master", "without a Cause", "without a Clue", "beyond the Grave", "beyond the Stars", "beyond Redemption", "beyond the Horizon", "beyond the Veil", "beyond All Reason", "beneath the Crown", "beneath the Waves", "beneath the Earth", "beneath the Moon", "above the Law", "above All Others", "against the Tide", "against All Odds", "among the Fallen", "among the Stars", "among the Dead", "among the Living", "between Two Worlds", "between the Shadows",
    	    "Keeper of Secrets", "Keeper of the Flame", "Bearer of Bad News", "Bearer of the Crown", "Warden of the North", "Warden of Lost Souls", "Master of the Hunt", "Guardian of Shadows", "Champion of the Weak", "Champion of Chaos", "Knight of the Broken Oath", "Knight of the Last Dawn", "Harbinger of Misrule", "Guardian of the Lake", "Champion of Thieves", "Guardian of Ashes", "Savior of None", "Slayer of Kings", "Friend of Beasts", "Enemy of Fate", "Voice of the Ancients", "Hand of the Crown", "Blade of the Realm", "Shield of the Innocent", "Scourge of the Wicked", "Bane of the Undead", "Hope of the Hopeless", "Terror of the Deep", "Wanderer of Worlds", "Seeker of Truth",
    	    "First of Their Name", "Last of Their Line", "Heir to Nothing", "Heir of the Ashen Throne", "Child of the Storm", "Child of Prophecy", "Kin of Misfortune", "Kin of the Dawn", "Born of Fire", "Forged in Shadow", "Raised by Wolves", "Marked by Fate", "Chosen by Nobody", "Favored by Fortune", "Cursed by Destiny", "Lost to Time", "Loved by None", "Feared by Many", "Known to Few", "Forgotten by All", "the Almost Famous", "the Mildly Dangerous", "the Reluctant Hero", "the Accidental Villain", "the Unpaid Mercenary", "the Last to Know", "the First to Flee", "the Master of Excuses", "the Surprisingly Competent", "the Professionally Unlucky"
    	};

    // =========================================================================
    // RPG Character record
    // =========================================================================

    public record RPGName(
        String gender,
        String firstname,
        String middlename,
        String lastname,
        String honorific,
        String title,
        String fullname,
        String honoredFullname
    ) {

        public JsonObject toJsonObject() {
            JsonObject json = new JsonObject();

            json.addProperty("GENDER", gender);
            json.addProperty("HONORIFIC", honorific);
            json.addProperty("FIRSTNAME", firstname);
            json.addProperty("MIDDLENAME", middlename);
            json.addProperty("LASTNAME", lastname);
            json.addProperty("TITLE", title);
            json.addProperty("FULLNAME", fullname);
            json.addProperty("HONORED_FULLNAME", honoredFullname);

            return json;
        }
    }

    // =========================================================================
    // Configuration
    // =========================================================================

    private int middleNamePercent = 15;
    private int honorificPercent = 10;
    private int titlePercent = 10;

    /*******************************************************************
     * Constructor
     *******************************************************************/
    public XRRandomRPGName() {}

    /*******************************************************************
     * 
     *******************************************************************/
    public XRRandomRPGName middleNamePercent(int percent) {
        this.middleNamePercent = percent;
        return this;
    }

    /*******************************************************************
     * 
     *******************************************************************/
    public XRRandomRPGName honorificPercent(int percent) {
        this.honorificPercent = percent;
        return this;
    }

    /*******************************************************************
     * 
     *******************************************************************/
    public XRRandomRPGName titlePercent(int percent) {
        this.titlePercent = percent;
        return this;
    }

    // =========================================================================
    // Random generation helpers
    // =========================================================================

    /*******************************************************************
     * 
     *******************************************************************/
    private boolean chance(int percent) {
        return XRRandom.integer(1, 100) <= percent;
    }

    /*******************************************************************
     * 
     *******************************************************************/
    private String connectNameParts( String[] beginning, String[] ending) {
        
    	String begin = XRRandom.fromArray(beginning);
    	String end = XRRandom.fromArray(ending);
    	String connector = XRRandom.fromArray(nameConnectors);

    	return begin + connector + end;
    }

    /*******************************************************************
     * 
     *******************************************************************/
    private String randomFirstname(boolean isMale) {

        if (isMale) { return connectNameParts(maleNameBeginning, maleNameEnding); }

        return connectNameParts(femaleNameBeginning, femaleNameEnding );
    }

    /*******************************************************************
     * 
     *******************************************************************/
    private String randomLastname() {
    	
    	String begin = XRRandom.fromArray(lastnameBeginning);
    	String end = XRRandom.fromArray(lastnameEnding);
    	String connector = XRRandom.fromArray(lastnameConnectors);

    	return begin + connector + end;
    }

    /*******************************************************************
     * 
     *******************************************************************/
    private void appendPart( StringBuilder builder, String part){
        if (part != null && !part.isBlank()) {
            if (builder.length() > 0) {
                builder.append(" ");
            }

            builder.append(part);
        }
    }

    // =========================================================================
    // Generation
    // =========================================================================

    /*******************************************************************
     * Generate an RPG Character Record
     *******************************************************************/
    public RPGName generateRecord() {

        boolean isMale = XRRandom.integer(0, 1) == 0;

        String gender = isMale ? "Male" : "Female";

        String firstname = randomFirstname(isMale);

        String middlename = chance(middleNamePercent)
            ? randomFirstname(isMale)
            : "";

        String lastname = randomLastname();

        String honorific = chance(honorificPercent)
            ? XRRandom.fromArray(honorifics)
            : "";

        String title = chance(titlePercent)
            ? XRRandom.fromArray(titles)
            : "";

        StringBuilder fullname = new StringBuilder();

        
        appendPart(fullname, firstname);
        appendPart(fullname, middlename);
        appendPart(fullname, lastname);
        appendPart(fullname, title);

        StringBuilder honoredFullname = new StringBuilder();
        appendPart(honoredFullname, honorific);
        appendPart(honoredFullname, fullname.toString());
        
        return new XRRandomRPGName.RPGName(
            gender,
            firstname,
            middlename,
            lastname,
            honorific,
            title,
            fullname.toString(),
            honoredFullname.toString()
        );
    }

    /*******************************************************************
     * Generate an RPG Character Record as JSON Object
     *******************************************************************/
    public JsonObject generateJsonObject() {
        return generateRecord().toJsonObject();
    }
}