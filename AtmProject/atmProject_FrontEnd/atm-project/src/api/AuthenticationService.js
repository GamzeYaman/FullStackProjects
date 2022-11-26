import axios from 'axios';

class AuthenticationService{

    register(newCustomer){
        const url = "/auth/register"
        return axios.post(url, newCustomer);
    }
}

export default new AuthenticationService();