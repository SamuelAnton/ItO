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
                print(x)
                break
    
    # Given initial point
    def minimize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        #TODO
        pass


if __name__ == "__main__":
    x = array([0.5, 3.5, 1, 2], float)
    A = array([[2, 4, 1, 0], [1, 3, 0, -1]], float)
    c = array([1, 1, 0, 0])
    i = InteriorPoint()
    i.maximize(A=A, c=c, b=array([]), alpha=0.5, eps=0.0001, x=x)