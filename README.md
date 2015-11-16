# code-retreat-zurich-2015
Code retreat exercise.

Exercise:

During one of the code retreat session we were to implement Conway's Game of Life.
There was one extra constraint, that we could not return values from our methods. 
This meant all method must have a void return value.

What this repository is:
- First draft of a possible solution

What this repository is not:
- The only solution;
- The most effective way to do it;
- A recommendation on how to build software (there are no tests in this version).

Challenges:
1 - Figuring out the first test to write. Figured out that I didn't know the first test because I lacked a proof of concept.
This code is the proof of concept.

2 - Allowing the world to grow with the generation. Initially the code required all cells (dead and alive) to be given.
Allowing the user to give only the live cells and allowing the world to grow, while obeying the constraint, was a challenge.

Next steps:
- Write TDD another version from scratch, but influenced by this "design".

Disclaimer:
The code seems to work for the common cases found on wikipedia (blinker, toad). The glider, because it keeps "gliding", is a bit
harder to check. The first three iteration match the pattern on wikipedia.

Conclusion:
I always welcome comments so please feel free.

Cheers.
