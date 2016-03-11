package org.hpc.its.realize.entities.a;

import java.util.HashMap;
import java.util.Map;

import org.hpc.its.realize.entities.abs.AbstractMap;

@SuppressWarnings("serial")
public class Map_ extends AbstractMap {

	private int width;
	private int height;
	private int count;

	Map<Long, Map<Long, Light>> changTime = new HashMap<>();

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Map<Long, Map<Long, Light>> getChangTime() {
		return changTime;
	}

}
