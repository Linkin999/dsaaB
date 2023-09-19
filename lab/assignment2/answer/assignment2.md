<h1 align = "center">Assignment2 </h1>

<center> 张旭东 12011923 </center>

## 1.Algorithm explanation

​	The point to find the $(\frac{i}{2} +1)_{th}$ element of ${A_{i}}$ after it is sorted is to create a $max-heap$ and a $min-heap$. All of elements in $A_{i}$ are put into $max-heap$ and $min-heap$. The difference of their size should be not greater than $1$ and the largest element in $max-heap$ should be nor greater than the smallest element in $min-heap$.  In that case, the $(\frac{i}{2} +1)_{th}$ element of $A_{i}$ is either the top element in $max-heap$ or the top element in $min-heap$.

### 1.1 Code

```java
public static int[] findMedians(int[] array){
        // TODO: implement this method
        int[] medianArray = new int[array.length];

        MaxPQ <Integer> max = new MaxPQ<Integer>();  
        MinPQ <Integer> min = new MinPQ<Integer>();

        for(int i=0 ; i<array.length;i++){
            if(max.isEmpty()){
                max.insert(array[i]);
            }
            else{

                if(max.size()==min.size()){
                    if(array[i]<max.max()){
                        max.insert(array[i]);
                    }
                    else{
                        min.insert(array[i]);
                    }
                }

                else if(max.size()<min.size()){
                    if(array[i]<=min.min()){
                        max.insert(array[i]);
                    }
                    else{
                        int tmp = min.delMin();
                        max.insert(tmp);
                        min.insert(array[i]);                       
                    }
                }
                else{
                    if(array[i]>=max.max()){
                        min.insert(array[i]);
                    }
                    else{
                        int tmp = max.delMax();
                        min.insert(tmp);
                        max.insert(array[i]);
                    }
                }

            }

            if(max.size()==min.size()){
                medianArray[i]=max.max();
            }
            else if(max.size()<min.size()){
                medianArray[i]=min.min();
            }
            else{
                medianArray[i]=max.max();
            }
        }


        return medianArray;
    }
```

The logic of $insert$ $num$ is below:

When the size of $max-heap$ is empty:

​		the new element to be inserted is put into $max-heap$. 

When the size of $max-heap$ isn't empty:

​		when the size of  $max-heap$ is equal to that of $min-heap$:

​				if the new element to be inserted is smaller than the top element of $max-heap$:

​						it is put into $max-heap$.

​				else:

​						it is put into $min-heap$.

​		when the size of  $max-heap$ is smaller to that of $min-heap$:

​				if the new element to be inserted is smaller than the top element of $min-heap$:

​						it is put into $max-heap$.

​				else:

​						the top element of $min-heap$ is popped and is added into $max-heap$.

​						the new element to be inserted is put into $min-heap$.

​						$($$to$ $keep$  $the$ $difference$ $of$ $their$ $size$ $should$ $be$ $not$ $greater$ $than$ $1$ $and$ $the$ $largest$ $element$ $in$ 							$max-heap$ $should$ $be$ $not$ $greater$ $than$ $the$ $smallest$ $element$ $in$ $min-heap$. $)$

​		when the size of  $max-heap$ is larger to that of $min-heap$:

​						if the new element to be inserted is smaller than the top element of $max-heap$:

​								it is put into $min-heap$.

​						else:

​								the top element of $min-heap$ is popped and is added into $min-heap$.

​								the new element to be inserted is put into $max-heap$.

​								$($$to$ $keep$  $the$ $difference$ $of$ $their$ $size$ $should$ $be$ $not$ $greater$ $than$ $1$ $and$ $the$ $largest$ $element$ $in$ 								$max-heap$ $should$ $be$ $not$ $greater$ $than$ $the$ $smallest$ $element$ $in$ $min-heap$. $)$

The logic of find the $(\frac{i}{2} +1)_{th}$ element of ${A_{i}}$ is below:

​			when the size of  $max-heap$ is equal to that of $min-heap$:

​					the $(\frac{i}{2} +1)_{th}$ element of ${A_{i}}$ is the top element of $max-heap$.

​			when the size of  $max-heap$ is smaller to that of $min-heap$:

​					the $(\frac{i}{2} +1)_{th}$ element of ${A_{i}}$ is the top element of $min-heap$.

​			when the size of  $max-heap$ is larger to that of $min-heap$:

​					the $(\frac{i}{2} +1)_{th}$ element of ${A_{i}}$ is the top element of $max-heap$.

### 1.2 Result of samples:

​	The code of $inputarray$ which is $[2,5,1,4,7]$ is below:

```java
int[] array = new int[]{2,5,1,4,7};
        int[] array2=findMedians(array);
        for(int i=0;i<array2.length;i++){
            System.out.println(array2[i]);
        }
```

​	The result of it is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221117163300484.png" alt="image-20221117163300484" style="zoom:67%;" />

​	The code of $inputarray$ which are $.in$ files in $teatdata$ and $sampleoutput$ which are $.out$ files in $testdata$ is below:

