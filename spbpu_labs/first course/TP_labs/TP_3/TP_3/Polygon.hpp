#ifndef POLYGON_HPP
#define POLYGON_HPP
#include <vector>

namespace roletskaya
{
  struct Point
  {
    int x, y;
  };
  struct Polygon
  {
    std::vector< Point > points;
  };

  double getSummArea();

}
#endif
