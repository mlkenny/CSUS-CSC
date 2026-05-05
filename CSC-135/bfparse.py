''' Michael Kenny, CSC-135-07, April 27th, 2025

Graded Homework #12:
    This file contains the provided supporting functions provided by the professor,
    as well as the filled in and completed functions with short documentation for:
        parseS
        parseL
        parseC
'''

# This file contains two support classes followed by parsing code.
# scanner is a simple class that delivers tokens to the parser
# node is a simple class for building trees with any number of children

class scanner:
    # toks[i] must evaluate to the i-th token in the token stream.
    # Assumes toks does not change during parsing
    def __init__(self,toks):
        self._toks = toks
        self._i = 0
    
    # If no more tokens exist or current token isn't s, raise exception.
    # Otherwise pass over the current one and move to the next.
    def match(self,s):
        if (self._i < len(self._toks)) and (self._toks[self._i] == s):
            self._i += 1
        else:
            raise Exception
            
    # If any tokens remain return the current one. If no more, return None.
    def next(self):
        if self._i < len(self._toks):
            return self._toks[self._i]
        else:
            return None

# A tree node is a piece of data along with an optional list of children.
# If the _children field is None it indicates the node is a leaf
class node:
    def __init__(self, data):
        self.data = data
        self.children = None
    
    def add_child(self, child):
        if self.children == None:
            self.children = []
        self.children.append(child)
        
    def is_leaf(self):
        return self.children == None

# parse accepts an indexible squence of strings and succeeds without exception
# if the token sequence is entirely consumed and is in L(S). In the case of Bf
# the input will be a string of characters from <>+-.,[]
def parse(input):
    toks = scanner(input)
    root = parseS(toks)
    if toks.next() != None:
        raise Exception
    return root

# --- DO NOT CHANGE ANY CODE ABOVE THIS LINE ---

# Parser for
# S → CS | LS | λ               # predictors = '>', '<', '+', '-', ',', '.', '[', ']', lambda
# L → [ S ]                     # predictors = '['
# C → > | < | + | - | ,  | .    # predictors = '>', '<', '+', '-', ',', '.'

# Consumes sequence of tokens in L(S), returns parse tree for it
def parseS(toks):
    tok = toks.next()
    new = node('S')
    if tok in ('>', '<', '+', '-', ',', '.'): # If tok in predictor table for S.
        new.add_child(parseC(toks)) # Always create a new node for each token or terminal symbol.
        new.add_child(parseS(toks)) # This creates a parse tree through recursion.
    elif tok == '[':
        new.add_child(parseL(toks))
        new.add_child(parseS(toks))
    elif tok in (']', None): # If tok is ']' or None, then add empty string to parse tree.
        new.add_child(node('')) # ']' symbolizes end of loop in the grammar.
    else:
        raise Exception
    return new

'''Process for parseL and parseC are very similar to parseS, and much simpler.'''

# Consumes sequence of tokens in L(L), returns parse tree for it
def parseL(toks):
    tok = toks.next()
    new = node('L')
    if tok == '[':
        toks.match('[')
        new.add_child(node('['))
        new.add_child(parseS(toks))
        toks.match(']')
        new.add_child(node(']'))
    else:
        raise Exception
    return new

# Consumes sequence of tokens in L(C), returns parse tree for it
def parseC(toks):
    tok = toks.next()
    new = node('C')
    if tok in ('>', '<', '+', '-', ',', '.'):
        toks.match(tok)
        new.add_child(node(tok))
    else:
        raise Exception
    return new
