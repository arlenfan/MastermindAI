#include <iostream>
#include<vector>
#include<algorithm>
#include <execution>
#include "current_time.cpp"

using namespace std;
int main() {
	  std::vector<int>foo(1000000);
		CurrentTime current_time;
	      uint64_t start1 = current_time.milliseconds();

	  std::for_each(
			      std::execution::par_unseq,
			          foo.begin(),
				      foo.end(),
				          [](auto&& item)
					      {
					      		//cout<< item <<endl;
							    });

	      uint64_t end1 = current_time.milliseconds();
	      cout<<"ms: "<<(end1-start1)<<endl;
		start1=current_time.milliseconds();
			      for (auto i=foo.begin();i!=foo.end();++i){
				//
			      }
			      end1=current_time.milliseconds();
			      cout<<"ms: "<<(end1-start1)<<endl;

	      return 0;
}
