#include "Guards.hpp"

roletskaya::Guards::Guards(std::basic_ios< char >& s):
  s_(s),
  fill_(s.fill()),
  precision_(s.precision()),
  flags_(s.flags())
{}

roletskaya::Guards::~Guards()
{
  s_.fill(fill_);
  s_.precision(precision_);
  s_.flags(flags_);
}
