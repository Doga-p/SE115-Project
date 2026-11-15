# SE115 Maps — Fastest Route Finder

A command-line Java program that reads a map of cities and routes from a text file
and finds the fastest route between two cities using **Dijkstra's algorithm**.
Individual term project for SE 115 Introduction to Programming I,
Izmir University of Economics (Fall 2024).

## Features
- Reads cities, routes and travel times from an input file
- Finds the fastest route with Dijkstra's algorithm and rebuilds the full path
- Writes the route and total travel time to an output file
- Validates the input line by line and reports errors with the line number
  (wrong city count, unknown city, invalid time, malformed route)

## How to run
Requires JDK 17 or newer.

```bash
javac -d out src/*.java
java -cp out SE115Maps input.txt output.txt
```

## Input format
```
4            <- number of cities
A B C D      <- city names
4            <- number of routes
A B 5        <- from, to, time in minutes
B C 3
A C 10
C D 2
A D          <- start city, destination
```
Routes are one-directional (from the first city to the second).

## Example output
```
Fastest Way: A -> B -> C -> D
Total Time: 10 min
```

## Project structure
| File | Responsibility |
|---|---|
| `SE115Maps.java` | Entry point; reads and validates the input file, writes the result |
| `CountryMap.java` | Stores cities and routes; finds the routes leaving a city |
| `WayFinder.java` | Dijkstra's algorithm and path reconstruction |
| `City.java` | `City` and `Route` classes |
