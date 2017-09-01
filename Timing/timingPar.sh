#!/bin/bash

#n = 50000

java InputFileWriter <<EOF
50000
EOF
echo "n = 50000" >> timingsSeq
echo "fsize = 21" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 21 out
EOF
done

echo "fsize = 75" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 99 out
EOF
done

echo "fsize = 151" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 151 out
EOF
done

#n = 100000

java InputFileWriter <<EOF
100000
EOF
echo "n = 100000" >> timingsSeq
echo "fsize = 21" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 21 out
EOF
done

echo "fsize = 75" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 99 out
EOF
done

echo "fsize = 151" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 151 out
EOF
done

#n = 500000

java InputFileWriter <<EOF
500000
EOF
echo "n = 500000" >> timingsSeq
echo "fsize = 21" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 21 out
EOF
done

echo "fsize = 75" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 99 out
EOF
done

echo "fsize = 151" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 151 out
EOF
done

#n = 1000000

java InputFileWriter <<EOF
1000000
EOF
echo "n = 1000000" >> timingsSeq
echo "fsize = 21" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 21 out
EOF
done

echo "fsize = 75" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 99 out
EOF
done

echo "fsize = 151" >> timingsSeq
for i in 1 2 3 4 5 6
do
java Medfiltseq >> timingsSeq<<EOF
outfile 151 out
EOF
done
