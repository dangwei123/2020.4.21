城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线
每个建筑物的几何信息用三元组 [Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。可以保证 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。

例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。

输出是以 [ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/the-skyline-problem
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/the-skyline-problem
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    private static class Node{
        private int pos;
        private int height;
        public Node(int pos,int height){
            this.pos=pos;
            this.height=height;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();
        PriorityQueue<Node> q=new PriorityQueue<>((o1,o2)->{
            return o1.pos==o2.pos?o1.height-o2.height:o1.pos-o2.pos;
        });
        for(int[] build:buildings){
            q.offer(new Node(build[0],-build[2]));
            q.offer(new Node(build[1],build[2]));
        }
        PriorityQueue<Integer> h=new PriorityQueue<>((a,b)->b-a);
        h.offer(0);
        int pre=0;
        while(!q.isEmpty()){
            Node node=q.poll();
            if(node.height<0){
                h.offer(-node.height);
            }else{
                h.remove(node.height);
            }
            int cur=h.peek();
            if(cur!=pre){
                res.add(Arrays.asList(node.pos,cur));
                pre=cur;
            }
        }
        return res;
    }
}


给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。
class Solution {
    private int max;
    public int largestRectangleArea(int[] heights) {
        dfs(heights,0,heights.length-1);
        return max;
    }
    private void dfs(int[] heights,int left,int right){
        if(left>right){
            return;
        }
        int min=heights[left];
        int minindex=left;
        for(int i=left+1;i<=right;i++){
            if(heights[i]<min){
                min=heights[i];
                minindex=i;
            }
        }
        max=Math.max(max,min*(right-left+1));
        dfs(heights,left,minindex-1);
        dfs(heights,minindex+1,right);
    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack=new Stack<>();
        stack.push(-1);
        int max=0;
        for(int i=0;i<heights.length;i++){
            while(stack.peek()!=-1&&heights[stack.peek()]>=heights[i]){
                max=Math.max(max,heights[stack.pop()]*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        while(stack.peek()!=-1){
                max=Math.max(max,heights[stack.pop()]*(heights.length-stack.peek()-1));
        }
        return max;
    }
}


