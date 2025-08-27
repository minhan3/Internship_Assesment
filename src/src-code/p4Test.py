from p4 import find_intersection  # import the function from p4.py

# Function to run a single test case
def run_test(list1, list2, output, test_name):
    result = find_intersection(list1, list2)

    # Compare the actual result with expected output
    if result == output:
        print(f"{test_name}: PASS")
    else:
        print(f"{test_name}: FAIL → Output should be: {output} but got: {result}")

# Run all test cases
def run_all_tests():
    # Test 1: Basic test from the question example
    run_test(
        [4, 5, 2, 3, 1, 6],
        [8, 7, 6, 9, 4, 5],
        [4, 5, 6],
        "Test 1"
    )

    # Test 2: All elements are the same but in different order
    run_test(
        [1, 2, 3],
        [3, 2, 1],
        [1, 2, 3],
        "Test 2"
    )

    # Test 3: Incorrect expected output (includes 5 which is not in list1)
    run_test(
        [1, 2, 3, 4],
        [3, 4, 5],
        [3, 5],  # wrong expected output
        "Test 3"
    )

    # Test 4: Missing 10 in expected result (should FAIL)
    run_test(
        [10, 20, 30],
        [5, 20, 10],
        [20],  # wrong: should be [10, 20]
        "Test 4"
    )

# Entry point
if __name__ == "__main__":
    run_all_tests()
