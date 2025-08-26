from collections import deque

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

# Example graph as a dictionary (directed)
graph = {
    'A': ['B'],
    'B': ['C', 'D'],
    'C': [],
    'D': ['E'],
    'E': ['F'],
    'F': ['B', 'A'],
    'G': []
}

# Test Examples
tests = [
    ('D', 'B'),  # Example 1: No path
    ('F', 'A'),  # Example 2: Path exists: F → B → A
    ('G', 'C'),  # Example 3: No path
    ('E', 'D')   # Example 4: Path exists: E → F → B → D
]

for i, (start, end) in enumerate(tests, 1):
    exists, path = find_path(graph, start, end)
    if exists:
        print(f"Test {i}: Path exists → {' → '.join(path)}")
    else:
        print(f"Test {i}: No path exists from {start} to {end}")
