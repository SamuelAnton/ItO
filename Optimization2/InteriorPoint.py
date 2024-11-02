from numpy import *


class InteriorPoint:
    # Given initial point
    def maximize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        i = 1
        while (True):
            D = diag(x)
            A_ = dot(A, D)
            c_ = dot(D, c)
            P = subtract(eye(len(A_[0])), dot(dot(transpose(A_), linalg.inv(dot(A_, transpose(A_)))), A_))
            cp = dot(P, c_)
            v = absolute(min(cp))
            x_ = add(ones(len(A_[0])), cp * (alpha / v))

            if linalg.norm(subtract(x_, x)) < eps:
                print(x_)
                break

            x = x_

            if i in [1, 2, 3, 4]:
                print("In iteration", i, "we have x =", x)
                i += 1

    # # Not given initial point
    # def maximize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001):
    #     self.maximize(A=A, c=c, b=b, alpha=alpha, eps=eps, x=self.findInitialPoint(A=A, b=b))
    
    # Given initial point
    def minimize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001, x:matrix):
        #TODO
        pass

    # Not given initial point
    def minimize(self, *, A:matrix, c:matrix, b:matrix, alpha:float=0.5, eps:float=0.001):
        self.minimize(A=A, c=c, b=b, alpha=alpha, eps=eps, x=self.findInitialPoint(A=A, b=b))
    
    def findInitialPoint(self, *, A:matrix, b:matrix):
        #TODO
        pass  



if __name__ == "__main__":
    x = array([2, 2, 3, 4], float)
    A = array([[2, -2, 8, 0], [-6, -1, 0, -1]], float)
    c = array([-2, 3, 0, 0])
    i = InteriorPoint()
    i.maximize(A=A, c=c, b=array([]), alpha=0.5, eps=0.0001, x=x)