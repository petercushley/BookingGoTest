# BookingGo Test Submission
## How to run
This is a gradle project and must first be compiled by using `gradle build` (will also run unit tests).

In order for gradle run to accept command line arguments with --agrs, you must have gradle 4.9 or later installed.
Also make sure that the arguments are surround by single quotes.
### Part 1
To request data from Dave's Taxis API:

`gradle run --args '{pickup} {dropoff}'`

Example: `gradle run --args '51.470020,-0.454295 51.00000,1.0000'`

To request data from all APIs and filter by price:

`gradle run --args '{pickup} {dropoff} {number of passengers}'`

Example: `gradle run --args '51.470020,-0.454295 51.00000,1.0000 6'`
### Part 2
To run the server
`gradle run --args 'server'`

Example Query: `http://localhost:8080/myapp/server/taxis/?pickup=51.470020%2C-0.454295&dropoff=51.00000%2C1.0000&numPassengers=6`
### Run Tests
`gradle test`

## About My Implementation
For this test I used Jersey (an implementation of JAX-RS) for REST functionality (both on the client and server) and 
Grizzly to run a HTTP server on localhost.

I initially started out by attempting to use Test Driven Development (TDD) to guide implementation but later found this 
to be infeasible as the supplier APIs would return something different every time you called them and would fail at
seemingly random times. I removed tests for the API calls as if these failed then it would interfere with gradle as 
tests must pass for builds to complete. I did, however, keep tests for the OutputFormatter which parses the responses 
and filters out unwanted results. 

I use Gson for parsing JSON returned from supplier APIs and serialising Java objects into JSON for return from my server
implementation. The classes SupplierResponse and Option exist so that Gson can deserialise JSON objects into a Java 
object that can be worked with.

OutputFormatter is only passed valid responses from the supplier APIs. If anything other than a 200 response code is 
received then the response is immediately discarded. It is especially important that response codes are checked before 
attempting to read the returned entity due to how timeouts are handled. When a timeout happens, an exception is thrown
and I handle this by returning a dummy response with the response code 408 from the getTaxisFromSupplier method. 
However, this is treated by Jersey as an Outbound response rather than an Inbound response and, as such, the readEntity
method is disabled on such responses meaning that an exception will be thrown if you attempt to read the entity from it.
Therefore it is very important that the response code is checked on all Responses from the supplier APIs.  
