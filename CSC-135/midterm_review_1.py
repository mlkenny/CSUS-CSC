from List135 import List135
from functools import reduce

# Midterm 1, CSC 135 Fall 2022
# Name:
# Sac State username:

# When you are done, write your name and username above, save, and
# submit this file to
#
#     https://fileinbox.com/csc135/xxyxx
#
# with xxyxx replaced by your Sac State username.

# Complete each of the following functions. Each problem will be scored
# separately, but the file as a whole must contain no syntax errors.
# That means running this file should not result in a syntax error.
# If a function has a syntax error, replace its body with a dummy return
# or pass statement to eliminate the error.


# Problem 1: The maximum distance between any element of the pyhton list
# [-1,2,0] and the number 1 is 2 because abs(-1 - 1) is larger than abs(2 - 1)
# and abs(0 - 1). The following two functions can be used together to find such
# maximum distances.

# Write a function that uses some combination of map, reduce, filter
# and/or lambda that takes a python list of numbers xs and a number c as
# parameters and produces a python list of the distances each element
# x is from c (ie, abs(x-c)). For example dists([-1, 2, 0], 1) should return
# [2, 1, 1]. You may use the builtin function abs(x) in your lambda.
def dists(xs, c):
    pass

# Write a function max_nonempty that takes a nonempty python list
# of numbers and returns the largest number. Use reduce to do this by
# using the builtin function max(a,b) in your lambda.
def max_nonempty(xs):
    pass


# Problem 2: Write a function that takes a unary boolean function and a python
# list as parameters and returns True if more elements of the list make f True
# than make it false. For example more_true(lambda x: x%2==0, [1,2,3,4])
# should return False because 2 elements make it True and 2 make it False.
def more_true(f, xs):
    pass


# NOTE: The following are client functions of List135. They must not
# access the _data or _next fields. Only the first(), rest(), add(),
# and empty() methods and no-argument List135() constructor are allowed.

# Problem 3: Write a recursive function firstN that takes a List135 xs as a
# parameter and returns a new List135 composed of the first n elements
# of xs. For example firstN([1,2,3], 2) would produce [1,2]. You may
# assume 0 <= n <= len(xs). Hint: n == 0 is your base case.
def firstN(xs, n):
    if n == 0:
        return List135()
    else:
        return firstN(xs.rest(), n - 1).add(xs.first())


# Problem 4: The following function returns how many elements of a List135
# are greater than x. Rewrite it to be tail recursive. You may add a helper
# function or an extra parameter with a default value if you want to.
#
#def num_greater(xs, x):
#    if xs.empty():
#        return 0
#    else:
#        cnt = 0
#        if xs.first() > x:
#            cnt = 1
#        return cnt + num_greater(xs.rest(), x)
def _num_greater(xs, x, count):
    if xs.empty():
        return 0
    else:
        if xs.first() > x:
            count += 1
        return _num_greater(xs.rest(), x, count)

def num_greater(xs, x):
    return _num_greater(xs, x, 0)

print(num_greater(List135().add(3).add(2).add(1), 2))

'''1) (3 Pts) Write a python function clamp that takes a python list xs of integers and an integer x as parameters and returns a list that contains the same elements as xs except that every element greater than x
is replaced by x. So, clamp([1,2,3,4], 2) would return [1,2,2,2] because both 3 and 4 are greater than
2. You must solve using map, filter, reduce and/or lambda and may not call any built-in functions or
list-comprehensions. Hint: The conditional statement “xx if yy else zz” may be useful.'''
def clamp(xs, x):
    pass


# Midterm testing will ignore the following indented code. You test here.
'''
if __name__ == '__main__':
    xs = List135().add(3).add(2).add(1)
    ys = dists([-1, 2, 0], 1)
    print(1, isinstance(ys, list))
    print(1, ys == [2,1,1])
    print(1, max_nonempty([2,1,1]) == 2)
    print(2, more_true(lambda x: x%2==0,[1,2,3,4]) == False)
    ys = firstN(xs,2)
    print(3, isinstance(ys, List135))
    print(3, str(ys) == "[1,2]")
    print(4, num_greater(xs, 1) == 2)
'''