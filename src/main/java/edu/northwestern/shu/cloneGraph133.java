package edu.northwestern.shu;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

class UndirectedGraphNode {
	int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

public class cloneGraph133 {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        Map<Integer, UndirectedGraphNode> nodeSet = new HashMap<Integer, UndirectedGraphNode>();
        UndirectedGraphNode start = new UndirectedGraphNode(node.label);
        nodeSet.put(start.label, start);

        Queue<UndirectedGraphNode> q = new ArrayDeque<UndirectedGraphNode>();
        q.add(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode oldNode = q.poll();
            UndirectedGraphNode newNode = nodeSet.get(oldNode.label);
            for (UndirectedGraphNode neighbor: oldNode.neighbors) {
                if (!nodeSet.containsKey(neighbor.label)) {
                    nodeSet.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    q.add(neighbor);
                }
                newNode.neighbors.add(nodeSet.get(neighbor.label));
            }
        }

        return start;
    }
}
