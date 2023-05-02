#ifndef ELLIPSE_HPP
#define ELLIPSE_HPP

#include "Shape.hpp"

namespace roletskaya
{
  class Ellipse: public Shape
  {
  public:
    Ellipse() = delete;
    Ellipse(const point_t& pos, double vertRadius, double horRadius);
    ~Ellipse() = default;

    double getArea() const override;
    rectangle_t getFrameRect() const override;
    void move(const point_t& pos) override;
    void move(double x, double y) override;
    void scale(double coefficient) override;
  private:
    point_t pos_;
    double vertRadius_;
    double horRadius_;
  };
}
#endif