```java
for(int i=1;i<=10;++i){
            try{
                In fin1=new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment2\\testing data\\"+i+".in");
                int[] initinput = fin1.readAllInts();
                fin1.close();
                int[] input = new int[initinput[0]];
                for(int j=1;j<initinput.length;j++){
                    input[j-1]=initinput[j];
                }

                int[] output = findMedians(input);

                In fin2=new In("D:\\Study in SUSTech\\First semester of junior year\\dsaaB\\lab\\assignment2\\testing data\\"+i+".out");
                int[] sampleOutput = fin2.readAllInts();
                fin2.close();

                if (Arrays.equals(output,sampleOutput)){
                    System.out.println("The method is correct");
                }
                else {
                    System.out.println("The method isn't correct");
                }
            } catch(IllegalArgumentException e){
                e.printStackTrace();
            }
        }
```

​	The result of it is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221117181518672.png" alt="image-20221117181518672" style="zoom:67%;" />

## 2.TestCode

### 2.1 Generate Number

​	First, an integer should be generated. The method of $getRandomNumberInRange(int$ $i, int$ $j)$ is to generated an  integer between $[i,j]$, which is used to generate the length of array $A_{N}$ and the elements of $A_{N}$.

```java
public static int getRandomNumberInRange(int i, int j) {
        if (i >= j) {
			throw new IllegalArgumentException("max must be greater than min");
		}
 
		Random r = new Random();
		return r.nextInt((j - i) + 1) + i;
    }
```

### 2.2 Generate Array

​	Second, an $8inputarray$ is generated.

```java
public static int[] inputArray(int N){

        int[] inputarray = new int[N];
        //随机生成输入的数组
        for(int i =0;i<N;i++){
            inputarray[i]=getRandomNumberInRange(1, (int) Math.pow(10, 9));
        }
        return inputarray;
    }
```

### 2.3 Algorithm of Test

​	Inspired by inserting one element one time, the method of $insertion( int[] array, int$ $left, int$ $right )$ is used. 

```java
public static int[] insertion( int[] array, int left, int right ) {
        for( int i = left+1; i <= right; ++ i ) {
            int value = array[i];
            int j;
            for( j = i; j > left && array[j-1] > value; -- j )
                array[j] = array[j-1];
            array[j] = value;
        }
        return array;
    }
```

​	After inserting one element and sorting, the median of it is the element whose index is $\frac{i}{2}$.

```java
public static int[] medianofInsertion(int[] array){
        int[] medianarray=new int[array.length];
        for(int i=0;i<array.length;i++){
            int[] array2 = insertion(array, 0, i);
            medianarray[i]=array2[i/2];
        }
        return medianarray;
    }
```

### 2.4 Result

​     The methods in $TestCode.java$ are invoked in $Median.java$ to prove the correctness of  method of $findMedians$.

​	The code is below:

```java
int N = TestCode.getRandomNumberInRange(1, (int) (5*Math.pow(10, 5)));//随机生成数组的长度
        int[] inputarray= TestCode.inputArray(N);//随机生成输入的数组
        int[] inputarray2= inputarray.clone();//将随机生成输入的数组克隆一份

        int[] methodmedian = findMedians(inputarray);//用填写的方法测试出来的median数组

        int[] testmedian = TestCode.medianofInsertion(inputarray2);
    

        if (Arrays.equals(testmedian,methodmedian)){
            System.out.println("The result of test is correct");
        }
        else {
            System.out.println("The result of test isn't correct");
        }
```

​	The result is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118140520319.png" alt="image-20221118140520319" style="zoom:67%;" />

## 3.Time Complexity Analyzation

### 3.1 Time Complexity Analyzation of Random Case.  

​	The code is below:

```java
int[] inputarray3= TestCode.inputArray(250);
       Stopwatch watch1 = new Stopwatch();
       int[] methodmedian2 = findMedians(inputarray3);
       double prev = watch1.elapsedTime();

       for(int n =250;n>0;n*=2){
            int[] inputarray4= TestCode.inputArray(n);

            Stopwatch watch2 = new Stopwatch();
            int[] methodmedian3 = findMedians(inputarray4);
            System.out.printf("%7d %7.1f %5.1f\n", n, watch2.elapsedTime(),watch2.elapsedTime()/prev);
            prev = watch2.elapsedTime();

       }
```

​	The result is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118141106902.png" alt="image-20221118141106902" style="zoom:67%;" />

​	From the above figure, when the size $N$ is enough larger, the fluctuation of the ratio of $\frac{T(2N)}{T(N)}$ is relatively small. So the ratio of $\frac{T(2N)}{T(N)}$ can be thought to approximately equal to $2$.  Then, we use MATLAB to plot the figure between the size $N$ and runtime.

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118141936310.png" alt="image-20221118141936310" style="zoom:67%;" />

​	From the picture, the relation between the size $N$ and runtime is approximately linear. And according to the theory of     $heap$, a reasonable time complexity analyzation of random case is $O(Nlog(N))$ because the mothed has used $heap$. 

