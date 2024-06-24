package model;

import java.util.*;

public class Graph {
    private ArrayList<LinkedList<Node>> alist;

    public ArrayList<LinkedList<Node>> getAlist() {
        return alist;
    }

    public Graph() {
        this.alist = new ArrayList<>();
    }

    public void setAlist(ArrayList<LinkedList<Node>> alist) {
        this.alist = alist;
    }

    public void addNode(Node node) {
        LinkedList<Node> currentList = new LinkedList<>();
        currentList.add(node);
        this.alist.add(currentList);
    }

    public void addEdge(int src, int dst) {
        LinkedList<Node> currentList = this.alist.get(src);
        Node dstNode = this.alist.get(dst).get(0);
        currentList.add(dstNode);
    }

    public void removeEdge(int src, int dst) {
        LinkedList<Node> currentList = this.alist.get(src);
        Node dstNode = this.alist.get(dst).get(0);
        currentList.remove(dstNode);
    }

    public boolean checkEdge(int src, int dst) {
        LinkedList<Node> currentList = this.alist.get(src);
        Node dstNode = this.alist.get(dst).get(0);

        for (Node node : currentList) {
            if (node == dstNode) {
                return true;
            }
        }
        return false;
    }

//    public void print() {
//        for (LinkedList<Node> currentList : this.alist) {
//            for (Node node : currentList) {
//                System.out.print(node.getData() + " -> ");
//            }
//            System.out.println();
//        }
//    }

    public Queue<Node> breadthFirstSearch(int src, int level) {
        int degree = 0;
        Queue<Node> currentLevel = new LinkedList<>();
        Queue<Node> nextLevel = new LinkedList<>();
        boolean[] visited = new boolean[this.alist.size()];

        currentLevel.offer(this.alist.get(src).get(0));
        visited[src] = true;

        while (currentLevel.size() != 0 && degree < level) {
            src = currentLevel.poll().getId();
            System.out.println(this.alist.get(src).get(0).getData() + " = visited");
            for (Node i : this.alist.get(src)) {
                if (!visited[i.getId()]) {
                    nextLevel.offer(i);
                    visited[i.getId()] = true;
                }
            }
            if (currentLevel.isEmpty()) {
                Queue<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
                degree++;
            }
        }
        return currentLevel;
    }

    public int[] shortestPath(int src) {
        //int[] path = new int[this.alist.size()];
        int[] distance = new int[this.alist.size()];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[src] = 0;
        //path[src] = src;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int vertex = queue.isEmpty() ? -1 : queue.poll();
                LinkedList<Node> adjVertices = this.alist.get(vertex);
                for (Node adjVertice : adjVertices) {
                    if (distance[adjVertice.getId()] == Integer.MAX_VALUE) {
                        distance[adjVertice.getId()] = distance[vertex] + 1;
                        //path[adjVertice.getId()] = vertex;
                        queue.add(adjVertice.getId());
                    }
                }
                size--;
            }
        }

        System.out.println("Distance from " + this.alist.get(src).get(0).getData());
        for (int i = 0; i < this.alist.size(); i++) {
            System.out.println(this.alist.get(i).get(0).getData() + " is " + distance[i]);
        }

        return distance;
    }
}
