#ifndef _BINARY_SEARCH_TREE_H
#define _BINARY_SEARCH_TREE_H
#include <iostream>
#include <string>
#include <fstream>
#include "SinglyOrderedList.h"

class BinarySearchTree
{
private:
	struct Node 
	{
		std::string word_;
		SinglyOrderedList translations_;
		Node* left_;
		Node* right_;
		Node(std::string word, SinglyOrderedList translation, Node* left = nullptr, Node* right = nullptr) :
			word_(word), translations_(translation), left_(left), right_(right)
		{};
		Node(const std::string word):
			word_(word), left_(nullptr), right_(nullptr)
		{
			SinglyOrderedList list;
			translations_ = list;
		};
	};
	Node* root_;
	Node* iterativeSearchNode(const std::string& key) const;
	void printNode(Node* root, std::ostream& out) const;
	void inorderWalk(Node* node, std::ostream& out) const;
	void deleteTree(Node* node);

public:
	BinarySearchTree() : root_(nullptr) {}
	BinarySearchTree(const BinarySearchTree& src) = delete;
	BinarySearchTree(BinarySearchTree&& src) noexcept;
	BinarySearchTree& operator= (const BinarySearchTree& src) = delete;
	BinarySearchTree& operator= (BinarySearchTree&& src) noexcept;
	~BinarySearchTree();
	void swap(BinarySearchTree& src);

	bool insert(const std::string& word, SinglyOrderedList& list);
	bool search(const std::string& word) const;
	bool deleteKey(const std::string& word);
	void inorderWalk(std::ostream& out) const;
	void insertList(const std::string& word, SinglyOrderedList& translations);
	void printLine(const std::string& word, std::ostream& out) const;
};
#endif
