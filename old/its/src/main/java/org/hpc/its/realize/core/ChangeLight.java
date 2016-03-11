package org.hpc.its.realize.core;

import org.hpc.its.realize.entities.a.Intersection;
import org.hpc.its.realize.entities.a.Light;

public class ChangeLight implements Runnable {

	private Intersection intersection = null;
	private long lightId;
	private int redTime;
	private int greenTime;

	public ChangeLight(Intersection intersection, long lightId, Light light) {
		super();
		this.intersection = intersection;
		this.lightId = lightId;
		this.redTime = light.getRed();
		this.greenTime = light.getGreen();
	}

	@Override
	public void run() {
		// Map<Long, AbstractLight> alights = ;
		// Set<Light> lights = new HashSet<Light>();
		// for (Entry<Long, AbstractLight> al : alights.entrySet()) {
		// Light l = (Light) al.getValue();
		// if (lightId == l.getLightId())
		// lights.add(l);
		// }
		Light light = (Light) intersection.getLights().get(lightId);
		while (true) {
			// for (Light l : lights) {
			if (light.getState() == 0) {
				light.setState(1);
				try {
					Thread.sleep(greenTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			} else {
				light.setState(0);
				try {
					Thread.sleep(redTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			// }

		}
	}

}
