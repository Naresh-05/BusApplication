package action;

import com.bus.dao.BusDao;

public class getBus{
	
	ArrayList<Bus> bus=new ArrayList<>();
	
	public ArrayList<Bus> getAllBus(){
		BusDao dao=BusDao.getInstance(null);
		bus=dao.getBus();
		return bus;
	}
}