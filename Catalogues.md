## 详细目录

### lesson01 稀疏数组 Sparse Array
  1. 稀疏数组的应用场景
  2. 课堂练习
     - 二维数组转为稀疏数组
     - 稀疏数组还原为原数组
  3. 课后练习
     - 稀疏数组转存为文本文件、从文本文件读取稀疏数组并还原数组
***
### lesson02 队列 Queue
  1. 队列特征
  2. 数组模拟队列
     - 入队、出队
     - 按顺序打印队列
     - 显示有效元素个数
  3. 数组模拟循环队列(Circular Queue)
     - 头尾指针的重新约定
     - 入队、出队
     - 按顺序打印队列
     - 显示有效元素个数
***
### lesson03 单链表 Single Linked List
  1. 链表中节点的构造：data域、next域
  2. 课堂练习
     - 链表的创建、添加节点
     - 链表的遍历
     - 按一定顺序（如节点的序号）添加节点
     - 按条件删除节点
     - 单链表的反转
     - 从尾至头的逆序遍历单链表（先反转再遍历；或者使用辅助栈）
  3. 课后练习
     - 将两个有序链表合并为一个有序链表
***
### lesson04 双向链表 Double Linked List
  1. 单链表的缺陷以及双链表的节点构造
  2. 课堂练习
     - 双链表的遍历
     - 添加节点：头插法、尾插法
     - 删除节点
  3. 课后练习
     - 双链表按顺序（如节点序号）添加节点
***
### lesson05 循环链表 Circular Linked List
  1. 单循环链表 Single Circular Linked List
     - 单循环链表的构建
     - 单循环链表的遍历
  2. 约瑟夫环 Joseph Ring
     - 创建辅助指针starter(报数时的遍历指针)和helper(删除时的前驱辅助指针)
     - 每一轮报数，都将first和helper同步后移countNum-1次
     - 每一轮报数后，starter所指即待删除（出列）节点，helper指向待删除节点的前驱
     - 链表只剩最后一个节点时，跳出循环，最后的节点打印出列
     - 循环条件(链表中节点数大于1) helper != starter与getSize() > 1的对比
***
### lesson06 栈 Stack
  1. 栈的特征及应用场景
  2. 课堂练习（数组模拟堆栈）
     - 栈顶指针top的定义
     - top指针在入栈和出栈的改变
  3. 课后练习（单链表模拟堆栈）
***
### lesson07 栈处理计算式 Handle Calculation By Stack
  1. 带括号的中缀表达式思路 Infix Expression
     - 通过索引遍历计算式
     - 数字、运算符分别存放在数栈和符号栈
     - 数字直接入数栈
     - 符号须根据优先级进行操作（注意同级运算的先后问题）
  2. 后缀表达式(逆波兰表达式) Suffix Expression
     - 数字直接入数栈
     - 扫描到符号则数栈弹出两个数进行运算，结果入栈
  3. 中缀表达式转后缀表达式 Transfer Infix To Suffix
     - 一个栈存运算符，一个栈暂存结果
     - 操作数入结果栈
     - 运算符根据与符号栈栈顶比较的结果操作
     - 括号按左/右括号区分操作
  4. 课后练习：完善逆波兰计算器（可以识别小数）
***
### lesson08 递归 Recursion
  1. 递归的演示 Recursion
     - 打印问题（if else的辨析）
     - 递归解决阶乘
  2. 迷宫回溯问题 Maze Backtrack
     - 寻路状态的约定（0、1、2、3）
     - 寻路策略的选择（下右上左、上右下左......）
     - 寻路策略与路径的关系
     - 如何获取最短路径（尝试多种策略，比较路径长度）
  3. 八皇后问题 Eight Queens
     - 第n个皇后放在第n行，每个皇后在其所在行逐列尝试摆放
     - 有冲突解决冲突（放至后一列），没冲突向前走（摆放下一个皇后），无处可放则回退（回溯），到最后（一行）即为解
     - 优化结构，用一维数组表示棋盘（不能放在同行，只考虑不同行情况）：下标为行，值为列
