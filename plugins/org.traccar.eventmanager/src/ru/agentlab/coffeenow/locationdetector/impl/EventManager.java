/**
 *
 */
package ru.agentlab.coffeenow.locationdetector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.traccar.model.Event;

import ru.agentlab.coffeenow.locationdetector.EventHandler;

/**
 * @author amivanoff
 *
 */
@Component
public class EventManager {
    protected List<EventHandler> eventHandlers = new ArrayList<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    ScheduledFuture<?> beeperHandle;

    @Activate
    public void activate() {
    }

    @Reference(policy = ReferencePolicy.DYNAMIC, bind = "bindEventHandler", cardinality = ReferenceCardinality.MULTIPLE)
    public void bindEventHandler(EventHandler handler) {
        eventHandlers.add(handler);

        if (eventHandlers.size() == 1)
        {
            startThread();
        }
    }

    public void unbindEventHandler(EventHandler handler) {
        eventHandlers.remove(handler);
        if (eventHandlers.size() == 0)
        {
            stopThread();
        }
    }

    /**
     * TODO JavaDoc
     *
     */
    private void startThread() {
        final Runnable beeper = new Runnable()
        {
            @Override
            public void run() {
                System.out.println("beep");
                for (EventHandler eventHandler : eventHandlers)
                {
                    eventHandler.process(new Event(Event.TYPE_GEOFENCE_ENTER, 3, 5));
                }
            }
        };

        beeperHandle = scheduler.scheduleAtFixedRate(beeper, 5, 5, TimeUnit.SECONDS);
    }

    private void stopThread() {
        beeperHandle.cancel(true);
    }

}
