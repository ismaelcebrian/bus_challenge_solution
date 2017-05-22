package com.icebrian.buses;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icebrian.buses.error.ApplicationInitializationException;

@Service
public class BusRouteServiceImpl implements BusRouteService {

	@Autowired
	private RouteFileLoader fileLoader;

	Map<Integer, List<Integer>> routesByStation;

	@PostConstruct
	private void initializeMap() {
		try {
			routesByStation = fileLoader.loadFile();

		} catch (IOException e) {
			throw new ApplicationInitializationException(e);
		}

	}

	@Override
	public boolean existDirectRoute(int depStationId, int arrStationId) {

		List<Integer> depRoutes = routesByStation.get(depStationId);
		List<Integer> arrRoutes = routesByStation.get(arrStationId);

		if (depRoutes == null || arrRoutes == null) {
			return false;
		} else {
			for (Integer route : depRoutes) {
				if (arrRoutes.contains(route)) {
					return true;
				}

			}
			return false;
		}
	}

}
