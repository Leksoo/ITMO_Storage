#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#include <cmath>
#include <map>
#include <unordered_map>
#include <ctime>
#include <cstdlib>


using namespace std;

typedef long long ll;

string fileName = "";
ofstream out(fileName + ".in");
ifstream in(fileName + ".in");


template<class T>
void printAllWithSpaces(vector<T>& vector, int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << " ";
        }
    } else {
        for (auto el : vector) {
            out << el + add << " ";
        }
    }
}

template<class T>
void printAllLn(vector<T>& vector, int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << '\n';
        }
    } else {
        for (auto el : vector) {
            out << el + add << '\n';
        }
    }
}

template<class T>
void printAllWithSpaces(T vector[], int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << " ";
        }
    } else {
        for (auto el : vector) {
            out << el + add << " ";
        }
    }
}

template<class T>
void printAllLn(T vector[], int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << '\n';
        }
    } else {
        for (auto el : vector) {
            out << el + add << '\n';
        }
    }
}

template<class T>
void printSingle(T el) {
    if (fileName.empty()) {
        cout << el << " ";
    } else {
        out << el << " ";
    }
}

template<class T>
void printSingleLn(T el) {
    if (fileName.empty()) {
        cout << el << '\n';
    } else {
        out << el << '\n';
    }
}

template<class T>
T read() {
    T tmp;
    if (fileName.empty()) {
        cin >> tmp;
    } else {
        in >> tmp;
    }
    return tmp;
}

double eps = 0.00000001;

bool eq(double a, double b) {
    return abs(a - b) < eps;
}

struct Crosspoint {
    double x;
    double y;

    Crosspoint(double x, double y) : x(x), y(y) {}

    Crosspoint() {}


    bool operator==(const Crosspoint& rhs) const {
        double diffX = x - rhs.x;
        double diffY = y - rhs.y;
        return (abs(diffX) < eps) &&
               (abs(diffY) < eps);
    }

    bool operator<(const Crosspoint& rhs) const {
        return (x < rhs.x - eps) || (abs(x - rhs.x) < eps && y < rhs.y - eps);
    }

};

struct Line {
    int x1;
    int y1;
    int x2;
    int y2;

    bool isVertical;

    //y=kx+b
    double k;
    double b;

    //x=vert
    double vert;


    Line(int x1, int y1, int x2, int y2) : x1(x1), y1(y1), x2(x2), y2(y2) {
        if (x1 == x2) {
            isVertical = true;
            vert = x1;
        } else {
            isVertical = false;
            k = (double) (y2 - y1) / (x2 - x1);
            b = (double) (x2 * y1 - x1 * y2) / (x2 - x1);
        }
    }

    Crosspoint cross(Line& other) {
        // x = vert
        // y = kx + b
        // y = k*vert + b
        if (isVertical && !other.isVertical) {
            return Crosspoint(vert, other.k * vert + other.b);
        }
        if (!isVertical && other.isVertical) {
            return Crosspoint(other.vert, k * other.vert + b);
        }
        // y1 = k1*x + b1
        // y2 = k2*x + b2
        // k1*x + b1 = k2*x + b2
        // x = (b2 - b1)/(k1 - k2)
        // y = k1*x + b1
        double x = (other.b - b) / (k - other.k);
        double y = k * x + b;
        return Crosspoint(x, y);
    }

    bool isCrossing(Line& other) {
        if (isVertical && other.isVertical) {
            return false;
        }
        if ((isVertical && !other.isVertical) || (!isVertical && other.isVertical)) {
            return true;
        }
        if (eq(k , other.k)) {
            return false;
        }
        return true;

    }


};


int N;
vector<Line> lines;
vector<Crosspoint> vertices;
vector<double> result;
map<Crosspoint, int> pointToIndex;
map<int, Crosspoint> indexToPoint;

vector<vector<int>> graph;


void solve() {


}


double pointX;
double pointY;

struct angleCompare {
    bool operator()(const int ind1, const int ind2) const {
        Crosspoint p1 = indexToPoint[ind1];
        Crosspoint p2 = indexToPoint[ind2];
        return atan2(p1.y - pointY, p1.x - pointX) <
               atan2(p2.y - pointY, p2.x - pointX);
    }
};