***
### lesson09 哈希（散列）表 Hash Table
  1. 实际问题
     - 通常情况下，在Java程序中多次访问数据库，打开/关闭数据库链接会造成极大的时间损耗
     - 应对方法为在程序与数据库中间加入缓存层（如一级不够的话甚至可以加入二级缓存），将可能访问到的数据预先从数据库加载到缓存中，再由程序直接从缓存读取；
     - 缓存的形式有：(1)缓存产品，如Redis、MemCache;(2)自写的数据结构，如哈希表
  2. 定义
     - 根据关键码值（Key Value）直接进行访问的数据结构，通过将key映射到表中一个位置的记录进行访问  
     - 其中，映射的方法称为散列函数，存放记录的表称为散列表
  3. 实现方式
     - 通常结构有 数组 + 链表、数组 + 二叉树等；
     - 散列函数可以为取模、按位与等
  4. 实际需求（谷歌上机题）
     - 实现对员工信息的存取操作，要求不使用数据库，尽量节省内存，速度尽量快（使用哈希表）
***
### lesson10 树 Tree
  1. 二叉树(Binary Tree)的基本概念
     - 满二叉树：叶子节点均在倒数第一层，且总节点数为2^n - 1(n为层数)
     - 完全二叉树：将节点从上至下、从左至右编号，节点与满二叉树中同编号的节点位置相同
     - 若一二叉树为满二叉树，则其一定也是完全二叉树；满二叉树是特殊的完全二叉树
  2. 二叉树的遍历与查找 Traversal & Search
     - 先序 根->左->右  
       [非递归方法] 先访问根节点，若右不空则右入栈，若左不空则左入栈（右先进栈是为了保证遍历时先左后右的顺序）
     - 中序 左->根->右  
       [非递归方法]（1）遍历左子树直至末端；（2）根出栈并访问；（3）之后进入右子树重复（1）和（2）
     - 后序 左->右->根  
       [非递归方法1] 使用指针lastVisit记录上次访问节点，若右为空或者lastVisit为右，则访问根；否则进入右子树  
       [非递归方法2] 对节点添加属性tag记录左右子树访问情况，当tag为right时说明左右子树均已访问，可以访问根节点
  3. 顺序存储二叉树 
     - 要求：  
      （1）用数组存储二叉树节点；  
      （2）仍然可按照先/中/后序的方式遍历
     - 特点：  
      （1）顺序存储二叉树一般用于完全二叉树；  
      （2）第n个元素的左子节点为`2n + 1`，右子节点为`2n + 2`；  
      （3）第n个元素的父节点为`(n - 1)/2`；  
      （4）n为节点数组中的索引
  4. 线索化二叉树 Threaded Binary Tree
     - 实际问题  
       在二叉树构建完成后，并非所有节点的左右指针（比如叶子节点）都被充分利用
     - 基本思想  
      （1）n个节点的二叉链表中含有 n+1 【公式：2n - (n-1) = n+1】 个空指针域，可以用来存放该节点在某种次序遍历中的前驱和后继节点的指针，线索化二叉树旨在提高节点的内存（指针）使用效率；  
      （2）这种加上了线索的二叉链表称为线索二叉链表，与之对应的二叉树称为线索二叉树。根据线索性质的不同，线索二叉树分为先序/中序/后序线索二叉树
***
### lesson11 哈夫曼树 Huffman Tree
  1. 知识预备
     - 路径  
       在一棵树中，结点可以到达的子结点或孙子结点的通路；
     - 路径长度  
       路径中分支的数目（根到第L层节点的路径长度为`L-1`）
     - 结点的权  
       结点被赋予的数值
     - 带权路径长度 Weighted Path Length  
       路径长度与结点权的乘积
     - 树的带权路径长度 Weighted Path Length of Tree  
       所有叶子结点的带权路径长度之和
  2. 基本介绍
     - 给定n个权值，作为n个叶子结点，构造一颗二叉树。若该树的wpl达到最小，则称这样的二叉树为最优二叉树，也称为哈夫曼树（Huffman Tree）
     - 哈夫曼树是树的带权路径长度最小的树，特点是权越大的结点离根越近
  3. 哈夫曼树的构建
     - 对给定序列从小到大排序，原序列的每个数值都是一个叶子结点  
     - 取出权值最小的两个二叉树作为左右子树组成一个新二叉树，父节点的权值为两个节点的权值之和
     - 将这个新的子树加入到哈夫曼树中（新节点的权加入序列）
     - 重复上述步骤直至序列中只剩下一个元素（即哈夫曼树的根）
