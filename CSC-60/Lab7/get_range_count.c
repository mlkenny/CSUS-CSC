/*-------------------------------------------------------------*/
/* Michael Kenny                                               */
/* This file returns the amount of values within a given range */

#include "lab7.h" 
#define MIN 90
#define MAX 99
void get_range_count(int number_list[],int real_filesize, int *range_count) {
    *range_count = 0;
    int i;
    for(i = 0; i < real_filesize; i++) {
       if(number_list[i] >= MIN && number_list[i] <= MAX) {
           (*range_count)++;
        
       }
     
    }
    return;
}
