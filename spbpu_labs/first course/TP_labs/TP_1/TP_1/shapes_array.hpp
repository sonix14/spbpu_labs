#ifndef SHAPES_ARRAY_HPP
#define SHAPES_ARRAY_HPP

#include <memory>

#include "Shape.hpp"

namespace roletskaya
{
  using ptrShape = std::unique_ptr< Shape >;
  using ptrShapes = std::unique_ptr< ptrShape[] >;

  void insertShape(std::unique_ptr< ptrShape[] >&& shapesArray, std::unique_ptr< Shape >&& shape, size_t& size);
  double getTotalArea(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size);
  void scaleAll(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size, const point_t& pos, double coefficient);
  void print(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size, std::ostream& out);
}
#endif
