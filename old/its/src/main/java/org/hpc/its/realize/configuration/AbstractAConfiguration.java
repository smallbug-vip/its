package org.hpc.its.realize.configuration;

import org.apache.log4j.Logger;
import org.hpc.its.realize.ItsConfiguration;

/**
 * Abstract class A scheme,it has a lot subclasses,each subclass represents a
 * way to read the configuration information,and each subclass rewrite
 * initProperties() method,this method represents specific implementation that
 * they obtain configuration information.init() method is subclass called to
 * initialize information that configuration data not containing.
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 2:58:30 PM
 */
public abstract class AbstractAConfiguration implements ItsConfiguration {

	Logger log = Logger.getLogger(AbstractAConfiguration.class);

	public int map_width;
	public int map_height;

	/******************************* Global Configure *******************************/

	/******************************* Map Configure *******************************/

	/******************************* Road Configure *******************************/

	/******************************* Intersection Configure *******************************/

	/**
	 * initialize properties
	 */
	public abstract void initProperties();

	public void init() {
		
	}

	public int getMap_width() {
		return map_width;
	}

	public void setMap_width(int map_width) {
		this.map_width = map_width;
	}

	public int getMap_height() {
		return map_height;
	}

	public void setMap_height(int map_height) {
		this.map_height = map_height;
	}

}
