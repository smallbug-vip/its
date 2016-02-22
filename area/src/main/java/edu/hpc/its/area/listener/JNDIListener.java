package edu.hpc.its.area.listener;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;

import edu.hpc.its.area.Area;
import edu.hpc.its.area.Lifecycle;
import edu.hpc.its.area.exception.LifecycleException;

public class JNDIListener implements LifecycleListener {

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getLifecycle() instanceof Area && Lifecycle.BEFORE_INIT_EVENT.equals(event.getType())) {
			try {
				try {
					LocateRegistry.createRegistry(10999);
					System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
					System.setProperty(Context.PROVIDER_URL, "rmi://localhost:10999");
				} catch (RemoteException e) {
					throw new LifecycleException(e);
				}
			} catch (Exception e) {
				throw new LifecycleException(e);
			}
		}
	}
}
