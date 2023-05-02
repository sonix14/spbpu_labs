#include <iostream>

#include "set_shapes.hpp"
#include "shapes_array.hpp"

int main()
{
  size_t size = 0;
  std::string string = "";
  std::string shapeName = "";
  roletskaya::ptrShapes shapesArray = std::make_unique< std::unique_ptr< roletskaya::Shape >[] >(size);
  roletskaya::point_t posForScale = {0, 0};
  double coefficient = 0;
  bool coefficientCorrectness = false;

  while (!std::cin.eof())
  {
    try
    {
      std::getline(std::cin, string);
      if (string.length() > 0)
      {
        shapeName = roletskaya::getElem(string);
        if (shapeName == "RECTANGLE")
        {
          roletskaya::Rectangle rectangle = roletskaya::createRectangle(string);
          roletskaya::insertShape(std::move(shapesArray), std::move(std::make_unique< roletskaya::Rectangle >(rectangle)), size);
        }
        else if (shapeName == "RING")
        {
          roletskaya::Ring ring = roletskaya::createRing(string);
          roletskaya::insertShape(std::move(shapesArray), std::move(std::make_unique< roletskaya::Ring >(ring)), size);
        }
        else if (shapeName == "ELLIPSE")
        {
          roletskaya::Ellipse ellipse = roletskaya::createEllipse(string);
          roletskaya::insertShape(std::move(shapesArray), std::move(std::make_unique< roletskaya::Ellipse >(ellipse)), size);
        }
        else if (shapeName == "SCALE")
        {
          coefficientCorrectness = true;
          double x = std::stod(roletskaya::getElem(string));
          double y = std::stod(roletskaya::getElem(string));
          posForScale = {x, y};
          coefficient = std::stod(roletskaya::getElem(string));
          if (coefficient <= 0.0)
          {
            coefficientCorrectness = false;
          }
          if (size == 0)
          {
            std::cerr << "Incorrect input\n";
            return 1;
          }
          roletskaya::print(std::move(shapesArray), size, std::cout);
          roletskaya::scaleAll(std::move(shapesArray), size, posForScale, coefficient);
          roletskaya::print(std::move(shapesArray), size, std::cout);
          break;
        }
        else
        {
          continue;
        }
      }
    }
    catch (const std::exception& e)
    {
      std::cerr << e.what();
    }
  }
  if (!coefficientCorrectness)
  {
    std::cerr << "Incorrect input\n";
    return 1;
  }
  return 0;
}
