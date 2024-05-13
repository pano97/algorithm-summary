package com.lc.tree.dict;

class Tier {
    public Tier[] next;
    public boolean isEnd;
    public String word;

    public Tier() {
        word = "";
        next = new Tier[26];
        isEnd = false;
    }

    public void insert(String w) {
        Tier root = this;

        for(int i=0;i<w.length();i++) {
            if(root.next[w.charAt(i) - 'a'] == null) {
                root.next[w.charAt(i) - 'a'] = new Tier();
            }
            root = root.next[w.charAt(i)-'a'];
        }
        root.word = w;
        root.isEnd = true;
    }

    public boolean searchPrefix(String w) {
        Tier root = this;

        for(int i=0;i< w.length();i++) {
            if(root.next[w.charAt(i) - 'a'] == null) return false;
            root = root.next[w.charAt(i) - 'a'];
        }
        return true;
    }

    public boolean search(String w) {
        Tier root = this;
        for(int i=0;i<w.length();i++) {
            if(root.next[w.charAt(i) - 'a'] == null) return false;
            root = root.next[w.charAt(i) - 'a'];
        }
        return root.isEnd;
    }
}
