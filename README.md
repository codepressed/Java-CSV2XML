# JAVA CSV to XML Converter
* It is a console application with var args
* Uses the traditional methodology to generate the xml (without a library), through
of nodes and tree structure.
* Can read ANY CSV File
* Args are:
  - [0] Input file 
  - [1] Output file 
  - [2] Element node name and optionally, 
  - [3] -s parameter, if you add it, program will read the csv
as semicolon-separated values instead of comma-separated values.

Example of command to compile and run the program for comma-separated values:
```
javac CSV2XML.java
java CSV2XML inputfilePath outputFilePath element -s
```

## Project test

### CSV Input File 

The fields are separated by commas in the original file

_(You can find it [here](examples/Grades.csv))_
```
Last name   First name	SSN             Test1   Test2   Test3   Test4   Final	Grades
Alfalfa	    Aloysius	123-45-6789	 40.0   90.0    100.0   83.0    49.0	D-
Alfred	    University	123-12-1234	 41.0   97.0    96.0    97.0    48.0	D+
Gerty	    Gramma      567-89-0123	 41.0   80.0    60.0    40.0    44.0	C
Android	    Electric	087-65-4321	 42.0   23.0    36.0    45.0    47.0	B-
Bumpkin	    Fred        456-78-9012	 43.0   78.0    88.0    77.0    45.0	A-
Rubble	    Betty       234-56-7890	 44.0   90.0    80.0    90.0    46.0	C-
Noshow	    Cecil       345-67-8901	 45.0   11.0    -1.0    4.0     43.0	F
Buff	    Bif	        632-79-9939	 46.0   20.0    30.0    40.0    50.0	B+
Airpump	    Andrew      223-45-6789	 49.0   1.0     90.0    100.0   83.0	A	
Backus	    Jim	        143-12-1234	 48.0   1.0     97.0    96.0    97.0	A+
Carnivore   Art	        565-89-0123	 44.0   1.0     80.0    60.0    40.0	D+
Dandy	    Jim	        087-75-4321	 47.0   1.0     23.0    36.0    45.0	C+
Elephant    Ima	        456-71-9012	 45.0   1.0     78.0    88.0    77.0	B-
Franklin    Benny       234-56-2890	 50.0   1.0     90.0    80.0    90.0	B-
George      Boy	        345-67-3901	 40.0   1.0     11.0    -1.0    4.0	B
Heffalump   Harvey      632-79-9439	 30.0   1.0     20.0    30.0    40.0	C

```

