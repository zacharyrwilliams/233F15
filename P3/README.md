This project is to implement an 8-puzzle solver- as in [this](https://en.wikipedia.org/wiki/15_puzzle "Wikipedia article") using a breadth-first search and a depth-first search.

For extra credit, we generalize it to solve an (n*n-1) puzzle. When n > 2 or 3, this becomes very computationally intensive, and my laptop would begin freezing up. I therefore included a couple magic constants to ballpark where I want the program to giveup before running out of memory. If you don't care about this, go ahead and remove them, as they don't actually change any of the fundamental functionality.

The BFS and DFS solver classes are very similar- only a line or two needed to be changed from one method to the other. I probably should have used a better code design so that I didn't need to write two very nearly identical classes, but it seems to have worked out anyway.
