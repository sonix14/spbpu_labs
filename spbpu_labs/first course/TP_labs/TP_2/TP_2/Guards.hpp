#ifndef GUARDS_HPP
#define GUARDS_HPP

#include <ios>
#include "DataStruct.hpp"

namespace roletskaya
{
  class Guards
  {
  public:
    Guards() = delete;
    Guards(std::basic_ios< char >& s);
    ~Guards();

  private:
    std::basic_ios< char >& s_;
    char fill_;
    std::streamsize precision_;
    std::basic_ios< char >::fmtflags flags_;
  };
}
#endif
