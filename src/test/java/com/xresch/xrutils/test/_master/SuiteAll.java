package com.xresch.xrutils.test._master;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Tests")
@SelectPackages("com.xresch.xrutils.test")
@IncludeClassNamePatterns("(Test.*|.+[.$]Test.*|.*Tests?)")
@ExcludeTags("manual")
public class SuiteAll { 
	
}  
