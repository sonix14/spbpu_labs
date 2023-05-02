#include <iostream>
#include <fstream>
#include <windows.h>
#include "Dictionary.h"
#include "Utilites.h"

#define ERROR_FILE_NOT_OPEN " : Couldn't open the file"

int main()
{
    SetConsoleCP(1251);
    SetConsoleOutputCP(1251);
    std::string line = "";
    std::string inFileName = "";
    try
    {
        Dictionary dictionary;
        std::ifstream in;
        std::cout << "Enter file's name to create a dictionary: ";
        std::cin >> inFileName;
        in.open(inFileName);
        if (!in)
        {
            throw inFileName + ERROR_FILE_NOT_OPEN;
        }
        dictionary.createDictionary(in);
        in.close();
        std::cout << '\n';
        dictionary.print(std::cout);
        std::cout << "\nIf you want to insert new word to the dictionary, write INSERT and new word with translations.\n"
            << "If you want to delete a word from the dictionary, write DELETE and word you want to delete.\n"
            << "If you want to find a word in the dictionary, write SEARCH and word you want to find.\n"
            << "If you want to display the dictionary, write PRINT and, if you want to output to a file, write it's name.\n"
            << "If you want to exit, write EXIT\n\n";
        std::getline(std::cin, line);
        while (!std::cin.eof())
        {
            try
            {
                std::getline(std::cin, line);   
                if (line == "EXIT")
                {
                    return 0;
                }
                else
                {
                    std::string command = getElem(line);
                    if (command == "INSERT")
                    {
                        if (dictionary.insert(line) == true)
                        {
                            std::cout << "The word is successfully inserted.\n\n";
                            continue;
                        }
                    }
                    else if (command == "DELETE")
                    {
                        if (dictionary.deleteKey(line) == true)
                        {
                            std::cout << "The word is successfully deleted.\n\n";
                            continue;
                        }
                    }
                    else if (command == "SEARCH")
                    {
                        if (dictionary.search(line) == true)
                        {
                            std::cout << "There is such word in the dictionary.\n\n";
                            continue;
                        }
                        else
                        {
                            std::cout << "There is no such word in the dictionary.\n\n";
                            continue;
                        }
                    }
                    else if (command == "PRINT")
                    {
                        if (line.empty())
                        {
                            dictionary.print(std::cout);
                            std::cout << '\n';
                            continue;
                        }
                        else
                        {
                            std::string fileName = getElem(line);
                            std::ofstream fout;
                            fout.open(fileName);
                            if (!fout)
                            {
                                throw fileName + ERROR_FILE_NOT_OPEN;
                            }
                            dictionary.print(fout);
                            fout.close();
                            std::cout << '\n';
                            continue;
                        }
                    }
                    else
                    {
                        std::cout << "No such command, try again.\n\n";
                        continue;
                    }
                }
                continue;
            }
            catch (const std::exception& error)
            {
                std::cerr << error.what() << '\n';
            }
            catch (std::string& error)
            {
                std::cerr << '\n' << error << '\n';
            }
        }
    }
    catch (std::string& error)
    {
        std::cerr << '\n' << error << '\n';
        return -1;
    }
    catch (const std::exception& error)
    {
        std::cerr << error.what();
        return -1;
    }
    return 0;
}
