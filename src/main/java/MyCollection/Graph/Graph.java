package MyCollection.Graph;

import java.util.Iterator;

import MyCollection.Exceptions.EmptyCollectionException;
import MyCollection.Stack.LinkedStack;
import MyCollection.Queue.LinkedQueue;
import MyCollection.List.ArrayUnorderedList;

public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; //numero de vertices no grafo
    protected boolean[][] adjMatrix; //matriz de adjacencias
    protected T[] vertices; //valores dos vertices

    /**
     * Creates an empty graph
     */
    @SuppressWarnings("unchecked")
    public Graph() {
        numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T vertex) {
        // TODO Auto-generated method stub
        if (numVertices == vertices.length)
            expandCapacity();
        vertices[numVertices] = vertex;
        for (int a = 0; a <= numVertices; a++) {
            adjMatrix[numVertices][a] = false;
            adjMatrix[a][numVertices] = false;
        }
        numVertices++;
    }

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        // TODO Auto-generated method stub
        T[] tempVertices = vertices;
        int i = vertices.length;
        this.vertices = (T[]) (new Object[i * 2]);
        for (int x = 0; x < i; x++) {
            this.vertices[x] = tempVertices[x];
        }
        boolean[][] tempAdjMatrix = adjMatrix;
        this.adjMatrix = new boolean[i * 2][i * 2];
        for (int x = 0; x < i; x++) {
            for (int y = 0; y < i; y++) {
                adjMatrix[x][y] = tempAdjMatrix[x][y];
            }
        }
    }

    @Override
    public void removeVertex(T vertex) {
        // TODO Auto-generated method stub
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                break;
            }
        }
    }

    private void removeVertex(int index) {
        // TODO Auto-generated method stub
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++)
                vertices[i] = vertices[i + 1];

            for (int i = index; i < numVertices; i++)
                for (int j = 0; j <= numVertices; j++)
                    adjMatrix[i][j] = adjMatrix[i + 1][j];

            for (int i = index; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    adjMatrix[j][i] = adjMatrix[j][i + 1];
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void addEdge(int vertex1, int vertex2) {
        // TODO Auto-generated method stub
        if (indexIsValid(vertex1) && indexIsValid(vertex2)) {
            adjMatrix[vertex1][vertex2] = true;
            adjMatrix[vertex1][vertex2] = true;
        }
    }

    /**
     * Retorna o index equivalente ao vertice
     *
     * @param vertex1
     * @return
     */
    public int getIndex(T vertex1) {
        // TODO Auto-generated method stub
        for (int i = 0; i <= numVertices; i++) {
            if (vertices[i].equals(vertex1)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns true if the index is valid, false otherwise.
     *
     * @param index
     * @return
     */
    public boolean indexIsValid(int index) {
        return (index < numVertices && index >= 0);
    }

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param index1 - index of the first vertex
     * @param index2 - index of the second vertex
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        Integer startIndex = getIndex(startVertex);

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
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Returns an iterator that performs a breadth first search traversal
     * starting at the given index.
     *
     * @param startIndex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
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
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Iterator iteratorDFS(T startVertex) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];
        Integer startIndex = getIndex(startVertex);

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
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
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
     * Returns an iterator that performs a depth first search traversal starting
     * at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     * @throws EmptyCollectionException
     */
    public Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
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
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
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

    @SuppressWarnings("rawtypes")
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException {
        // TODO Auto-generated method stub
        Integer x, index, level;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        Integer startIndex = getIndex(startVertex);
        Integer endIndex = getIndex(targetVertex);
        Integer[] dist = new Integer[numVertices];

        //verifica se os indexes s�o validos, caso contrario retorna um iterator vazio
        if (!indexIsValid(startIndex) || !indexIsValid(endIndex)) {
            return resultList.iterator();
        }

        //inicializa o array visited com valores false
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        //adiciona o startIndex a queue
        traversalQueue.enqueue(new Integer(startIndex));
        //marca o startIndex como visited
        visited[startIndex] = true;
        //inicializa a distancia de startIndex como 0
        dist[startIndex] = 0;
        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            /**
             * Encontra todos os vertices adjacentes a x e incrementa 1 �
             * distancia dos que ainda n�o foram visitados
             */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[x.intValue()][i] && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                    dist[i] = dist[x.intValue()] + 1;
                }
            }
            //p�ra o cilco quando o endIndex for visitado
            if (visited[endIndex] == true) {
                break;
            }
        }
        //se o endIndex nao foi visitado retorna um iterador vazio
        if (visited[endIndex] == false) {
            return resultList.iterator();
        }

        //Adiciona o vertice de endIndex na frente da lista
        resultList.addToFront(vertices[endIndex]);
        index = endIndex;
        level = dist[endIndex];
        while (index != startIndex) {
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[index.intValue()][i] && dist[i] == level - 1) {
                    /**
                     * Encontra um vertice adjacente a index que o seu dist seja
                     * igual a level-1 e adiciona-o na frente da lista
                     */
                    resultList.addToFront(vertices[i]);
                    index = i;
                    level--;
                    break;
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if (vertices[0] == null)
            return true;
        else
            return false;
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException {
        // TODO Auto-generated method stub
        if (isEmpty())
            return false;
        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return (count == numVertices);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return numVertices;
    }
}
