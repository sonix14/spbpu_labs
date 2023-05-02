#include "SinglyOrderedList.h"

SinglyOrderedList::SinglyOrderedList(const SinglyOrderedList& right)
{
	head_ = nullptr;
	SinglyOrderedList list;
	Node* temp = right.head_;
	while (temp != nullptr)
	{
		list.add(temp->translation_);
		temp = temp->next_;
	}
	swap(list);
}

void SinglyOrderedList::add(std::string translation)
{
	if (insertNode(new Node(translation)))
	{
		count_++;
	}
}

void SinglyOrderedList::swap(SinglyOrderedList& other) noexcept
{
	std::swap(head_, other.head_);
	std::swap(count_, other.count_);
}

SinglyOrderedList& SinglyOrderedList::operator=(const SinglyOrderedList& right)
{
	if (this != &right)
	{
		SinglyOrderedList temp(right);
		swap(temp);
	}
	return *this;
}

SinglyOrderedList::SinglyOrderedList(SinglyOrderedList&& other) noexcept :
	head_(other.head_),
	count_(other.count_)
{
	other.head_ = nullptr;
	other.count_ = 0;
}

SinglyOrderedList& SinglyOrderedList::operator=(SinglyOrderedList&& right) noexcept
{
	Node* temp = right.head_;
	if (this != &right)
	{
		swap(right);
	}
	return *this;
}
bool SinglyOrderedList::insertNode(Node* node)
{
	if (head_ == nullptr)
	{
		head_ = node;
		return true;
	}
	else if (head_->translation_ == node->translation_)
	{
		delete node;
		return false;
	}
	else
	{
		Node* temp = head_;
		Node* prev = head_;
		if (temp->next_ == nullptr)
		{
			if (temp->translation_ > node->translation_)
			{
				node->next_ = head_;
				head_ = node;
				return true;
			}
			temp->next_ = node;
			return true;
		}
		while (temp->next_ != nullptr)
		{
			if (temp->translation_ < node->translation_)
			{
				if (temp->next_ == nullptr)
				{
					temp->next_ = node;
					return true;
				}
				if (temp->next_->translation_ == node->translation_)
				{
					delete node;
					return false;
				}
				if (node->translation_ < temp->next_->translation_)
				{
					node->next_ = temp->next_;
					temp->next_ = node;
					return true;
				}
				prev = temp;
				temp = temp->next_;
			}
			else if (temp->translation_ > node->translation_)
			{
				node->next_ = temp;
				prev->next_ = node;
				return true;
			}
			else
			{
				delete node;
				return false;
			}
		}
		temp->next_ = node;
		return true;
	}
}

bool SinglyOrderedList::insertTranslation(std::string& translation)
{
	if (insertNode(new Node(translation)) == true)
	{
		count_++;
		return 1;
	}
	return 0;
}

bool SinglyOrderedList::operator == (const SinglyOrderedList& temp)
{
	if (count_ != temp.count_)
	{
		return false;
	}
	Node* t1 = head_;
	Node* t2 = temp.head_;
	while (t1 != 0)
	{
		if (t1->translation_ != t2->translation_)
		{
			return false;
		}
		t1 = t1->next_;
		t2 = t2->next_;
	}
	return true;
}

SinglyOrderedList::~SinglyOrderedList()
{
	Node* current = nullptr;
	Node* next = head_;
	while (next != nullptr)
	{
		current = next;
		next = next->next_;
		delete current;
	}
}

void SinglyOrderedList::print(std::ostream& out)
{
	Node* current = head_;
	while (current != nullptr)
	{
		out << current->translation_ << ' ';
		current = current->next_;
	}
}
