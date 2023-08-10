/**
 * CS 2110 - Summer 2012 - Homework #10
 * Edited by: Brandon Whitehead, Andrew Wilder
 * // PARTH PATEL
 * list.c: Complete the functions!
 */

#include <stdlib.h>
#include <stdio.h>
#include "list.h"

/* The node struct.  Has a prev pointer, next pointer, and data. */
/* DO NOT DEFINE ANYTHING OTHER THAN WHAT'S GIVEN OR YOU WILL GET A ZERO*/
/* Design consideration only this file should know about nodes */
/* Only this file should be manipulating nodes */
/* DO NOT TOUCH THIS DECLARATION DO NOT PUT IT IN OTHER FILES */
typedef struct lnode
{
  struct lnode* prev; /* Pointer to previous node */
  struct lnode* next; /* Pointer to next node */
  void* data; /* User data */
} node;


/* Do not create any global variables here. Your linked list library should obviously work for multiple linked lists */
// This function is declared as static since you should only be calling this inside this file.
static node* create_node(void* data);

/** create_node
  *
  * Helper function that creates a node by allocating memory for it on the heap.
  * Be sure to set its pointers to NULL.
  *
  * @param data a void pointer to data the user wants to store in the list
  * @return a node
  */
static node* create_node(void* data)
{
    /// @todo Implement changing the return value!
	node* newNode = malloc(sizeof(node));
	newNode -> prev = NULL;
	newNode -> next = NULL;
	newNode -> data = data;
	
   return newNode;
}

/** create_list
  *
  * Creates a list by allocating memory for it on the heap.
  * Be sure to initialize size to zero and head/tail to NULL.
  *
  * @return an empty linked list
  */
list* create_list(void)
{
    /// @todo Implement changing the return value!

	list* newlist = malloc(sizeof(list));
	newlist -> head = NULL;
	newlist -> tail = NULL;
	newlist -> size = 0;

    return newlist;
}

/** push_front
  *
  * Adds the data to the front of the linked list
  *
  * @param llist a pointer to the list.
  * @param data pointer to data the user wants to store in the list.
  */
void push_front(list* llist, void* data)
{
    /// @todo Implement
	node* curr = create_node(data);

	if(is_empty(llist)){
		llist -> head = curr;
		llist -> tail = curr;
	}
	if(size(llist) > 0){
		curr -> next = llist -> head;
		llist -> head -> prev = curr;
		llist -> head = curr;
	}
	llist->size++;
}

/** push_back
  *
  * Adds the data to the back/end of the linked list
  *
  * @param llist a pointer to the list.
  * @param data pointer to data the user wants to store in the list.
  */
void push_back(list* llist, void* data)
{
    /// @todo Implement
	node* curr = create_node(data);
	if(is_empty(llist)){
		llist -> head = curr;
		llist -> tail = curr;
	}
	if(size(llist)>0){
	curr -> prev = llist -> tail;
	llist -> tail -> next = curr;
	llist -> tail = curr;
	}
	llist->size++;
	
}

/** remove_front
  *
  * Removes the node at the front of the linked list
  *
  * @warning Note the data the node is pointing to is also freed. If you have any pointers to this node's data it will be freed!
  *
  * @param llist a pointer to the list.
  * @param free_func pointer to a function that is responsible for freeing the node's data.
  * @return -1 if the remove failed (which is only there are no elements) 0 if the remove succeeded.
  */
int remove_front(list* llist, list_op free_func)
{
    /// @todo Implement
    /// @note remember to also free the node itself
    /// @note free_func is a function that is responsible for freeing the node's data only.
	
	
	if (llist->size == 0) {
       return -1;
   }
	if(size(llist)>0){
	node* newNode = llist -> head;
	node* nextNode = newNode -> next;
		 if (size(llist) == 1){
		llist -> head = NULL;
		llist -> tail = NULL;
		free_func(newNode -> data);
		free_func(newNode);
	
	}
	else{
		llist -> head = nextNode;
		free_func(newNode -> data);
		free(newNode);
	}
		llist->size--;
		return 0;
	}
		return -1;
}
		
	


/** remove_back
  *
  * Removes the node at the back of the linked list
  *
  * @warning Note the data the node is pointing to is also freed. If you have any pointers to this node's data it will be freed!
  *
  * @param llist a pointer to the list.
  * @param free_func pointer to a function that is responsible for freeing the node's data.
  * @return -1 if the remove failed 0 if the remove succeeded.
  */
int remove_back(list* llist, list_op free_func)
{
    /// @todo Implement
    /// @note Remember to also free the node itself
    /// @note free_func is a function that is responsible for freeing the node's data only.
	

		
	if (llist->size == 0) {
       return -1;
   }
	if(size(llist)>0){
	node* newNode = llist -> tail;
	node* prevNode = newNode -> prev;
		 if (size(llist) == 1){
		llist -> head = NULL;
		llist -> tail = NULL;
		free_func(newNode -> data);
		free_func(newNode);
	
	}
	else{
		llist -> tail = prevNode;
		free_func(newNode -> data);
		free(newNode);
	}
		llist->size--;
		return 0;
	}
		return -1;
}

