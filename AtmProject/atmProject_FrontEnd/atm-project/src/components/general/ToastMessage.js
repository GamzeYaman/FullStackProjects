import React from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

toast.configure()
var timeout = null;

function ToastMessage() {

   const notif = (message) =>{
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            toast[this.state.type](message, {
                autoClose: 2500,
                position: toast.POSITION.BOTTOM_CENTER,
            });
        }, 100);
    };
    
   return (
     <div>{notif(this.state.message)}</div>
   )
}

export default ToastMessage;