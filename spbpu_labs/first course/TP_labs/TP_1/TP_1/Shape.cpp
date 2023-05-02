#include <stdexcept>

#include "Shape.hpp"

void roletskaya::Shape::scaleShape(double coefficient)
{
  if (coefficient <= 0.0)
  {
    throw std::invalid_argument("Incorrect coefficient\n");
  }
  scale(coefficient);
}
