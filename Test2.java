给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
class Solution {
    public boolean isPalindrome(String s) {
        int left=0;
        int right=s.length()-1;
        while(left<right){
            while(left<right&&!isValid(s.charAt(left))){
                left++;
            }
            while(left<right&&!isValid(s.charAt(right))){
                right--;
            }
            if(left<right){
                char a=s.charAt(left);
            char b=s.charAt(right);
            if(a>='0'&&a<='9'&&b>='0'&&b<='9'){
                if(a!=b) return false;
            }else if(ispl(a)&&ispl(b)){
                if((a^b)==0||(a^b)==32){
                    
                }else{
                    return false;
                }
                
            }else{
                return false;
            }
            left++;
            right--;
            }
            
        }
        return true;
    }
    private boolean isValid(char c){
        return (c>='0'&&c<='9')||(c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }
    private boolean ispl(char c){
        return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
    }
}

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。
class Solution {
    List<List<String>> res=new ArrayList<>();
    public List<List<String>> partition(String s) {
        back(s,0,s.length(),new ArrayList<>());
        return res;
    }
    private void back(String s,int begin,int len,List<String> row){
        if(begin==len){
            res.add(new ArrayList(row));
            return ;
        }
        for(int i=begin;i<len;i++){
            if(isValid(s,begin,i)){
                row.add(s.substring(begin,i+1));
                back(s,i+1,len,row);
                row.remove(row.size()-1);
            }
        }
    }
    private boolean isValid(String s,int left,int right){
        while(left<right&&s.charAt(left)==s.charAt(right)){
            left++;
            right--;
        }
        return left>=right;
    }
}

给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set=new HashSet<>(wordDict);
        int n=s.length();
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

前缀树
class Trie {
    private static class Node{
        private Node[] next;
        private boolean isEnd;
        public Node(){
            next=new Node[26];
        }
    }
    
    private Node root;
    /** Initialize your data structure here. */
    public Trie() {
        root=new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(cur.next[index]==null){
                cur.next[index]=new Node();
            }
            cur=cur.next[index];
        }
        cur.isEnd=true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            int index=word.charAt(i)-'a';
            if(cur.next[index]==null){
                return false;
            }
            cur=cur.next[index];
        }
        return cur.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur=root;
        for(int i=0;i<prefix.length();i++){
            int index=prefix.charAt(i)-'a';
            if(cur.next[index]==null){
                return false;
            }
            cur=cur.next[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */