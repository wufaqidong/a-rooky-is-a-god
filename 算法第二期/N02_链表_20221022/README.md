关于栈与递归的选择：单链表只能从前往后遍历，不支持从后往前遍历，所以遇到诸如倒数打印，反转之类的题，
我们往往会选择栈来辅助，也可以选择用递归。但是从面试官的解法看，比起栈，递归会更好，因为用栈，那就有点太简单的。
不过无论选择递归还是栈，空间复杂度一样，就是选择递归时，还需要考虑一个递归太深的话，出现内存溢出的情况。

今天的题有三道：
剑指 Offer 06. 从尾到头打印链表
剑指 Offer 24. 反转链表
剑指 Offer 35. 复杂链表的复制

下面是详细说明：

剑指 Offer 06. 从尾到头打印链表
难度：★
要求掌握程度：★★★★
说明：基础题，不建议用栈，最好可以会递归，不会的话，勉强用栈也可以了。
描述+答案+代码：https://www.playoffer.cn/479.html

剑指 Offer 24. 反转链表
难度：★★
要求掌握程度：★★★★★
说明：基础题，递归反转+原地反转，两种方法都要掌握
描述+答案+代码：https://www.playoffer.cn/481.html

剑指 Offer 35. 复杂链表的复制
难度：★★★
要求掌握程度：★★
说明：这道题考的比较少，主要了解下思路，很容易写错，主要考察代码能力。
描述+答案+代码：https://www.playoffer.cn/483.html