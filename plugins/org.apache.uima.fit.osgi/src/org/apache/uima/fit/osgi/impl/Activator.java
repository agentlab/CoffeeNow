/**
 *
 */
package org.apache.uima.fit.osgi.impl;

import org.apache.uima.fit.osgi.utils.MyBundleTracker;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 */
public class Activator implements BundleActivator{
	private MyBundleTracker bundleTracker;

	@Override
	public void start(BundleContext context) throws Exception {
		bundleTracker = new MyBundleTracker(context, null);
		bundleTracker.open();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		bundleTracker.close();
		bundleTracker = null;
	}

}
