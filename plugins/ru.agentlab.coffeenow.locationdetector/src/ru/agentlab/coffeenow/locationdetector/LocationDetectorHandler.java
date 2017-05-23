/**
 *
 */
package ru.agentlab.coffeenow.locationdetector;

import org.traccar.model.Event;

/**
 * @author amivanoff
 *
 */
public class LocationDetectorHandler implements EventHandler {

    @Override
    public void process(Event e) {
        System.out.println("Yooooo!!! ------ " + e);
    }

}
