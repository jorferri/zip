https://www.youtube.com/watch?v=O17OjxBLVrs&t=12s

Terminal:
~/Downloads$ mkdir test
~/Downloads$ cd test/
~/Downloads/test$ touch one
~/Downloads/test$ ls
one
~/Downloads/test$ touch two
~/Downloads/test$ ls
one  two
~/Downloads/test$ echo 1 > one
~/Downloads/test$ echo 2 > two
~/Downloads/test$ cat one
1
~/Downloads/test$ cd ..
~/Downloads$ zip -r test.zip test
adding: test/ (stored 0%)
adding: test/two (stored 0%)
adding: test/one (stored 0%)
~/Downloads$ du -hs test.zip
4,0K	test.zip
~/Downloads$ ls
test  test.zip
~/Downloads$ ls -lrt
total 8
drwxrwxr-x 2 jorge jorge 4096 nov 12 16:42 test
-rw-rw-r-- 1 jorge jorge  452 nov 12 16:43 test.zip
~/Downloads$ ls -lrtu
total 8
drwxrwxr-x 2 jorge jorge 4096 nov 12 16:42 test
-rw-rw-r-- 1 jorge jorge  452 nov 12 16:43 test.zip
~/Downloads$ du -hs
20K	.
~/Downloads$ ls -hslrt
total 8,0K
4,0K drwxrwxr-x 2 jorge jorge 4,0K nov 12 16:42 test
4,0K -rw-rw-r-- 1 jorge jorge  452 nov 12 16:43 test.zip
~/Downloads$ cd ..
~$ cd D
Desktop/   Documents/ Downloads/
~$ cd Desktop/
~/Desktop$ cd ..
~$ cp ~/Downloads/test.zip ~/Desktop/in/

Install graalvm:
https://www.graalvm.org/docs/getting-started/linux/

tar -xzf 

Intellij terminal:
mvn -DskipTests -Pnative clean package

setup jdk and maven

go to target directory and run the zip program with ./zip