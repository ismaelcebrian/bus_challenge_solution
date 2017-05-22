package com.icebrian.buses;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icebrian.buses.error.BadRequestException;
import com.icebrian.buses.response.BusRouteResponse;
import com.icebrian.buses.response.ErrorResponse;

/**
 * Rest controller that exposes the endpoint to check for direct routes.
 */
@RestController
public class BusRouteController {
	
	@Autowired
	private BusRouteService busRouteService;

	

	/**
	 * This endpoint takes the ids of two bus stations, and returns an object that contains thoses ids, and a boolean that
	 * indicates if there is a direct route connecting the 2 stations.
	 * The ids are mandatory and must be non-negative integers
	 * @param depSid - Departure station id 
	 * @param arrSid - Arrival station id
	 * @return
	 */
	@RequestMapping(value = "/api/direct", method = RequestMethod.GET)
	public BusRouteResponse hasDirectRoute(@RequestParam(value = "dep_sid") int depSid,
			@RequestParam(value = "arr_sid") int arrSid) {
		
		if(depSid < 0 || arrSid < 0) throw new BadRequestException("Bust station ids can not be negative numbers");

		BusRouteResponse response = new BusRouteResponse();
		response.setDepSid(depSid);
		response.setArrSid(arrSid);
		
		response.setDirectBusRoute(busRouteService.existDirectRoute(depSid, arrSid));

		return response;

	}
	
	/**
	 * Error handling for this controller. If the parameters are not valid, an object providing the details 
	 * of the error is returned, and the Http Status is 400 "bad request"
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleException(BadRequestException e, HttpServletRequest request) {
		ErrorResponse responseBody = new ErrorResponse();
		
		responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
		responseBody.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		responseBody.setException(e.getClass().getCanonicalName());
		responseBody.setMessage(e.getMessage());
		responseBody.setPath(request.getServletPath());
		
        return new ResponseEntity<ErrorResponse>(responseBody,HttpStatus.BAD_REQUEST);
    }

}
