package com.icebrian.buses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class RouteFileLoaderImplTest {
	
	RouteFileLoader loader = new RouteFileLoaderImpl();
	
	@Test
	public void loadRightFile() throws IOException {
		ReflectionTestUtils.setField(loader, "filePath", "data/example");
		Map<Integer, List<Integer>> routes = loader.loadFile();
		
		assertNotNull(routes);
		assertEquals(routes.size(),24);
		
		List<Integer> routesForStation5 = routes.get(5);
		assertNotNull(routesForStation5);
		assertEquals(routesForStation5.size(),5);
		assertTrue(routesForStation5.contains(2));
		assertFalse(routesForStation5.contains(1));
		
	}

	
	@Test(expected=FileNotFoundException.class)
	public void loadMissingFile() throws IOException {
		ReflectionTestUtils.setField(loader, "filePath", "data/missing");
		loader.loadFile();
	}
	
	@Test(expected=NumberFormatException.class)
	public void loadWrongFile() throws IOException {
		ReflectionTestUtils.setField(loader, "filePath", "data/wrongData");
		loader.loadFile();
	}


}
