import numpy as np
from scipy.optimize import linprog
c = np.array([-1, 0.0, 0.0])
n = int(input())
m = int(input())
b_ub = [][1]
A_ub = [][3]
for i in range(0, n):
    A_ub[i][0] = 1
    A_ub[i][1] = float(input())
    A_ub[i][2] = 1
    b_ub[i][0] = float(input())
for i in range(0, m):
    A_ub[i][0] = 1
    A_ub[i][1] = -float(input())
    A_ub[i][2] = -1
    b_ub[i][0] = -float(input())
b_ub = np.array(b_ub)
A_ub = np.array(A_ub)
x0_bounds = (None, None)
x1_bounds = (None, None)
x2_bounds = (None,None)
bounds = [x0_bounds, x1_bounds, x2_bounds]
result = linprog(c, A_ub=A_ub, b_ub=b_ub,  bounds=bounds)
print("%4f"% result.x[1]+","+"%4f"% result.x[2])