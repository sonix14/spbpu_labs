#ifndef RECTANGLE_HPP
#define RECTANGLE_HPP

#include "Shape.hpp"

namespace roletskaya
{
  class Rectangle: public Shape
  {
  public:
    Rectangle() = delete;
    Rectangle(const rectangle_t& rectangle);
    Rectangle(const point_t& lowerLeft, const point_t& upperRight);
    ~Rectangle() = default;

    double getArea() const override;
    rectangle_t getFrameRect() const override;
    void move(const point_t& pos) override;
    void move(double x, double y) override;
    void scale(double coefficient) override;
  private:
    rectangle_t rectangle_;
  };
}
#endif
