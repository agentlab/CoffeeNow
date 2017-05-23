/**
 *
 */
package ru.agentlab.coffeenow;

import static com.codeaffine.osgi.test.util.ServiceCollector.collectServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.traccar.model.Device;
import org.traccar.model.DeviceTotalDistance;
import org.traccar.model.Position;
import org.traccar.services.Devices;
import org.traccar.services.Positions;

import com.codeaffine.osgi.test.util.Registration;
import com.codeaffine.osgi.test.util.ServiceRegistrationRule;

import ru.agentlab.coffeenow.friendsfinder.Friends;

/**
 * @author kiric
 *
 */
public class FriendsTest {

    @Rule
    public final ServiceRegistrationRule serviceRegistration = new ServiceRegistrationRule(getClass());


    @Test
    public void positionTracking() {
        Registration<Devices> devicesRegistration = serviceRegistration.register(Devices.class, new Devices()
        {
            @Override
            public Collection<Device> get(boolean all, long userId) {
                return null;
            }

            @Override
            public Collection<Device> getDevices() {
                Collection<Device> devices = new ArrayList<>(10);
                int i = 0;
                for (Device d : devices)
                {
                    d.setName(i + " ");
                    d.setId(i);
                }
                return devices;
            }

            @Override
            public Device add(Device entity) {
                return null;
            }

            @Override
            public Device update(Device entity) {
                return null;
            }

            @Override
            public boolean remove(long id) {
                return false;
            }

            @Override
            public boolean updateTotalDistance(DeviceTotalDistance entity) {
                return false;
            }
        });

        Registration<Positions> positionsRegistration = serviceRegistration.register(Positions.class, new Positions()
        {
            @Override
            public Collection<Position> get(long deviceId, List<Long> positionIds, String from, String to) {
                return null;
            }

            @Override
            public Collection<Position> getPositions() {
                Collection<Position> positions = new ArrayList<>(10);
                int i = 0;
                for (Position p : positions)
                {
                    p.setAltitude(i * 10);
                    p.setLatitude(i * 10);
                    p.setId(i);
                }
                return positions;
            }
        });

        List<Friends> collectServices = collectServices(Friends.class, Friends.class);
        Friends service = collectServices.get(0);
        service.getPositions(0);
        //consumer = mock(ICalendarServiceConsumer.class);

        devicesRegistration.unregister();
        positionsRegistration.unregister();
    }

}
