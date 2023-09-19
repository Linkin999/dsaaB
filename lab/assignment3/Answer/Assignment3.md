<h1 align = "center">Assignment3 </h1>

<center> 张旭东 12011923 </center>

## 1.Problem1

### 1.1 Explanation of Algorithm

​	Inspired by Undirected Graph and Depth-First-Paths in the the textbook, $Problem$ $1$ can be considered to search the penultimate node in the path from the root node to the node. However, the differences between $Problem$ $1$ and the textbook is that the root node of $Problem$ $1$ is $1$ while that of the textbook is $0$. So, some adjustment is needed to be done. The adjustment is as follows:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217213430367.png" alt="image-20221217213430367" style="zoom:67%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217213532630.png" alt="image-20221217213532630" style="zoom: 80%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217213611508.png" alt="image-20221217213611508" style="zoom: 80%;" />

​	According to the above pictures, the second element in each queue is the target we want.

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217213837999.png" alt="image-20221217213837999" style="zoom: 80%;" />

​	The result of comparison of output $A1.out$ and $A2.out$ between the output of input sample $A1.in$ and $A2.in$ using my method is following:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217214204901.png" alt="image-20221217214204901"  />

  ![image-20221217214253986](C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217214253986.png)

### 1.2 Analysis of  Time Complexity

​	According to the Depth-First-Paths, the time it takes to find a path from a given starting point to any marked vertex using depth-first search is proportional to the length of the path. Assume that the time of accessing, finding, dequeuing is the same. For a path whose length is $x$, the time it takes is $n+2$. In $Problem$ $1$, the tree is binary tree. So for $N$ tree nodes, which means the number of paths is $N-1$, the total time is 
$$
T=2×1+2^2×2+2^3×3+·······+\frac{N}{2}×lgN+2(N-1)
$$
​	When $N$ is approximate to infinite, the $Big Oh$ is 
$$
O(N)=NlgN
$$


## 2.Problem2

### 2.1 Explanation of Algorithm

​	Inspired by Undirected Graph , Depth-First-Paths in the the textbook and $Problem$ $1$, $Problem$ $2$ can be considered to search special paths. The meaning of special is that the cost of the path is equal to the target number and the start point and end point is the root node and the leaf node. So, some adjustment is needed to be done. 

​	First, the cost of each edge is needed to be kept in the graph. It is shown as below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217215252072.png" alt="image-20221217215252072" style="zoom:67%;" />

​				<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217214820902.png" alt="image-20221217214820902" style="zoom:67%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217214853827.png" alt="image-20221217214853827" style="zoom: 80%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217214929807.png" alt="image-20221217214929807"  />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217215047244.png" alt="image-20221217215047244" style="zoom:80%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217215324965.png" alt="image-20221217215324965" style="zoom:67%;" />

​	Then, total cost of each path is needed to be calculated.

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217215425961.png" alt="image-20221217215425961" style="zoom: 67%;" />

​	The most important is to find the leaf node. When using Depth-First-Path, all of the nodes which the leaf node is connected to have been visited before visiting the leaf node. According to this, an array of Boolean named $Leafnode$ is created to record the leaf node. If $v$ is leaf node, $Leafnode[v]$  is true. Otherwise, $Leafnode[v]$ is false.

![image-20221217220021447](C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217220021447.png)

​																						 <img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217220054075.png" alt="image-20221217220054075" style="zoom:80%;" />

​	The result of comparison of output $B1.out$ and $B2.out$ between the output of input sample $B1.in$ and $B2.in$ using my method is following:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217221721225.png" alt="image-20221217221721225" style="zoom:67%;" />

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221217221808332.png" alt="image-20221217221808332" style="zoom:67%;" />

### 2.2 Analysis of  Time Complexity

​	According to the Depth-First-Paths, the time it takes to find a path from a given starting point to any marked vertex using depth-first search is proportional to the length of the path. Assume that the time of accessing, finding, comparing is the same.  For $N$ tree nodes,the total time used in $if(search.Leafnode[v]==true)$ is $N-1$, and the total time used in $if(search.costOfPathTo(v)==num)$ is $N-1$. However, the time used in $costOfPathTo(int$ $v)$ is proportional to $lgN$. The worst case is that every leaf node is satisfied with the requirement and the tree is binary tree. So, the total time of the worst case is:
$$
T=2(N-1)+\frac{N}{2}lgN
$$
When $N$ is approximate to infinite, the $Big Oh$ is:
$$
O(N)=NlgN
$$
