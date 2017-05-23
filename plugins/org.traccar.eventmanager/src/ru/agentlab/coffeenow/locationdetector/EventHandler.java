/**
 *
 */
package ru.agentlab.coffeenow.locationdetector;

import org.traccar.model.Event;

/**
 * @author ekaterina
 *
 */
public interface EventHandler {
    void process(Event e);
}
