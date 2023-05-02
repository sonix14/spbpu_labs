#include "ProcessingCommands.hpp"

bool roletskaya::isOddCount(Polygon& polygon)
{
	return polygon.points.size() % 2 != 0;
}

bool roletskaya::isEvenCount(Polygon& polygon)
{
	return polygon.points.size() % 2 == 0;
}