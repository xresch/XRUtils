package com.xresch.xrutils.base;

import com.xresch.xrutils.utils.XRCSV;
import com.xresch.xrutils.utils.XRFiles;
import com.xresch.xrutils.utils.XRJson;
import com.xresch.xrutils.utils.XRMath;
import com.xresch.xrutils.utils.XRRandom;
import com.xresch.xrutils.utils.XRText;
import com.xresch.xrutils.utils.XRTime;

/**************************************************************************************
 * The Report class provides methods to add items to the reports, create screenshots
 * and write the report to the disk.
 * 
 * Copyright Reto Scheiwiller, 2026
 * @license MIT License
 **************************************************************************************/

public class XR {	
	
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
