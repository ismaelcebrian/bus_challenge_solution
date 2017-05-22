package com.icebrian.buses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.icebrian.buses.error.BadRequestException;
import com.icebrian.buses.response.BusRouteResponse;

public class BusRouteControllerTest {

	BusRouteController controller = new BusRouteController();

	@Before
	public void setup() {
		BusRouteService serviceMock = mock(BusRouteService.class);
		when(serviceMock.existDirectRoute(0, 1)).thenReturn(true);
		when(serviceMock.existDirectRoute(2, 3)).thenReturn(false);
		ReflectionTestUtils.setField(controller, "busRouteService", serviceMock);

	}

	@Test
	public void testValidRoute() {

		BusRouteResponse routeResponse = controller.hasDirectRoute(0, 1);
		assertEquals(0, routeResponse.getDepSid());
		assertEquals(1, routeResponse.getArrSid());
		assertTrue(routeResponse.isDirectBusRoute());

	}

	@Test
	public void testNoRoute() {

		BusRouteResponse routeResponse = controller.hasDirectRoute(2, 3);
		assertEquals(2, routeResponse.getDepSid());
		assertEquals(3, routeResponse.getArrSid());
		assertFalse(routeResponse.isDirectBusRoute());

	}

	@Test(expected = BadRequestException.class)
	public void testNegativeDep() {

		controller.hasDirectRoute(-1, 0);

	}

	@Test(expected = BadRequestException.class)
	public void testNegativeArr() {

		controller.hasDirectRoute(0, -1);

	}

}
