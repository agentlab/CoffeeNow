/**
 *
 */
package org.apache.uima.fit.osgi.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.fit.internal.MetaDataType;
import org.apache.uima.resource.ResourceInitializationException;
import org.eclipse.gemini.blueprint.io.OsgiBundleResourcePatternResolver;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ivanov_AM
 *
 */
public class MyBundleTracker extends BundleTracker {
	private final Logger log = LoggerFactory.getLogger(MyBundleTracker.class);

	protected static ArrayList<String> tdLocations = new ArrayList<>();
	protected static String[] tmp = {};

	public MyBundleTracker(BundleContext context, int stateMask, BundleTrackerCustomizer<?> customizer) {
		super(context, stateMask, customizer);
	}

	public static String[] getLocations() {
		return tdLocations.toArray(tmp);
	}

	public Object addingBundle(Bundle bundle, BundleEvent event) {
		// Typically we would inspect bundle, to figure out if we want to
		// track it or not. If we don't want to track return null, otherwise
		// return an object.
		if(event == null || bundle == null)
			return null;

		int t = event.getType();
		if(t == BundleEvent.RESOLVED || t == BundleEvent.STARTED || t == BundleEvent.STARTING) {
			URL url = bundle.getResource("META-INF/org.apache.uima.fit/types.txt");
			if(url != null) {
				log.debug("In bundle {} found URL {}", bundle.getSymbolicName(), url);

				OsgiBundleResourcePatternResolver resolver = new OsgiBundleResourcePatternResolver(bundle);

				try {
	  				List<String> descriptorList = Arrays.asList(org.apache.uima.fit.osgi.impl.MetaDataUtil.scanDescriptors(resolver, MetaDataType.TYPE_SYSTEM));
					tdLocations.addAll(descriptorList);
	  				/*BufferedReader br =new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
	  				while(br.ready()){
	  				    System.out.println(br.readLine());
	  				}
	  				br.close();*/
				}
				catch (ResourceInitializationException/* | IOException*/ e) {
					e.printStackTrace();
				}
				return bundle;
			}
		}
		return null;
	}

	public void removedBundle(Bundle bundle, BundleEvent event, Object object) {
		log.debug("Removed bundle {}", bundle.getSymbolicName());
	}

	public void modifiedBundle(Bundle bundle, BundleEvent event, Object object) {
		log.debug("Modified bundle {}", bundle.getSymbolicName());
	}
}
