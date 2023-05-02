#include <stdexcept>

#include "ellipse.hpp"

const double PI = 3.1415926535;

roletskaya::Ellipse::Ellipse(const roletskaya::point_t& pos, double vertRadius, double horRadius):
  pos_(pos),
  vertRadius_(vertRadius),
  horRadius_(horRadius)
{
  if ((vertRadius <= 0) || (horRadius <= 0))
  {
    throw std::invalid_argument("Ellipse incorrect\n");
  }
}

double roletskaya::Ellipse::getArea() const
{
  return PI * vertRadius_ * horRadius_;
}

roletskaya::rectangle_t roletskaya::Ellipse::getFrameRect() const
{
  roletskaya::rectangle_t rectangle;
  rectangle.pos = pos_;
  rectangle.height = vertRadius_ * 2;
  rectangle.width = horRadius_ * 2;
  return rectangle;
}

void roletskaya::Ellipse::move(const roletskaya::point_t& pos)
{
  pos_ = pos;
}

void roletskaya::Ellipse::move(double x, double y)
{
  pos_.x = x;
  pos_.y = y;
}

void roletskaya::Ellipse::scale(double coefficient)
{
  vertRadius_ = vertRadius_ * coefficient;
  horRadius_ = horRadius_ * coefficient;
}
