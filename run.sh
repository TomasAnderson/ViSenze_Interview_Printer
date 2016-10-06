mkdir bin
javac -d ./bin PrinterRunner.java
for i in {0..9}; do
	java -cp ./bin PrinterRunner < ./printer_input/"$i".in >| ./my_ans/"$i".out
done

cmp ./my_ans/0.out ./printer_output/0.out
