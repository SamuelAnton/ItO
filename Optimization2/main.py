from InteriorPoint import InteriorPoint
from numpy import array
ip = InteriorPoint()
eps = 0.001

# Test 1
print("Test 1:")
C1 = array([-9, -10, -16, 0, 0, 0], float)
A1 = array([
    [18, 15, 12, 1, 0, 0],
    [6, 4, 8, 0, 1, 0],
    [5, 3, 3, 0, 0, 1]
], float)
b1 = array([360, 192, 180], float)
x1_initial = array([1.0, 1.0, 1.0, 315, 174, 169], float)
result1 = ip.maximize(A=A1, c=C1, b=b1, alpha=0.5, eps=eps, x=x1_initial)
print(f"Interior-Point Solution for Test 1: x* = {result1}")
