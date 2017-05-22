package com.icebrian.buses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class BusRouteServiceImplTest {

	BusRouteService service = new BusRouteServiceImpl();

	@Before
	public void setup() {
		Map<Integer, List<Integer>> routes = new HashMap<>();
		routes.put(0, IntStream.rangeClosed(0, 2).boxed().collect(Collectors.toList()));
		routes.put(1, IntStream.rangeClosed(2, 3).boxed().collect(Collectors.toList()));
		routes.put(2, IntStream.rangeClosed(3, 4).boxed().collect(Collectors.toList()));
		ReflectionTestUtils.setField(service, "routesByStation", routes);
	}

	@Test
	public void testRoutes() throws IOException {

		assertTrue(service.existDirectRoute(0, 1));
		assertFalse(service.existDirectRoute(0, 2));

	}

}
