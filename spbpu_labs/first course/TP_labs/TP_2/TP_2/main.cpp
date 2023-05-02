#include <algorithm>
#include <iomanip>
#include <iostream>
#include <iterator>
#include <vector>

#include "DataStruct.hpp"

int main()
{
  std::vector< roletskaya::DataStruct > vector;
  using istreamIterator = std::istream_iterator< roletskaya::DataStruct >;
  while (!std::cin.eof())
  {
    std::cin.clear();
    std::copy(istreamIterator(std::cin), istreamIterator(), std::back_inserter(vector));
  }
  std::sort(vector.begin(), vector.end());
  using ostreamIterator = std::ostream_iterator< roletskaya::DataStruct >;
  std::copy(std::begin(vector), std::end(vector), ostreamIterator(std::cout, "\n"));
  return 0;
}
