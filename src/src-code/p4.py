def find_intersection(list1, list2):
    result = []
    for i in range(len(list1)):
        for j in range(len(list2)):
            if list1[i] == list2[j]:
                # Check if already in result
                exists = False
                for k in range(len(result)):
                    if result[k] == list1[i]:
                        exists = True
                        break
                if not exists:
                    result.append(list1[i])
                break
    return result

# Example usage
list1 = [4, 5, 2, 3, 1, 6]
list2 = [8, 7, 6, 9, 4, 5]

output = find_intersection(list1, list2)
print("Intersection:", output)
