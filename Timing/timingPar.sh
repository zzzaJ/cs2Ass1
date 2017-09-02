#!/bin/bash

#n = 50000

java InputFileWriter <<EOF
50000
EOF
echo 
echo "n = 50000" >> timingsPar
echo "fsize = 3" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 3 out
EOF
done

echo "fsize = 11" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 11 out
EOF
done

echo "fsize = 21" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 21 out
EOF
done

#n = 100000

java InputFileWriter <<EOF
100000
EOF
echo "n = 100000" >> timingsPar
echo "fsize = 3" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 3 out
EOF
done

echo "fsize = 11" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 11 out
EOF
done

echo "fsize = 21" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 21 out
EOF
done

#n = 500000

java InputFileWriter <<EOF
500000
EOF
echo "n = 500000" >> timingsPar
echo "fsize = 3" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 3 out
EOF
done

echo "fsize = 11" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 11 out
EOF
done

echo "fsize = 21" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 21 out
EOF
done

#n = 1000000

java InputFileWriter <<EOF
1000000
EOF
echo "n = 1000000" >> timingsPar
echo "fsize = 3" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 3 out
EOF
done

echo "fsize = 11" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 11 out
EOF
done

echo "fsize = 21" >> timingsPar
for i in 1 2 3 4 5 6
do
java Medfiltpar >> timingsPar<<EOF
outfile 21 out
EOF
done
