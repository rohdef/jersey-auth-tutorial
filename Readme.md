# Jersey 2 authentication tutorial
This code is part of a the [Jersey 2 authentication tutorial](http://rohdef.dk/authentication-with-jersey-2/),  The 
code is designed, so it should be usable even without reading the tutoral, but please do read it before asking
questions. This code is run on this [live example](http://rohdef.dk:8080/jersey-auth/) compiled as is.

# Build tasks
The code is using an ant build script with ivy for dependency management. The current build tasks is:

* init - creates the directory structure (mostly dependency for the other tasks)
* resolve - downloads the dependencies to the lib folder if necessary (using ivy)
* compile - compiles the source codes
* build-war - combines the above 3 taks and builds a war file for deployment on an application server
* clean - deletes the directories (including lib)

Most likely you just want to run either `ant resolve` or `ant build-war`.

Setting up ant and ivy is beyond the scope of this guide, but if you're on a system using a package manger (such as
 Linux) you'll likely be able to install it from there and not need to worry.

# Version notice
The `@Consumes` and `@Produces` annotations may be needed for earlier versions of Jersey than 2.19, for which this code
was tested.

# License
The MIT License (MIT)

Copyright (c) 2015 Rohde Fischer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

