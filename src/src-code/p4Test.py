from p4 import find_intersection  # import p4.py file

def run_test(list1, list2, output, test_name):
    result = find_intersection(list1, list2)

    if result == output:
        print(f"{test_name}: PASS")
    else:
        print(f"{test_name}: FAIL → Output should be: {output} but got: {result}")

def run_all_tests():
    # Test 1: elements in question (PASS)
    run_test(
        [4, 5, 2, 3, 1, 6],
        [8, 7, 6, 9, 4, 5],
        [4, 5, 6],
        "Test 1"
    )

    # Test 2: All same elements (PASS)
    run_test(
        [1, 2, 3],
        [3, 2, 1],
        [1, 2, 3],
        "Test 2"
    )

    # Test 3: Wrong expected output (FAIL)
    run_test(
        [1, 2, 3, 4],
        [3, 4, 5],
        [3, 5],  # wrong: 5 is not in list1
        "Test 3"
    )

    # Test 4: Wrong order and values (FAIL)
    run_test(
        [10, 20, 30],
        [5, 20, 10],
        [20],  # wrong: should be [10, 20]
        "Test 4"
    )

if __name__ == "__main__":
    run_all_tests()
