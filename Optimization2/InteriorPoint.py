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