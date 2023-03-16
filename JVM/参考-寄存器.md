# 寄存器
https://www.cnblogs.com/cxuanBlog/p/13818439.html
### 通用寄存器
**AX** (Accumulator Register) <br>
累加寄存器，主要用于输入/输出和大规模的指令运算，函数的返回值一般也放在这里

**BX** (Base Register) <br>
基地址寄存器，用来存储基础访问地址

**CX** (Count Register) <br>
计数寄存器，再迭代的操作种会循环计数

**DX** (Data Register) <br>
数据寄存器，也用与输入/输出操作，与AX一起使用用于设计大数值的乘法和除法运算

### 状态和控制寄存器
**IP** (Instruction Pointer) <br>
指令指针寄存器，它是从Code Segment的偏移来存储执行的下一条指令

**FLAG** <br>
用于存储CPU执行指令过程种的一系列状态
* 位置 (Direction)：用于数据块的传输方向，是向上传输还是向下传输
* 中断标志位 (Interrupt) ：1 - 允许；0 - 禁止
* 陷入位 (Trap) ：确定每条指令执行完成后，CPU 是否应该停止。1 - 开启，0 - 关闭
* 进位 (Carry) : 设置最后一个无符号算术运算是否带有进位
* 溢出 (Overflow) : 设置最后一个有符号运算是否溢出
* 符号 (Sign) : 如果最后一次算术运算为负，则设置 1 =负，0 =正
* 零位 (Zero) : 如果最后一次算术运算结果为零，1 = 零
* 辅助进位 (Aux Carry) ：用于第三位到第四位的进位
* 奇偶校验 (Parity) : 用于奇偶校验

### 段寄存器
用作程序指令，数据或栈的基础位置<br>

**CS** (Code Segment) <br>
代码寄存器，程序代码的基础位置

**DS** (Data Segment) <br>
数据寄存器，变量的基本位置

**SS** (Stack Segment) <br>
栈寄存器，栈的基础位置

**ES** (Extra Segment) <br>
其它寄存器，内存种变量的其它基本位置

### 索引寄存器
主要包含段地址的偏移量

**BP** (Base Pointer) <br>
基础指针，栈寄存器上的偏移量，用来定位栈上的变量

**SP** (Stack Pointer) <br>
栈指针，栈寄存器上的偏移量，用来定位栈顶

**SI** (Source Index) <br>
变址寄存器，用来拷贝源字符串

**DI** (Destination Index) <br>
目标变址寄存器，用来复制到目标字符串






