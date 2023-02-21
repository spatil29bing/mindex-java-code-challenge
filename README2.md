How to Use
The following endpoints are available to use:

COMPENSATION
* CREATE
    * HTTP Method: POST
    * URL: localhost:8080/employee/compensation
    * PAYLOAD: Compensation
    * RESPONSE: Compensation
* READ
    * HTTP Method: GET
    * URL: localhost:8080/employee/compensation/{id}
    * RESPONSE: Compensation

REPORTING STRUCTURE
* READ
    * HTTP Method: GET
    * URL: localhost:8080/employee/reportingStructure/{id}
      * RESPONSE: List<List<Employee>,ReportingStructure>>

* My Logic:
  * Compensation Logic
    * If the employee with given employeeId is not found in database then exception is thrown. 
    * Compensation REST API only add compensation for existing employees.
    * For adding Compensation for new Employee first create new Employee then add compensation for that employee.
  * Reporting Structure Logic
    * Output :
      * ResponseEntity<List<Object>> contains two output List<List<Employee><ReportingStructure>>
        * List<Employee> : Details about the employee with given employee id, and it's all directReports.
        * List<ReportingStructure>: Details about the employee with given employee id, numberOfReports.

* Exception Handling and Error Handling
  * Exceptions and Description
    *  APIException is for all Exceptions
    * CompensationNotFoundException occurs when compensation details are not found
    * DateInValidException occurs when Date is invalid for format MM/DD/YYYY eg: 23/01/2023 ,12/32/2023
    * EmployeeNotFoundException when employee is not found for given employeeId
    * SalaryNegativeException occurs when salary is negative
    * ResourceNotFoundException thrown when REst api calls doesnt existing table
    * APIError : Compile time errors.Occurs while parsing the JSON file
  
  * Exception Handlers
    * Spring Internal Errors Handler
      * RestExceptionHandler
    * User Defined Exception Handler
      * GlobalExceptionHandler

* Performed end to end REST API testing using POSTMAN
    
    

