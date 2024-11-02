from InteriorPoint import InteriorPoint
from numpy import array, dot
ip = InteriorPoint()
eps = 0.001

# Test 1
print("Test 1:")
C1 = array([9, 10, 16, 0, 0, 0], float)
A1 = array([
    [18, 15, 12, 1, 0, 0],
    [6, 4, 8, 0, 1, 0],
    [5, 3, 3, 0, 0, 1]
], float)
b1 = array([360, 192, 180], float)
x1_initial = array([1.0, 1.0, 1.0, 315, 174, 169], float)
result1_alpha_05 = ip.maximize(A=A1, c=C1, b=b1, alpha=0.5, eps=eps, x=x1_initial)
result1_alpha_09 = ip.maximize(A=A1, c=C1, b=b1, alpha=0.9, eps=eps, x=x1_initial)
F_star_alpha_05 = dot(C1, result1_alpha_05)
F_star_alpha_09 = dot(C1, result1_alpha_09)
print(f"Interior-Point Solution with alpha=0.5: X* = {result1_alpha_05[:3]}")
print(f"Interior-Point Solution with alpha=0.9: X* = {result1_alpha_09[:3]}")
print(f"Optimized Objective Value with alpha=0.5: F* = {dot(C1[:3], result1_alpha_05[:3])}")
print(f"Optimized Objective Value with alpha=0.9: F* = {dot(C1[:3], result1_alpha_09[:3])}")
print("Simplex Solution: X = [0, 8, 20], F* = 400")

# Test 2
print("\nTest 2:")
C2 = array([3, 2, 0, 0, 0], float)
A2 = array([
    [2, 1, 1, 0, 0],
    [2, 3, 0, 1, 0],
    [3, 1, 0, 0, 1]
], float)
b2 = array([18, 42, 24], float)
x2_initial = array([1.0, 1.0, 15, 37, 20], float)
result2_alpha_05 = ip.maximize(A=A2, c=C2, b=b2, alpha=0.5, eps=eps, x=x2_initial)
result2_alpha_09 = ip.maximize(A=A2, c=C2, b=b2, alpha=0.9, eps=eps, x=x2_initial)
print(f"Interior-Point Solution with alpha=0.5: X* = {result2_alpha_05[:2]}")
print(f"Interior-Point Solution with alpha=0.9: X* = {result2_alpha_09[:2]}")
print(f"Optimized Objective Value with alpha=0.5: F* = {dot(C2[:2], result2_alpha_05[:2])}")
print(f"Optimized Objective Value with alpha=0.9: F* = {dot(C2[:2], result2_alpha_09[:2])}")
print("Simplex Solution: X = [3,12], F* = 33")

# Test 3
print("\nTest 3:")
C3 = array([3, 5, 4, 0, 0, 0], float)
A3 = array([
    [2, 3, 0, 1, 0, 0],
    [0, 2, 5, 0, 1, 0],
    [3, 2, 4, 0, 0, 1]
], float)
b3 = array([8, 10, 15], float)
x3_initial = array([1.0, 1.0, 1.0, 3, 3, 6], float)
result3_alpha_05 = ip.maximize(A=A3, c=C3, b=b3, alpha=0.5, eps=eps, x=x3_initial)
result3_alpha_09 = ip.maximize(A=A3, c=C3, b=b3, alpha=0.9, eps=eps, x=x3_initial)
print(f"Interior-Point Solution with alpha=0.5: X* = {result3_alpha_05}")
print(f"Interior-Point Solution with alpha=0.9: X* = {result3_alpha_09}")
print(f"Optimized Objective Value with alpha=0.5: F* = {dot(C3, result3_alpha_05)}")
print(f"Optimized Objective Value with alpha=0.9: F* = {dot(C3, result3_alpha_09)}")
print("Simplex Solution: X = [2.17, 1.22, 1.51], F* = 18.66")


# Test 4
print("\nTest 4:")
C6 = array([2, 1, 0, 0], float)
A6 = array([
    [1, -1, 1, 0],
    [2, -1, 0, 1]
], float)
b6 = array([10, 40], float)
x6_initial = array([1.0, 1.0, 10, 39], float)
result6_alpha_05 = ip.maximize(A=A6, c=C6, b=b6, alpha=0.5, eps=eps, x=x6_initial)
result6_alpha_09 = ip.maximize(A=A6, c=C6, b=b6, alpha=0.9, eps=eps, x=x6_initial)
print(f"Interior-Point Solution with alpha=0.5: X* = {result6_alpha_05[:2]}")
print(f"Interior-Point Solution with alpha=0.9: X* = {result6_alpha_09[:2]}")
print("Expected Output: The system has no solutions or has many solutions.")