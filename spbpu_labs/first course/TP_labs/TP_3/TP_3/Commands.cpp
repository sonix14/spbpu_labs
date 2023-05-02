#include "Commands.hpp"

#include "ProcessingCommands.hpp"

double roletskaya::getAreaOdd(figuresArray& polygons)
{
	auto end = std::copy_if(polygons.begin(), polygons.end(), temp.begin(), isEvenPointsCount);
	return std::accumulate(temp.begin(), end, 0.0, calculateSumArea);
}
