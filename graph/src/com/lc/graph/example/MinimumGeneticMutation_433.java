package com.lc.graph.example;

import java.util.*;

public class MinimumGeneticMutation_433 {
    public static void main(String[] args) {
        String startGene = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {
                "AAAACCCC","AAACCCCC","AACCCCCC"
        };
        System.out.println(minMutation(startGene, end, bank));
    }
    public static int minMutation(String startGene, String endGene, String[] bank) {
        Deque<char[]> queue = new LinkedList<>();
        HashSet<String> bankSet = new HashSet<>();
        HashMap<String, Integer> visitedMap = new HashMap<>();

        // construct bank set
        bankSet.addAll(Arrays.asList(bank));

        queue.offer(startGene.toCharArray());
        visitedMap.put(startGene, 0);

        char[] change = {'A', 'C', 'G', 'T'};

        while(!queue.isEmpty()) {
            char[] curGene = queue.pollFirst();
            String curGeneString = String.valueOf(curGene);

            for(int i=0;i<8;i++) {
                for(int k=0;k<change.length;k++) {
                    char[] newGene = Arrays.copyOf(curGene, curGene.length);
                    newGene[i] = change[k];
                    String newGeneString = String.valueOf(newGene);

                    if(!bankSet.contains(newGeneString)) continue;

                    if(!visitedMap.containsKey(newGeneString)) {
                        visitedMap.put(newGeneString, visitedMap.get(curGeneString)+1);
                        queue.offer(newGene);
                    }

                    if(newGeneString.equals(endGene)) return visitedMap.get(newGeneString);
                }
            }
        }

        return -1;
    }
}
