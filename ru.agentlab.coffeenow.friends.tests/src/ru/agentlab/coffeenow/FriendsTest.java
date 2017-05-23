/**
 *
 */
package ru.agentlab.coffeenow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.traccar.model.Device;
import org.traccar.model.DeviceTotalDistance;
import org.traccar.model.Position;
import org.traccar.services.Devices;
import org.traccar.services.Positions;

/**
 * @author kiric
 *
 */
public class FriendsTest implements Positions, Devices {

	@Override
	public Collection<Device> get(boolean all, long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Device> getDevices() {
		Collection<Device> devices = new ArrayList<Device>(10);
		int i = 0;
		for (Device d : devices){
			d.setName(i + " ");
			d.setId(i);
		}
		return devices;
	}

	@Override
	public Device add(Device entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Device update(Device entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTotalDistance(DeviceTotalDistance entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Position> get(long deviceId, List<Long> positionIds, String from, String to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Position> getPositions() {
		Collection<Position> positions = new ArrayList<Position>(10);
		int i = 0;
		for (Position p : positions){
			p.setAltitude(i*10);
			p.setLatitude(i*10);
			p.setId(i);
		}
		return positions;
	}



}
