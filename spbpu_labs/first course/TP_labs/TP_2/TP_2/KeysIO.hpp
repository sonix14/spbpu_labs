#ifndef KEYS_IO_HPP
#define KEYS_IO_HPP

#include <string>
#include <iosfwd>

namespace roletskaya
{
  struct DelimeterIO
  {
    char exp;
  };

  struct SllLitIO
  {
    long long& ref;
  };

  struct ChrLitIO
  {
    char& ref;
  };

  struct StringIO
  {
    std::string& ref;
  };

  std::istream& operator>>(std::istream& in, DelimeterIO&& dest);
  std::istream& operator>>(std::istream& in, SllLitIO&& dest);
  std::istream& operator>>(std::istream& in, ChrLitIO&& dest);
  std::istream& operator>>(std::istream& in, StringIO&& dest);
}
#endif
