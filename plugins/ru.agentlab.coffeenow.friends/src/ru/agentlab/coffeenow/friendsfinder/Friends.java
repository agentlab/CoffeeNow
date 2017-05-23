/**
 *
 */
package ru.agentlab.coffeenow.friendsfinder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.osgi.service.component.annotations.Activate;
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
public class Friends extends TimerTask {

	private Positions positions;

	private Devices devices;

	private double RADIUS = 30;

	Collection<Device> devicesCollection;

	private int MYID = 0;

	@Reference()
	public void bindDevices(Devices devices) {
		this.devices = devices;
	}
	public void unbindDevices(Devices devices) {
	}

	@Reference()
	public void bindPosition(Positions position) {
		this.positions = position;
	}
	public void unbindPosition(Positions position) {
	}

	public boolean isFriendCloser (double x1, double x2, double y1, double y2, double radius){
		return ((x1-x2)*(x1-x2) + (y1-y2) * (y1-y2) < radius * radius);
	}

	public Map<String, Long> getDevices(){
		devicesCollection = devices.getDevices();
		Map<String, Long> result = new HashMap<String, Long>();
		for (Device d : devicesCollection){
			result.put(d.getName(), d.getId());
		}
		return result;
	}

	public void getPositions (double myId){
		Collection<Position> positionsCollection = positions.getPositions();
		getDevices();
		double myX = 0, myY = 0;
		for (Position p : positionsCollection){
			if (p.getId() == myId){
				myX = p.getAltitude();
				myY = p.getLongitude();
				break;
			}
		}
		for (Position p : positionsCollection){
			if (p.getId() != myId){
				if (isFriendCloser(myX, p.getAltitude(), myY, p.getLongitude(), RADIUS)){
					//do somethink
					String name = "";
					for (Device d : devicesCollection){
						if (d.getId() == p .getId()){
							name = d.getName();
							break;
						}
					}
					System.out.printf("friend with name ", name, " near you");
				}
			}
		}

	}

    @Override
    public void run() {
    	getPositions(MYID);
    }

	@Activate
	public void start(){
		final TimerTask task = new Friends();
		Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 10*1000);
	}



}
