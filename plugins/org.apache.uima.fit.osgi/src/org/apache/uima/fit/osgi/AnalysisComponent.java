package org.apache.uima.fit.osgi;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

public interface AnalysisComponent {

	/**
	 * This method should be overriden by component classes. Inputs a JCAS to the
	 * AnalysisComponent. The AnalysisComponent "owns" this JCAS until such time as
	 * {@link #hasNext()} is called and returns false (see {@link AnalysisComponent}
	 * for details).
	 *
	 * @param aJCas
	 *            a JCAS that this AnalysisComponent should process.
	 *
	 * @throws AnalysisEngineProcessException
	 *             if a problem occurs during processing
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException;
}
