package com;

import java.util.Comparator;

public class HeuristicsComparator implements Comparator<Node>
{
	int count = 0;
    @Override
    public int compare(Node x, Node y)
    {
        if (x.getHeuristics() < y.getHeuristics())
        {
            return -1;
        }
        if (x.getHeuristics() > y.getHeuristics())
        {
            return 1;
        }
        return 0;
    }
    
}
