A Linked List Implementation for Demonstration Java based MOP
=============================================================

Building
--------

 - Use `./build` to fetch [rv-monitor](https://github.com/runtimeverification/rv-monitor)
    and [rosmop](https://github.com/runtimeverification/rosmop) and build dependencies.


Running
-------

 - `mvn generate-sources` generates an aspectJ and rvm files from `src/main/resources/ListReadsInorder.mop`

 - `mvn test` runs LinkedList tests after weaving source and generate aspect files using the
   aspectJ compiler. (Todo: This should ideally be handled via a mojo)

