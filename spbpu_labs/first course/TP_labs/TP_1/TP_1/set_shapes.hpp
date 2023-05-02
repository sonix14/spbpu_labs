#ifndef SET_SHAPES_HPP
#define SET_SHAPES_HPP

#include <string>

#include "ellipse.hpp"
#include "ring.hpp"
#include "rectangle.hpp"

namespace roletskaya
{
  Ellipse createEllipse(std::string& string);
  Ring createRing(std::string& string);
  Rectangle createRectangle(std::string& string);
  std::string getElem(std::string& string);
}
#endif
