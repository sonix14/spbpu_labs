#include <stdexcept>

#include "rectangle.hpp"

roletskaya::Rectangle::Rectangle(const rectangle_t& rectangle):
  rectangle_(rectangle)
{
  if (rectangle.width <= 0.0 || rectangle.height <= 0.0)
  {
    throw std::invalid_argument("Rectangle incorrect\n");
  }
}

roletskaya::Rectangle::Rectangle(const point_t& lowerLeft, const point_t& upperRight)
{
  if (lowerLeft.x >= upperRight.x || lowerLeft.y >= upperRight.y)
  {
    throw std::invalid_argument("Rectangle incorrect\n");
  }
  rectangle_.pos.x = (lowerLeft.x + upperRight.x) / 2;
  rectangle_.pos.y = (lowerLeft.y + upperRight.y) / 2;
  rectangle_.width = upperRight.x - lowerLeft.x;
  rectangle_.height = upperRight.y - lowerLeft.y;
}

double roletskaya::Rectangle::getArea() const
{
  return rectangle_.width * rectangle_.height;
}

roletskaya::rectangle_t roletskaya::Rectangle::getFrameRect() const
{
  return rectangle_;
}

void roletskaya::Rectangle::move(const point_t& pos)
{
  rectangle_.pos = pos;
}

void roletskaya::Rectangle::move(double x, double y)
{
  rectangle_.pos.x = x;
  rectangle_.pos.y = y;
}

void roletskaya::Rectangle::scale(double coefficient)
{
  rectangle_.width *= coefficient;
  rectangle_.height *= coefficient;
}
