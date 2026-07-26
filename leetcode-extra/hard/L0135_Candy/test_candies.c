#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Include the candy function
int candy(int* ratings, int ratingsSize);

// Test helper function
void run_test(const char* test_name, int* ratings, int size, int expected) {
    printf("\n=== %s ===\n", test_name);
    printf("Input: [");
    for(int i = 0; i < size; i++) {
        printf("%d", ratings[i]);
        if(i < size - 1) printf(", ");
    }
    printf("]\n");
    
    int result = candy(ratings, size);
    printf("Expected: %d, Got: %d", expected, result);
    
    if(result == expected) {
        printf(" ✓ PASS\n");
    } else {
        printf(" ✗ FAIL\n");
    }
}

int main() {
    printf("========================================\n");
    printf("   CANDY DISTRIBUTION TEST SUITE\n");
    printf("========================================\n");

    // Test 1: Single child
    int test1[] = {5};
    run_test("Test 1: Single Child", test1, 1, 1);

    // Test 2: Two children - ascending
    int test2[] = {1, 2};
    run_test("Test 2: Two Children (Ascending)", test2, 2, 3);

    // Test 3: Two children - descending
    int test3[] = {2, 1};
    run_test("Test 3: Two Children (Descending)", test3, 2, 3);

    // Test 4: Two children - equal
    int test4[] = {1, 1};
    run_test("Test 4: Two Children (Equal)", test4, 2, 2);

    // Test 5: All equal ratings
    int test5[] = {1, 1, 1, 1};
    run_test("Test 5: All Equal Ratings", test5, 4, 4);

    // Test 6: Strictly ascending
    int test6[] = {1, 2, 3, 4, 5};
    run_test("Test 6: Strictly Ascending", test6, 5, 15);

    // Test 7: Strictly descending
    int test7[] = {5, 4, 3, 2, 1};
    run_test("Test 7: Strictly Descending", test7, 5, 15);

    // Test 8: Peak in the middle
    int test8[] = {1, 3, 2};
    run_test("Test 8: Peak in Middle", test8, 3, 6);

    // Test 9: Valley in the middle
    int test9[] = {3, 1, 2};
    run_test("Test 9: Valley in Middle", test9, 3, 4);

    // Test 10: Multiple peaks and valleys
    int test10[] = {1, 3, 2, 2, 1};
    run_test("Test 10: Multiple Peaks and Valleys", test10, 5, 7);

    // Test 11: Complex pattern
    int test11[] = {1, 2, 2};
    run_test("Test 11: Complex Pattern (1, 2, 2)", test11, 3, 4);

    // Test 12: Large array with varied ratings
    int test12[] = {1, 0, 2};
    run_test("Test 12: Valley Pattern (1, 0, 2)", test12, 3, 5);

    // Test 13: Leetcode example 1
    int test13[] = {1, 0, 2};
    run_test("Test 13: LeetCode Example 1", test13, 3, 5);

    // Test 14: Leetcode example 2
    int test14[] = {1, 2, 2};
    run_test("Test 14: LeetCode Example 2", test14, 3, 4);

    // Test 15: Alternating pattern
    int test15[] = {1, 3, 2, 4, 3};
    run_test("Test 15: Alternating Pattern", test15, 5, 9);

    // Test 16: Long ascending sequence
    int test16[] = {1, 2, 3, 4, 5, 6, 7, 8};
    run_test("Test 16: Long Ascending Sequence", test16, 8, 36);

    // Test 17: Long descending sequence
    int test17[] = {8, 7, 6, 5, 4, 3, 2, 1};
    run_test("Test 17: Long Descending Sequence", test17, 8, 36);

    // Test 18: Plateau pattern
    int test18[] = {1, 2, 2, 2, 1};
    run_test("Test 18: Plateau Pattern", test18, 5, 6);

    // Test 19: Random pattern 1
    int test19[] = {5, 3, 7, 1};
    run_test("Test 19: Random Pattern 1", test19, 4, 7);

    // Test 20: Random pattern 2
    int test20[] = {2, 4, 3, 5, 1};
    run_test("Test 20: Random Pattern 2", test20, 5, 9);

    printf("\n========================================\n");
    printf("   TEST SUITE COMPLETED\n");
    printf("========================================\n");

    return 0;
}
