# bmpdisjoint
solution to an old google interview question I found

right now it's set up for an NxN array of bools, that could easily be changed to allow for rectangular images and color depth (would just need to add a function somewhere that determines if two depths are 'close' enough to be considered part of the same region)

the algorithm isn't exactly efficient but works fine for small 'files' (I only tested with smallish arrays of bools)

in terms of practical application, would be good for a first pass to generate inputs for a NN, probly a classifier
