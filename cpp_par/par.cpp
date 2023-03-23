#include <iostream>
#include<vector>
#include<algorithm>
#include <execution>



#ifndef CURRENT_TIME_H
#define CURRENT_TIME_H

#include <chrono>
#include <cstdint>

class CurrentTime {
    std::chrono::high_resolution_clock m_clock;

public:
//    uint64_t milliseconds();
//    uint64_t microseconds();
//    uint64_t nanoseconds();
    uint64_t milliseconds()
    {
        return std::chrono::duration_cast<std::chrono::milliseconds>
                (m_clock.now().time_since_epoch()).count();
    }

    uint64_t microseconds()
    {
        return std::chrono::duration_cast<std::chrono::microseconds>
                (m_clock.now().time_since_epoch()).count();
    }

    uint64_t nanoseconds()
    {
        return std::chrono::duration_cast<std::chrono::nanoseconds>
                (m_clock.now().time_since_epoch()).count();
    }
};

#endif  /* CURRENT_TIME_H */

using namespace std;

int main() {
    std::vector<int> foo(10000);
    CurrentTime current_time;

    uint64_t start1 = current_time.milliseconds();

    std::for_each(
            std::execution::par_unseq,
            foo.begin(),
            foo.end(),
            [](auto &&item) {
                //cout<< item <<endl;
            });


    uint64_t end1 = current_time.milliseconds();
    cout<<(end1-start1)<<endl;
    start1 = current_time.milliseconds();
    for (auto i = foo.begin(); i != foo.end(); ++i) {
        //
    }
    end1 = current_time.milliseconds();
    cout<<(end1-start1)<<endl;
    cout<<"Done"<<endl;

    return 0;
}
