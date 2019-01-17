export class User {
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    token: string;

    constructor(firstName: string, lastName: string, email: string, username: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }
}
