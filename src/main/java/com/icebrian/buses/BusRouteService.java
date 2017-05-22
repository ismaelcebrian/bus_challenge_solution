package com.icebrian.buses;

public interface BusRouteService {
	
	/**
	 * returns true if there is a direct bus route between the two stations provided
	 * @param depStationId
	 * @param arrStationId
	 * @return
	 */
	boolean existDirectRoute(int depStationId, int arrStationId);

}
