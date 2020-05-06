export class Signupinfo {



  employeeId: string;
    empName: string;
    password: string;
    dateOfBirth:string
    role:number

    constructor( employeeId: string, empName: string, password: string,dateOfBirth:string) {

        this.employeeId = employeeId;
        this.empName = empName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role=1;
    }
}
