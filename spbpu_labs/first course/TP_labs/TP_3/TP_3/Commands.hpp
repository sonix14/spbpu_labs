#ifndef COMMANDS_HPP
#define COMMANDS_HPP
#include "Polygon.hpp"

namespace roletskaya
{
	using figuresArray = std::vector< Polygon >;

	double getAreaOdd(figuresArray& polygons);
	double getAreaEven(figuresArray& polygons);
	double getAreaMean(figuresArray& polygons);
	double getAreaNumOfVertexes(figuresArray& polygons);
	double getMaxVertexes(figuresArray& polygons);
	double getMaxArea(figuresArray& polygons);
	double getMinVertexes(figuresArray& polygons);
	double getMinArea(figuresArray& polygons);
	int getCountEven(figuresArray& polygons);
	int getCountOdd(figuresArray& polygons);
	int getCountNumOfVertexes(figuresArray& polygons);

	int getPerms(figuresArray& polygons);
	int getRightShapes(figuresArray& polygons);
}

#endif