## XML Output file
_(You can find it [here](examples/Grades.xml))_
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<root>
    <elements>
        <element>
            <Lastname>Alfalfa</Lastname>
            <Firstname>Aloysius</Firstname>
            <SSN>123-45-6789</SSN>
            <Test1>40.0</Test1>
            <Test2>90.0</Test2>
            <Test3>100.0</Test3>
            <Test4>83.0</Test4>
            <Final>49.0</Final>
            <Grades>D-</Grades>
        </element>
        <element>
            <Lastname>Alfred</Lastname>
            <Firstname>University</Firstname>
            <SSN>123-12-1234</SSN>
            <Test1>41.0</Test1>
            <Test2>97.0</Test2>
            <Test3>96.0</Test3>
            <Test4>97.0</Test4>
            <Final>48.0</Final>
            <Grades>D+</Grades>
        </element>
        <element>
            <Lastname>Gerty</Lastname>
            <Firstname>Gramma</Firstname>
            <SSN>567-89-0123</SSN>
            <Test1>41.0</Test1>
            <Test2>80.0</Test2>
            <Test3>60.0</Test3>
            <Test4>40.0</Test4>
            <Final>44.0</Final>
            <Grades>C</Grades>
        </element>
        <element>
            <Lastname>Android</Lastname>
            <Firstname>Electric</Firstname>
            <SSN>087-65-4321</SSN>
            <Test1>42.0</Test1>
            <Test2>23.0</Test2>
            <Test3>36.0</Test3>
            <Test4>45.0</Test4>
            <Final>47.0</Final>
            <Grades>B-</Grades>
        </element>
        <element>
            <Lastname>Bumpkin</Lastname>
            <Firstname>Fred</Firstname>
            <SSN>456-78-9012</SSN>
            <Test1>43.0</Test1>
            <Test2>78.0</Test2>
            <Test3>88.0</Test3>
            <Test4>77.0</Test4>
            <Final>45.0</Final>
            <Grades>A-</Grades>
        </element>
        <element>
            <Lastname>Rubble</Lastname>
            <Firstname>Betty</Firstname>
            <SSN>234-56-7890</SSN>
            <Test1>44.0</Test1>
            <Test2>90.0</Test2>
            <Test3>80.0</Test3>
            <Test4>90.0</Test4>
            <Final>46.0</Final>
            <Grades>C-</Grades>
        </element>
        <element>
            <Lastname>Noshow</Lastname>
            <Firstname>Cecil</Firstname>
            <SSN>345-67-8901</SSN>
            <Test1>45.0</Test1>
            <Test2>11.0</Test2>
            <Test3>-1.0</Test3>
            <Test4>4.0</Test4>
            <Final>43.0</Final>
            <Grades>F</Grades>
        </element>
        <element>
            <Lastname>Buff</Lastname>
            <Firstname>Bif</Firstname>
            <SSN>632-79-9939</SSN>
            <Test1>46.0</Test1>
            <Test2>20.0</Test2>
            <Test3>30.0</Test3>
            <Test4>40.0</Test4>
            <Final>50.0</Final>
            <Grades>B+</Grades>
        </element>
        <element>
            <Lastname>Airpump</Lastname>
            <Firstname>Andrew</Firstname>
            <SSN>223-45-6789</SSN>
            <Test1>49.0      1.0</Test1>
            <Test2>90.0</Test2>
            <Test3>100.0</Test3>
            <Test4>83.0</Test4>
            <Final>A</Final>
        </element>
        <element>
            <Lastname>Backus</Lastname>
            <Firstname>Jim</Firstname>
            <SSN>143-12-1234</SSN>
            <Test1>48.0</Test1>
            <Test2>1.0</Test2>
            <Test3>97.0</Test3>
            <Test4>96.0</Test4>
            <Final>97.0</Final>
            <Grades>A+</Grades>
        </element>
        <element>
            <Lastname>Carnivore</Lastname>
            <Firstname>Art</Firstname>
            <SSN>565-89-0123</SSN>
            <Test1>44.0</Test1>
            <Test2>1.0</Test2>
            <Test3>80.0</Test3>
            <Test4>60.0</Test4>
            <Final>40.0</Final>
            <Grades>D+</Grades>
        </element>
        <element>
            <Lastname>Dandy</Lastname>
            <Firstname>Jim</Firstname>
            <SSN>087-75-4321</SSN>
            <Test1>47.0</Test1>
            <Test2>1.0</Test2>
            <Test3>23.0</Test3>
            <Test4>36.0</Test4>
            <Final>45.0</Final>
            <Grades>C+</Grades>
        </element>
        <element>
            <Lastname>Elephant</Lastname>
            <Firstname>Ima</Firstname>
            <SSN>456-71-9012</SSN>
            <Test1>45.0</Test1>
            <Test2>1.0</Test2>
            <Test3>78.0</Test3>
            <Test4>88.0</Test4>
            <Final>77.0</Final>
            <Grades>B-</Grades>
        </element>
        <element>
            <Lastname>Franklin</Lastname>
            <Firstname>Benny</Firstname>
            <SSN>234-56-2890</SSN>
            <Test1>50.0</Test1>
            <Test2>1.0</Test2>
            <Test3>90.0</Test3>
            <Test4>80.0</Test4>
            <Final>90.0</Final>
            <Grades>B-</Grades>
        </element>
        <element>
            <Lastname>George</Lastname>
            <Firstname>Boy</Firstname>
            <SSN>345-67-3901</SSN>
            <Test1>40.0</Test1>
            <Test2>1.0</Test2>
            <Test3>11.0</Test3>
            <Test4>-1.0</Test4>
            <Final>4.0</Final>
            <Grades>B</Grades>
        </element>
        <element>
            <Lastname>Heffalump</Lastname>
            <Firstname>Harvey</Firstname>
            <SSN>632-79-9439</SSN>
            <Test1>30.0</Test1>
            <Test2>1.0</Test2>
            <Test3>20.0</Test3>
            <Test4>30.0</Test4>
            <Final>40.0</Final>
            <Grades>C</Grades>
        </element>
    </elements>
</root>

```

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## [Donate](https://www.paypal.me/apesteguia)

Any amount is welcome ☕💕
[PayPal](https://www.paypal.me/apesteguia)