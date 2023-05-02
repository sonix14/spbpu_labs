#include <iomanip>
#include <stdexcept>

#include "shapes_array.hpp"

void roletskaya::insertShape(std::unique_ptr< ptrShape[] >&& shapesArray, std::unique_ptr< Shape >&& shape, size_t& size)
{
  if (shape == nullptr)
  {
    throw std::invalid_argument("Incorrect shape\n");
  }
  ptrShapes newShapesArray = std::make_unique< std::unique_ptr< Shape >[] >(size + 1);
  for (size_t i = 0; i < size; i++)
  {
    newShapesArray[i] = std::move(shapesArray[i]);
  }
  newShapesArray[size++] = std::move(shape);
  shapesArray = std::move(newShapesArray);
}

double roletskaya::getTotalArea(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size)
{
  double area = 0;
  for (size_t i = 0; i < size; i++)
  {
    area += shapesArray[i].get()->getArea();
  }
  return area;
}

void roletskaya::scaleAll(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size, const point_t& pos, double coefficient)
{
  for (size_t i = 0; i < size; i++)
  {
    double originalLeftX = shapesArray[i]->getFrameRect().pos.x - shapesArray[i]->getFrameRect().width / 2;
    double originalLeftY = shapesArray[i]->getFrameRect().pos.y - shapesArray[i]->getFrameRect().height / 2;
    double originalRightX = shapesArray[i]->getFrameRect().pos.x + shapesArray[i]->getFrameRect().width / 2;
    double originalRightY = shapesArray[i]->getFrameRect().pos.y + shapesArray[i]->getFrameRect().height / 2;
    shapesArray[i]->scaleShape(coefficient);
    double newLeftX = (originalLeftX - pos.x) * coefficient + pos.x;
    double newLeftY = (originalLeftY - pos.y) * coefficient + pos.y;
    double newRightX = (originalRightX - pos.x) * coefficient + pos.x;
    double newRightY = (originalRightY - pos.y) * coefficient + pos.y;
    shapesArray[i]->move((newLeftX + (newRightX - newLeftX) / 2), (newLeftY + (newRightY - newLeftY) / 2));
  }
}

void roletskaya::print(std::unique_ptr< ptrShape[] >&& shapesArray, size_t size, std::ostream& out)
{
  out << std::fixed << std::setprecision(1) << roletskaya::getTotalArea(std::move(shapesArray), size) << ' ';
  size_t i = 0;
  for (i = 0; i < size - 1; i++)
  {
    out << shapesArray[i]->getFrameRect().pos.x - shapesArray[i]->getFrameRect().width / 2 << ' ';
    out << shapesArray[i]->getFrameRect().pos.y - shapesArray[i]->getFrameRect().height / 2 << ' ';
    out << shapesArray[i]->getFrameRect().pos.x + shapesArray[i]->getFrameRect().width / 2 << ' ';
    out << shapesArray[i]->getFrameRect().pos.y + shapesArray[i]->getFrameRect().height / 2 << ' ';
  }
  out << shapesArray[i]->getFrameRect().pos.x - shapesArray[i]->getFrameRect().width / 2 << ' ';
  out << shapesArray[i]->getFrameRect().pos.y - shapesArray[i]->getFrameRect().height / 2 << ' ';
  out << shapesArray[i]->getFrameRect().pos.x + shapesArray[i]->getFrameRect().width / 2 << ' ';
  out << shapesArray[i]->getFrameRect().pos.y + shapesArray[i]->getFrameRect().height / 2 << '\n';
}
