#include <iomanip>

#include "set_shapes.hpp"

std::string roletskaya::getElem(std::string& string)
{
  size_t spaceIndex = string.find_first_of(' ', 0);
  std::string elem = string.substr(0, spaceIndex);
  if (spaceIndex != string.npos)
  {
    spaceIndex++;
  }
  string.erase(0, spaceIndex);
  return elem;
}

roletskaya::Ellipse roletskaya::createEllipse(std::string& string)
{
  double x = std::stod(getElem(string));
  double y = std::stod(getElem(string));
  point_t pos = { x, y };
  double vertRadius = std::stod(getElem(string));
  double horRadius = std::stod(getElem(string));
  Ellipse ellipse(pos, vertRadius, horRadius);
  return ellipse;
}

roletskaya::Ring roletskaya::createRing(std::string& string)
{
  double x = std::stod(getElem(string));
  double y = std::stod(getElem(string));
  point_t pos = { x, y };
  double outerRadius = std::stod(getElem(string));
  double innerRadius = std::stod(getElem(string));
  Ring ring(pos, outerRadius, innerRadius);
  return ring;
}

roletskaya::Rectangle roletskaya::createRectangle(std::string& string)
{
  double xLower = std::stod(getElem(string));
  double yLower = std::stod(getElem(string));
  point_t lowerLeft = { xLower, yLower };
  double xUpper = std::stod(getElem(string));
  double yUpper = std::stod(getElem(string));
  point_t upperRight = { xUpper, yUpper };
  Rectangle rectangle(lowerLeft, upperRight);
  return rectangle;
}
