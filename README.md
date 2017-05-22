# bus_challenge_solution
A solution for the bus route challenge described in [this page](https://github.com/goeuro/challenges/tree/master/bus_route_challenge).

### Used technologies:
The solution uses Spring Boot as the main framework.

### Description of the solution:
The service loads the routes file (with the provided path) using simply a BufferedReader. The information of the routes is then stored in memory in Hashmap. The key of each entry is the id of a bus station, and the value is a list (of integers) that represent all the bus routes that visit that station.

*Note:* The solution work under the assumption that bus routes work in both directions, so  following route  
`0 10 11 12`   
connects not only the station 10 with 12, but also 12 with 10. At the time of writing this, I am not particularly happy I made that assumption.
