import numpy as np
import math

# This is the cost/error function.
# This entire algorithm is replaced by the model.fit method moving forward.
# But we must do it manually at least once.

# Derivative is the key to this entire process in machine learning.
# Helps optimize the error function, giving us the "best" predicted value.
def gradient_descent(x,y):
    m_curr = b_curr = 0
    iterations = 10000000 # Start small when testing: iterations = 10, iterations = 100, ...
    n = len(x)
    learning_rate = 0.000021

    cost_prev = 0
    
    for i in range(iterations):
        # This calculates the derivatives for the function.
        y_predicted = m_curr * x + b_curr
        cost = (1/n) * sum([val**2 for val in (y-y_predicted)])
        md = -(2/n)*sum(x*(y-y_predicted)) # Derivative for m.
        bd = -(2/n)*sum(y-y_predicted) # Derivative for b.
        m_curr = m_curr - learning_rate * md
        b_curr = b_curr - learning_rate * bd
        # print ("m {}, b {}, cost {} iteration {}".format(m_curr,b_curr,cost, i))
        if math.isclose(cost, cost_prev, rel_tol=1e-20):
            print ("m {}, b {}, cost {} iteration {}".format(m_curr,b_curr,cost, i))
            break
        else:
            cost_prev = cost

# x = np.array([1,2,3,4,5])
# y = np.array([5,7,9,11,13])

# gradient_descent(x,y)