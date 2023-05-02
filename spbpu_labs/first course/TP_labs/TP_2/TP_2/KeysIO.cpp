#include "KeysIO.hpp"
#include <algorithm>
#include <istream>

std::istream& roletskaya::operator>>(std::istream& in, DelimeterIO&& dest)
{
  std::istream::sentry sentry(in);
  if (!sentry)
  {
    return in;
  }
  char c = '0';
  in >> c;
  if (in && (c != dest.exp))
  {
    in.setstate(std::ios::failbit);
  }
  return in;
}

std::istream& roletskaya::operator>>(std::istream& in, SllLitIO&& dest)
{
  std::istream::sentry sentry(in);
  if (!sentry)
  {
    return in;
  }
  long long value = 0.0;
  in >> value;
  char c = in.get();
  if (c == 'l')
  {
    in >> DelimeterIO{'l'};
  }
  else if (c == 'L')
  {
    in >> DelimeterIO{'L'};
  }
  else
  {
    return in;
  }
  if (in)
  {
    dest.ref = value;
  }
  return in;
}

std::istream& roletskaya::operator>>(std::istream& in, ChrLitIO&& dest)
{
  std::istream::sentry sentry(in);
  if (!sentry)
  {
    return in;
  }
  in >> DelimeterIO{'\''} >> dest.ref >> DelimeterIO{'\''};
  return in;
}

std::istream& roletskaya::operator>>(std::istream& in, roletskaya::StringIO&& dest)
{
  std::istream::sentry sentry(in);
  if (!sentry)
  {
    return in;
  }
  return std::getline(in >> DelimeterIO{'"'}, dest.ref, '"');
}
