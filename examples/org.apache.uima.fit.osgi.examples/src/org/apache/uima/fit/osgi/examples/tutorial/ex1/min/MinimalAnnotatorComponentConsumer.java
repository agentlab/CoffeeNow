package org.apache.uima.fit.osgi.examples.tutorial.ex1.min;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.fit.osgi.AnalysisComponent;
import org.apache.uima.fit.osgi.examples.tutorial.type.RoomNumber;
import org.apache.uima.jcas.JCas;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true)
public class MinimalAnnotatorComponentConsumer {
	private static final Logger log = LoggerFactory.getLogger(MinimalAnnotatorComponentConsumer.class);

	@Reference(name="org.apache.uima.fit.osgi.examples.tutorial.ex1.min.MinimalAnnotatorComponent", policy=ReferencePolicy.STATIC)
	AnalysisComponent annotator;

	@Activate
	public void start(BundleContext context) {
		/*log.debug("Activate MinimalComponentWithAnnotator component");

		JCas jCas;

		annotator.process(aJCas);

		for (RoomNumber roomNumber : select(jCas, RoomNumber.class)) {
			log.info(roomNumber.getCoveredText() + "\tbuilding = " + roomNumber.getBuilding());
		}*/
	}
}
