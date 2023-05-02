#include <stdexcept>

#include "ring.hpp"

const double PI = 3.1415926535;

roletskaya::Ring::Ring(const point_t& pos, double outerRadius, double innerRadius) :
  pos_(pos),
  outerRadius_(outerRadius),
  innerRadius_(innerRadius)
{
  if ((outerRadius < innerRadius) || (outerRadius <= 0) || (innerRadius <= 0))
  {
    throw std::invalid_argument("Ring incorrect\n");
  }
}

double roletskaya::Ring::getArea() const
{
  return PI * (outerRadius_ * outerRadius_ - innerRadius_ * innerRadius_);
}

roletskaya::rectangle_t roletskaya::Ring::getFrameRect() const
{
  roletskaya::rectangle_t rectangle;
  rectangle.pos = pos_;
  rectangle.height = outerRadius_ * 2;
  rectangle.width = rectangle.height;
  return rectangle;
}

void roletskaya::Ring::move(const roletskaya::point_t& pos)
{
  pos_ = pos;
}

void roletskaya::Ring::move(double x, double y)
{
  pos_.x = x;
  pos_.y = y;
}

void roletskaya::Ring::scale(double coefficient)
{
  outerRadius_ = outerRadius_ * coefficient;
  innerRadius_ = innerRadius_ * coefficient;
}
