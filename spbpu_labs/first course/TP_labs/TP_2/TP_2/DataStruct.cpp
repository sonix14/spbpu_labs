#include "DataStruct.hpp"
#include <algorithm>
#include <iostream>
#include "Guards.hpp"
#include "KeysIO.hpp"

std::istream& roletskaya::operator>>(std::istream& in, DataStruct& data)
{
  std::istream::sentry sentry(in);
  if (!sentry)
  {
    return in;
  }
  DataStruct input;
  {
    using sep = DelimeterIO;
    using sllLit = SllLitIO;
    using chrLit = ChrLitIO;
    using str = StringIO;
    in >> sep{'('} >> sep{':'};
    for (int i = 0; i < 3; i++)
    {
      in >> sep{'k'} >> sep{'e'} >> sep{'y'};
      char key = in.get();
      if (key == '1')
      {
        in >> sllLit{input.key1};
      } 
      else if (key == '2')
      {
        in >> chrLit{input.key2};
      } 
      else if (key == '3')
      {
        in >> str{input.key3};
      } 
      else
      {
        in.setstate(std::ios::failbit);
      }
      in >> sep{':'};
    }
    in >> sep{')'};
  }
  if (in)
  {
    data = input;
  }
  return in;
}

std::ostream& roletskaya::operator<<(std::ostream& out, const roletskaya::DataStruct& data)
{
  std::ostream::sentry sentry(out);
  if (!sentry)
  {
    return out;
  }
  Guards guard(out);
  out << "(:key1 " << data.key1 << "ll";
  out << ":key2 " << '\'' << data.key2 << '\'';
  out << ":key3 " << '\"' << data.key3 << '\"';
  out << ":)";
  return out;
}

bool roletskaya::operator<(const DataStruct& left, const DataStruct& right)
{
  if (left.key1 != right.key1)
  {
    return left.key1 < right.key1;
  }
  else if (left.key2 != right.key2)
  {
    return left.key2 < right.key2;
  }
  else
  {
    return left.key3.size() < right.key3.size();
  }
}
