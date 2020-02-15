package MyCollection.Graph;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.Heap.LinkedHeap;
import MyCollection.List.ArrayUnorderedList;
import MyCollection.Queue.LinkedQueue;
import MyCollection.Stack.LinkedStack;

import java.util.Iterator;

public class Network<T> extends Graph<T> implements NetworkADT<T> {

    private double[][] adjMatrix; // adjacency matrix

    /**
     * ****************************************************************
     * Creates an empty network.
     * ****************************************************************
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);

    }

    public double[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(double[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    /**
     * ****************************************************************
     * Returns a string representation of the adjacency matrix.
     * ****************************************************************
     */
    @Override
    public String toString() {
        if (numVertices == 0) {
            return "Graph is empty";
        }

        String result = new String("");

        /**
         * Print the adjacency Matrix
         */
        result += "Adjacency Matrix\n";
        result += "----------------\n";
        result += "index\t";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i;
            if (i < 10) {
                result += " ";
            }
        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += "1 ";
                } else {
                    result += "0 ";
                }
            }
            result += "\n";
        }

        /**
         * Print the vertex values
         */
        result += "\n\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        /**
         * Print the weights of the edges
         */
        result += "\n\nWeights of Edges";
        result += "\n----------------\n";
        result += "index\tweight\n\n";

        for (int i = 0; i <=numVertices; i++) {
            for (int j = numVertices - 1; j > i; j--) {
                if (adjMatrix[i][j] < Double.POSITIVE_INFINITY) {
                    result += i + " to " + j + "\t";
                    result += adjMatrix[i][j] + "\n";
                }
            }
        }

        result += "\n";
        return result;
    }

    /**
     * ****************************************************************
     * Inserts an edge between two vertices of the graph.
     *
     * @param index1
     * @param index2
     * @param weight
     * ****************************************************************
     */
    public void addEdge(int index1, int index2, double weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = weight;
        }
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.
     *
     * @param index1
     * @param index2
     * ****************************************************************
     */
    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * ****************************************************************
     * Inserts an edge with the given weight between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * ****************************************************************
     * Inserts an edge between two vertices of the graph. Assumes a weight of 0.
     * ****************************************************************
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), 0);
    }

    /**
     * ****************************************************************
     * Removes an edge between two vertices of the graph.
     * ****************************************************************
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary.
     * ****************************************************************
     */
    public void addVertex() {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = null;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * ****************************************************************
     * Adds a vertex to the graph, expanding the capacity of the graph if
     * necessary. It also associates an object with the vertex.
     * ****************************************************************
     */
    @Override
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++) {
            adjMatrix[numVertices][i] = Double.POSITIVE_INFINITY;
            adjMatrix[i][numVertices] = Double.POSITIVE_INFINITY;
        }
        numVertices++;
    }

    /**
     * ****************************************************************
     * Removes a vertex at the given index from the graph. Note that this may
     * affect the index values of other vertices.
     *
     * @param index
     * ****************************************************************
     */
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
                }
            }
        }
    }

    /**
     * ****************************************************************
     * Removes a single vertex with the given value from the graph.
     * ****************************************************************
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     * @param startIndex
     * @return ****************************************************************
     */
    public Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            /**
             * Find a vertex adjacent to x that has not been visited and push it
             * on the stack
             */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a depth first search traversal starting
     * at the given vertex.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws EmptyCollectionException {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.
     *
     * @param startIndex
     * @return ****************************************************************
     */
    public Iterator<T> iteratorBFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);

            /**
             * Find all vertices adjacent to x that have not been visited and
             * queue them up
             */
            for (int i = 0; i < numVertices; i++) {
                if ((adjMatrix[x.intValue()][i] < Double.POSITIVE_INFINITY) && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given vertex.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the indices of the vertices that are in
     * the shortest path between the two given vertices.
     *
     * @param startIndex
     * @param targetIndex
     * @return ****************************************************************
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        /**
         * Update the pathWeight for each vertex except the startVertex. Notice
         * that all vertices not adjacent to the startVertex will have a
         * pathWeight of infinity for now.
         */
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(new Double(pathWeight[i]));
            }
        }

        do {
            weight = (traversalMinHeap.removeMin()).doubleValue();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                visited[index] = true;
            }

            /**
             * Update the pathWeight for each vertex that has has not been
             * visited and is adjacent to the last vertex that was visited.
             * Also, add each unvisited vertex to the heap.
             */
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY)
                            && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(new Double(pathWeight[i]));
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(new Integer(index));
        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * ****************************************************************
     * Returns the index of the the vertex that that is adjacent to the vertex
     * with the given index and also has a pathWeight equal to weight.
     *
     * @param visited
     * @param pathWeight
     * @param weight
     * @return ****************************************************************
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((adjMatrix[i][j] < Double.POSITIVE_INFINITY)||(adjMatrix[j][i] < Double.POSITIVE_INFINITY) && visited[j]) {
                        return i;
                    }
                }
            }
        }

        return -1; // should never get to here
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * @param startIndex
     * @param targetIndex
     * @return ****************************************************************
     */
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            templist.addToRear(vertices[(it.next().intValue())]);
        }
        return templist.iterator();
    }

    /**
     * ****************************************************************
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     * ****************************************************************
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * @param startIndex
     * @param targetIndex
     * @return ****************************************************************
     */
    public double shortestPathWeight(int startIndex, int targetIndex) throws EmptyCollectionException {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return Double.POSITIVE_INFINITY;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);

        if (it.hasNext()) {
            index1 = ((Integer) it.next()).intValue();
        } else {
            return Double.POSITIVE_INFINITY;
        }

        while (it.hasNext()) {
            index2 = (it.next()).intValue();
            result += adjMatrix[index1][index2];
            index1 = index2;
        }

        return result;
    }

    /**
     * ****************************************************************
     * Returns the weight of the least weight path in the network. Returns
     * positive infinity if no path is found.
     *
     * @param startVertex
     * @param targetVertex
     * ****************************************************************
     */
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) throws EmptyCollectionException {
        return shortestPathWeight(getIndex(startVertex), getIndex(targetVertex));
    }


    /**
     * ****************************************************************
     * Returns the edge with the given weight. Exactly one vertex of the edge
     * must have been visited.
     *
     * @param weight
     * @param visited
     * @return ****************************************************************
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((adjMatrix[i][j] == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        /**
         * Will only get to here if a valid edge is not found
         */
        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * ****************************************************************
     * Creates new arrays to store the contents of the graph with twice the
     * capacity.
     * ****************************************************************
     */
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        double[][] largerAdjMatrix = new double[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

  
    public boolean isConnected() throws EmptyCollectionException {
        int cont = 0;
        Iterator it = iteratorBFS(vertices[0]);
        while (it.hasNext()) {
            it.next();
            cont++;
        }
        if (cont < numVertices) {
            return false;
        }
        
        return true;
    }

public void addEdgeUtilizadores(T vertex1, Long vertex2, double weight) {
        addEdge(getIndex(vertex1), vertex2, weight);
    }

    private void addEdge(int index1, Long index2, double weight) {
      
        if (indexIsValid(index1) && indexIsValid(index2.intValue())) {
            adjMatrix[index1][index2.intValue()] = weight;
            adjMatrix[index2.intValue()][index1] = weight;
        }
    }
}

