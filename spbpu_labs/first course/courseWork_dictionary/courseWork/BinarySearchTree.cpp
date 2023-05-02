#include "BinarySearchTree.h"

BinarySearchTree::BinarySearchTree(BinarySearchTree&& src) noexcept :
	root_(src.root_)
{
	root_ = nullptr;
}

BinarySearchTree& BinarySearchTree::operator= (BinarySearchTree&& src) noexcept
{
	if (this != &src)
	{
		swap(src);
	}
	return *this;
}

void BinarySearchTree::swap(BinarySearchTree& src)
{
	std::swap(root_, src.root_);
}

BinarySearchTree::~BinarySearchTree()
{
	deleteTree(this->root_);
}

void BinarySearchTree::deleteTree(Node* node)
{
	if (node)
	{
		deleteTree(node->left_);
		deleteTree(node->right_);
		delete node;
	}
}

bool BinarySearchTree::insert(const std::string& word, SinglyOrderedList& list)
{
	if (root_ == nullptr)
	{
		root_ = new Node(word, list);
		return true;
	}
	Node* temp = root_;
	while (temp != nullptr)
	{
		if ((temp->word_ > word) && (temp->left_ != nullptr))
		{
			temp = temp->left_;
		}
		else if ((temp->word_ > word) && (temp->left_ == nullptr))
		{
			temp->left_ = new Node(word, list);
			return true;
		}
		if ((temp->word_ < word) && (temp->right_ != nullptr))
		{
			temp = temp->right_;
		}
		else if ((temp->word_ < word) && (temp->right_ == nullptr))
		{
			temp->right_ = new Node(word, list);
			return true;
		}
		if (temp->word_ == word)
		{
			return false;
		}
	}
}

bool BinarySearchTree::search(const std::string& word) const
{
	if (iterativeSearchNode(word) != nullptr)
	{
		return true;
	}
	else
	{
		return false;
	}
}

BinarySearchTree::Node* BinarySearchTree::iterativeSearchNode(const std::string& word) const
{
	Node* node = root_;
	while (node)
	{
		if (node->word_ > word)
		{
			node = node->left_;
			continue;
		}
		else if (node->word_ < word)
		{
			node = node->right_;
			continue;
		}
		else
		{
			return node;
		}
	}
	return nullptr;
}

bool BinarySearchTree::deleteKey(const std::string& word)
{
	Node* target = root_;
	Node* parent = nullptr;
	while (target != nullptr)
	{
		parent = target;
		if (target->word_ < word)
		{
			target = target->right_;
		}
		else if (target->word_ > word)
		{
			target = target->left_;
		}
		if (target == nullptr) return false;
		if (target->word_ == word)
		{
			if (target->left_ && target->right_)
			{
				Node* localMax = target->left_;
				while (localMax->right_)
				{
					parent = localMax;
					localMax = localMax->right_;
				}
				deleteKey(localMax->word_);
				target->word_ = localMax->word_;
				target->translations_ = localMax->translations_;
				return true;
			}
			else if (target->left_)
			{
				if (target == parent->left_) {
					parent->left_ = target->left_;
					return true;
				}
				else {
					parent->right_ = target->left_;
					return true;
				}
			}
			else if (target->right_)
			{
				if (target == parent->right_)
				{
					parent->right_ = target->right_;
					return true;
				}
				else
				{
					parent->left_ = target->right_;
					return true;
				}
			}
			else
			{
				if (target == parent->left_)
				{
					parent->left_ = nullptr;
					return true;
				}
				else
				{
					parent->right_ = nullptr;
					return true;
				}
			}
		}
	}
	if (target == nullptr) return false;
}

void BinarySearchTree::inorderWalk(std::ostream& out) const
{
	inorderWalk(this->root_, out);
	return;
}

void BinarySearchTree::inorderWalk(Node* node, std::ostream& out) const
{
	if (!node) return;
	inorderWalk(node->left_, out);
	printNode(node, out);
	inorderWalk(node->right_, out);
	return;
}

void BinarySearchTree::printNode(Node* root, std::ostream& out) const
{
	if (root == nullptr) return;
	out << root->word_ << " - ";
	root->translations_.print(out);
	out << '\n';
}

void BinarySearchTree::insertList(const std::string& word, SinglyOrderedList& translations)
{
	Node* temp = iterativeSearchNode(word);
	if (temp == nullptr)
	{
		return;
	}
	temp->translations_ = translations;
}

void BinarySearchTree::printLine(const std::string& word, std::ostream& out) const
{
	Node* temp = iterativeSearchNode(word);
	if (temp == nullptr)
	{
		return;
	}
	printNode(temp, out);
}
