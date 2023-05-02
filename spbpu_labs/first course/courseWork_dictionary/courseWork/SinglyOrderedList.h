#ifndef SINGLY_ORDERED_LIST_H
#define SINGLY_ORDERED_LIST_H
#include <iostream>
#include <string>

class SinglyOrderedList
{
private:
    struct Node
    {
        std::string translation_;
        Node* next_;
        Node(std::string translation, Node* next = nullptr) : translation_(translation), next_(next) { }
    };
    int count_;
    Node* head_;
    void swap(SinglyOrderedList& other) noexcept;
    Node* head() const
    {
        return head_;
    }
    bool insertNode(Node* translation);
    void add(std::string translation);

public:
    SinglyOrderedList() : count_(0), head_(nullptr) {}
    SinglyOrderedList(const SinglyOrderedList& src);
    SinglyOrderedList& operator=(const SinglyOrderedList& right);
    SinglyOrderedList(SinglyOrderedList&& other) noexcept;
    SinglyOrderedList& operator=(SinglyOrderedList&& right) noexcept;
    ~SinglyOrderedList();

    int count() const { return count_; }
    bool insertTranslation(std::string& translation);
    bool operator==(const SinglyOrderedList& temp);
    void print(std::ostream& out);
};
#endif
