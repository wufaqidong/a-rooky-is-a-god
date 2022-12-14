剑指 Offer 32 – III. 从上到下打印二叉树

剑指offer题解
7天前
0
19
本问题对应的 leetcode 原文链接：剑指 Offer 32 – III. 从上到下打印二叉树
本次对应打卡任务：【二叉树专题】多次考察真题：剑指 Offer 32 – III. 从上到下打印二叉树

问题描述
请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
/ \
9  20
/  \
15   7
返回其层次遍历结果：

[
[3],
[20,9],
[15,7]
]
提示：

节点总数 <= 1000


解题思路：
利用双端队列的两端皆可添加元素的特性，设打印列表（双端队列） tmp ，并规定：
奇数层 则添加至 tmp 尾部 ，
偶数层 则添加至 tmp 头部 。


```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}
```