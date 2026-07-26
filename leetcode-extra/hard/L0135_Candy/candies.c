#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int candy(int* ratings, int ratingsSize){
    int candyCount=0;

    //  [Debug] Creating a Dynamic array to print what value to assigned to what
    int* candies = (int*)calloc(ratingsSize, sizeof(int));

    // if only one rating is there then return with 1 candy. Nothing to compare
    if(ratingsSize == 1){
        candies[0] = 1;  // [Debug]
        printf("Candies: [1]\n");
        return 1;
    }
    // handling empty Arrays
    else if(ratingsSize < 1){
        printf("\nThe array is empty");
        exit(EXIT_FAILURE);
    }

    for(int index=0; index<ratingsSize; index++){
        // handling the first index
        if(index==0){
            candyCount++;
            candies[index]++;   // [Debug]
            if(ratingsSize > 1 && ratings[index] > ratings[index+1]){
                candyCount++;
                candies[index]++;  // [Debug]
            }
            continue;  // skipping middle logic
        }

        // handling the last index
        if(index==ratingsSize-1){
            candyCount++;
            candies[index]++;   // [Debug]
            if(index > 0 && ratings[index] > ratings[index-1]){
                candyCount++;
                candies[index]++;  // [Debug]
            }
            continue;  // skipping middle logic
        }

        // ------------------------------
        // handling middle values
        // ------------------------------
        candyCount++;
        candies[index]++;

        bool leftExecuted = false;
        if(ratings[index] != ratings[index -1] && ratings[index] > ratings[index -1]){
            leftExecuted = true;
            candyCount++;
            candies[index]++;  // [Debug]
        }

        if(!leftExecuted){
            if(ratings[index] != ratings[index +1] && ratings[index] > ratings[index +1]){
                candyCount++;
                candies[index]++; // [Debug]
        }
        }
    }

    // [Debug] Printing output of the debug Array
    printf("Candies: [");
    for(int i = 0; i < ratingsSize; i++){
        printf("%d", candies[i]);
        if(i < ratingsSize - 1) printf(", ");
    }
    printf("]\n");

    free(candies);
    return candyCount;
}