/** copy_list
  *
  * Create a new list structure, new nodes, and new copies of the data by using
  * the copy function. Its implementation for any test structure must copy
  * EVERYTHING!
  *
  * @param llist A pointer to the linked list to make a copy of
  * @param copy_func A function pointer to a function that makes a copy of the
  *        data that's being used in this linked list, allocating space for
  *        every part of that data on the heap
  * @return The linked list created by copying the old one
  */
list* copy_list(list* llist, list_cpy copy_func)
{
	/// @todo implement
	int arnold = size(llist);
	int i;
	list* clone = create_list();
    node* newNode = llist -> head;
   for (i = 0; i < arnold; i++) {
		node* dataNode = copy_func(newNode -> data);
		newNode = newNode -> next;
       push_back(clone, dataNode);
   }
	clone->size = llist->size;
	return clone;
}


/** front
  *
  * Gets the data at the front of the linked list
  * If the list is empty return NULL.
  *
  * @param llist a pointer to the list
  * @return The data at the first node in the linked list or NULL.
  */
void* front(list* llist)
{
    /// @todo Implement changing the return value!
    /// @note you are returning the HEAD's DATA not the head node. Remember the user should never deal with the linked list nodes.
	if(size(llist)>0){
	return llist -> head -> data;
	}
	else{
    return NULL;
	}
}

/** back
  *
  * Gets the data at the "end" of the linked list
  * If the list is empty return NULL.
  *
  * @param llist a pointer to the list
  * @return The data at the last node in the linked list or NULL.
  */
void* back(list* llist)
{
    /// @todo Implement changing the return value!
	if(size(llist)>0){
	return llist -> tail -> data;
	}
	else{
    return NULL;
	}
}

/** size
  *
  * Gets the size of the linked list
  *
  * @param llist a pointer to the list
  * @return The size of the linked list
  */
int size(list* llist)
{
    ///@note simply return the size of the linked list.  Its that easy!

	int lsize = llist->size;
    return lsize;
}

/** traverse
  *
  * Traverses the linked list calling a function on each node's data.
  *
  * @param llist a pointer to a linked list.
  * @param do_func a function that does something to each node's data.
  */
void traverse(list* llist, list_op do_func)
{
    /// @todo Implement
	int i;
	int arnold = size(llist);
	node* newNode = llist -> head;
   for(i = 0; i < arnold; i++) {
       do_func(newNode->data);
		newNode = newNode -> next;
   }

}

/** remove_if
  *
  * Removes all nodes whose data when passed into the predicate function returns true
  *
  * @param llist a pointer to the list
  * @param pred_func a pointer to a function that when it returns true it will remove the element from the list and do nothing otherwise @see list_pred.
  * @param free_func a pointer to a function that is responsible for freeing the node's data
  * @return the number of nodes that were removed.
  */
int remove_if(list* llist, list_pred pred_func, list_op free_func)
{
    /// @todo Implement changing the return value!
    /// @note remember to also free all nodes you remove.
    /// @note be sure to call pred_func on the NODES DATA to check if the node needs to be removed.
    /// @note free_func is a function that is responsible for freeing the node's data only.
   
   node* newNode;
   
   int counter = 0;

   for(newNode = llist -> head; newNode; newNode = newNode -> next){
     if(pred_func(newNode -> data)){
	  if(size(llist) == 0){
		return 0;
		}
			node* nextNode = newNode -> next;
         if( !(newNode -> next) && (newNode -> prev)){
           remove_back(llist, free_func);
           counter++;
		   return counter;
         }
         else if( !(newNode -> next) && !(newNode -> prev) ){
           remove_back(llist, free_func);
           counter++;
		   return counter;
         }
		 if( !(newNode -> prev) && (newNode -> next)){
           remove_front(llist, free_func);
           newNode = nextNode;
           nextNode = newNode -> next;
           counter++;
		   return counter;
         }
         else{
           free_func(newNode -> data);
           newNode -> next -> prev = newNode -> prev;
           newNode -> prev -> next = newNode -> next;
           free(newNode);
           newNode = nextNode;
           nextNode = newNode -> next;
           counter++;
           llist -> size--;
         }
     }
     else{
		node* nextNode = newNode -> next;
       if(nextNode){
         newNode = nextNode;
         nextNode = newNode -> next;
       }
       else{
         return counter;
       }
     }
   } // end of while
return counter;
}




/** is_empty
  *
  * Checks to see if the list is empty.
  *
  * @param llist a pointer to the list
  * @return 1 if the list is indeed empty 0 otherwise.
  */
int is_empty(list* llist)
{
    ///@note an empty list by the way we want you to implement it has a size of zero and head points to NULL.
    if((llist -> head == NULL) && size(llist) == 0){
	return 1;
	}
	else{
    return 0;
	}
}

/** empty_list
  *
  * Empties the list after this is called the list should be empty.
  *
  * @param llist a pointer to a linked list.
  * @param free_func function used to free the node's data.
  *
  */
void empty_list(list* llist, list_op free_func)
{
    /// @todo Implement
    /// @note Free all of the nodes not the linked list itself.
    /// @note do not free llist.

	traverse(llist, free_func);
	node* newNode = llist -> head;
	
	for(int i = 0; i < size(llist); i++){
		node* nextNode = newNode -> next;
		free(newNode);
		newNode = nextNode;
	}
	llist -> head = NULL;
	llist -> tail = NULL;
	llist -> size = 0;

}
