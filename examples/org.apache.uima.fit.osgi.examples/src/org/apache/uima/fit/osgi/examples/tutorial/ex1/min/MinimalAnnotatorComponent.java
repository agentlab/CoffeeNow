package org.apache.uima.fit.osgi.examples.tutorial.ex1.min;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.osgi.AnalysisComponent;
import org.apache.uima.fit.osgi.examples.tutorial.type.RoomNumber;
import org.apache.uima.jcas.JCas;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MinimalAnnotatorComponent implements AnalysisComponent {
	private static final Logger log = LoggerFactory.getLogger(MinimalAnnotatorComponent.class);

	private Pattern mYorktownPattern = Pattern.compile("\\b[0-4]\\d-[0-2]\\d\\d\\b");
	private Pattern mHawthornePattern = Pattern.compile("\\b[G1-4][NS]-[A-Z]\\d\\d\\b");

	@Activate
	public void start(BundleContext context) {
		log.debug("Activate MinimalComponentWithAnnotator component");
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		// search for Yorktown room numbers
		Matcher matcher = mYorktownPattern.matcher(docText);
		while (matcher.find()) {
			// found one - create annotation
			RoomNumber annotation = new RoomNumber(aJCas, matcher.start(), matcher.end());
			annotation.setBuilding("Yorktown");
			annotation.addToIndexes();
		}
		// search for Hawthorne room numbers
		matcher = mHawthornePattern.matcher(docText);
		while (matcher.find()) {
			// found one - create annotation
			RoomNumber annotation = new RoomNumber(aJCas, matcher.start(), matcher.end());
			annotation.setBuilding("Hawthorne");
			annotation.addToIndexes();
		}
	}

	@Deactivate
	public void stop(BundleContext context) throws Exception {
		log.debug("Deactivate MinimalComponentWithAnnotator component");
	}
}
