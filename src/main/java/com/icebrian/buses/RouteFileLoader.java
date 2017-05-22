package com.icebrian.buses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Component in charge of reading the file with the bus routes, and loading its contents into a map.
 */
public interface RouteFileLoader {
	
	/**
	 * 
	 * @return a Map with the contents of the file. The key of each property is the id of a bus station.
	 * the value of each entry is a list with the ids of all the bus routes that contain that bus station.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Map<Integer, List<Integer>> loadFile() throws FileNotFoundException, IOException;

}
