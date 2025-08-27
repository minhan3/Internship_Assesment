from collections import deque

# Function to check if a path exists between two nodes and return the path if found
def find_path(graph, start, end):
    visited = set()            # to keep track of visited nodes
    queue = deque([[start]])   # queue to store paths

    while queue:
        path = queue.popleft()     # get the first path in queue
        node = path[-1]            # check the last node in the path

        if node == end:
            return True, path      # found the path

        if node not in visited:
            visited.add(node)
            for neighbor in graph.get(node, []):
                new_path = list(path)      # copy current path
                new_path.append(neighbor)  # add neighbor to path
                queue.append(new_path)     # add new path to queue

    return False, []    # if no path found

# Example directed graph
graph = {
    'A': ['B'],
    'B': ['C', 'D'],
    'C': [],
    'D': ['E'],
    'E': ['F'],
    'F': ['B', 'A'],
    'G': []  # Disconnected node
}

# List of test cases: (start node, end node)
tests = [
    ('D', 'B'),  # Test 1: No path from D to B
    ('F', 'A'),  # Test 2: Path exists → F → B → A
    ('G', 'C'),  # Test 3: G is isolated, no path to C
    ('E', 'D')   # Test 4: Path exists → E → F → B → D
]

# Run and print results
for i, (start, end) in enumerate(tests, 1):
    exists, path = find_path(graph, start, end)
    if exists:
        print(f"Test {i}: Path exists → {' → '.join(path)}")
    else:
        print(f"Test {i}: No path exists from {start} to {end}")
