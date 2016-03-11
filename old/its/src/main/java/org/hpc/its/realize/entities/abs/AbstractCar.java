package org.hpc.its.realize.entities.abs;

import org.hpc.its.realize.ItsEntity;

/**
 * abstract entity for all car,it provides all the properties of car and can be
 * extended, the programmer can extends cars according this abstract class
 * 
 * @author smallbug
 * @createTime Nov 8, 2015 1:07:47 AM
 */
@SuppressWarnings("serial")
public abstract class AbstractCar implements ItsEntity {

	// this car traveling on this driveWay current
	protected AbstractLane currentLine;

	public synchronized AbstractLane getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(AbstractLane currentLine) {
		this.currentLine = currentLine;
	}

}