***
### lesson12 哈夫曼编码 Huffman Coding
  1. 知识预备
     - 定长编码  
       将字符转换成固定长度二进制编码的方式
     - 变长编码VLC Variable-Length Coding  
     （1）根据字符出现的次数进行编码，原则是出现次数越多、编码越小（与哈夫曼树权越大的结点距离根越近契合）  
     （2）变长编码中为了防止编码的二义性，一般采取前缀编码（即字符的编码不为其他字符编码的前缀）
  2. 基本介绍
     - 哈夫曼编码是哈夫曼树在通信中的应用之一
     - 广泛的应用于数据文件压缩，压缩率在20%-90%
     - VLC的一种，由David Huffman于1952年提出
  3. 数据压缩
     - 将数据转换为字节数组，以此构建哈夫曼树
     - 遍历哈夫曼树获取哈夫曼编码表  
     （1）遍历哈夫曼树的叶子结点，在遍历过程中根据路径（左子树编“0”，右子树编“1”）  
     （2）将结点的data域和对应的哈夫曼编码作为键值对存入map，构成哈夫曼编码表
     - 根据编码表对字节数组进行压缩  
     （1）将字节数组中元素根据编码表获取哈夫曼编码，并拼接成字符串  
     （2）计算压缩后的数组长度(记编码字符串长度为length) `l = (length + 7) / 8`  
     （3）将编码字符串每8位转为byte类型存入压缩后的byte数组
  4. 数据解压
     - 将压缩后的byte数组转换拼接为二进制的哈夫曼编码字符串
     - 将哈夫曼编码表map反转：byte数值为key，编码字符串为value => 编码字符串为key，byte数值为value
     - 对照反转的编码表，将二进制编码字符串还原为原数组
  5. 注意事项
     - 如果文件本身已经过压缩处理（如视频、pptx文件），则使用哈夫曼编码压缩效率不会有明显变化
     - 哈夫曼编码按字节来处理文件，因此理论上可以处理各种文件（二进制文件、文本文件等）
     - 如果一个文件的内容中，重复的数据不多，压缩效果也不会明显（如高清图片）
***
### lesson13 二叉排序树 Binary Sort Tree
  1. 基本介绍
     - 也称二叉查找树（Binary Search Tree），对于任何一个非叶子结点，要求左子结点的关键字小于该节点、右子结点的关键字大于等于当前结点
  2. 添加结点
     - 对于关键字小/大于根的结点，将其添加至根的左/右子树
     - 若根已有左/右子结点，则向其左/右子树递归
  3. 删除结点
     - 待删除的为叶子结点  
     （1）根据关键字找到待删除的结点target  
     （2）找到target的父节点parent  
     （3）确定target是parent的左子结点还是右子结点，将其置为null
     - 待删除的子结点有两棵子树
     （1）根据关键字找到待删除的结点target  
     （2）找到target的父节点parent  
     （3）找到target的右子树中关键字最小的结点（向左遍历至左子树末尾），用temp暂存其关键字，删除这个结点  
     （4）将temp赋给target（3、4两步可理解为将target移除，将temp结点挪到target原来的位置）
     - 待删除的结点有且仅有一棵子树  
     （1）根据关键字找到待删除的结点target  
     （2）找到target的父节点parent  
     （3）确定target是parent的左/右节点  
     （4）target有左/右子树 => 左/右子树挪至target原来的位置
