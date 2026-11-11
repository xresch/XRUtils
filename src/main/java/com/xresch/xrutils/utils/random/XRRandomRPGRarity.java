package com.xresch.xrutils.utils.random;

import com.xresch.xrutils.base.XR;

/**************************************************************************************************************
 * Used to select random RPG Rarity.
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 * 
 **************************************************************************************************************/
public enum XRRandomRPGRarity {

	  COMMON       (1.0, "Ordinary and widely encountered, with no particularly remarkable qualities.")
	, UNCOMMON     (1.25, "Less frequently encountered, possessing some distinctive or noteworthy qualities.")
	, RARE         (1.5, "Unusual and valuable, distinguished by notable qualities and capabilities.")
	, EPIC         (1.75, "Exceptional in nature, possessing remarkable qualities and considerable power.")
	, LEGENDARY    (2.0, "Extraordinary and renowned, associated with great power and lasting significance.")
	, MYTHIC       (2.5, "Mythical in nature, possessing extraordinary powers and qualities beyond the ordinary.")
	, ANCIENT      (3.0, "Rooted in forgotten ages, embodying immense power, wisdom, or mysterious origins.")
	, DIVINE       (3.5, "Transcendent in nature, embodying the power and essence of divine beings.")
	, CELESTIAL    (4.0, "Of cosmic significance, embodying extraordinary power beyond the mortal realm.")
	;

    private final double multiplier;
    private final String description;

    XRRandomRPGRarity(double multiplier, String description) {
        this.multiplier = multiplier;
        this.description = description;
    }

    public double multiplier() {
        return multiplier;
    }

    public String description() {
        return description;
    }

    /**************************************************************************
     * 
     **************************************************************************/
    public static XRRandomRPGRarity getRandom() {
    		return getRandom(
    	      600f  // COMMON
    	    , 250f  // UNCOMMON
    	    , 100f  // RARE
    	    , 40f   // EPIC
    	    , 8f    // LEGENDARY
    	    , 2f    // MYTHIC
    	    , 0.5f  // ANCIENT
    	    , 0.1f  // DIVINE
    	    , 0.01f // CELESTIAL
    	);
    }
    
    /***************************************************************************
     * Selects a random RPG rarity based on relative probabilities.
     * Set a chance to zero to exclude that rarity. 
     * @param chanceCommon
     * @param chanceUncommon
     * @param chanceRare
     * @param chanceEpic
     * @param chanceLegendary
     * @param chanceMythic
     * @param chanceAncient
     * @param chanceDivine
     * @param chanceCelestial
     * @return Randomly selected rarity.
     ***************************************************************************/
    public static XRRandomRPGRarity getRandom(
              float chanceCommon
            , float chanceUncommon
            , float chanceRare
            , float chanceEpic
            , float chanceLegendary
            , float chanceMythic
            , float chanceAncient
            , float chanceDivine
            , float chanceCelestial
            ) {

        float total =
                  chanceCommon
                + chanceUncommon
                + chanceRare
                + chanceEpic
                + chanceLegendary
                + chanceMythic
                + chanceAncient
                + chanceDivine
                + chanceCelestial;

        if (total <= 0) {
            return COMMON;
        }

        float random = XR.Random.floatInRange(0.0f, total);

        if ((random -= chanceCommon) < 0) return COMMON;
        if ((random -= chanceUncommon) < 0) return UNCOMMON;
        if ((random -= chanceRare) < 0) return RARE;
        if ((random -= chanceEpic) < 0) return EPIC;
        if ((random -= chanceLegendary) < 0) return LEGENDARY;
        if ((random -= chanceMythic) < 0) return MYTHIC;
        if ((random -= chanceAncient) < 0) return ANCIENT;
        if ((random -= chanceDivine) < 0) return DIVINE;

        return CELESTIAL;
    }

}