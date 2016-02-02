Introduction.
----
BinMsg Compiler is a code generator for protocols buffers. 
As of now it is possible to generate reading, writing and dumping code for Java, C++ and C. There are separate runtime libraries for each of the languages.

The protocols are defined in an XML file. Any decent XML editor can be used and code completion is supported in Eclipse's XML editor.

To see what all kind of complex structures can be handled by BinMsg Compiler, check these pdf document. All of the structures specified in these specifications has been encoded and corresponding code has been generated.

Title | Specification | Code
----- | ------------- | -------------
| [Cable Card CCIF Spec.](http://www.cablelabs.com/specifications/OC-SP-CCIF2.0-I25-120531.pdf) | [Link to definition file](https://raw.githubusercontent.com/krishnact/projects/master/ccif/CCIF2.0-I25.xml) |Link to [Java ](https://github.com/krishnact/projects/tree/master/ccif) , [CPP](https://github.com/krishnact/projects/tree/master/libccif) code|
| [SCTE65 Spec](https://www.scte.org/documents/pdf/Standards/ANSI_SCTE%2065%202008.pdf) | [Link to definition file](https://raw.githubusercontent.com/krishnact/projects/master/SCTE65/SI-1.xml) | Link to [Java code](https://github.com/krishnact/projects/tree/master/SCTE65) , [CPP](https://github.com/krishnact/projects/tree/master/libcdl) code |
| [CableLabs Common Download](http://www.cablelabs.com/wp-content/uploads/specdocs/OC-SP-CDL2.0-I11-100507.pdf) | [Link to definition file](https://raw.githubusercontent.com/krishnact/projects/master/cdl/CableLabsCommonDownload.xml) |Link to [Java code](https://github.com/krishnact/projects/tree/master/cdl) , [CPP](https://github.com/krishnact/projects/tree/master/libsi) code|

Quick Start
----
Please see this [tutorial ](https://github.com/krishnact/projects/tree/master/dnslib)about how to write your own parser.

