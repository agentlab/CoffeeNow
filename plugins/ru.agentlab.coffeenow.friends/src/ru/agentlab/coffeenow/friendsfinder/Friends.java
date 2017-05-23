/**
 *
 */
package ru.agentlab.coffeenow.friendsfinder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.traccar.model.Device;
import org.traccar.model.Position;
import org.traccar.services.Devices;
import org.traccar.services.Positions;


/**
 * @author kiric
 *
 */
@Component()
public class Friends {

	private Positions mMpositions;

	private Devices mDevices;

	private double RADIUS = 30;

	@Reference()
	public void bindDevices(Devices devices) {
	}
	public void unbindDevices(Devices devices) {
	}

	@Reference()
	public void bindPosition(Positions position) {
	}
	public void unbindPosition(Positions position) {
	}

	public boolean isFriendCloser (double x1, double x2, double y1, double y2, double radius){
		return ((x1-x2)*(x1-x2) + (y1-y2) * (y1-y2) < radius * radius);
	}

	public Map<String, Long> getDevices(){
		Collection<Device> devices = mDevices.getDevices();
		Map<String, Long> result = new HashMap<String, Long>();
		for (Device d : devices){
			result.put(d.getName(), d.getId());
		}
		return result;
	}

	public void getPositions (double myId){
		Collection<Position> positions = mMpositions.getPositions();
		double myX = 0, myY = 0;
		for (Position p : positions){
			if (p.getId() == myId){
				myX = p.getAltitude();
				myY = p.getLongitude();
				break;
			}
		}
		for (Position p : positions){
			if (p.getId() != myId){
				if (isFriendCloser(myX, p.getAltitude(), myY, p.getLongitude(), RADIUS)){
					//do somethink
				}
			}
		}

	}



}
