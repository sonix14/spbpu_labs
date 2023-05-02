#ifndef DICTIONARY_H
#define DICTIONARY_H
#include <fstream>
#include <string>
#include "BinarySearchTree.h"

class Dictionary
{
private:
	BinarySearchTree dictionary_;

	bool checkWord(const std::string& string) const;
	bool checkTranslation(const std::string& string) const;
public:
	Dictionary() = default;
	Dictionary(const Dictionary& src) = delete;
	Dictionary(Dictionary&& src) = default;
	Dictionary& operator= (const Dictionary& src) = delete;
	Dictionary& operator= (Dictionary&& src) = delete;
	~Dictionary() = default;

	void createDictionary(std::ifstream& stream);
	bool insert(const std::string& string);
	bool search(const std::string& word) const;
	bool deleteKey(const std::string& word);
	void print(std::ostream& out) const;
};
#endif
