#include <iostream>
#include <dirent.h>
#include <sys/stat.h>
#include <cstring>
#include <vector>
#include <unistd.h>
#include <wait.h>

using namespace std;

const static string INUM = "-inum";
const static string NAME = "-name";
const static string SIZE = "-size";
const static string NLINKS = "-nlinks";
const static string EXEC = "-exec";

vector<string> searchResult;

unsigned long long toULL(string &str) {
    try {
        return stoull(str);
    }
    catch (invalid_argument &e) {
        throw invalid_argument("Wrong number format in arguments");
    }
}

long long toLL(string &str) {
    try {
        return stoll(str);
    }
    catch (invalid_argument &e) {
        throw invalid_argument("Wrong number format in arguments");
    }
}

class Settings {
public:
    Settings() {
        hasInum = false;
        hasExec = false;
        hasLinknum = false;
        hasSize = false;
        hasName = false;
    }

    void setInum(string &strNum) {
        hasInum = true;
        inum = toULL(strNum);
    }

    void setName(string &str) {
        hasName = true;
        name = str;
    };

    void setSize(string &str) {
        hasSize = true;
        char sym = str[0];
        if (sym == '-' || sym == '+' || sym == '=') {
            sizeCompareSymbol = sym;
            auto num = str.substr(1);
            size = toLL(num);
        }
    }

    void setLinknum(string &strNum) {
        hasLinknum = true;
        linknum = toULL(strNum);
    }

    void setExec(string &str) {
        hasExec = true;
        exec = str;
    }

    bool checkInum(unsigned long long val) {
        return !hasInum || (hasInum && val == inum);
    }

    bool checkName(string val) {
        return !hasName || (hasName && name == val);
    }

    bool checkLinknum(nlink_t val) {
        return !hasLinknum || (hasLinknum && linknum == val);
    }

    string getExec() {
        if (hasExec) {
            return exec;
        }
        return "";
    }

    bool checkSize(off_t &val) {
        if (!hasSize) {
            return true;
        }
        if (sizeCompareSymbol == '-' && val < size) {
            return true;
        }
        if (sizeCompareSymbol == '+' && val > size) {
            return true;
        }
        if (sizeCompareSymbol == '=' && val == size) {
            return true;
        }
        return false;
    }


private:
    ino_t inum{};
    bool hasInum;

    string name;
    bool hasName;

    off_t size{};
    char sizeCompareSymbol{};
    bool hasSize;

    nlink_t linknum{};
    bool hasLinknum;

    string exec;
    bool hasExec;
};


void printMsg(const string &msg) {
    cout << msg << endl;
}

void printErrorMsg(const string &msg) {
    cerr << msg << endl;
}

void run(string &pathToExe, string &arg) {
    vector<char *> args;
    args.push_back(&(pathToExe.front()));
    args.push_back(&(arg.front()));
    args.push_back(nullptr);
    vector<char *> env;
    env.push_back(nullptr);
    pid_t pid = fork();
    if (pid == 0) {
        if (execve(args[0], args.data(), env.data()) == -1) {
            printErrorMsg("Error while executing: " + pathToExe);
            exit(-1);
        }
    } else if (pid > 0) {
        int res;
        if (waitpid(pid, &res, 0) != -1) {
            printMsg("result code: " + to_string(WEXITSTATUS(res)));
        } else {
            printErrorMsg("Error while executing child");
        }
    } else {
        printErrorMsg("Error while forking: " + pathToExe);
    }


}

void find(Settings &settings, string &dirName) {
    auto dir = opendir(dirName.data());
    if (dir == nullptr) {
        printErrorMsg("Cannot open directory: " + dirName);
        return;
    }
    while (true) {
        auto curFile = readdir(dir);
        if (curFile == nullptr) {
            break;
        }
        auto fileName = curFile->d_name;
        if (!strcmp(fileName, "..") || !strcmp(fileName, ".")) {
            continue;
        }
        string path = dirName + '/' + fileName;
        if (curFile->d_type == DT_DIR) {
            find(settings, path);
        } else if (curFile->d_type == DT_REG) {
            struct stat fileSettings{};
            if (stat(path.data(), &fileSettings) < 0) {
                printErrorMsg("cannot get file stat: " + path);
                continue;
            }
            if (settings.checkInum(fileSettings.st_ino)
                && settings.checkLinknum(fileSettings.st_nlink)
                && settings.checkName(fileName)
                && settings.checkSize(fileSettings.st_size)) {
                auto exec = settings.getExec();
                if (exec.empty()) {
                    searchResult.push_back(path);
                } else {
                    run(exec, path);
                }
            }

        }
    }
    closedir(dir);
}


int main(int argc, char *argv[]) {
    try {
        if (argc <= 1 || argc % 2 == 1) {
            throw invalid_argument("Wrong number of arguments");
        }
        string start = argv[1];
        Settings settings;
        for (int i = 2; i < argc; ++i) {
            string cur = argv[i];
            string val = argv[++i];
            if (cur == INUM) {
                settings.setInum(val);
            } else if (cur == NAME) {
                settings.setName(val);
            } else if (cur == SIZE) {
                settings.setSize(val);
            } else if (cur == EXEC) {
                settings.setExec(val);
            } else if (cur == NLINKS) {
                settings.setLinknum(val);
            } else {
                throw invalid_argument("Wrong argument");
            }
        }
        find(settings, start);
        for (auto el : searchResult) {
            printMsg(el);
        }

    }
    catch (invalid_argument &e) {
        printErrorMsg(e.what());
        return EXIT_FAILURE;
    }
    return 0;
}