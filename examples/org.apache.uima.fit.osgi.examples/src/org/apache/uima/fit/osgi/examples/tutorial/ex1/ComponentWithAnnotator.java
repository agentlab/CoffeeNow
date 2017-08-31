package org.apache.uima.fit.osgi.examples.tutorial.ex1;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.osgi.examples.tutorial.type.RoomNumber;
import org.apache.uima.fit.osgi.utils.AnalysisEngineFactoryOSGi;
import org.apache.uima.jcas.JCas;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class ComponentWithAnnotator {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Activate
	public void start(BundleContext context) {
		try {
			String text = "The meeting was moved from Yorktown 01-144 to Hawthorne 1S-W33.";

			AnalysisEngine analysisEngine = AnalysisEngineFactoryOSGi.createEngine(RoomNumberAnnotator.class);
			JCas jCas = analysisEngine.newJCas();
			jCas.setDocumentText(text);
			analysisEngine.process(jCas);

			for (RoomNumber roomNumber : select(jCas, RoomNumber.class)) {
				System.out.println(roomNumber.getCoveredText() + "\tbuilding = " + roomNumber.getBuilding());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Deactivate
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
	}
}
