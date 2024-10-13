package org.example;

import org.example.tree.ConiferousTree;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        ConiferousTree coniferousTree = new ConiferousTree();
        IntStream.range(0, 10).forEach(i -> {
            presentTree(coniferousTree);
        });
    }

    private static void presentTree(ConiferousTree coniferousTree) {
        coniferousTree.grow();
        System.out.println(coniferousTree);
    }

}