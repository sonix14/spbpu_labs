#ifndef RING_HPP
#define RING_HPP

#include "Shape.hpp"

namespace roletskaya
{
  class Ring: public Shape
  {
  public:
    Ring() = delete;
    Ring(const point_t& pos, double outerRadius, double innerRadius);
    ~Ring() = default;

    double getArea() const override;
    rectangle_t getFrameRect() const override;
    void move(const point_t& pos) override;
    void move(double x, double y) override;
    void scale(double coefficient) override;
  private:
    point_t pos_;
    double outerRadius_;
    double innerRadius_;
  };
}
#endif
