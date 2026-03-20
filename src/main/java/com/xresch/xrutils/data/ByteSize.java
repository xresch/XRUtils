package com.xresch.xrutils.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**************************************************************************************************************
 * Enum used for calculating bytes.
 * 
 * Copyright Reto Scheiwiller, 2026
 * @license MIT License
 **************************************************************************************************************/
public enum ByteSize {
	
	  B(1L)
	, KB(1024L)
	, MB(1_048_576L)
	, GB(1_073_741_824L)
	, TB(1_099_511_627_776L)
	;

	public final long bytesCount;
	
	/*******************************************************************
	 * Private Constructor
	 * 
	 *******************************************************************/
	private ByteSize(long bytesCount) {
		this.bytesCount = bytesCount;
	}
	
	/*******************************************************************
	 * Converts an amount of bytes to the chosen enum amount.
	 * For example, if you call ByteSize.KB.convertBytes(2048, 0) the
	 * return value will be "2".
	 * 
	 * @param bytes the size in bytes
	 * 
	 *******************************************************************/
	public long convertBytes(Long bytes){
		return bytes / bytesCount;
	}
	
	/*******************************************************************
	 * Converts an amount of bytes with the chosen decimal precision
	 * to the chosen enum amount. 
	 * For example, if you call ByteSize.KB.convertBytes(2048, 3) the
	 * return value will be "2.000".
	 * 
	 * @param bytes the size in bytes
	 * @param decimals the number of decimals
	 * 
	 *******************************************************************/
	public BigDecimal convertBytes(long bytes, int decimals){
		return new BigDecimal( bytes / (bytesCount * 1.0) )
					.setScale(decimals, RoundingMode.HALF_UP)
				;
	}
	
	/*******************************************************************
	 * Converts an amount of bytes to the chosen enum amount.
	 * For example, if you call ByteSize.KB.getAsBytes(2) the
	 * return value will be "2048".
	 * 
	 * @param amount the amount to return in bytes
	 * 
	 *******************************************************************/
	public long getAsBytes(int amount){
		return amount * bytesCount;
	}
	
	/*******************************************************************
	 * Converts an amount of bytes to the chosen enum amount.
	 * For example, if you call ByteSize.KB.getAsBytes(2) the
	 * return value will be "2048".
	 * 
	 * @param amount the amount to return in bytes
	 * 
	 *******************************************************************/
	public long getAsBytes(Long amount){
		return amount * bytesCount;
	}
	
	/*******************************************************************
	 * Returns the size that is the most appropriate for displaying the
	 * amount of bytes. 
	 * For example, 999.99 MB will return "MB" while, 1 GB will return "GB"-
	 * 
	 * @param bytes the size in bytes
	 * @return the best fitting size
	 * 
	 *******************************************************************/
	public static ByteSize getBestSize(Long bytes){

		if(bytes == null) { return ByteSize.B; }
		
	    if ( 	  bytes >= ByteSize.TB.bytesCount ){	return ByteSize.TB; }
	    else if ( bytes >= ByteSize.GB.bytesCount ){ 	return ByteSize.GB; }
	    else if ( bytes >= ByteSize.MB.bytesCount ){	return ByteSize.MB; }
	    else if ( bytes >= ByteSize.KB.bytesCount ){ 	return ByteSize.KB; }
	    else { 											return ByteSize.B;  }

	}
	
	/*******************************************************************
	 * Converts a size of bytes into a bytes String with a suffix like
	 * KB, MB, GB or TB that can be read by a human being.
	 * 
	 * @param sizeBytes the size in bytes
	 * @param decimals the number of decimals
	 * 
	 *******************************************************************/
	public String toHumanReadableBytes(Long sizeBytes, int decimals){
		
		DecimalFormat dec = new DecimalFormat("0."+ "0".repeat(decimals) );
		
		return dec.format( this.convertBytes(sizeBytes, decimals) )
				.concat(" ")
				.concat(this.toString());
	}
		
	/*******************************************************************
	 * Converts a size of bytes into a bytes String with a suffix like
	 * KB, MB, GB or TB that can be read by a human being.
	 * 
	 * @param sizeBytes the size in bytes
	 * @param decimals the number of decimals
	 * 
	 *******************************************************************/
	public static String toHumanReadableBytesAuto(Long sizeBytes, int decimals){

		if(sizeBytes == null) { return "0 B"; }
		
	    ByteSize best = getBestSize(sizeBytes);
	    
	    return best.toHumanReadableBytes(sizeBytes, decimals);

	}
}
