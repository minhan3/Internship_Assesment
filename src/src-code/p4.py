# Problem - Find Intersection of Two Lists
# This function returns a list of elements that appear in both input lists, without duplicates.

# Language Choice Reasoning:
# Python is used here because its list operations and syntax are clean and efficient for
# comparison tasks. Nested loops and conditions are easy to read and maintain in Python,
# making it suitable for intersection logic involving linear scans.

def find_intersection(list1, list2):
    result = []  # Final list to store intersection values

    # Loop through each element in list1
    for i in range(len(list1)):
        # Compare with each element in list2
        for j in range(len(list2)):
            if list1[i] == list2[j]:
                # Check if already in result to avoid duplicates
                exists = False
                for k in range(len(result)):
                    if result[k] == list1[i]:
                        exists = True
                        break
                if not exists:
                    result.append(list1[i])
                break  # Found match, no need to check further in list2
    return result

# Example usage
list1 = [4, 5, 2, 3, 1, 6]
list2 = [8, 7, 6, 9, 4, 5]

output = find_intersection(list1, list2)
print("Intersection:", output)  # Output: [4, 5, 6]
