[![Build Status](https://img.shields.io/badge/license-GNU%203.0-brightgreen.svg)](https://github.com/skyme32/ValidateXML/blob/master/LICENSE)
[![Build Status](https://img.shields.io/badge/version-v0.2.1-orange.svg)]()
[![Build Status](https://img.shields.io/badge/platform-linux--64%20%7C%20win--32%20%7C%20win--64%20%7C%20osx--54-lightgrey.svg)]()
[![Build Status](https://img.shields.io/badge/status-alpha-blueviolet.svg)]()


# Validate XML with XSD
Validate the directory with xml from Schema. Search all direcotories for validate the XML to Schema XSD.


## New Features!
- Uncompress the .zips 
- find the name XML
- ADD new Arguments

## Help
### SYNOPSIS
- **Search inside the directory:**
```sh
$ java -jar validate.jar [input_DIR]
```
- **Search inside the directory (spacify the Schema):**
```sh
$ java -jar validate.jar [input DIR] [XSD_FILE]
```
- **Search with diferent options:**
```sh
$ java -jar validate.jar -d [input DIR] -x [XSD_FILE] -t [TEMPORAL_NAME] -l [XML_NAME]
```

### DESCRIPTION
List information about the Errors. Count all errors to the all files.
- Mandatory arguments to long options are mandatory for short options
       too.
```sh
-x, -xsd
    filename to schema XSD
-d, -dir
    path to input directory
-t, -temp
    name to search the input directory
-l, -xml
    name to search the XML's
-z, -unzip
    uncompress the file zip and search the XML and XSD
```

## TYPE LOGS
### SUCCESFULL
```sh
[2019-06-06 18:46:38.840] [INFO   ] XML: C:\..\0306006001001\example.xml 
[2019-06-06 18:46:38.841] [INFO   ] XSD: C:\...\example_1.0.xsd 
[2019-06-06 18:46:39.104] [CONFIG ] Succesfully Validated 
[2019-06-06 18:46:39.104] [INFO   ] ------------------------------------------- 
[2019-06-06 18:46:39.105] [INFO   ] Find 0 ERROR(S) to the XML's. 
```
### ERROR
```sh

```

## FAQS

## License
GNU GENERAL PUBLIC LICENSE
Version 3, 29 June 2007

Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.
