from numpy import *


class InteriorPoint:
    # Given initial point
    def maximize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        i = 1
        while (True):
            prevX = x
            D = diag(x)
            A_ = dot(A, D)
            c_ = dot(D, c)
            P = subtract(eye(len(A_[0])), dot(dot(transpose(A_), linalg.inv(dot(A_, transpose(A_)))), A_))
            cp = dot(P, c_)
            v = absolute(min(cp))
            x_ = add(ones(len(A_[0])), cp * (alpha / v))
            x = dot(D, x_)


            if linalg.norm(subtract(prevX, x), ord=2) < eps:
                for i in range(len(x)):
                    x[i] = "{:.2f}".format(round(x[i], len(str(eps)) - 2))
                return x
                break
    
    # Given initial point
    def minimize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        return self.maximize(A=A, c=-1 * c, b=array([]), alpha=0.5, eps=0.0001, x=x)


if __name__ == "__main__":
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
    result1 = ip.maximize(A=A1, c=C1, b=b1, alpha=0.5, eps=eps, x=x1_initial)
    print(f"Interior-Point Solution for Test 1: x* = {result1}")    