### 3.2 Time Complexity Analyzation of Extreme Case. 

#### 3.2.1 Same elements

​	The code is below:

```java
int value = TestCode.getRandomNumberInRange(1, (int) Math.pow(10, 9));
       int[] inputarray5 = new int[250];
       for(int i=0;i<inputarray5.length;i++){
            inputarray5[i]=value;
       }
       Stopwatch watch3 = new Stopwatch();
       int[] methodmedian4 = findMedians(inputarray5);
       double prev2 = watch3.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray6= new int[n];
        for(int i=0;i<inputarray6.length;i++){
            inputarray6[i]=value;
        }

        Stopwatch watch4 = new Stopwatch();
        int[] methodmedian5 = findMedians(inputarray6);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch4.elapsedTime(),watch4.elapsedTime()/prev2);
        prev2 = watch4.elapsedTime();
       }
```

​	The result is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118143707580.png" alt="image-20221118143707580" style="zoom:67%;" />

​	We use MATLAB to plot the figure between the size $N$ and runtime.

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118144026669.png" alt="image-20221118144026669" style="zoom:67%;" />

​	From the first pictures,  with the size $N$ increasing, the fluctuation of the ratio of $\frac{T(2N)}{T(N)}$ is relatively large. It is hard to analyze time complexity of the case in which all of the elements are the same. And the fluctuation of the ratio of $\frac{T(2N)}{T(N)}$ is related to the computer.

#### 3.2.2 In-order Element

​		The code is below:

```java
int[] inputarray7 = new int[250];
       for(int i=0;i<inputarray7.length;i++){
        inputarray7[i]=i+1;
       }

       Stopwatch watch5 = new Stopwatch();
       int[] methodmedian6 = findMedians(inputarray7);
       double prev3 = watch5.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray8= new int[n];
        for(int i=0;i<inputarray8.length;i++){
            inputarray8[i]=i+1;
        }

        Stopwatch watch6 = new Stopwatch();
        int[] methodmedian7 = findMedians(inputarray8);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch6.elapsedTime(),watch6.elapsedTime()/prev3);
        prev3 = watch6.elapsedTime();
       }
```

​		The result is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118144844432.png" alt="image-20221118144844432" style="zoom:67%;" />

​	We use MATLAB to plot the figure between the size $N$ and runtime.



<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118145310459.png" alt="image-20221118145310459" style="zoom:67%;" />

​	From the first pictures,  with the size $N$ increasing, the fluctuation of the ratio of $\frac{T(2N)}{T(N)}$ is relatively small. So the ratio of $\frac{T(2N)}{T(N)}$ can be thought to approximately between $[2,4]$. From the second picture, the relation between the size $N$ and runtime is approximately linear. And according to the theory of $heap$, a reasonable time complexity analyzation of random case is $O(Nlog(N))$ because the mothed has used $heap$.

#### 3.2.3 Reverse-order Element

​	The code is below:

```java
 int[] inputarray9 = new int[250];
       for(int i=0;i<inputarray9.length;i++){
        inputarray9[i]=inputarray9.length-i;
       }
       Stopwatch watch7 = new Stopwatch();
       int[] methodmedian8 = findMedians(inputarray9);
       double prev4 = watch7.elapsedTime();

       for(int n =250;n>0;n*=2){
        int[] inputarray10= new int[n];
        for(int i=0;i<inputarray10.length;i++){
            inputarray10[i]=inputarray10.length-i;
        }

        Stopwatch watch8 = new Stopwatch();
        int[] methodmedian9 = findMedians(inputarray10);
        System.out.printf("%7d %7.1f %5.1f\n", n, watch8.elapsedTime(),watch8.elapsedTime()/prev4);
        prev4 = watch8.elapsedTime();
       }
```

​	The result is below:

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118145824837.png" alt="image-20221118145824837" style="zoom:67%;" />

​	We use MATLAB to plot the figure between the size $N$ and runtime.  

<img src="C:\Users\胡晨\AppData\Roaming\Typora\typora-user-images\image-20221118150123276.png" alt="image-20221118150123276" style="zoom:67%;" />

​	From the first pictures,  with the size $N$ increasing, the fluctuation of the ratio of $\frac{T(2N)}{T(N)}$ is relatively small. So the ratio of $\frac{T(2N)}{T(N)}$ can be thought to approximately between $[1,4]$. From the second picture, the relation between the size $N$ and runtime is approximately linear. And according to the theory of  $heap$, a reasonable time complexity analyzation of random case is $O(Nlog(N))$ because the mothed has used $heap$.

## Reference

[CSDN](https://blog.csdn.net/Little_Fire/article/details/81098584?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166867848416800215086837%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=166867848416800215086837&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-5-81098584-null-null.142^v64^js_top,201^v3^control_2,213^v2^t3_control1&utm_term=%E6%80%8E%E4%B9%88%E7%94%A8max-heap%E5%92%8Cmin-heap%E5%8E%BB%E6%89%BE%E4%B8%AD%E4%BD%8D%E6%95%B0&spm=1018.2226.3001.4187)