***
### lesson14 平衡二叉树 Self-balance Binary Sort Tree
  1. BST的问题
     - 当对一个有序序列构建BST时，所得树形式上与单链表相近
     - 插入速度无影响
     - 查询遍历时，（虽然左/右子树为空）仍需要取比较左/右子树，实际效率低于单链表
  2. 基本介绍
     - 平衡二叉树也叫平衡搜索二叉树（Self-balance Binary Sort Tree），又称AVL树
     - 它是一棵空树或者它的左右两个子树的高度差不大于1
     - 对于每个非叶子结点，以它为根的子树也是平衡二叉树
     - 常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树
  3. 实现方式
     - 当右子树高度 - 左子树高度 > 1时，左旋转（Left Rotate）  
     （1）创建一个新结点newNode，值为当前结点的值  
     （2）新结点的左指针指向当前结点的左子树 `newNode.left = this.left`  
     （3）新结点的右指针指向当前结点的右右子结点的左子结点 `newNode.right = this.left.right`  
     （4）当前结点的值改为其右子结点的值 `this.val = this.right.val`  
     （5）当前结点的右指针指向右子结点的右子结点 `this.right = this.right.right`  
     （6）当前结点的左指针指向新结点 `this.left = newNode`
     - 当左子树高度 - 右子树高度 > 1时，右旋转（Right Rotate）  
     （1）创建一个新的结点newNode，值为当前结点的值  
     （2）新结点的右指针指向当前结点的右子树 `newNode.right = this.right`  
     （3）新结点的左指针指向当前结点的左子树的右子树 `newNode.left = this.left.right`  
     （4）当前结点的值改为其左子结点的值 `this.val = this.left.val`  
     （5）当前结点的左指针指向其左子树的左子树 `this.left = this.left.left`  
     （6）当前结点的右指针指向新结点 `this.right = newNode`
     - 双旋转（针对单次LR或者RR无法获得BT的情况）  
     （1）LR时，若右子树的左子树高度大于其右子树高度，应先对右子树RR，再对当前结点LR  
     （2）RR时，若左子树的右子树高度大于其左子树高度，应先对左子树LR，再对当前结点RR
***
### lesson15 多路查找树 Multi-way Search Tree
  1. 二叉树的问题
     - 当数据存于数据库或文件中时，构建二叉树需要进行多次I/O操作
     - 当结点数量较大时，二叉树高度也更大，查找、新增、删除的效率也会降低
  2. 多叉树 Multi-way Tree
     - 可以拥有多个数据项和子结点的树就是多叉树
     - 典型的多叉树有2-3树，2-3-4树
     - 多叉树通过重新组织结点，减少了树的高度，是对二叉树的优化
  3. B树 B-tree
     - 2-3树  
     （1）所有叶子结点在同一层（B树的共性）  
     （2）有2个子结点的为2结点  
     （3）有3个子结点的为3结点  
     （4）2/3结点要么没有子结点，要么有2/3个子结点（对应第一个特点）
     - B树  
     （1）B树的阶：结点的最多子结点个数  
     （2）B树的搜索：从根开始，对结点的关键字进行二分查找，若命中则结束查找，否则根据范围遍历对应子树  
     （3）叶子结点和非叶子结点均可存放数据，搜索可能在非叶子结点结束  
     （4）搜索性能等价于在关键字全集内左二分查找，时间复杂度为线性对数阶
     - B+树  
     （1）B树的变种，重新组织了关键字的存放  
     （2）关键字均存于叶子结点的链表中（数据只能在叶子结点，稠密索引），且链表中关键字有序  
     （3）非叶子结点相当于叶子结点的索引（稀疏索引），叶子结点相当于数据层
     - B\*树  
     （1）B+树的变种，非根和非叶子结点增加了指向兄弟的指针  
     （2）B\*树规定非叶子结点关键字的个数至少为(2/3)\*M，M为B\*树的度，块的最低使用率为2/3，而B+树块的最低使用率为1/2  
     （3）B\*树分配新结点的概率比B+树低，空间使用率更高
***
### lesson16 图 Graph
  1. 线性表和树的局限性
     - 线性表局限于一个直接前驱和一个直接后继的关系
     - 树的结点也只有一个直接前驱，即父结点
     - 这两种结构用来表示多对多关系并不方便
  2. 图的常用概念
     - 顶点    Vertex
     - 边      Edge
     - 路径    Path
     - 无向图  Undirected Graph
     - 有向图  Directed Graph
     - 带权图  Weighted Graph
  3. 图的表示方式
     - 邻接矩阵 Adjacency Matrix  
     （1）`n * n`的矩阵，n为图中顶点的个数  
     （2）第i行第j列的元素，表示第i个顶点与第j个顶点的连接关系(从0开始标号)  
     （3）为每个顶点都分配了n个边的空间，但若大量的边不存在即顶点不连接，则会造成空间的浪费
     - 邻接表 Adjacency List  
     （1）邻接表由数组+单链表组成，类似哈希表  
     （2）数组罗列图中的各顶点，链表描述顶点与其他顶点的关联情况  
     （3）邻接表只关心存在的边，对于不存在的边不会分配空间表示，故在内存上的浪费较小