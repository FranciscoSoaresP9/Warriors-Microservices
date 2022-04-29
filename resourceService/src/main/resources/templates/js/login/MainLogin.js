import {Login} from "./Login.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";
import {RecoveryPassword} from "./RecoveryPassword.js";


const login = new Login();
const recoveryPassword= new RecoveryPassword();

window.onload = () => {
    const accountLodgedChecking = new AccountLodgedChecker();
    accountLodgedChecking.isLodged('../page/mainpageloged');
}

function bootStrap() {
    recoveryPassword.setRequestSender(new RequestSender());
    login.setLoader(new Loader());
    login.setRequestSender(new RequestSender());
}

async function handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    const value = Object.fromEntries(data.entries());
    login.setData(value);

    await login.login();

}
$('#forgot_password').click(()=>{
   recoveryPassword.recovery(prompt("Insert your user Name"));
});
bootStrap();
const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);