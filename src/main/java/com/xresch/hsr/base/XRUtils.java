package com.xresch.hsr.base;

import com.xresch.hsr.utils.XRCSV;
import com.xresch.hsr.utils.XRFiles;
import com.xresch.hsr.utils.XRJson;
import com.xresch.hsr.utils.XRMath;
import com.xresch.hsr.utils.XRRandom;
import com.xresch.hsr.utils.XRText;
import com.xresch.hsr.utils.XRTime;

/**************************************************************************************
 * The Report class provides methods to add items to the reports, create screenshots
 * and write the report to the disk.
 * 
 * Copyright Reto Scheiwiller, 2026
 * @license MIT License
 **************************************************************************************/

public class XRUtils {	
	
	/***********************************************************************************
	 * Utility References
	 ***********************************************************************************/
	public static class CSV extends XRCSV {}
	public static class Files extends XRFiles {}
	public static class JSON extends XRJson {}
	public static class Math extends XRMath {}
	public static class Random extends XRRandom {}
	public static class Text extends XRText {}
	public static class Time extends XRTime {}
	
}