void input() {
    N = read<int>();
    for (int i = 0; i < N; ++i) {
        int x1 = read<int>();
        int y1 = read<int>();
        int x2 = read<int>();
        int y2 = read<int>();
        lines.push_back(Line(x1, y1, x2, y2));
    }
    int ind = 0;
    graph.resize(10000);
    set<pair<int, int>> added;
    for (int i = 0; i < N; ++i) {
        Line line1 = lines[i];
        set<Crosspoint> points;
        for (int j = 0; j < N; ++j) {
            if (i == j) {
                continue;
            }
            Line line2 = lines[j];
            if (line1.isCrossing(line2)) {
                Crosspoint p = line1.cross(line2);
                if (isfinite(p.x) && isfinite(p.y)) {
                    points.insert(p);
                    if (pointToIndex.count(p) == 0) {
                        vertices.push_back(p);
                        pointToIndex[p] = ind;
                        indexToPoint[ind] = p;
                        ind++;
                    }
                }
            }
        }
        if (points.size() < 2) {
            continue;
        }
        auto it = points.begin();
        for (int k = 0; k < points.size() - 1; ++k) {
            auto itplus = it;
            itplus++;
            graph[pointToIndex[*it]].push_back(pointToIndex[*itplus]);
            graph[pointToIndex[*itplus]].push_back(pointToIndex[*it]);


            it++;
        }
    }

    //check
//    for (int n = 0; n < graph.size(); ++n) {
//        for (int i = 0; i < graph[n].size(); ++i) {
//            if (n == graph[n][i]) {
//                printSingleLn("bad");
//            }
//        }
//    }
//    for (int n = 0; n < graph.size(); ++n) {
//        for (int i = 0; i < graph[n].size(); ++i) {
//            for (int j = 0; j < graph[n].size(); ++j) {
//                if (graph[n][i] == graph[n][j] && j != i) {
//                    printSingleLn("bad");
//                }
//            }
//        }
//    }

//    for (int i = 0; i < vertices.size(); ++i) {
//        Crosspoint p1 = vertices[i];
//        for (int k = 0; k < N; ++k) {
//            Line line = lines[k];
//            vector<int> dir1;
//            vector<int> dir2;
//            int ind1 = pointsOnLine[k][0];
//            Crosspoint point1 = indexToPoint[ind1];
//            auto vec = make_pair(point1.x - p1.x, point1.y - p1.y);
//            dir1.push_back(ind1);
//            for (int j = 1; j < pointsOnLine[k].size(); ++j) {
//                int pointInd = pointsOnLine[k][j];
//                Crosspoint p2 = indexToPoint[pointInd];
//
//
//            }
//            graph[pointToIndex[p1]].push_back(pointToIndex[p2]);
//            // graph[pointToIndex[p2]].push_back(pointToIndex[p1]);
//        }
//    }
    for (int i = 0; i < graph.size(); ++i) {
        pointX = indexToPoint[i].x;
        pointY = indexToPoint[i].y;
        sort(graph[i].begin(), graph[i].end(), angleCompare());
    }

    int S = graph.size();
    vector<vector<int>> used(S);
    for (int i = 0; i < S; ++i)
        used[i].resize(graph[i].size());
    for (int i = 0; i < S; ++i) {
        for (int j = 0; j < graph[i].size(); ++j) {
            if (!used[i][j]) {
                used[i][j] = true;
                int v = graph[i][j];
                int u = i;
                vector<int> face;
                while (true) {
                    face.push_back(v);

                    auto it = find(graph[v].begin(), graph[v].end(), u);
                    if (++it == graph[v].end()) {
                        it = graph[v].begin();
                    }
                    if (used[v][it - graph[v].begin()]) {
                        break;
                    }
                    used[v][it - graph[v].begin()] = true;
                    u = v, v = *it;
                }

                double res = 0.0;
                face.push_back(face[0]);
                for (int k = 0; k < face.size() - 1; ++k) {
                    res += indexToPoint[face[k]].x * indexToPoint[face[k + 1]].y;
                }
                res += indexToPoint[face.back()].x * indexToPoint[face[0]].y;
                for (int k = 0; k < face.size() - 1; ++k) {
                    res -= indexToPoint[face[k + 1]].x * indexToPoint[face[k]].y;
                }
                res -= indexToPoint[face.back()].y * indexToPoint[face[0]].x;
                res = abs(res);
                res *= 0.5;
                if (res >= 0.00000001) {
                    result.push_back(res);
                }

            }
        }
    }
    sort(result.begin(), result.end());
    cout.precision(15);
    if (result.size() == 0) {
        cout << "0" << '\n';
        return;
    }
    cout << result.size() - 1 << '\n';
    for (int m = 0; m < result.size() - 1; ++m) {
        cout << result[m] << '\n';

    }


}


int main() {
    input();
    solve();
    return 0;
}
