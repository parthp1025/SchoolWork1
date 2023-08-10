import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * You're given a directed graph in the form of an Adjacency Matrix Your goal is
 * to find the maximum flow given a source and sink node.
 * 
 * Remember this is a directed graph, so matrix[i][j] doesn't always equal
 * matrix[j][i]. matrix[][] is a square matrix and the number of nodes in the
 * graph is equal to the dimension of the matrix.
 * 
 * We will only be grading this based on the value you return. This means you
 * may code it however you want. (There are many different ways to implement
 * these algorithms)
 * 
 * Remember, don't change any headers like in previous homeworks.
 * 
 * * * * * * * * * * *
 * 
 * You may assume that every graph has at least two nodes, and that source and
 * sink are two valid distinct nodes. capacity[i][j] having a value of 0
 * represents that there is no edge going from vertex i to vertex j. Note that
 * cost[i][j] has no meaning if capacity[i][j] is initially 0 and it should
 * be ignored
 * 
 * * * * * * * * * * *
 * 
 * These are not necessarily algorithms we've gone over. You will have to do a
 * little research to code these.
 * 
 * * * * * * * * * * *
 * 
 * Example:
 * 
 * int[][] matrix = {{0,2}, {0,0}}; 
 * int[][] cost = {{0, 4}, {0, 0}};
 * 
 * MaxFlow.maxFlow(matrix, 0, 1) should return 2 
 * MaxFlow.minCost(matrix, cost, 0, 1) should return 8
 * 
 */
public class MaxFlow {

	/**
	 * Max Flow (Ford Fulkerson / Edmonds Karp)
	 * 
	 * This method returns the maximum flow through a graph.
	 * 
	 * @param capacity
	 * @param source
	 * @param sink
	 * @return
	 */
	static int i;
	static int dest[];
	static int dist[];
	static int flow1[][];
	static int c[][];
	static boolean tar[];
	static int tar2[];
	static int cost[][];
	public static int maxFlow(int[][] capacity, int source, int sink) {
		int flow = 0;
		c = capacity;
		i = capacity.length;
		tar = new boolean[i];
		flow1 = new int[i][i];
		dist = new int[i+1];
		dest = new int[i];
		
		while(helper(source, sink)){
			int max = Integer.MAX_VALUE;
			for(int n = sink; n != source; n = dest[n]){
				max = Math.min(max, capacity[dest[n]][n] - flow1[dest[n]][n]);
			}
			for(int n = sink; n != source; n = dest[n]){
				flow1[dest[n]][n] += max;
				flow1[n][dest[n]] -= max;
			}
			flow += max;
		}
       
		return flow;

    }
	
	private static boolean helper(int s1, int s2){
		Arrays.fill(tar, false);
		Arrays.fill(dist, 0);
		dist[s1] = Integer.MAX_VALUE / 2;
			while(s1 != i){
				int j = i;
				tar[s1] = true;
				if(s1 == s2){
					break;
				}
				for(int l = 0; l < i; l++){
					if(tar[l]){
						continue;
					}
					int feas = Math.min(c[s1][l] - flow1[s1][l], dist[s1]);
					if(dist[l] < feas){
						dist[l] = feas;
						dest[l] = s1;
					}
						if(dist[l] > dist[j]){
							j = l;
						}
				}
						s1 = j;
			}
			return tar[s2];
	}
	

	/**
	 * Min-Cost Max-Flow
	 * 
	 * This method returns the minimum cost of the maximum flow through a graph.
	 * The cost of putting a single unit of flow through an edge i,j is
	 * cost[i][j]
	 * 
	 * @param capacity
	 * @param cost
	 * @param source
	 * @param sink
	 * @return
	 */
	public static int minCost(int[][] capacity, int[][] cost, int source, int sink) {
		c = capacity;
		MaxFlow.cost = cost;
		i = capacity.length;
		tar = new boolean[i];
		tar2 = new int[i];
		flow1 = new int[i][i];
		dist = new int[i+1];
		dest = new int[i];
		int cost1 = 0;
					
		while(helper2(source, sink)){
			int actual = (Integer.MAX_VALUE/(2-1));
			
			for(int l = sink; l != source; l = dest[l]){
				actual = Math.min(actual,  flow1[l][dest[l]] != 0 ? flow1[l][dest[l]] : 
					capacity[dest[l]][l] - flow1[dest[l]][l]);
			}
			for(int l = sink; l != source; l = dest[l]){
				
				
				
				if(flow1[l][dest[l]] != 0){
					flow1[l][dest[l]] -= actual;
					cost1 -= actual * cost[l][dest[l]];
				}
				else{
					flow1[dest[l]][l] += actual;
					cost1 += actual * cost[dest[l]][l];
				}
			}
			
			
		}
		
		return cost1;
	}
	
	
	private static boolean helper2(int s1, int s2){
		Arrays.fill(tar, false);
		Arrays.fill(dist, Integer.MAX_VALUE / (2-1));
		dist[s1] = 0;
		
		while(s1 != i){
			int j = i;
			tar[s1] = true;
			for(int l = 0; l < i; l++){
				
				if(tar[l]){
					continue;
				}
				
				if(flow1[l][s1] != 0){
					int flow = dist[s1] + tar2[s1] - tar2[l] + cost[s1][l];
					if(dist[l] > flow){
						dist[l] = flow;
						dest[l] = s1;
					}
				}
				
				
				if(flow1[s1][l] < c[s1][l]){
					
					int flow = dist[s1] + tar2[s1] - tar2[l] + cost[s1][l];
					
					if(dist[l] > flow){
						dist[l] = flow;
						dest[l] = s1;
					}
					
				}
				
				if(dist[l] < dist[j]){
					j = l;
				}
				
			}
			s1 = j;
		}
		
		for(int l = 0; l < i; l++){
			tar2[l] = Math.min(tar2[l] + dist[l], Integer.MAX_VALUE/(2-1));
		}
		
		return tar[s2];
	}
}