#include <stdexcept>
#include "Dictionary.h"
#include "Utilites.h"

void Dictionary::createDictionary(std::ifstream& stream)
{
	int count = 0;
	std::string line = "";
	while (!stream.eof())
	{
		count = 0;
		std::getline(stream, line);
		std::string word = getElem(line);
		if (checkWord(word) == false)
		{
			continue;
		}
		if (line == "")
		{
			continue;
		}
		SinglyOrderedList list;
		std::string translation = "";
		if (line.size() == 0.0)
		{
			continue;
		}
		while (line.size() != 0.0)
		{
			translation = getElem(line);
			if (checkTranslation(translation) == false)
			{
				continue;
			}
			list.insertTranslation(translation);
			count++;
		}
		if (count > 0)
		{
			dictionary_.insert(word, list);
		}
	}
}

bool Dictionary::checkWord(const std::string& string) const
{
	for (int i = 0; i < string.length(); i++)
	{
		if (string[i] > 'z' || string[i] < 'a')
		{
			return false;
		}
	}
	return true;
}

bool Dictionary::checkTranslation(const std::string& string) const
{
	for (int i = 0; i < string.length(); i++)
	{
		if (string[i] > 'ÿ' || string[i] < 'à')
		{
			return false;
		}
	}
	return true;
}

bool Dictionary::insert(const std::string& string)
{
	std::string line = string;
	std::string word = getElem(line);
	if (dictionary_.search(word) == true)
	{
		throw std::invalid_argument("This word is already in the dictionary.\n");
		return false;
	}
	if (checkWord(word) == false)
	{
		throw std::invalid_argument("Incorrect input\n");
		return false;
	}
	SinglyOrderedList list;
	std::string translation = "";
	if (line.empty())
	{
		throw std::invalid_argument("Incorrect input\n");
		return false;
	}
	while (!line.empty())
	{
		translation = getElem(line);
		if (checkTranslation(translation) == false)
		{
			throw std::invalid_argument("Incorrect input\n");
			return false;
		}
		list.insertTranslation(translation);
	}
	dictionary_.insert(word, list);
	return true;
}

void Dictionary::print(std::ostream& out) const
{
	out << "Dictionary:\n";
	dictionary_.inorderWalk(out);
}

bool Dictionary::search(const std::string& word) const
{
	if (checkWord(word) == false)
	{
		throw std::exception("Incorrect input\n");
		return false;
	}
	if (dictionary_.search(word) == true)
	{
		dictionary_.printLine(word, std::cout);
		return true;
	}
	return false;
}

bool Dictionary::deleteKey(const std::string& word)
{
	if (checkWord(word) == false)
	{
		throw std::exception("Incorrect input\n");
		return false;
	}
	if (dictionary_.search(word) == false)
	{
		throw std::invalid_argument("There is no such word in the dictionary.\n");
		return false;
	}
	if (dictionary_.deleteKey(word) == true)
	{
		return true;
	}
	return false;
}
