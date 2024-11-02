from numpy import *


class InteriorPoint:
    # Given initial point
    def maximize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        y = 1
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

            y += 1

            if linalg.norm(subtract(prevX, x), ord=2) < eps:
                for i in range(len(x)):
                    x[i] = "{:.2f}".format(round(x[i], len(str(eps)) - 2))
                return x
            
            if (y >= 10000):
                return "The problem does not have solution or has many solutions."


    
    # Given initial point
    def minimize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        return self.maximize(A=A, c=-1 * c, b=array([]), alpha=0.5, eps=0.0001, x=x)


if __name__ == "__main__":
    ip = InteriorPoint()
    eps = 0.001

    print("\nTest 6:")
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